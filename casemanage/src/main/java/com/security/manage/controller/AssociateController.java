package com.security.manage.controller;

import java.io.UnsupportedEncodingException;  
import java.util.ArrayList; 
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

import com.security.manage.model.Associate;
import com.security.manage.model.AssociateType;
import com.security.manage.service.AssociateService;  
import com.security.manage.util.Constants; 
import com.security.manage.common.JsonResult;


@Scope("prototype")
@Controller
@RequestMapping("/associate")
public class AssociateController {
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
		//设置页面初始值及页面尺寸
		//associate.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		//int pageNo = associate.getPageSize()*(associate.getPageNo()-1);
		//associate.setPageCount(pageNo);
		List<Associate> associatelist = new ArrayList<Associate>();
		int countTotal = 0;
		try { 
			//Associate ax = associateService.getAssociateById(1);
			associatelist = associateService.getAssociateList(associate); 
//			for(Associate a :associatelist){
//				if(a.getTimespan()!= null){
//					String strDate = new String(a.getTimespan(),"UTF-8");
//					try{
//						 int year = Integer.parseInt(strDate.substring(0, 4));
//					     int month = Integer.parseInt(strDate.substring(4, 6));
//					     int day = Integer.parseInt(strDate.substring(6, 8));
//					     String createdate = year+"-"+month+"-"+day;
//					     a.setCreatedate(createdate);
//					}catch(Exception ex){
//					     a.setCreatedate("");
//					}
//				}
//			}
			countTotal = associateService.getTotalCount(associate);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//通过request绑定对象传到前台
		associate.setTotalCount(countTotal);
		associate.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		associate.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1); 
		if (associate.getPageNo() == null)
			associate.setPageNo(1);
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
	@RequestMapping(value = "/associateInfo.do",method=RequestMethod.POST)
	public String associateInfo(
			@RequestParam(value="associateId", required = false)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){ 
		Associate  associate = new Associate();
		if(associateId != null && associateId != 0){
			try{
				associate = associateService.getAssociateById(associateId);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			request.setAttribute("Associate", associate);
		}else{
			associate.setId(associateId);
		}
		List<AssociateType> assoType = new ArrayList<AssociateType>();
		AssociateType associateType = new AssociateType();
		assoType = associateService.getAssociateTypeList(associateType);
		request.setAttribute("assoType", assoType);
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
		//设置页面初始值及页面尺寸
		//associate.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		//int pageNo = associate.getPageSize()*(associate.getPageNo()-1);
		//associate.setPageCount(pageNo);
		List<AssociateType> associateTypelist = new ArrayList<AssociateType>();
		int countTotal = 0;
		try { 
			//Associate ax = associateService.getAssociateById(1);
			associateTypelist = associateService.getAssociateTypeList(associateType); 
//			for(Associate a :associatelist){
//				if(a.getTimespan()!= null){
//					String strDate = new String(a.getTimespan(),"UTF-8");
//					try{
//						 int year = Integer.parseInt(strDate.substring(0, 4));
//					     int month = Integer.parseInt(strDate.substring(4, 6));
//					     int day = Integer.parseInt(strDate.substring(6, 8));
//					     String createdate = year+"-"+month+"-"+day;
//					     a.setCreatedate(createdate);
//					}catch(Exception ex){
//					     a.setCreatedate("");
//					}
//				}
//			}
			countTotal = associateService.getAssociateTypeTotalCount(associateType);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//通过request绑定对象传到前台
		associateType.setTotalCount(countTotal);
		associateType.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		associateType.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1); 
		if (associateType.getPageNo() == null)
			associateType.setPageNo(1);
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
	public JsonResult<AssociateType> SaveOrUpdateTask(AssociateType associateType,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult<AssociateType> js = new JsonResult<AssociateType>();
		js.setCode(new Integer(1));
		js.setMessage("保存失败!");
		try {
			if (associateType.getId() == null || associateType.getId() == 0)
			{
				associateType.setId(0);	
			}
			if (associateType.getName() != null) {
				AssociateType p = new AssociateType();
				String name = associateType.getName();
				p.setName(name);
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
			} else {
				js.setMessage("社会组织类型名称不能为空!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return js;
	}
}
