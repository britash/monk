package com.monk.customer.common.web.vo;


public class UserVo extends ParentParamComponent{

	private Long id;
	
	private String userName;
	
	private String password;
	
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private Integer status;
	
	private Long commpanyId;
	
	
	public Long getCommpanyId() {
		return commpanyId;
	}

	public void setCommpanyId(Long commpanyId) {
		this.commpanyId = commpanyId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
