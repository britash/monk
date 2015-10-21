package com.monk.customer.service.dal.manager;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monk.customer.service.dal.CalenderMapper;
import com.monk.customer.service.dal.entity.AbstractEntity;

@Component
public class CompanyManager<T extends AbstractEntity,V> extends AbstractManager<T, V>{
	
	private static final Logger log = LoggerFactory.getLogger(CompanyManager.class);

	@Autowired
	private CalenderMapper<T,V> mapper;
	
	@PostConstruct
	public void init(){
		setMapper(mapper);
	}
}
