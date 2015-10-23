package com.monk.customer.common.web.vo;

import java.util.Date;


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
    
 // 开始时间
 	private Date start;
 	
 	// 结束时间
 	private Date end;
 	
 	private Integer status;
 	
 	private Long id;
 	
 	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
 	
 	 public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStart() {
 		return start;
 	}

 	public void setStart(Date start) {
 		this.start = start;
 	}

 	public Date getEnd() {
 		return end;
 	}

 	public void setEnd(Date end) {
 		this.end = end;
 	}
    
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
