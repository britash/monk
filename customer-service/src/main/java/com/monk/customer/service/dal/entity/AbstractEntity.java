package com.monk.customer.service.dal.entity;

import java.io.Serializable;

public class AbstractEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;// ID
	public Long getId(){
		return id;
	}

}
