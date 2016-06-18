package com.security.manage.controller;
  
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Branch;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Visitor;
import org.dom4j.XPath;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.xml.sax.EntityResolver;

import com.security.manage.common.JsonResult; 
import com.security.manage.model.Associate;
import com.security.manage.model.AssociateType;
import com.security.manage.model.Person;
import com.security.manage.model.ResultList;
import com.security.manage.model.TypeStatistic;
import com.security.manage.model.User;
import com.security.manage.service.AssociateService;
import com.security.manage.service.PersonService;
import com.security.manage.util.Constants;

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
		json.setCode(new Integer(0));
		//json.setCode(new Integer(1));
		json.setMessage("登录失败!");
		try{
//			String path = "http://服务器域名(端口)/index.php/API/Police/policeLogin?pc_num="+user.getAccount()+"&pc_pwd="+user.getPassword();
//			URL url = new URL(path);
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        
//	        conn.setRequestMethod("POST");
//	        conn.setConnectTimeout(5 * 1000);// 设置连接超时时间为5秒 
//	        conn.setReadTimeout(20 * 1000);// 设置读取超时时间为20秒 
//	        // 使用 URL 连接进行输出，则将 DoOutput标志设置为 true
//	        conn.setDoOutput(true); 
//	        conn.setUseCaches(false);
//	        conn.setInstanceFollowRedirects(true);
//	        conn.setRequestProperty("Content-Type", "application/x-javascript");
//	        conn.connect();
//	        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//	        String content = "pc_num=" + user.getAccount()+"&pc_pwd="+user.getPassword();
//	        out.writeBytes(content);  
//	        out.flush();
//	        out.close(); // flush and close 
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        String msg;
//	        while ((msg = reader.readLine()) != null) {
//	        	msg += msg;
//	        }
//	        reader.close();
			User u = new User();
			u.setGuid("111111");
			u.setName("张三");
			u.setOrganId(1);
			u.setId(3);
			u.setOrganName("青羊区公安分局");
			u.setKeyWords("ssss");
			this.setLoginUser(u);
//				
//				json.setCode(new Integer(0)); 
//				json.setGotoUrl(lf.get(0).getUrl());
//				json.setMessage("登录成功!");
//			}else{				
//				json.setMessage(res.getMessage());
//			}

			json.setCode(new Integer(0));  
			json.setMessage("登录成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 信息采集概况
	 * @param associate
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/statisticInfo.do",method=RequestMethod.GET)
	public String statisticInfo( 
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
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

			//List<Associate> assoList = associateService.getAssociateList(new Associate());
			//List<Person> personList = personService.getPersonList(new Person());
			
			
			List<TypeStatistic> assoMonthList = new ArrayList<TypeStatistic>();
			List<TypeStatistic> personMonthList = new ArrayList<TypeStatistic>(); 
			List<TypeStatistic> assoWeekList = new ArrayList<TypeStatistic>();
			List<TypeStatistic> personWeekList = new ArrayList<TypeStatistic>(); 
			//DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			 
			
			
			 
			ResultList assoMonth = new ResultList();
			assoMonth.setList(assoMonthList);
			xmlList.add(assoMonth); 
			ResultList personMonth = new ResultList();
			personMonth.setList(personMonthList);
			xmlList.add(personMonth);
			

			 
			ResultList assoWeek = new ResultList();
			assoWeek.setList(assoWeekList);
			xmlList.add(assoWeek);  
			ResultList personWeek = new ResultList();
			personWeek.setList(personWeekList);
			xmlList.add(personWeek);
			
			json.setCode(new Integer(0));  
			json.setMessage("登录成功!"); 
			json.setObj(xmlList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
}
