package com.security.manage.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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

import com.security.manage.common.JsonResult; 
import com.security.manage.model.Associate;
import com.security.manage.model.AssociateType;
import com.security.manage.model.Person;
import com.security.manage.model.PersonCar;
import com.security.manage.model.PersonLevel; 
import com.security.manage.model.PersonType;
import com.security.manage.model.User;
import com.security.manage.service.PersonService;
import com.security.manage.util.Constants;
 
@Scope("prototype")
@Controller
@RequestMapping("/person")
public class PersonController extends BaseController{
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
		if (personType.getPageNo() == null)
			personType.setPageNo(1);
		personType.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		List<PersonType> personTypelist = new ArrayList<PersonType>();
		int countTotal = 0;
		try { 
		
			personTypelist = personService.getPersonTypeList(personType); 		
			countTotal = personService.getPersonTypeTotalCount(personType);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//通过request绑定对象传到前台
		personType.setTotalCount(countTotal);
		
		personType.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1);		
		request.setAttribute("PersonType", personType);
		request.setAttribute("PersonTypelist",personTypelist);
		return "web/person/personTypeList";
	}	
	
	/**
	 * 重点人员车辆管理
	 * @param personCar
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/personCarList.do" ,method=RequestMethod.GET)
	public String personCarList(
			PersonCar personCar,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
		//判断搜索栏是否为空，不为空则转为utf-8编码
		if(personCar.getSearchName() != null && personCar.getSearchName() != ""){
			String searchName = new String(personCar.getSearchName().getBytes(
					"iso8859-1"), "utf-8");
			personCar.setSearchName(searchName);
		}
		
		List<PersonCar> personCarlist = new ArrayList<PersonCar>();
		int countTotal = 0;
		try { 
		
			personCarlist = personService.getPersonCarList(personCar); 		
			countTotal = personService.getPersonCarTotalCount(personCar);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//通过request绑定对象传到前台
		personCar.setTotalCount(countTotal);
		personCar.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		personCar.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1);	
		if (personCar.getPageNo() == null)
			personCar.setPageNo(1);
		request.setAttribute("PersonCar", personCar);
		request.setAttribute("PersonCarlist",personCarlist);
		return "web/person/personCarList";
	}	
	
	/**
	 * 重点人员级别管理
	 * @param personLevel
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/PersonLevelList.do" ,method=RequestMethod.GET)
	public String personLevelList(
			PersonLevel personLevel,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
				//判断搜索栏是否为空，不为空则转为utf-8编码
			if(personLevel.getSearchName() != null && personLevel.getSearchName() != ""){
				String searchName = new String(personLevel.getSearchName().getBytes(
						"iso8859-1"), "utf-8");
				personLevel.setSearchName(searchName);
			}
			List<PersonLevel> personLevellist = new ArrayList<PersonLevel>();
			int countTotal = 0;
			try { 
				//Associate ax = associateService.getAssociateById(1);
				personLevellist = personService.getPersonLevelList(personLevel); 
//				for(Associate a :associatelist){
//					if(a.getTimespan()!= null){
//						String strDate = new String(a.getTimespan(),"UTF-8");
//						try{
//							 int year = Integer.parseInt(strDate.substring(0, 4));
//						     int month = Integer.parseInt(strDate.substring(4, 6));
//						     int day = Integer.parseInt(strDate.substring(6, 8));
//						     String createdate = year+"-"+month+"-"+day;
//						     a.setCreatedate(createdate);
//						}catch(Exception ex){
//						     a.setCreatedate("");
//						}
//					}
//				}
				countTotal = personService.getPersonLevelTotalCount(personLevel);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			//通过request绑定对象传到前台
			personLevel.setTotalCount(countTotal);
			personLevel.setPageSize(Constants.DEFAULT_PAGE_SIZE);
			personLevel.setPageCount(countTotal/Constants.DEFAULT_PAGE_SIZE+1);
			if (personLevel.getPageNo() == null)
				personLevel.setPageNo(1);
			request.setAttribute("PersonLevel", personLevel);
			request.setAttribute("PersonLevellist",personLevellist);
			return "web/person/PersonLevelList";
	}
	
	/**
	 * 重点人员类型查看编辑
	 * @param personTypeId
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
		return "web/person/PersonTypeInfo";
	}	
	
	
	/**
	 * 重点人员车辆查看编辑
	 * @param personCarId
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/personCarInfo.do")
	public String personCarInfo(
			@RequestParam(value="personCarId", required = false)Integer personCarId,
			HttpServletRequest request, HttpServletResponse response){ 
		PersonCar  personCar = new PersonCar();
		if(personCarId != null && personCarId != 0){
			try{
				personCar = personService.getPersonCarById(personCarId);
			}catch(Exception ex){
				ex.printStackTrace();
			}			
		}else{
			personCar.setId(0);
		}
		request.setAttribute("PersonCar", personCar);
		return "web/person/personCarInfo";
	}	
	
	/**
	 * 重点人员级别查看编辑
	 * @param personLevelId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/PersonLevelInfo.do")
	public String personLevelInfo(
			@RequestParam(value="personLevelId", required = false)Integer personLevelId,
			HttpServletRequest request, HttpServletResponse response){
		PersonLevel personLevel = new PersonLevel();
		if(personLevelId != null && personLevelId != 0){
			try
			{
				personLevel = personService.getPersonLevelById(personLevelId);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			personLevel.setId(0);
		}
		request.setAttribute("PersonLevel", personLevel);
		return "web/person/PersonLevelInfo";
	}
	
	
	/**
	 * 重点人员类型新建/编辑保存
	 * 
	 * @param personType
	 * @param request
	 * @param response
	 * @return js
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdatePersonType.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
	public JsonResult<PersonType> SaveOrUpdatePersonType(PersonType personType,
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
					js.setMessage("重点人员类型已存在!");
				}									
			} else {
				js.setMessage("重点人员类型名称不能为空!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return js;
	}
	
	/**
	 * 重点人员车辆新建/编辑保存
	 * 
	 * @param personCar
	 * @param request
	 * @param response
	 * @return js
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdatePersonCar.do", method = RequestMethod.POST, produces = { "text/html;charset=UTF-8" })
	public JsonResult<PersonCar> SaveOrUpdatePersonCar(PersonCar personCar,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult<PersonCar> js = new JsonResult<PersonCar>();
		js.setCode(new Integer(1));
		js.setMessage("保存失败!");
		try {
			if (personCar.getId() == null || personCar.getId() == 0)
			{
				personCar.setId(0);	
			}
			if (personCar.getName() != null) {
				PersonCar p = new PersonCar();
				String name = personCar.getName();
				p.setName(name);
				if (personCar.getId() > 0) {
					p.setId(personCar.getId());
				}				
				List<PersonCar> lc = personService.getExistPersonCar(p);
				if (lc.size() == 0) {
					personService.saveOrUpdatePersonCar(personCar); 
					js.setCode(new Integer(0));											
					js.setMessage("保存成功!");
				}else
				{
					js.setMessage("该重点人员车辆已存在!");
				}									
			} else {
				js.setMessage("重点人员姓名不能为空!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return js;
	}
	 
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdatePersonLevel.do", method = RequestMethod.POST)
	public JsonResult<PersonLevel> SaveOrUpdatePersonLevel(PersonLevel personLevel,
			HttpServletRequest request, HttpServletResponse response){
		JsonResult<PersonLevel> js = new JsonResult<PersonLevel>();
		js.setCode(new Integer(1));
		js.setMessage("保存失败!");
		try
		{
			if (personLevel.getId() == null || personLevel.getId() == 0)
			{
				personLevel.setId(0);	
			}
			if (personLevel.getName() != null){
				PersonLevel p = new PersonLevel();
				String name = personLevel.getName();
				p.setName(name);
				if (personLevel.getId() > 0) {
					p.setId(personLevel.getId());
				}
				List<PersonLevel> lc = personService.getExistPersonLevel(p);
				if (lc.size() == 0) {
					personService.saveOrUpdatePersonLevel(personLevel); 
					js.setCode(new Integer(0));											
					js.setMessage("保存成功!");
				}
				else
				{
					js.setMessage("重点人员类型已存在!");
				}
			}
			else
			{
				js.setMessage("重点人员类型名称不能为空!");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return js;
	}
	
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
			for(Person p:personlist){
				Date d = p.getCreatetime();
				if(d != null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
					String s = sdf.format(d);
					p.setCreatetimes(s);
				}
			}
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
			Date d = p.getCreatetime();
			if(d != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String s = sdf.format(d);
				p.setCreatetimes(s);
			}
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
            @RequestParam(value = "file", required = true) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		JsonResult<Person> js = new JsonResult<Person>();
		js.setCode(1);
		js.setMessage("保存失败!");
		try {
			if (person.getId() == null || person.getId() == 0) { 
				User u = this.getLoginUser();
				person.setCreator(u.getId());
				person.setCreatorname(u.getName());
				person.setOrganname(u.getOrganName());   
				person.setCreatetime(new Date());
				String serialNo = getPersonSerialNo(person.getTypeid()); 
				person.setSerialno(serialNo); 
				person.setCreatetime(new Date());
				person.setId(0);
			} 
			if(person.getLevelid() == null){
				js.setMessage("请选择人员级别!");
				return js;
			}
			if(person.getTypeid() == null){
				js.setMessage("请选择人员类型!");
				return js;
			}
			if (person.getIdcard() != null && !"".equals(person.getIdcard())) {
				Person p = new Person();
				p.setIdcard(person.getIdcard());
				if (person.getId() > 0) {
					p.setId(person.getId());
				}
				List<Person> lc = personService.getExistPersonList(p);
				if (lc.size() > 0) {
					js.setMessage("身份证号已存在!");
					return js;
				}
			}  
			 if(file.getSize()>0){
				String path = request.getSession().getServletContext().getRealPath("uploadsource");
				String tempName = file.getOriginalFilename();    //这里不用原文件名称 
				String fileType = tempName.split("\\.")[1];
				String fileName = person.getSerialno()+"."+fileType;
				File targetFile = new File(path);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				targetFile = new File(path+"/userphoto");
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				targetFile = new File(path+"/userphoto",fileName);  
				if(targetFile.exists()){
					targetFile.delete();
				}
				String filePath ="uploadsource/userphoto/"+fileName;
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
				person.setPhotourl(filePath); 
				//}
				//else {

//					targetFile = new File(path+"/userphoto",fileName);  
//					if(targetFile.exists()){
//						targetFile.delete();
//					}
					//js.setMessage("上传头像，不符合公安部要求，请重新选择图片上传!"); 
				// }
			 }else{
				js.setMessage("请选择头像文件进行上传！");
				return js;
			 }
			personService.saveOrUpdatePerson(person);
						 
			js.setCode(new Integer(0));
			js.setMessage("保存成功!"); 
			js.setObj(person.getId());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js;
	}

	private String getPersonSerialNo(Integer typeid) {
		// TODO Auto-generated method stub
		String serialNo = "";
		PersonType at = new PersonType();
		at = personService.getPersonTypeById(typeid);
		serialNo = at.getKeyword() +"000001";
		List<Person> personList = new ArrayList<Person>();
		Person person = new Person();
		person.setSearchName(at.getKeyword());
		personList = personService.getPersonList(person); 
		int temp =0;
		if(personList.size() >0){
			for(Person a : personList){
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




