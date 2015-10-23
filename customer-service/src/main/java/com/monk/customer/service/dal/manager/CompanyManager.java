package com.monk.customer.service.dal.manager;

import javax.annotation.PostConstruct;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monk.customer.service.dal.CompanyMapper;
import com.monk.customer.service.dal.entity.AbstractEntity;

@Component
public class CompanyManager<T extends AbstractEntity,V> extends AbstractManager<T, V>{
	
	private static final Logger log = LoggerFactory.getLogger(CompanyManager.class);

	@Autowired
	private CompanyMapper<T,V> mapper;
	
	@PostConstruct
	public void init(){
		setMapper(mapper);
	}
	
	public int insertUser( Long userId,Long companyId){
		return mapper.insertUser(userId, companyId);
	}
	public int updateUser(Long userId, Long companyId, Long oldUserId, Long oldCompanyId){
		return mapper.updateUser(userId, companyId, oldUserId, oldCompanyId);
	}
	public int deleteUser(@Param("userId") Long userId,@Param("companyId") Long companyId){
		return mapper.deleteUser(userId, companyId);
	}
}
