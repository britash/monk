package com.monk.customer.service.dal.entity;

import java.util.Date;

public class CompanyEntity extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;// ID
	private String name;
	
	private Integer status;
	
	private Date createTime;//create_time;
	private Date updateTime;//update_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
