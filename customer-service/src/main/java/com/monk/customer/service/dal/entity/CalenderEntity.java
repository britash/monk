package com.monk.customer.service.dal.entity;

import java.util.Date;

public class CalenderEntity extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;// ID
	private String title;
	private Date startTime;//start_time;
	private Date endTime;//end_time;
	private Date createTime;//create_time;
	private String color;
	private Integer urgent;//'1:正常，2：紧急',
	private Integer status;//'0:未确认,1:已确认,2:已删除',
	private UserEntity client;//client_id
	private UserEntity admin;//admin_id
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getUrgent() {
		return urgent;
	}
	public void setUrgent(Integer urgent) {
		this.urgent = urgent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public UserEntity getClient() {
		return client;
	}
	public void setClient(UserEntity client) {
		this.client = client;
	}
	public UserEntity getAdmin() {
		return admin;
	}
	public void setAdmin(UserEntity admin) {
		this.admin = admin;
	}
	
	
}
