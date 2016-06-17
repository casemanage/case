package com.security.manage.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppReturnResult<E>  implements Serializable {


	/** 变量 serialVersionUID(long) */
	private static final long serialVersionUID = 1L;
 

	private Integer code;
	
	private String message;
	
	private Object obj;
	
	private List<E> list =new ArrayList<E>(); 
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
}
