package com.monk.customer.common.web.vo;

import java.util.Date;

public class CalenderVo extends ParentParamComponent{

	private String title;
	
	private Date startTime;//start_time;
	private Date endTime;//end_time;
	private Date createTime;//create_time;
	private String color;
	private Integer urgent;//'1:正常，2：紧急',
	
	private Integer status;
	
	private UserVo client = new UserVo();
	
	private UserVo admin = new UserVo();

	public UserVo getAdmin() {
		return admin;
	}

	public void setAdmin(UserVo admin) {
		this.admin = admin;
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

	public UserVo getClient() {
		return client;
	}

	public void setClient(UserVo client) {
		this.client = client;
	}

	

}
