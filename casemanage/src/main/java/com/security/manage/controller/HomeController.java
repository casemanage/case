package com.security.manage.controller;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 

import com.security.manage.common.JsonResult; 
import com.security.manage.model.User;

@Scope("prototype")
@Controller
@RequestMapping("/Home")
public class HomeController { 
	@ResponseBody
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public JsonResult <User> login(User user, 
			HttpServletRequest request, HttpServletResponse response){
		JsonResult <User>json  = new JsonResult<User>();
		json.setCode(new Integer(1));
		json.setMessage("登录失败!");
		try{
//			ReturnResult<User> res =login(user.getAccount(), user.getPassword());
//			if(res.getCode() == ReturnResult.SUCCESS){
//				//List<Functions> lf = parseFunctionList(functionService.getFunctionByParentId(res.getResultObject().getId()));
//				List<Functions> lf = parseFunctionList(functionService.getFunctionByParentId(0));
//				request.getSession().setAttribute(Constants.USER_SESSION_FUNCTION,lf); 
//				
//				
//				res.getResultObject().setSelectedMainMemu(lf.get(0).getId());
//				res.getResultObject().setSelectedChildMenu(lf.get(0).getChildFunctionlist().get(0).getId());
//				res.getResultObject().setChildMenuList(lf.get(0).getChildFunctionlist());
//				this.setLoginUser(res.getResultObject());
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

}
