package com.monk.customer.common.web.vo;


public class ProjectVo extends ParentParamComponent{

	private Long id;

	private String name;

	private Integer status;
	
	private FactoryVo factory;

	public FactoryVo getFactory() {
		return factory;
	}
	public void setFactory(FactoryVo factory) {
		this.factory = factory;
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
