package com.security.manage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.security.manage.model.Associate;
import com.security.manage.model.AssociateType;
import com.security.manage.model.User;
import com.security.manage.service.AssociateService;
import com.security.manage.util.Constants;

@Scope("prototype")
@Controller
@RequestMapping("/fileUpload")
public class UploadFileController extends BaseController {
	
	@Resource(name = "associateService")
	private AssociateService associateService;
	
	@ResponseBody
	@RequestMapping(value = "/downfile.do")
	public void downFile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "filepath", required = true) String filepath) {
		try {
//			String moduleName = new String(filepath.getBytes("iso8859-1"),
//					"utf-8");
//			String filePath = request.getRealPath("/") + moduleName;
			String filePath = request.getRealPath("/") + filepath;
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
			fileName = new String(fileName.getBytes("ISO-8859-1"), "utf-8");
			/*response.reset();
			response.setContentType("APPLICATION/OCTET-STREAM; charset=UTF-8");
			response.setHeader("Content-disposition", "attachment;filename="
					+ fileName + ".xls");*/
			response.reset();
			final String userAgent = request.getHeader("USER-AGENT");
			if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器  
				fileName = URLEncoder.encode(fileName,"UTF8");  
			}else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器  
				fileName = new String(fileName.getBytes(), "ISO8859-1");  
			}else{  
				fileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器  
			}  
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");//这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开  
			response.setContentType("application/vnd.ms-excel"); 
			filePath = new String(filePath.getBytes("ISO-8859-1"), "utf-8");
			FileInputStream inStream = new FileInputStream(filePath);
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
				// this.getRes().getOutputStream().write(b,0,len);
			}
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 导入社会机构导入成功后，跳转到导入结果临时页面
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadAssociateExcel.do")
	public String uploadAssociateExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		// 导入总记录数
		List<Associate> defaultlist = new ArrayList<Associate>();
		// 导入成功记录数
		List<Associate> list = new ArrayList<Associate>();
		// 导入失败记录
		List<Associate> nulllist = new ArrayList<Associate>();
		// 导入总数
		int totalCount = 0;
		try {
			// 项目在容器中的实际发布运行的upload路径
			String path = request.getSession().getServletContext()
					.getRealPath("upload");
			// 获取文件名
			String fileName = file.getOriginalFilename();
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				File f = new File(targetFile.getPath());
				if (f.exists()) {
					f.delete();
				}
				// 把MultipartFile转换成File类型,MultipartFile自带的transferTo
				file.transferTo(targetFile);
				InputStream stream = new FileInputStream(targetFile.getPath());
				Workbook wb = WorkbookFactory.create(stream);
				// HSSFWorkbook是解析出来excel 2007 以前版本的，后缀名为xls的;
				// XSSFWorkbook是解析excel 2007 版的，后缀名为xlsx。
				try {
					stream = new FileInputStream(targetFile.getPath());
					wb = new XSSFWorkbook(stream);
					defaultlist = getXSSFResult(wb);
				} catch (Exception ex) {
					stream = new FileInputStream(targetFile.getPath());
					wb = new HSSFWorkbook(stream);
					defaultlist = getHSSFResult(wb);
				}
				for (Associate associate : defaultlist) {
					// 把刚获取的列存入list,判断获取的对象是否按照规则
					if (associate.getName() == null
						|| "".equals(associate.getName())
						|| associate.getTypeid() == null
						|| "".equals(associate.getTypeid())
					){
						nulllist.add(associate);
					} else {
						list.add(associate);
					}
				}
				// stream.close();
				IOUtils.closeQuietly(stream);
				//totalCount = list.size() + nulllist.size();
				if (list.size() > 0) {
					insertListToDatabase(list);
					/*File files = new File(targetFile.getPath());
					if (files.exists()) {
						files.delete();
					}*/
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Associate associate = new Associate();
		associate.setPageNo(1);
		associate.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		List<Associate> associatelist = new ArrayList<Associate>();
		int countTotal = 0;
		try { 
			associatelist = associateService.getAssociateList(associate);  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(Associate a:associatelist){
				Date d = a.getCreatetime();
				if(d != null){
					String s = sdf.format(d);
					a.setCreatetimes(s);
				}
			}
			countTotal = associateService.getTotalCount(associate);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		associate.setTotalCount(countTotal);
		request.setAttribute("associate", associate);
		request.setAttribute("associatelist",associatelist);
		/*request.setAttribute("pointlist", nulllist);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("rightCount", rightCount);
		request.setAttribute("subCount", subCount);*/

		return "web/associate/associateList";
	}
	/**
	 * 插入机构到数据库
	 * 
	 * @param list
	 * @return
	 */
	private void insertListToDatabase(List<Associate> list) {
		try {
			for (Associate associate : list) {
				associate.setId(0);
				try {
					associateService.saveOrUpdateAssociate(associate);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// 解析excel文件
		private List<Associate> getXSSFResult(Workbook wb) {
			// TODO Auto-generated method stub
			List<Associate> result = new ArrayList<Associate>();
			List<AssociateType> associateTypeList = new ArrayList<AssociateType>();
			associateTypeList = associateService.getAssociateTypeAllList();
			for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
				XSSFSheet st = (XSSFSheet) wb.getSheetAt(sheetIndex);
				// 第一行为标题，不取
				for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
					XSSFRow row = st.getRow(rowIndex);
					if (row == null) {
						continue;
					}
					Associate associate = new Associate();
					User u = this.getLoginUser();
					associate.setCreator(u.getId());
					associate.setCreatorname(u.getName());
					associate.setOrganname(u.getOrganName()); 
					associate.setCreatetime(new Date());
					associate.setGuid(u.getGuid());
					XSSFCell cell0 = row.getCell(0);
					if (cell0 != null || "".equals(cell0)) {
						associate.setName(cell0.getStringCellValue());
					}
					XSSFCell cell1 = row.getCell(1);
					if (cell1 != null || "".equals(cell1)) {
						for(AssociateType a : associateTypeList){
							String name = a.getName();
							if(name.equals(cell1.getStringCellValue())){
								associate.setTypeid(a.getId());
								String serialNo = getAssoSerialNo(associate.getTypeid()); 
								associate.setSerialno(serialNo);
							}
						}
					}
					XSSFCell cell2 = row.getCell(2);
					if (cell2 != null || "".equals(cell2)) {
						associate.setAddress(cell2.getStringCellValue());
					}
					XSSFCell cell3 = row.getCell(3);
					if (cell3 != null || "".equals(cell3)) {
						associate.setTelephone(cell3.getStringCellValue());
					}
					XSSFCell cell4 = row.getCell(4);
					if (cell4 != null || "".equals(cell4)) {
						associate.setLatitude(cell4.getStringCellValue());
					}
					XSSFCell cell5 = row.getCell(5);
					if (cell5 != null || "".equals(cell5)) {
						associate.setLongitude(cell5.getStringCellValue());
					}
					XSSFCell cell6 = row.getCell(6);
					if (cell6 != null || "".equals(cell6)) {
						associate.setDescription(cell6.getStringCellValue());
					}
					result.add(associate);
				}
			}
			return result;
		}

		private List<Associate> getHSSFResult(Workbook wb) {
			// TODO Auto-generated method stub
			List<Associate> result = new ArrayList<Associate>();
			List<AssociateType> associateTypeList = new ArrayList<AssociateType>();
			associateTypeList = associateService.getAssociateTypeAllList();
			for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
				HSSFSheet st = (HSSFSheet) wb.getSheetAt(sheetIndex);
				// 第一行为标题，不取
				for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
					HSSFRow row = st.getRow(rowIndex);
					if (row == null) {
						continue;
					}
					Associate associate = new Associate();
					User u = this.getLoginUser();
					associate.setCreator(u.getId());
					associate.setCreatorname(u.getName());
					associate.setOrganname(u.getOrganName()); 
					associate.setCreatetime(new Date());
					associate.setGuid(u.getGuid());
					HSSFCell cell0 = row.getCell(0);
					if (cell0 != null || "".equals(cell0)) {
						associate.setName(cell0.getStringCellValue());
					}
					HSSFCell cell1 = row.getCell(1);
					if (cell1 != null || "".equals(cell1)) {
						for(AssociateType a : associateTypeList){
							String name = a.getName();
							if(name.equals(cell1.getStringCellValue())){
								associate.setTypeid(a.getId());
								String serialNo = getAssoSerialNo(associate.getTypeid()); 
								associate.setSerialno(serialNo);
							}
						}
					}
					HSSFCell cell2 = row.getCell(2);
					if (cell2 != null || "".equals(cell2)) {
						associate.setAddress(cell2.getStringCellValue());
					}
					HSSFCell cell3 = row.getCell(3);
					if (cell3 != null || "".equals(cell3)) {
						associate.setTelephone(cell3.getStringCellValue());
					}
					HSSFCell cell4 = row.getCell(4);
					if (cell4 != null || "".equals(cell4)) {
						associate.setLatitude(cell4.getStringCellValue());
					}
					HSSFCell cell5 = row.getCell(5);
					if (cell5 != null || "".equals(cell5)) {
						associate.setLongitude(cell5.getStringCellValue());
					}
					HSSFCell cell6 = row.getCell(6);
					if (cell6 != null || "".equals(cell6)) {
						associate.setDescription(cell6.getStringCellValue());
					}
					result.add(associate);
				}
			}
			return result;
		}
		private String getAssoSerialNo(Integer typeid) {
			// TODO Auto-generated method stub
			String serialNo = "";
			AssociateType at = new AssociateType();
			at = associateService.getAssociateTypeById(typeid);
			serialNo = at.getKeyword() +"000001";
			List<Associate> assoList = new ArrayList<Associate>();
			Associate associate = new Associate();
			associate.setSearchName(at.getKeyword());
			assoList = associateService.getAssociateList(associate); 
			int temp =0;
			if(assoList.size() >0){
				for(Associate a : assoList){
					String sNo = a.getSerialno();
					sNo = sNo.replaceAll(at.getKeyword(), "");
					sNo = sNo.replaceAll("^(0+)", "");
					int maxSerialNo = Integer.parseInt(sNo);
					if(maxSerialNo > temp){
						temp = maxSerialNo;
					}
				}
				int maxNo  = temp +1;
				String maxStr = maxNo+"";
				if(maxStr.length() == 1){
					serialNo = at.getKeyword() + "00000" + maxStr;
				}else if(maxStr.length() == 2){
					serialNo = at.getKeyword() + "0000" + maxStr;
				}else if(maxStr.length() == 3){
					serialNo = at.getKeyword() + "000" + maxStr;
				}else if(maxStr.length() == 4){
					serialNo = at.getKeyword() + "00" + maxStr;
				}else if(maxStr.length() == 5){
					serialNo = at.getKeyword() + "0" + maxStr;
				}else if(maxStr.length() == 6){
					serialNo = at.getKeyword() + maxStr;
				}
			} 
			return serialNo;
		}
}
