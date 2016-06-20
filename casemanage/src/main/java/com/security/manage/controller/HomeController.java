package com.security.manage.controller;
   
import java.util.ArrayList; 
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 

import com.security.manage.common.JsonResult; 
import com.security.manage.common.UserLogin;
import com.security.manage.model.LoginResult;
import com.security.manage.model.ResultList;
import com.security.manage.model.TypeStatistic;
import com.security.manage.model.User;
import com.security.manage.service.AssociateService;
import com.security.manage.service.PersonService;
import com.security.manage.util.StringUtil;

@Scope("prototype")
@Controller
@RequestMapping("/Home")
public class HomeController extends BaseController { 
	@Resource(name = "associateService")
	private AssociateService associateService;
	@Resource(name = "personService")
	private PersonService personService;
	
	
	
	@ResponseBody
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public JsonResult <User> login(User user, 
			HttpServletRequest request, HttpServletResponse response){
		JsonResult <User>json  = new JsonResult<User>();
		json.setCode(1); 
		json.setMessage("登录失败!"); 
		try{
			if(StringUtil.isEmpty(user.getAccount())){ 
				json.setMessage("请输入用户名");
				return json;
			}
			if(StringUtil.isEmpty(user.getPassword())){ 
				json.setMessage("请输入密码");
				return json;
			}
			if(!user.getAccount().equals("admin")){ 
				json.setMessage("用户名错误");
				return json;
			}
			if(!user.getPassword().equals("111111")){ 
				json.setMessage("密码错误");
				return json;
			}
			json.setCode(0); 
			json.setMessage("登录成功");
			user.setGuid("111111");
			user.setName("张三");
			user.setDeptGuid("111111");
			user.setOrganName("111111");
			user.setId(1);
			user.setKeyWords("111111");
			this.setLoginUser(user);
//			LoginResult loginResult = UserLogin.login(user.getAccount(),user.getPassword());
//			if(loginResult.getStatus().equals("200")){
//				user.setGuid(loginResult.getPoliceman().getGuid());
//				user.setName(loginResult.getPoliceman().getPc_name());
//				user.setDeptGuid(loginResult.getPoliceman().getDept_guid());
//				user.setId(loginResult.getPoliceman().getId());
//				user.setKeyWords(loginResult.getPoliceman().getGuid());
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
	 * 信息采集概况
	 * @param associate
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping(value = "/statisticInfo.do",method=RequestMethod.GET)
	public String statisticInfo( 
			HttpServletRequest request, HttpServletResponse response){ 
		return "web/home/statisticInfo";
	}	
	
	/**
	 * 获取概况统计信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/jsonLoadStatisticXML.do", method=RequestMethod.POST)
	public JsonResult<List<ResultList>> jsonLoadStatisticXML(
			HttpServletRequest request, HttpServletResponse response){
		JsonResult<List<ResultList>>json  = new JsonResult<List<ResultList>>();
		json.setCode(new Integer(1));
		json.setMessage("登录失败!");
		List<ResultList> xmlList = new ArrayList<ResultList>();
		try{   
			 
			List<TypeStatistic> assoTypeList = new ArrayList<TypeStatistic>();
			assoTypeList = associateService.getAssociateTypeCountList(); 
			ResultList assoType = new ResultList();
			assoType.setList(assoTypeList);
			xmlList.add(assoType);
			List<TypeStatistic> personTypeList = new ArrayList<TypeStatistic>();
			personTypeList = personService.getPersonTypeCountList();  
			ResultList personType = new ResultList();
			personType.setList(personTypeList);
			xmlList.add(personType);
			
			
			
			List<TypeStatistic> assostationList = new ArrayList<TypeStatistic>();
			assostationList = associateService.getAssociateStationCountList(); 
			ResultList assostation = new ResultList();
			assostation.setList(assostationList);
			xmlList.add(assostation);
			
			List<TypeStatistic> personstationList = new ArrayList<TypeStatistic>();
			personstationList = personService.getPersonStationCountList();  
			ResultList personstation = new ResultList();
			personstation.setList(personstationList);
			xmlList.add(personstation);

//			List<Associate> assoList = associateService.getAssociateList(new Associate());
//			List<Person> personList = personService.getPersonList(new Person());
//			
//			
//			List<TypeStatistic> assoMonthList = new ArrayList<TypeStatistic>();
//			List<TypeStatistic> personMonthList = new ArrayList<TypeStatistic>(); 
//			List<TypeStatistic> assoWeekList = new ArrayList<TypeStatistic>();
//			List<TypeStatistic> personWeekList = new ArrayList<TypeStatistic>(); 
//			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
//			 
//			
//			
//			 
//			ResultList assoMonth = new ResultList();
//			assoMonth.setList(assoMonthList);
//			xmlList.add(assoMonth); 
//			ResultList personMonth = new ResultList();
//			personMonth.setList(personMonthList);
//			xmlList.add(personMonth);
//			
//
//			 
//			ResultList assoWeek = new ResultList();
//			assoWeek.setList(assoWeekList);
//			xmlList.add(assoWeek);  
//			ResultList personWeek = new ResultList();
//			personWeek.setList(personWeekList);
//			xmlList.add(personWeek);
			
			json.setCode(new Integer(0));  
			json.setMessage("登录成功!"); 
			json.setObj(xmlList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
}
