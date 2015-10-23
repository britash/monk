package com.monk.customer.common.web.vo;


public class FactoryVo extends ParentParamComponent{

	private Long id;

	private String name;

	private Integer status;
	
	private UserVo client;

	public UserVo getClient() {
		return client;
	}
	public void setClient(UserVo client) {
		this.client = client;
	}
	public String getName() {
		return name;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
