package com.monk.customer.common.web.vo;


public class ContactVo extends ParentParamComponent{

	private String content;

	private Integer status;
	
	private UserVo admin = new UserVo();

	private UserVo client = new UserVo();

	public UserVo getClient() {
		return client;
	}

	public void setClient(UserVo client) {
		this.client = client;
	}

	public UserVo getAdmin() {
		return admin;
	}

	public void setAdmin(UserVo admin) {
		this.admin = admin;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
