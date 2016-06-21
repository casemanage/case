package com.security.manage.controller.app;

 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.File;
import java.io.UnsupportedEncodingException;
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

import com.security.manage.common.AppReturnResult;
import com.security.manage.common.UserLogin;
import com.security.manage.controller.BaseController; 
import com.security.manage.model.AssociatePlan;
import com.security.manage.model.AssociateType;
import com.security.manage.model.LoginResult;
import com.security.manage.model.Person;
import com.security.manage.model.PersonLevel; 
import com.security.manage.model.Associate;
import com.security.manage.model.AssociatePerson;
import com.security.manage.model.PersonType;
import com.security.manage.model.PlanPicture;
import com.security.manage.model.Police;
import com.security.manage.model.PoliceMan;
import com.security.manage.model.User;
import com.security.manage.service.AssociateService;
import com.security.manage.service.PersonService;
import com.security.manage.util.Constants;
import com.security.manage.util.StringUtil;


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
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public AppReturnResult<PoliceMan> login(
			@RequestParam(value="account", required = true)String account,
			@RequestParam(value="password", required = true)String password,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<PoliceMan> json = new AppReturnResult<PoliceMan>();
		json.setCode(201);
		json.setMessage(Constants.LOGIN_FAIL);
		User user = new User();
		try{
			if(StringUtil.isEmpty(account)){ 
				json.setMessage("请输入用户名");
				return json;
			}
			if(StringUtil.isEmpty(password)){ 
				json.setMessage("请输入密码");
				return json;
			}
			user.setAccount(account);
			user.setPassword(password);
			if(user.getAccount().equals("admin")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin1")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin2")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin3")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin4")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin5")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin6")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin7")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin8")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin9")&&user.getPassword().equals("111111")){ 
				 
			}else if(user.getAccount().equals("admin10")&&user.getPassword().equals("111111")){ 
				 
			}else{
				json.setMessage("用户名或密码错误");
				return json;
			} 
			user.setGuid("111111");
			user.setName("张三");
			user.setDeptGuid("111111");
			user.setId(1);
			user.setKeyWords("111111");
			user.setOrganName("111111");
			//json.setObj(loginResult.getPoliceman());
			json.setCode(200); 
			json.setMessage("登录成功");
