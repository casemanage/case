package com.security.manage.model;

public class LoginResult {
	private String status;
	private String msg;
	private PoliceMan policeman;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public PoliceMan getPoliceman() {
		return policeman;
	}
	public void setPoliceman(PoliceMan policeman) {
		this.policeman = policeman;
	}
}
