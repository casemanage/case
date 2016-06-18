package com.security.manage.controller.app;

 
import java.util.ArrayList;
import java.io.File;
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
import com.security.manage.common.JsonResult;
import com.security.manage.controller.BaseController; 
import com.security.manage.model.AssociateType;
import com.security.manage.model.Person;
import com.security.manage.model.PersonLevel; 
import com.security.manage.model.Associate;
import com.security.manage.model.AssociatePerson;
import com.security.manage.model.PersonType;
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
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public JsonResult <User> login(User user, 
			HttpServletRequest request, HttpServletResponse response){
		JsonResult <User>json  = new JsonResult<User>();
		json.setCode(1);
		json.setMessage(Constants.LOGIN_FAIL);
		try{ 
			User u = new User();
			u.setGuid("111111");
			u.setName("张三");
			u.setOrganId(1);
			u.setId(3);
			u.setOrganName("青羊区公安分局");
			u.setKeyWords("ssss");
			this.setLoginUser(u); 
			json.setCode(0);  
			json.setMessage(Constants.LOGIN_SUCCESS);
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
	@RequestMapping(value = "/jsonLoadPersonTypeList.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<PersonType> PersonTypeList(
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
	@RequestMapping(value = "/jsonLoadPersonLevelList.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<PersonLevel> PersonLevelList(
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
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonLoadPersonList.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> PersonList(
			@RequestParam(value="guid", required = false)String guid,
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);	
		Person person = new Person();
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
			personlist = personService.getPersonList(person);
			 
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
	@RequestMapping(value = "/jsonLoadPersonById.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> GetPersonById(
			@RequestParam(value="id", required = false)Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);	
		try 
		{
			
			if(id == null||id==0)
			{	 
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
				return js;
			}	
			Person person  = personService.getPersonById(id);
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
	@RequestMapping(value="/jsonLoadAssociateListByGuid.do")
	public AppReturnResult <Associate> jsonLoadAssociateListByGuid(
			@RequestParam(value="guid", required = false)String guid,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult <Associate> js = new AppReturnResult<Associate>();
		Associate associate = new Associate();
		List<Associate> la = new ArrayList<Associate>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
		try {
			if(StringUtil.isEmpty(guid)){ 
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
				return js;
			} 
			associate.setCreatorname(guid);
			la = associateService.getAssociateListByCreatorname(associate); 
				js.setCode(200);
				js.setObj(associate);
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
	 */
	@ResponseBody
	@RequestMapping(value="/jsonLoadAssociateInfoByAssociateId.do")
	public AppReturnResult <Associate> jsonLoadAssociateInfoByAssociateId(
			@RequestParam(value="associateId", required = true)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult <Associate> js = new AppReturnResult<Associate>();
		Associate associate = new Associate();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
		try {
			if(associateId != null){
				associate = associateService.getAssociateById(associateId);
				js.setCode(200);
				js.setObj(associate);
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
	@RequestMapping(value = "/jsonSaveOrUpdateAssociated.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Associate> jsonSaveOrUpdateAssociated(Associate associate,
			@RequestParam(value = "file1", required = false) CommonsMultipartFile file1,
            @RequestParam(value = "file2", required = false) CommonsMultipartFile file2,
            @RequestParam(value = "file3", required = false) CommonsMultipartFile file3,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<Associate> js = new AppReturnResult<Associate>();
		js.setCode(201);
		js.setMessage(Constants.SAVE_FAIL_MESSAGE);
		try {
			if(associate.getId() == null ||associate.getId() == 0){
				User u = this.getLoginUser();
				associate.setCreator(u.getId());
				associate.setCreatorname(u.getName());
				associate.setOrganname(u.getOrganName()); 
				associate.setCreatetime(new Date());
				String serialNo = getAssoSerialNo(associate.getTypeid()); 
				associate.setSerialno(serialNo); 
				associate.setId(0);
			}
			if(associate.getName() != null && !"".equals(associate.getName())){
				Associate a = new Associate();
				a.setName(associate.getName());
				if(associate.getId() > 0){
					a.setId(associate.getId());
				}
				List<Associate> la = new ArrayList<Associate>();
				la = associateService.getExistAssociate(a);
				if(la.size() > 0){
					return js;
				}
			}
			associateService.saveOrUpdateAssociate(associate);
			
			List<String> urlList = new ArrayList<String>();
			String path = request.getSession().getServletContext().getRealPath("uploadsource");
			File targetFile = new File(path);
			String filePath = "";
			String fileName = "";
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
	/**
	 * App获取数据接口---获取社会相关人员
	 */
	@ResponseBody
	@RequestMapping(value="/jsonLoadAssociateMemberByAssociateId.do")
	public AppReturnResult <AssociatePerson> jsonLoadAssociateMemberByAssociateId(
			@RequestParam(value="associateId", required = true)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		js.setCode(201);
		js.setMessage(Constants.LOAD_FAIL_MESSAGE);
		List<AssociatePerson> la = new ArrayList<AssociatePerson>();
		try {
			if(associateId == null || associateId == 0 ){
				js.setCode(203);
				js.setMessage(Constants.PARAM_ERROR_MESSAGE);
				return js;
			}else{
				la = associateService.getAssociateListById(associateId);
			}
			js.setCode(200);
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
	 * App获取数据接口---获取社会相关人员详情
	 */
	@ResponseBody
	@RequestMapping(value="/jsonLoadAssociateMemberInfoById.do")
	public AppReturnResult <AssociatePerson> jsonLoadAssociateMemberInfoById(
			@RequestParam(value="Id", required = true)Integer id,
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
	@RequestMapping(value = "/jsonSaveOrUpdatedAssociateMember.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<AssociatePerson> jsonSaveOrUpdatedAssociateMember(AssociatePerson associatePerson,
            @RequestParam(value = "file", required = false) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		js.setCode(201);
		js.setMessage(Constants.SAVE_FAIL_MESSAGE);
		try { 
			if(associatePerson.getId() == null || associatePerson.getId() == 0){
				User u = this.getLoginUser();
				associatePerson.setCreator(u.getId());
				associatePerson.setCreatorname(u.getName());
				associatePerson.setOrganname(u.getOrganName());  
			}
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
					associateService.updateAssociatePerson(associatePerson);
					js.setCode(200);
					js.setMessage(Constants.SAVE_OK_MESSAGE);
				//}
				//else {
	
	//							targetFile = new File(path+"/userphoto",fileName);  
	//							if(targetFile.exists()){
	//								targetFile.delete();
	//							}
					//js.setMessage("上传头像，不符合公安部要求，请重新选择图片上传!"); 
				// }
			 }else{
				return js;
			 }
				
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
	@RequestMapping(value = "/jsonSaveOrUpdatedAssociated.do", produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> jsonSaveOrUpdatedAssociated(Person person,
			@RequestParam(value = "file", required = false) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage(Constants.SAVE_FAIL_MESSAGE);
		try {
			if (person.getId() == null || person.getId() == 0) { 
				User u = this.getLoginUser();
				person.setCreator(u.getId());
				person.setCreatorname(u.getName());
				person.setOrganname(u.getOrganName());   
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
}
