package com.security.manage.controller.app;

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

import com.security.manage.common.AppReturnResult;
import com.security.manage.common.JsonResult;
import com.security.manage.controller.BaseController;
import com.security.manage.model.AssociateType;
import com.security.manage.model.Person;
import com.security.manage.model.PersonLevel;
import com.security.manage.model.PersonType;
import com.security.manage.model.User;
import com.security.manage.service.AssociateService;
import com.security.manage.service.PersonService;


@Scope("prototype")
@Controller
@RequestMapping("/instance/api")
public class AppController extends BaseController {

	@Resource(name = "associateService")
	private AssociateService associateService; 

	@Resource(name = "personService")
	private PersonService personService; 
	
	
	/**
	 * App获取数据接口---登录
	 */
	@ResponseBody
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public JsonResult <User> login(User user, 
			HttpServletRequest request, HttpServletResponse response){
		JsonResult <User>json  = new JsonResult<User>();
		json.setCode(new Integer(1));
		json.setMessage("登录失败!");
		try{ 
			User u = new User();
			u.setGuid("111111");
			u.setName("张三");
			u.setOrganId(1);
			u.setId(3);
			u.setOrganName("青羊区公安分局");
			u.setKeyWords("ssss");
			this.setLoginUser(u); 
			json.setCode(new Integer(0));  
			json.setMessage("登录成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 社会机构类型列表加载
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonLoadAssociateTypeList.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<AssociateType> AssociateTypeList(
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<AssociateType> js = new AppReturnResult<AssociateType>();
		js.setCode(201);
		js.setMessage("社会机构类型列表获取失败!");
	
		try {
			List<AssociateType> associateTypelist = new ArrayList<AssociateType>();
			AssociateType associateType = new AssociateType();
			associateTypelist = associateService.getAssociateTypeList(associateType);
			if(associateTypelist.size() != 0)
			{
				js.setList(associateTypelist);
				js.setCode(200);
				js.setMessage("社会机构类型列表获取成功!");		
			}
					
			} catch (Exception ex)
		{
			js.setCode(202);
			js.setMessage("社会机构类型列表获取内部方法异常!");	
			ex.printStackTrace();
		}
		return js;
	}
	
	/**
	 * 重点人员类型列表加载
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonLoadPersonTypeList.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<PersonType> PersonTypeList(
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<PersonType> js = new AppReturnResult<PersonType>();
		js.setCode(201);
		js.setMessage("重点人员类型列表获取失败!");
	
		try {
			List<PersonType> personTypelist = new ArrayList<PersonType>();
			PersonType personType = new PersonType();
			personTypelist = personService.getPersonTypeList(personType);
			if(personTypelist.size() != 0)
			{
				js.setList(personTypelist);
				js.setCode(200);
				js.setMessage("重点人员类型列表获取成功!");		
			}
					
			} catch (Exception ex)
		{
			js.setCode(202);
			js.setMessage("重点人员类型列表获取内部方法异常!");	
			ex.printStackTrace();
		}
		return js;
	}
	
	/**
	 * 重点人员级别列表加载
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonLoadPersonLevelList.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<PersonLevel> PersonLevelList(
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<PersonLevel> js = new AppReturnResult<PersonLevel>();
		js.setCode(201);
		js.setMessage("重点人员级别列表获取失败!");
	
		try {
			List<PersonLevel> personLevellist = new ArrayList<PersonLevel>();
			PersonLevel personLevel = new PersonLevel();
			personLevellist = personService.getPersonLevelList(personLevel);
			if(personLevellist.size() != 0)
			{
				js.setList(personLevellist);
				js.setCode(200);
				js.setMessage("重点人员级别列表获取成功!");	
			}			
			} catch (Exception ex) 
		{
				js.setCode(202);
				js.setMessage("重点人员级别列表获取内部方法异常!");	
			    ex.printStackTrace();
		}
		return js;
	}
	
	
	/**
	 * 重点人员列表加载
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonLoadPersonList.do",method=RequestMethod.GET, produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> PersonList(
			@RequestParam(value="guid", required = false)String guid,
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage("重点人员列表获取失败!");	
		Person person = new Person();
		if(guid != null && guid != "")
		{
			try {
				person.setCreatorname(new String(guid.getBytes("iso8859-1"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try {
			List<Person> personlist = new ArrayList<Person>();
			personlist = personService.getPersonList(person);
			if(personlist.size() != 0)
			{
				js.setList(personlist);
				js.setCode(200);
				js.setMessage("重点人员级别列表获取成功!");	
			}							
			} catch (Exception ex) 
		{
				js.setCode(202);
				js.setMessage("重点人员级别列表获取内部方法异常!");	
			    ex.printStackTrace();
		}
		return js;
	}
	

	/**
	 * 重点人员信息加载
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonLoadPersonById.do",method=RequestMethod.GET, produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> GetPersonById(
			@RequestParam(value="id", required = false)Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage("重点人员信息获取失败!");	
		try 
		{
			Person person = new Person();
			if(id != 0 && id != null)
			{
				person = personService.getPersonById(id);
				if(person.getName() != null)
				{
					js.setObj(person);
					js.setCode(200);
					js.setMessage("重点人员信息获取成功!");	
				}	
			}					
		} catch (Exception ex) 
		{
				js.setCode(202);
				js.setMessage("重点人员信息获取内部方法异常!");	
			    ex.printStackTrace();
		}
		return js;
	}
}
