package com.monk.customer.service.dal.manager;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monk.customer.service.dal.UserMapper;
import com.monk.customer.service.dal.entity.AbstractEntity;
@Component
public class UserManager<T extends AbstractEntity,V> extends AbstractManager<T, V>{
	
	private static final Logger log = LoggerFactory.getLogger(UserManager.class);

	@Autowired
	private UserMapper<T,V> mapper;
	
	
	@PostConstruct
	public void init(){
		setMapper(mapper);
	}
	
	public Integer countCustomerForAdmin(@Param("example") V entityExample){
		return mapper.countCustomerForAdmin(entityExample);
	}

	public List<T> findCustomerForAdmin(@Param("example") V entityExample){
		return mapper.findCustomerForAdmin(entityExample);
	}
}
