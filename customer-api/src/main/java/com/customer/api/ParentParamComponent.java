package com.customer.api;


public class ParentParamComponent {

	 /**
     * 分页参数，默认为每页10条
     */
    private Integer pageSize = 10;
    
    /**
     * 分页参数，默认为第一页
     */
    private Integer pageNo = 1;
    
    /**
     * 排序字段
     */
    private String sort;
    
    /**
     * 排序顺序
     */
    private String order;
    
    
	/**
     * 分页参数，返回mysql查询的起始行号
     */
    public Integer getPageStart(){
    	return (pageNo - 1)* pageSize;
    }
    
    public Integer getPageLimit(){
    	return pageSize;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "ParentParamComponent [pageSize=" + pageSize + ", pageNo="
				+ pageNo + ", sort=" + sort + ", order=" + order + "]";
	}

}
