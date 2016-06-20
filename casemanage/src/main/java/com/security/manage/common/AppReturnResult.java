package com.security.manage.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppReturnResult<E>  implements Serializable {


	/** 变量 serialVersionUID(long) */
	private static final long serialVersionUID = 1L;
 
    /**
     * 返回状态值：200：正常，201：获取数据失败，202：内部方法异常,203:传入参数出错
     */
	private Integer code;
	
	private String message;
	
	private Object obj;
	
	/**
	 * 数据总记录条数
	 */
	private Integer totalCount;
	
	/**
	 * 总记录数按照pageSize计算得到总页数
	 */
	private Integer pageCount;
	
	/**
	 * 项目部署绝对路径，用于图片头像访问
	 */
	private String url; 
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

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
