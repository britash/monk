package com.monk.customer.common.web.vo;


public class CompanyVo extends ParentParamComponent{

	private Long id;


	private String name;

	private Integer status;
	
	private UserVo user;

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

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

}
