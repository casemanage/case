package com.security.manage.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import org.apache.log4j.Logger; 
import org.apache.shiro.SecurityUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.security.manage.model.User;
 

public abstract class BaseController extends CommonsMultipartResolver {
	protected Logger log = Logger.getLogger(getClass());

	private String resultCode;
	private String resultMessage;
	
	private HttpServletRequest req;
	private HttpServletResponse res;
	

	public HttpServletRequest getReq() {
		return req;
	}

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public HttpServletResponse getRes() {
		return res;
	}

	public void setRes(HttpServletResponse res) {
		this.res = res;
	}
 
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	/**
	 * 鐢ㄦ埛session
	 * 
	 * @return
	 */
	protected User getLoginUser() {
		User loginUser = (User) SecurityUtils.getSubject().getSession().getAttribute("username");
		return loginUser;
	}

	/**
	 * 鐢ㄦ埛session
	 * 
	 * @param loginUser
	 */
	protected void setLoginUser(User loginUser) {
		SecurityUtils.getSubject().getSession().setAttribute("username", loginUser);
	}

	
}
