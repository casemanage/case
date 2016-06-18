package com.security.manage.controller.app;

import java.io.File;
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

import com.security.manage.common.AppReturnResult;
import com.security.manage.common.JsonResult;
import com.security.manage.controller.BaseController;
import com.security.manage.model.Associate;
import com.security.manage.model.AssociatePerson;
import com.security.manage.model.AssociateType;
import com.security.manage.model.Person;
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
	 * App获取数据接口---获取社会机构列表
	 */
	@ResponseBody
	@RequestMapping(value="/jsonLoadAssociateListByGuid.do", method=RequestMethod.GET)
	public AppReturnResult <Associate> jsonLoadAssociateListByGuid(
			@RequestParam(value="guid", required = false)String guid,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult <Associate> js = new AppReturnResult<Associate>();
		Associate associate = new Associate();
		List<Associate> la = new ArrayList<Associate>();
		js.setCode(201);
		js.setMessage("查询失败!");
		try {
			if(guid != null){
				String creatorname = new String(guid.getBytes(
						"iso8859-1"), "utf-8");
				associate.setCreatorname(creatorname);
			}
			la = associateService.getAssociateListByCreatorname(associate);
			if(la.size() != 0)
			{
				js.setCode(200);
				js.setObj(associate);
				js.setList(la);
				js.setMessage("查询成功!");
			}			
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage("内部方法异常!");
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---获取社会机构详情
	 */
	@ResponseBody
	@RequestMapping(value="/jsonLoadAssociateInfoByAssociateId.do", method=RequestMethod.GET)
	public AppReturnResult <Associate> jsonLoadAssociateInfoByAssociateId(
			@RequestParam(value="associateId", required = false)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult <Associate> js = new AppReturnResult<Associate>();
		Associate associate = new Associate();
		js.setCode(201);
		js.setMessage("查询失败!");
		try {
			if(associateId != null){
				associate = associateService.getAssociateById(associateId);
			}
			js.setCode(200);
			js.setObj(associate);
			js.setMessage("查询成功!");
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage("内部方法异常!");
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---新增社会机构
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdateAssociated.do", method = RequestMethod.GET, produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Associate> jsonSaveOrUpdateAssociated(Associate associate,
			@RequestParam(value = "file1", required = false) CommonsMultipartFile file1,
            @RequestParam(value = "file2", required = false) CommonsMultipartFile file2,
            @RequestParam(value = "file3", required = false) CommonsMultipartFile file3,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<Associate> js = new AppReturnResult<Associate>();
		js.setCode(201);
		js.setMessage("保存失败!");
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
			if(associate.getTypeid() == null){
				js.setMessage("机构类型不能为空!");
				return js;
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
					js.setMessage("机构名已存在!");
					return js;
				}
			}else{
				js.setMessage("机构名不能为空!");
				return js;
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
				js.setMessage("保存成功!");
			}
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage("内部方法异常!");
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
	@RequestMapping(value="/jsonLoadAssociateMemberByAssociateId.do", method=RequestMethod.GET)
	public AppReturnResult <AssociatePerson> jsonLoadAssociateMemberByAssociateId(
			@RequestParam(value="associateId", required = false)Integer associateId,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		js.setCode(201);
		js.setMessage("获取人员数据失败!");
		List<AssociatePerson> la = new ArrayList<AssociatePerson>();
		try {
			if(associateId != null){
				la = associateService.getAssociateListById(associateId);
			}
			js.setCode(200);
			js.setList(la);
			js.setMessage("获取人员数据成功!");
			
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage("内部方法异常!");
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---获取社会相关人员详情
	 */
	@ResponseBody
	@RequestMapping(value="/jsonLoadAssociateMemberInfoById.do", method=RequestMethod.GET)
	public AppReturnResult <AssociatePerson> jsonLoadAssociateMemberInfoById(
			@RequestParam(value="Id", required = false)Integer id,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult <AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		AssociatePerson ap = new AssociatePerson();
		js.setCode(201);
		js.setMessage("获取社会相关人员详情失败!");
		try {
			if(id != null){
				ap = associateService.getAssociateMemberById(id);
			}
			js.setCode(200);
			js.setObj(ap);
			js.setMessage("获取社会相关人员详情成功!");
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage("内部方法异常!");
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---社会机构相关人员的新增
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdatedAssociateMember.do", method = RequestMethod.GET, produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<AssociatePerson> jsonSaveOrUpdatedAssociateMember(AssociatePerson associatePerson,
            @RequestParam(value = "file", required = false) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<AssociatePerson> js = new AppReturnResult<AssociatePerson>();
		js.setCode(201);
		js.setMessage("社会机构相关人员的新增失败!");
		try { 
			User u = this.getLoginUser();
			associatePerson.setCreator(u.getId());
			associatePerson.setCreatorname(u.getName());
			associatePerson.setOrganname(u.getOrganName());   
			if(associatePerson.getName() != null){
				AssociatePerson a = new AssociatePerson();
				a.setName(associatePerson.getName());
				List<Associate> la = new ArrayList<Associate>();
				la = associateService.getExistAssociate(a);
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
							associateService.updateAssociatePerson(associatePerson);
							js.setCode(200);
							js.setMessage("保存成功!");
						//}
						//else {

//							targetFile = new File(path+"/userphoto",fileName);  
//							if(targetFile.exists()){
//								targetFile.delete();
//							}
							//js.setMessage("上传头像，不符合公安部要求，请重新选择图片上传!"); 
						// }
					 }else{
						js.setMessage("请选择头像文件进行上传！");
						return js;
					 }
				}else{
					js.setMessage("人员名已存在!");
				}
			}else{
				js.setMessage("人员名不能为空!");
			}
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage("内部方法异常!");
			e.printStackTrace();
		}
		return js;
	}
	/**
	 * App获取数据接口---重点人员新增
	 */
	@ResponseBody
	@RequestMapping(value = "/jsonSaveOrUpdatedAssociated.do", method = RequestMethod.GET, produces = { "text/html;charset=UTF-8" })
	public AppReturnResult<Person> jsonSaveOrUpdatedAssociated(Person person,
			@RequestParam(value = "file", required = true) CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		AppReturnResult<Person> js = new AppReturnResult<Person>();
		js.setCode(201);
		js.setMessage("重点人员新增失败!");
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
			js.setCode(new Integer(200));
			js.setMessage("保存成功!"); 
		} catch (Exception e) {
			js.setCode(202);
			js.setMessage("内部方法异常!");
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