//			LoginResult loginResult = UserLogin.login(account,password);
//			if(loginResult.getStatus().equals("200")){
//				user.setGuid(loginResult.getPoliceman().getGuid());
//				user.setName(loginResult.getPoliceman().getPc_name());
//				user.setDeptGuid(loginResult.getPoliceman().getDept_guid());
//				user.setId(loginResult.getPoliceman().getId());
//				user.setKeyWords(loginResult.getPoliceman().getGuid());
//				json.setObj(loginResult.getPoliceman());
//				json.setCode(0);
//				json.setMessage(loginResult.getMsg());
//			}else{
//				json.setMessage(loginResult.getMsg());
//			} 
		}catch(Exception e){
			e.printStackTrace();
			json.setMessage("登录接口调用异常，详细："+e.getMessage());
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
	@RequestMapping(value = "/getAssoType.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<AssociateType> AssociateTypeList(
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<AssociateType> js = new AppReturnResult<AssociateType>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
	
		try {
			List<AssociateType> associateTypelist = new ArrayList<AssociateType>();
			AssociateType associateType = new AssociateType();
			associateTypelist = associateService.getAssociateTypeList(associateType);
			if(associateTypelist.size() != 0)
			{
				js.setList(associateTypelist);
				js.setCode(200);
				js.setMessage(Constants.LOAD_OK_MESSAGE);		
			}
					
			} catch (Exception ex)
		{
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);	
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
	@RequestMapping(value = "/getPersonType.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<PersonType> getPersonType(
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<PersonType> js = new AppReturnResult<PersonType>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
	
		try {
			List<PersonType> personTypelist = new ArrayList<PersonType>();
			PersonType personType = new PersonType();
			personTypelist = personService.getPersonTypeList(personType);
			 
				js.setList(personTypelist);
				js.setCode(200);
				js.setMessage(Constants.LOAD_OK_MESSAGE);	 
					
			} catch (Exception ex)
		{
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);	
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
	@RequestMapping(value = "/getPersonLevel.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<PersonLevel> getPersonLevel(
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<PersonLevel> js = new AppReturnResult<PersonLevel>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
	
		try {
			List<PersonLevel> personLevellist = new ArrayList<PersonLevel>();
			PersonLevel personLevel = new PersonLevel();
			personLevellist = personService.getPersonLevelList(personLevel); 
				js.setList(personLevellist);
				js.setCode(200);
				js.setMessage(Constants.LOAD_OK_MESSAGE);	 
			} catch (Exception ex) 
		{
				js.setCode(202);
				js.setMessage(Constants.INNER_ERROR_MESSAGE);	
			    ex.printStackTrace();
		}
		return js;
	}
	
	
	/**
	 * 重点人员列表加载
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/getPersonList.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> getPersonList(
			@RequestParam(value="guid", required = true)String guid,
			@RequestParam(value="page", required = false)Integer page,
			@RequestParam(value="search", required = false)String searchName,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);	
		Person person = new Person();
		if(page != null && page > 0){
			person.setPageNo(page);
		}
		person.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		if(searchName != null && !"".equals(searchName)){
			String s = new String(searchName.getBytes("iso8859-1"), "utf-8");
			person.setSearchName(s);
		}
		if(!StringUtil.isEmpty(guid))
		{ 
			person.setCreatorname(guid); 
		}else{
			js.setCode(203);
			js.setMessage(Constants.PARAM_ERROR_MESSAGE);	
			return js;
		}
		try {
			List<Person> personlist = new ArrayList<Person>();
			int count = 0;
			personlist = personService.getPersonList(person);
			count = personService.getTotal(person);
			js.setTotalCount(count);
			if(count%Constants.DEFAULT_PAGE_SIZE == 0){
				js.setPageCount(count/Constants.DEFAULT_PAGE_SIZE);
			}else{
				js.setPageCount(count/Constants.DEFAULT_PAGE_SIZE + 1);
			}
			for(Person p : personlist){
				Police police  = new Police();
				police.setPolicename(p.getPolicename());
				police.setPolicephone(p.getPolicephone());
				police.setPolicesector(p.getPolicesector());
				p.setPolice(police);
			}
			
			js.setUrl(Constants.PROJECT_URL);
			js.setList(personlist);
			js.setCode(200);
			js.setMessage(Constants.LOAD_OK_MESSAGE);	
		} catch (Exception ex) 
		{
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);	
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
	@RequestMapping(value = "/getPersonById.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> getPersonById(
			@RequestParam(value="id", required = false)Integer id,
			@RequestParam(value="idcard", required = false)String idcard,
			@RequestParam(value="name", required = false)String name,
			@RequestParam(value="birth", required = false)String birth,  
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);	
		try 
		{
			if(id == null&&StringUtil.isEmpty(idcard)&&StringUtil.isEmpty(name)&&StringUtil.isEmpty(birth))
			{	 
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
				return js;
			}	
			Person person  = new Person();
			if(id != null&& id>0){ 
				person  = personService.getPersonById(id);
			}else{
				if(!StringUtil.isEmpty(idcard)){
					person.setIdcard(idcard);
				}
				if(!StringUtil.isEmpty(name)){
					String personName = new String(name.getBytes("iso8859-1"), "utf-8");
					person.setName(personName);
				}
				if(!StringUtil.isEmpty(birth)){ 
					person.setBirth(birth);
				}
				person  = personService.getPersonByCondition(person);
			}
			if(person != null)
			{
				js.setObj(person);
				js.setCode(200);
				js.setMessage(Constants.LOAD_OK_MESSAGE);	
			}else{ 
				js.setCode(201); 
			}
		} catch (Exception ex) 
		{
				js.setCode(202);
				js.setMessage(Constants.INNER_ERROR_MESSAGE);	
			    ex.printStackTrace();
		}
		return js;
	} 
	/**
	 * App获取数据接口---获取社会机构列表
	 */
	@ResponseBody
	@RequestMapping(value="/getAssoListByGuid.do")
	public AppReturnResult <Associate> jsonLoadAssociateListByGuid(
			@RequestParam(value="guid", required = true)String guid,
			@RequestParam(value="page", required = false)Integer page,
			@RequestParam(value="search", required = false)String searchName,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult <Associate> js = new AppReturnResult<Associate>();
		Associate associate = new Associate();
		List<Associate> la = new ArrayList<Associate>();
		int count = 0;
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
		try {
			if(page != null && page > 0){
				associate.setPageNo(page);
			}
			associate.setPageSize(Constants.DEFAULT_PAGE_SIZE);
			if(searchName != null && !"".equals(searchName)){
				String s = new String(searchName.getBytes("iso8859-1"), "utf-8");
				associate.setSearchName(s);
			}
			if(StringUtil.isEmpty(guid)){ 
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
				return js;
			} 
			associate.setCreatorname(guid); 
			la = associateService.getAssociateList(associate); 
			count = associateService.getTotalCount(associate);
			js.setTotalCount(count);
			if(count%Constants.DEFAULT_PAGE_SIZE == 0){
				js.setPageCount(count/Constants.DEFAULT_PAGE_SIZE);
			}else{
				js.setPageCount(count/Constants.DEFAULT_PAGE_SIZE + 1);
			} 
			js.setCode(200); 
			//js.setObj(associate);
			js.setList(la);
			js.setMessage(Constants.LOAD_OK_MESSAGE); 
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---获取社会机构详情
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="/getAssociateById.do")
	public AppReturnResult<AssociatePerson> getAssociateById(
			@RequestParam(value="id", required = true)Integer id,
			@RequestParam(value="page", required = false)Integer page,
			@RequestParam(value="search", required = false)String searchName,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		Associate associate = new Associate();
		AssociatePerson associatePerson = new AssociatePerson();
		if(page != null && page > 0){
			associatePerson.setPageNo(page);
		}
		associatePerson.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		if(searchName != null && !"".equals(searchName)){
			String s = new String(searchName.getBytes("iso8859-1"), "utf-8");
			associatePerson.setSearchName(s);
		}
		List<AssociatePerson> la = new ArrayList<AssociatePerson>();
		List<AssociatePlan> associatePlanList = new ArrayList<AssociatePlan>(); 
		int count = 0;
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
		try {
			if(id != null){
				associatePerson.setAssociateid(id);
				associate = associateService.getAssociateById(id);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			 
					Date d =associate.getCreatetime(); 
						String s = sdf.format(d);
						associate.setCreatetimes(s); 
				la = associateService.getAssociateListById(associatePerson);
				associatePlanList = associateService.getAssociatePlanListById(id);
				count = associateService.getTotalCount(associatePerson);
				js.setTotalCount(count);
				if(count%Constants.DEFAULT_PAGE_SIZE == 0){
					js.setPageCount(count/Constants.DEFAULT_PAGE_SIZE);
				}else{
					js.setPageCount(count/Constants.DEFAULT_PAGE_SIZE + 1);
				}
				PlanPicture picture = new PlanPicture();
				picture.setCount(associatePlanList.size());
				List<String> urlList = new ArrayList<String>();
				for(AssociatePlan ap:associatePlanList){ 
					urlList.add(ap.getPlanurl());
				}
				picture.setFile(urlList);
				associate.setPicture(picture);
				
				js.setCode(200);
				js.setObj(associate);
				js.setList(la);
				js.setUrl(Constants.PROJECT_URL);
				js.setMessage(Constants.LOAD_OK_MESSAGE);
			}else{
				js.setCode(203); 
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
			}
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---新增社会机构
	 */
	@ResponseBody
	@RequestMapping(value = "/saveAssociate.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Associate> saveAssociate(Associate associate,
			@RequestParam(value = "file", required = false) CommonsMultipartFile file,
//            @RequestParam(value = "file2", required = false) CommonsMultipartFile file2,
//            @RequestParam(value = "file3", required = false) CommonsMultipartFile file3,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<Associate> js = new AppReturnResult<Associate>();
		js.setCode(201);
		js.setMessage(Constants.SAVE_FAIL_MESSAGE);
		try {
			if(associate.getId() == null ||associate.getId() == 0){
//				User u = this.getLoginUser();
//				associate.setCreator(u.getId());
				associate.setCreatorname(associate.getGuid());
//				associate.setOrganname(u.getOrganName()); 
				associate.setCreatetime(new Date());
				String serialNo = getAssoSerialNo(associate.getTypeid()); 
				associate.setSerialno(serialNo); 
				associate.setId(0);
			} 
			associateService.saveOrUpdateAssociate(associate);
			
			List<String> urlList = new ArrayList<String>();
			String path = request.getSession().getServletContext().getRealPath("uploadsource");
			File targetFile = new File(path);
			String filePath = "";
			String fileName = "";
			if(file!=null){
				if(file.getSize()>0){ 
					fileName = file.getOriginalFilename();  
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
					file.transferTo(targetFile);
					urlList.add(filePath);
				}
			}
//			if(file2.getSize()>0){
//				fileName = file2.getOriginalFilename();   
//				if (!targetFile.exists()) {
//					targetFile.mkdirs();
//				}
//				targetFile = new File(path+"/associateplan");
//				if (!targetFile.exists()) {
//					targetFile.mkdirs();
//				}
//				targetFile = new File(path+"/associateplan",fileName);  
//				if(targetFile.exists()){
//					targetFile.delete();
//				}
//				filePath ="uploadsource/associateplan/"+fileName;
//				file2.transferTo(targetFile);
//				urlList.add(filePath);
//			}
//			if(file3.getSize()>0){
//				fileName = file3.getOriginalFilename();   
//				if (!targetFile.exists()) {
//					targetFile.mkdirs();
//				}
//				targetFile = new File(path+"/associateplan");
//				if (!targetFile.exists()) {
//					targetFile.mkdirs();
//				}
//				targetFile = new File(path+"/associateplan",fileName);  
//				if(targetFile.exists()){
//					targetFile.delete();
//				}
//				filePath ="uploadsource/associateplan/"+fileName;
//				file3.transferTo(targetFile);
//				urlList.add(filePath);
//			}
			if(urlList.size()>0){
				associateService.saveOrUpdateAssociatePlan(urlList,associate.getId());
				js.setCode(200);
				js.setMessage(Constants.SAVE_OK_MESSAGE);
			}
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
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
//	/**
//	 * App获取数据接口---获取社会相关人员
//	 */
//	@ResponseBody
//	@RequestMapping(value="/jsonLoadAssociatePersonById.do")
//	public AppReturnResult <AssociatePerson> jsonLoadAssociatePersonById(
//			@RequestParam(value="associateId", required = true)Integer associateId,
//			HttpServletRequest request, HttpServletResponse response){
//		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
//		js.setCode(201);
//		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
//		List<AssociatePerson> la = new ArrayList<AssociatePerson>();
//		try {
//			if(associateId == null || associateId == 0 ){
//				js.setCode(203);
//				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
//				return js;
//			}else{
//				la = associateService.getAssociateListById(associateId);
//			}
//			js.setCode(200);
//			js.setList(la);
//			js.setMessage(Constants.LOAD_OK_MESSAGE);
//		} catch (Exception e) {
//			js.setCode(202);
//			js.setMessage(Constants.INNER_ERROR_MESSAGE);
//			e.printStackTrace();
//		}
//		return js;
//	}
	/**
	 * App获取数据接口---获取社会相关人员详情
	 */
	@ResponseBody
	@RequestMapping(value="/getAssoPersonById.do")
	public AppReturnResult <AssociatePerson> jsonLoadAssociateMemberInfoById(
			@RequestParam(value="id", required = true)Integer id,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult <AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		AssociatePerson ap = new AssociatePerson();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
		try {
			if(id == null || id == 0){
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
				return js;
			}else{
				ap = associateService.getAssociateMemberById(id);
				
			}
			js.setUrl(Constants.PROJECT_URL);
			js.setCode(200);
			js.setObj(ap);
			js.setMessage(Constants.LOAD_OK_MESSAGE);
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---社会机构相关人员的新增
	 */
	@ResponseBody
	@RequestMapping(value = "/saveAssoPerson.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<AssociatePerson> saveAssoPerson(AssociatePerson associatePerson,
            @RequestParam(value = "file", required = false) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		js.setCode(201);
		js.setMessage(Constants.SAVE_FAIL_MESSAGE);
		try { 
			if(associatePerson.getId() == null || associatePerson.getId() == 0){
//				User u = this.getLoginUser();
//				associatePerson.setCreator(u.getId());
//				associatePerson.setCreatorname(u.getName());
				associatePerson.setCreatorname(associatePerson.getGuid());
//				associatePerson.setOrganname(u.getOrganName());  
			}
			if(file != null){
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
			}
				associateService.updateAssociatePerson(associatePerson);
				js.setCode(200);
				js.setMessage(Constants.SAVE_OK_MESSAGE);	
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---重点人员新增
	 */
	@ResponseBody
	@RequestMapping(value = "/savePerson.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> savePerson(Person person,
			@RequestParam(value = "file", required = false) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage(Constants.SAVE_FAIL_MESSAGE);
		try {
			if (person.getId() == null || person.getId() == 0) { 
//				User u = this.getLoginUser();
//				person.setCreator(u.getId());
				person.setCreatorname(person.getGuid());
//				person.setOrganname(u.getOrganName());   
				person.setCreatetime(new Date());
				String serialNo = getPersonSerialNo(person.getTypeid()); 
				person.setSerialno(serialNo); 
				person.setId(0);
			} 
			if (person.getIdcard() != null && !"".equals(person.getIdcard())) {
				Person p = new Person();
				p.setIdcard(person.getIdcard());
				if (person.getId() > 0) {
					p.setId(person.getId());
				}
				List<Person> lc = personService.getExistPersonList(p);
				if (lc.size() > 0) {
					js.setMessage(Constants.ID_CARD_EXIST);
					return js;
				}
			}  
			if(file!= null){
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
					return js;
				 }
			}
			personService.saveOrUpdatePerson(person);
			js.setCode(200);
			js.setMessage(Constants.SAVE_OK_MESSAGE); 
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
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
	@ResponseBody
	@RequestMapping(value="/deleteAssociateById.do")
	public AppReturnResult<Associate> deleteAssociateById(
			@RequestParam(value="id", required = true)Integer id,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		AppReturnResult<Associate> js = new AppReturnResult<Associate>();
		js.setCode(201);
		js.setMessage(Constants.DELETE_FAIL);
		try {
			if(id == null){
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
			}else{
				associateService.deleteAssociateById(id);
				js.setCode(200);
				js.setMessage(Constants.DELETE_SUCCESS);
			}
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
			// TODO: handle exception
			e.printStackTrace();
		}
		return js;
	}
	@ResponseBody
	@RequestMapping(value="/deletePersonById.do")
	public AppReturnResult<Person> deletePersonById(
			@RequestParam(value="id", required = true)Integer id,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage(Constants.DELETE_FAIL);
		try {
			if(id == null){
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
			}else{
				personService.deletePersonById(id);
				js.setCode(200);
				js.setMessage(Constants.DELETE_SUCCESS);
			}
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
			// TODO: handle exception
			e.printStackTrace();
		}
		return js;
	}
	@ResponseBody
	@RequestMapping(value="/deleteAssoPersonById.do")
	public AppReturnResult<AssociatePerson> deleteAssoPersonById(
			@RequestParam(value="id", required = true)Integer id,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		js.setCode(201);
		js.setMessage(Constants.DELETE_FAIL);
		try {
			if(id == null){
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
			}else{
				associateService.deleteAssociatePersonById(id);
				js.setCode(200);
				js.setMessage(Constants.DELETE_SUCCESS);
			}
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage(Constants.INNER_ERROR_MESSAGE);
			// TODO: handle exception
			e.printStackTrace();
		}
		return js;
	}
}
