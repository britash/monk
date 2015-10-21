package com.monk.customer.service;


import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.monk.customer.service.dal.entity.UserEntity;
import com.monk.customer.service.dal.entity.UserEntityExample;
import com.monk.customer.service.dal.manager.UserManager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-service-test.xml" })
public class TestUserService {
	private static final Logger log = LoggerFactory.getLogger(TestUserService.class);

	@Resource
	private UserManager<UserEntity,UserEntityExample> service;

	//@Test
	public void testSave() {
		UserEntity user = new UserEntity();
		user.setEmail("email@email.com");
		user.setUsername("username");
		user.setName("name");
		user.setPassword("12345678901234567890123456789012");
		user.setTelephone("1233333");
		user.setRegisterTime(new Date());
		user.setRegisterIp("127.0.0.1");
		user.setStatus(0);
		user.setType(0);
		try{
			System.out.println(service.save(user));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		UserEntity user = new UserEntity();
		user.setId(63L);
		user.setEmail("email@email.com");
		user.setUsername("username1");
		user.setName("name1");
		user.setPassword("12345678901234567890123456789012");
		user.setTelephone("1233333");
		user.setRegisterTime(new Date());
		user.setRegisterIp("127.0.0.1");
		user.setStatus(0);
		user.setType(0);
		try{
			System.out.println(service.update(user));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

	//@Test
	public void testList() {
		UserEntityExample user = new UserEntityExample();
		user.setOrderWithRegisterTime(null);
		try{
			System.out.println(service.findByExample(user).size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
