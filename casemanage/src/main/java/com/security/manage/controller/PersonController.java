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
import com.security.manage.model.Associate;
import com.security.manage.model.Person;
import com.security.manage.model.PersonLevel;
import com.security.manage.model.PersonType;
import com.security.manage.service.PersonService;
import com.security.manage.util.Constants;
 
@Scope("prototype")
@Controller
@RequestMapping("/person")
public class PersonController {
	@Resource(name = "personService")
	private PersonService personService;
	@RequestMapping(value = "/personList.do")
	public String personList(
			Person person,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if(person.getSearchName() != null && person.getSearchName() != ""){
			String searchName = new String(person.getSearchName().getBytes(
					"iso8859-1"), "utf-8");
			person.setSearchName(searchName);
		}
		List<Person> personlist = new ArrayList<Person>();
		int countTotal = 0;
		try { 
			personlist = personService.getPersonList(person);
			countTotal = personService.getTotal(person);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(person.getPageNo() == null){
			person.setPageNo(1);
		}
		person.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		person.setTotalCount(countTotal);
		person.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1);
		request.setAttribute("personList", personlist);
		request.setAttribute("Person", person);
		return "web/person/personList";
	}
	@RequestMapping(value = "/personInfo.do")
	public String personInfo(
			@RequestParam(value="personId", required = false)Integer personId,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Person p = new Person();
		if(personId != 0 && personId != null){
			p = personService.getPersonById(personId);
		}else{
			p.setId(personId);
		}
		List<PersonType> pt = new ArrayList<PersonType>();
		List<PersonLevel> pl = new ArrayList<PersonLevel>();
		pt = personService.getPersonType();
		pl = personService.getPersonLevel();
		request.setAttribute("person", p);
		request.setAttribute("personType", pt);
		request.setAttribute("personLevel", pl);
		return "web/person/personInfo";
	}
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdatePerson.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
	public JsonResult<Person> jsonSaveOrUpdatePerson(Person person,
			HttpServletRequest request, HttpServletResponse response){
		JsonResult<Person> js = new JsonResult<Person>();
		js.setCode(1);
		js.setMessage("保存失败!");
		try {
			if (person.getId() == null || person.getId() == 0) {
				person.setId(0);
			}
			if (person.getIdcard()!= null) {
				Person p = new Person();
				p.setIdcard(person.getIdcard());
				if (person.getId() > 0) {
					p.setId(person.getId());
				}
				List<Person> lc = personService.getExistPersonList(p);
				if (lc.size() == 0) {
					personService.saveOrUpdatePerson(person);
					js.setCode(new Integer(0));
					js.setMessage("保存成功!");
				} else {
					js.setMessage("身份证号已存在!");
				}
			} else {
				js.setMessage("身份证号都不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js;
	}
}
