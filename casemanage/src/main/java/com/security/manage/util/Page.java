/**
 * 
 */
package com.security.manage.util;

/**
 * @author Leo
 *
 */
public class Page {
	/**
	 * 分页起始行数，自动计算
	 */
	private Integer pageStart;
	/**
	 * 每页记录数，需设置
	 */
    private Integer pageSize;
    /**
	 * 当前页数，需设置
	 */
    private Integer pageNo;
    /**
     * 查询条件
     */
    private String searchName;

    /**
	 * 分页起始行数，自动计算
	 */
    @SuppressWarnings("unused")
	private Integer pageCount;
    
    private Integer totalCount;  
    
	public Integer getPageStart() {
		if (pageNo != null && pageSize != null) {
			pageStart = pageSize * (pageNo-1);
		}
		return pageStart;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	} 
	
	public Integer getPageCount() {
		int pageCount = 0;
		if(totalCount != null){
			 pageCount = totalCount%pageSize==0 ? totalCount/pageSize:totalCount/pageSize+1;
			if(pageCount == 0){
				pageCount = 1;
			}
		}else{
			pageCount = 1;
		}
		return pageCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

 
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	} 

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	} 
}

