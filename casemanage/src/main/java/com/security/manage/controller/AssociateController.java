package com.security.manage.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;  
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.security.manage.model.Associate;
import com.security.manage.model.AssociatePerson;
import com.security.manage.model.AssociatePlan;
import com.security.manage.model.AssociateType;
import com.security.manage.model.User;
import com.security.manage.service.AssociateService;  
import com.security.manage.util.Constants; 
import com.security.manage.util.DateUtil;
import com.security.manage.util.StringUtil;
import com.security.manage.common.JsonResult;


@Scope("prototype")
@Controller
@RequestMapping("/associate")
public class AssociateController extends BaseController{
	@Resource(name = "associateService")
	private AssociateService associateService; 
	
	/**
	 * 社会组织管理
	 * @param associate
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/associateList.do",method=RequestMethod.GET)
	public String associateList(
			Associate associate,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
		//判断搜索栏是否为空，不为空则转为utf-8编码
		if(associate.getSearchName() != null && associate.getSearchName() != ""){
			String searchName = new String(associate.getSearchName().getBytes(
					"iso8859-1"), "utf-8");
			associate.setSearchName(searchName);
		}
		//通过request绑定对象传到前台
		associate.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		if (associate.getPageNo() == null)
			associate.setPageNo(1);
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
		return "web/associate/associateList";
	}	
	
	/**
	 * 社会组织查看编辑
	 * @param associateId
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/associateInfo.do")
	public String associateInfo(
			AssociatePerson associatePerson,
			@RequestParam(value="associateId", required = false)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){ 
		Associate  associate = new Associate();
		List<AssociatePerson> la = new ArrayList<AssociatePerson>();
		List<AssociatePlan> plala = new ArrayList<AssociatePlan>();
		if(associatePerson.getPageNo() == null){
			associatePerson.setPageNo(1);
		}
		associatePerson.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		int total = 0;
		if(associateId != null && associateId != 0){
			associatePerson.setAssociateid(associateId);
			try{
				associate = associateService.getAssociateById(associateId);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date d = associate.getCreatetime();
				if(d != null){
					String s = sdf.format(d);
					associate.setCreatetimes(s);
				}
				la = associateService.getAssociateListById(associatePerson);
				total = associateService.getTotalCount(associatePerson);
				la = associateService.getAssociatePersonListById(associatePerson);
				plala = associateService.getAssociatePlanListById(associateId);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			associatePerson.setTotalCount(total);
			request.setAttribute("associatePerson", associatePerson);
			request.setAttribute("Associate", associate);
			request.setAttribute("associateList",la);
			request.setAttribute("associateplanList",plala);
		}else{
			associate.setId(associateId);
		}
		List<AssociateType> assoType = new ArrayList<AssociateType>();
		AssociateType associateType = new AssociateType();
		assoType = associateService.getAssociateTypeList(associateType);
		request.setAttribute("assoType", assoType);
		request.setAttribute("Associate", associate);
		String url = "";
		
		if(plala.size()>0){
			url = plala.get(0).getPlanurl();
		}
		request.setAttribute("AssociateUrl", url);
		return "web/associate/associateInfo";
	}	
	
	
	/**
	 * 社会组织类型管理
	 * @param associateType
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/associateTypeList.do",method=RequestMethod.GET)
	public String associateTypeList(
			AssociateType associateType,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
		//判断搜索栏是否为空，不为空则转为utf-8编码
		if(associateType.getSearchName() != null && associateType.getSearchName() != ""){
			String searchName = new String(associateType.getSearchName().getBytes(
					"iso8859-1"), "utf-8");
			associateType.setSearchName(searchName);
		}
		if(associateType.getPageNo() == null){
			associateType.setPageNo(1);
		}
		associateType.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		List<AssociateType> associateTypelist = new ArrayList<AssociateType>();
		int countTotal = 0;
		try { 
			associateTypelist = associateService.getAssociateTypeList(associateType); 
			countTotal = associateService.getAssociateTypeTotalCount(associateType);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//通过request绑定对象传到前台
		associateType.setTotalCount(countTotal);
		request.setAttribute("AssociateType", associateType);
		request.setAttribute("AssociateTypelist",associateTypelist);
		return "web/associate/associateTypeList";
	}	
	
	
	/**
	 * 社会组织类型查看编辑
	 * @param associateTypeId
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/associateTypeInfo.do")
	public String associateTypeInfo(
			@RequestParam(value="associateTypeId", required = false)Integer associateTypeId,
			HttpServletRequest request, HttpServletResponse response){ 
		AssociateType  associateType = new AssociateType();
		if(associateTypeId != null && associateTypeId != 0){
			try{
				associateType = associateService.getAssociateTypeById(associateTypeId);
			}catch(Exception ex){
				ex.printStackTrace();
			}			
		}else{
			associateType.setId(0);
		}
		request.setAttribute("AssociateType", associateType);
		return "web/associate/associateTypeInfo";
	}	
	
	
	/**
	 * 社会组织新建/编辑保存
	 * 
	 * @param associateType
	 * @param request
	 * @param response
	 * @return js
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdateAssociateType.do", method = RequestMethod.POST)
	public JsonResult<AssociateType> SaveOrUpdateAssociateType(AssociateType associateType,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult<AssociateType> js = new JsonResult<AssociateType>();
		js.setCode(new Integer(1));
		js.setMessage("保存失败!");
		try {
			if (associateType.getId() == null || associateType.getId() == 0)
			{
				associateType.setId(0);	
			}
			if (associateType.getKeyword() != null) {
				AssociateType p = new AssociateType();
				String key = associateType.getKeyword();
				p.setKeyword(key);
				if (associateType.getId() > 0) {
					p.setId(associateType.getId());
				}				
				List<AssociateType> lc = associateService.getExistAssociateType(p);
				if (lc.size() == 0) {
					associateService.saveOrUpdateAssociateType(associateType); 
					js.setCode(new Integer(0));											
					js.setMessage("保存成功!");
				}else
				{
					js.setMessage("社会组织类型已存在!");;
				}									
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return js;
	}
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdateAssociate.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
	public JsonResult<Associate> jsonSaveOrUpdateAssociate(Associate associate,
            @RequestParam(value = "file1", required = false) CommonsMultipartFile file1,
			HttpServletRequest request, HttpServletResponse response){
		JsonResult<Associate> js = new JsonResult<Associate>();
		js.setCode(1);
		js.setMessage("保存失败!");
		try {
			if(associate.getTypeid() == null){
				js.setMessage("机构类型不能为空!");
				return js;
			}
			if(associate.getId() == null ||associate.getId() == 0){
				User u = this.getLoginUser();
				associate.setCreator(u.getId());
				associate.setCreatorname(u.getName());
				associate.setOrganname(u.getOrganName()); 
				associate.setCreatetime(new Date());
				associate.setGuid(u.getGuid());
				/*String serialNo = getAssoSerialNo(associate.getTypeid()); 
				associate.setSerialno(serialNo); 
				associate.setId(0);*/
//				associate.setCreator(1);
//				associate.setCreatorname("张三");
//				associate.setOrganname("派出所"); 
				String serialNo = getAssoSerialNo(associate.getTypeid()); 
				associate.setSerialno(serialNo); 
				associate.setId(0);
			}
			if(associate.getTelephone() != null && !"".equals(associate.getTelephone())){
				String telephone = associate.getTelephone().trim();
				System.out.println(telephone);
				Boolean  b = StringUtil.isMobileNumber(telephone);
				if(!b){
					js.setMessage("手机格式不正确!");
					return js;
				}else{
					associate.setTelephone(telephone);
				}
			}
			if(associate.getName() != null && !"".equals(associate.getName())){
				Associate a = new Associate();
				a.setName(associate.getName());
				if(associate.getId() > 0){
					a.setId(associate.getId());
				}
				List<Associate> la = new ArrayList<Associate>();
				la = associateService.getExistAssociate(a);
				if(la.size() == 0){
					List<String> urlList = new ArrayList<String>();
					String path = request.getSession().getServletContext().getRealPath("uploadsource");
					File targetFile = new File(path);
					String filePath = "";
					String fileName = "";
					if(file1.getSize()>0){ 
						String tempName = file1.getOriginalFilename();  
						String fileType = tempName.split("\\.")[1];
						fileName = associate.getSerialno()+"."+fileType;
						
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						targetFile = new File(path+"/associateplan");
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						targetFile = new File(path+"/associateplan",fileName);  
						if(targetFile.exists()){
							targetFile.delete();
						}
						filePath ="uploadsource/associateplan/"+fileName;
						file1.transferTo(targetFile);
						urlList.add(filePath);
					}
					associate.setCreatetime(new Date());
					associateService.saveOrUpdateAssociate(associate);
					if(urlList.size()>0){
						associateService.saveOrUpdateAssociatePlan(urlList,associate.getId());
						js.setCode(0);
						js.setMessage("上传成功!");
					}
					js.setCode(0);
					js.setMessage("保存成功!");
					js.setObj(associate.getId());
				}else{
					js.setMessage("机构名已存在!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js;
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

	@RequestMapping(value = "/associateMember.do")
	public String associateMember(
			@RequestParam(value="associateId", required = false)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){
		Associate associate = new Associate();
		associate.setId(associateId);
		associate = associateService.getAssociateById(associateId);
		request.setAttribute("Associate",associate);
		return "web/associate/associateMember";
	}
	@ResponseBody
	@RequestMapping(value = "/jsonUpdateMember.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
	public JsonResult<AssociatePerson> jsonUpdateMember(AssociatePerson associatePerson,
            @RequestParam(value = "file", required = true) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		JsonResult<AssociatePerson> js = new JsonResult<AssociatePerson>();
		js.setCode(1);
		js.setMessage("保存失败!");
		try { 
			User u = this.getLoginUser();
			associatePerson.setCreator(u.getId());
			associatePerson.setCreatorname(u.getName());
			associatePerson.setOrganname(u.getOrganName()); 
			associatePerson.setGuid(u.getGuid());
			associatePerson.setId(0);
			if(associatePerson.getName() != null){
				AssociatePerson a = new AssociatePerson();
				a.setName(associatePerson.getName());
				if(associatePerson.getAssociateid() != null){
					a.setAssociateid(associatePerson.getAssociateid());
				}
				List<AssociatePerson> la = new ArrayList<AssociatePerson>();
				la = associateService.getExistAssociatePerson(a);
				if(la.size() == 0){
					 if(file.getSize()>0){
						String path = request.getSession().getServletContext().getRealPath("uploadsource");
						String fileName = file.getOriginalFilename();    //这里不用原文件名称 
						//String fileType = tempName.split("\\.")[1];
						//String fileName = associatePerson.getSerialno()+"."+fileType;
						File targetFile = new File(path);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						targetFile = new File(path+"/associateuserphoto");
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						targetFile = new File(path+"/associateuserphoto",fileName);  
						if(targetFile.exists()){
							targetFile.delete();
						}
						String filePath ="uploadsource/associateuserphoto/"+fileName;
						/*BufferedInputStream in = new BufferedInputStream(file.getInputStream());
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path+"/userphoto/"+fileName));
						int i;
						while((i=in.read())!=-1){
							out.write(i);
						}
						out.flush(); 
						out.close();
						in.close();*/
						//调用公安内部图像审核接口，是否符合要求
						//if(filePath 上传头像验证成功){
							file.transferTo(targetFile);	
							associatePerson.setPhotourl(filePath); 
						//}
						//else {

//							targetFile = new File(path+"/userphoto",fileName);  
//							if(targetFile.exists()){
//								targetFile.delete();
//							}
							//js.setMessage("上传头像，不符合公安部要求，请重新选择图片上传!"); 
						// }
					 }
					 if (associatePerson.getIdcard() != null && !"".equals(associatePerson.getIdcard())) {
						 	AssociatePerson p = new AssociatePerson();
							p.setIdcard(associatePerson.getIdcard());
							if (associatePerson.getAssociateid() > 0) {
								p.setId(associatePerson.getAssociateid());
							}
							List<AssociatePerson> lc = associateService.getExistAssociatePerson(p);
							if (lc.size() > 0) {
								js.setMessage("身份证号已存在!");
								return js;
							}
						} 
					 if(associatePerson.getBirth() != null && !"".equals(associatePerson.getBirth())){
						 String birth = associatePerson.getBirth();
						 String result = DateUtil.validate(birth);
						 if(!"".equals(result)){
							 js.setMessage("身份证号有误，请确认!");
							 return js;
						 }
					 }
					associateService.updateAssociatePerson(associatePerson);
					js.setCode(0);
					js.setMessage("保存成功!");
				}else{
					js.setMessage("人员名已存在!");
				}
			}else{
				js.setMessage("人员名不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js;
	}
		@ResponseBody
		@RequestMapping(value = "/jsonDeleteMember.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
		public JsonResult<AssociatePerson> jsonDeleteMember(
				@RequestParam(value="associateId", required = false)Integer associateId,
				@RequestParam(value="Id", required = false)Integer Id,
				HttpServletRequest request, HttpServletResponse response){
			JsonResult<AssociatePerson> js = new JsonResult<AssociatePerson>();
			js.setCode(1);
			js.setMessage("删除失败!");
			List<AssociatePerson> la = new ArrayList<AssociatePerson>();
			AssociatePerson ap = new AssociatePerson();
			ap.setId(Id);
			ap.setAssociateid(associateId);
			try {
				associateService.deleteMemberById(ap);
				la = associateService.getAssociatePersonListById(ap);
				if(la.size() > 0){
					js.setList(la);
				}
				js.setCode(0);
				js.setMessage("删除成功!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return js;
		}
		
		@ResponseBody
		@RequestMapping(value = "/jsonupdateplan.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
		public JsonResult<AssociatePlan> jsonupdateplan(AssociatePlan associatePlan,
				@RequestParam(value="associateid", required = false)Integer associateid, 
	            @RequestParam(value = "file1", required = false) CommonsMultipartFile file1,
	            @RequestParam(value = "file2", required = false) CommonsMultipartFile file2,
	            @RequestParam(value = "file3", required = false) CommonsMultipartFile file3,
	            @RequestParam(value = "file4", required = false) CommonsMultipartFile file4,
	            @RequestParam(value = "file5", required = false) CommonsMultipartFile file5,
				HttpServletRequest request, HttpServletResponse response){
			JsonResult<AssociatePlan> js = new JsonResult<AssociatePlan>();
			js.setCode(1);
			js.setMessage("上传失败!");
			List<String> urlList = new ArrayList<String>();
			String path = request.getSession().getServletContext().getRealPath("uploadsource");
			File targetFile = new File(path);
			String filePath = "";
			String fileName = "";
			try { 
				if(file1.getSize()>0){ 
					fileName = file1.getOriginalFilename();  
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan");
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan",fileName);  
					if(targetFile.exists()){
						targetFile.delete();
					}
					filePath ="uploadsource/associateplan/"+fileName;
					file1.transferTo(targetFile);
					urlList.add(filePath);
				}
				if(file2.getSize()>0){
					fileName = file2.getOriginalFilename();   
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan");
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan",fileName);  
					if(targetFile.exists()){
						targetFile.delete();
					}
					filePath ="uploadsource/associateplan/"+fileName;
					file2.transferTo(targetFile);
					urlList.add(filePath);
				}
				if(file3.getSize()>0){
					fileName = file3.getOriginalFilename();   
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan");
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan",fileName);  
					if(targetFile.exists()){
						targetFile.delete();
					}
					filePath ="uploadsource/associateplan/"+fileName;
					file3.transferTo(targetFile);
					urlList.add(filePath);
				}
				if(file4.getSize()>0){
					fileName = file4.getOriginalFilename();   
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan");
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan",fileName);  
					if(targetFile.exists()){
						targetFile.delete();
					}
					filePath ="uploadsource/associateplan/"+fileName;
					file4.transferTo(targetFile);
					urlList.add(filePath);
				}
				if(file5.getSize()>0){
					fileName = file5.getOriginalFilename();   
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan");
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					targetFile = new File(path+"/associateplan",fileName);  
					if(targetFile.exists()){
						targetFile.delete();
					}
					filePath ="uploadsource/associateplan/"+fileName;
					file5.transferTo(targetFile);
					urlList.add(filePath);
				}
				if(urlList.size()>0){
					associateService.saveOrUpdateAssociatePlan(urlList,associateid);
					js.setCode(0);
					js.setMessage("上传成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return js;
		}
		@ResponseBody
		@RequestMapping(value = "/jsonLoadAssociatePersonList.do", method = RequestMethod.POST)
		public JsonResult<AssociatePerson> jsonLoadAssociatePersonList(
				@RequestParam(value="associateId", required = false)Integer associateId,
				@RequestParam(value="pageNumber", required = false)Integer pageNumber,
				HttpServletRequest request, HttpServletResponse response) {
			JsonResult<AssociatePerson> js = new JsonResult<AssociatePerson>();
			AssociatePerson ap = new AssociatePerson();
			List<AssociatePerson> la = new ArrayList<AssociatePerson>();
			int total = 0;
			if(pageNumber != null){
				ap.setPageNo(pageNumber);
			}else{
				ap.setPageNo(1);
			}
			ap.setPageSize(Constants.DEFAULT_PAGE_SIZE);
			if(associateId != null){
				ap.setAssociateid(associateId);
			}
			js.setMessage("加载失败!");
			js.setCode(1);
			try {
				la = associateService.getAssociateListById(ap);
				total = associateService.getTotalCount(ap);
				ap.setTotalCount(total);
				js.setObj(ap);
				js.setCode(0);
				js.setList(la);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return js;
		}
		@ResponseBody
		@RequestMapping(value = "/jsonDeleteAssociate.do", method = RequestMethod.POST)
		public JsonResult<Associate> jsonDeleteAssociate(
				@RequestParam(value="associateId", required = false)Integer associateId,
				HttpServletRequest request, HttpServletResponse response) {
			JsonResult<Associate> js = new JsonResult<Associate>();
			js.setCode(1);
			js.setMessage("删除失败!");
			try {
				if(associateId != null){
					associateService.deleteAssociateById(associateId);
				}
				js.setCode(0);
				js.setMessage("删除成功!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return js;
		}
		@ResponseBody
		@RequestMapping(value = "/jsonDeleteAssociateType.do", method = RequestMethod.POST)
		public JsonResult<AssociateType> jsonDeleteAssociateType(
				@RequestParam(value="associateTypeId", required = false)Integer id,
				HttpServletRequest request, HttpServletResponse response) {
			JsonResult<AssociateType> js = new JsonResult<AssociateType>();
			js.setCode(1);
			js.setMessage("删除失败!");
			try {
				if(id != null){
					associateService.deleteAssociateTypeById(id);
				}
				js.setCode(0);
				js.setMessage("删除成功!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return js;
		}
}
