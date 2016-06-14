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

import com.security.manage.common.JsonResult;
import com.security.manage.model.PersonType;
import com.security.manage.service.PersonService;
import com.security.manage.util.Constants;
 
@Scope("prototype")
@Controller
@RequestMapping("/person")
public class PersonController {
	@Resource(name = "personService")
	private PersonService personService;
	
	/**
	 * 重点人员类管理
	 * @param personType
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/personTypeList.do" ,method=RequestMethod.GET)
	public String personTypeList(
			PersonType personType,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
		//判断搜索栏是否为空，不为空则转为utf-8编码
		if(personType.getSearchName() != null && personType.getSearchName() != ""){
			String searchName = new String(personType.getSearchName().getBytes(
					"iso8859-1"), "utf-8");
			personType.setSearchName(searchName);
		}
		//设置页面初始值及页面尺寸
		//associate.setPageSize(Constants.DEFAULT_PAGE_SIZE); 
		//int pageNo = associate.getPageSize()*(associate.getPageNo()-1);
		//associate.setPageCount(pageNo);
		List<PersonType> personTypelist = new ArrayList<PersonType>();
		int countTotal = 0;
		try { 
			//Associate ax = associateService.getAssociateById(1);
			personTypelist = personService.getPersonTypeList(personType); 
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
			countTotal = personService.getPersonTypeTotalCount(personType);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//通过request绑定对象传到前台
		personType.setTotalCount(countTotal);
		personType.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		personType.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1);	
		if (personType.getPageNo() == null)
			personType.setPageNo(1);
		request.setAttribute("PersonType", personType);
		request.setAttribute("PersonTypelist",personTypelist);
		return "web/person/personTypeList";
	}	
	
	/**
	 * 重点人员类型查看编辑
	 * @param associateTypeId
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/personTypeInfo.do")
	public String personTypeInfo(
			@RequestParam(value="personTypeId", required = false)Integer personTypeId,
			HttpServletRequest request, HttpServletResponse response){ 
		PersonType  personType = new PersonType();
		if(personTypeId != null && personTypeId != 0){
			try{
				personType = personService.getPersonTypeById(personTypeId);
			}catch(Exception ex){
				ex.printStackTrace();
			}			
		}else{
			personType.setId(0);
		}
		request.setAttribute("PersonType", personType);
		return "web/person/personTypeInfo";
	}	
	
	
	/**
	 * 重点人员类型新建/编辑保存
	 * 
	 * @param associateType
	 * @param request
	 * @param response
	 * @return js
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdatePersonType.do", method = RequestMethod.POST)
	public JsonResult<PersonType> SaveOrUpdateTask(PersonType personType,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult<PersonType> js = new JsonResult<PersonType>();
		js.setCode(new Integer(1));
		js.setMessage("保存失败!");
		try {
			if (personType.getId() == null || personType.getId() == 0)
			{
				personType.setId(0);	
			}
			if (personType.getName() != null) {
				PersonType p = new PersonType();
				String name = personType.getName();
				p.setName(name);
				if (personType.getId() > 0) {
					p.setId(personType.getId());
				}				
				List<PersonType> lc = personService.getExistPersonType(p);
				if (lc.size() == 0) {
					personService.saveOrUpdatePersonType(personType); 
					js.setCode(new Integer(0));											
					js.setMessage("保存成功!");
				}else
				{
					js.setMessage("重点人员类型已存在!");;
				}									
			} else {
				js.setMessage("重点人员类型名称不能为空!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return js;
	}
	
}
