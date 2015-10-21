package com.monk.customer.service;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.monk.customer.service.dal.entity.ContactEntity;
import com.monk.customer.service.dal.entity.ContactEntityExample;
import com.monk.customer.service.dal.entity.UserEntity;
import com.monk.customer.service.dal.manager.ContactManager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-service-test.xml" })
public class TestContactService {
	private static final Logger log = LoggerFactory.getLogger(TestContactService.class);

	@Resource
	private ContactManager<ContactEntity,ContactEntityExample> service;

	//@Test
	public void testSave() {
		ContactEntity contact = new ContactEntity();
		contact.setAttachment("http://localhost");
		UserEntity user = new UserEntity();
		user.setId(63L);
		contact.setClient(user);
		contact.setContents("test insert contents");
		contact.setCreateTime(new Date());
		contact.setStatus(0);
		try{
			System.out.println(service.save(contact));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUpdate() {
		ContactEntity user = new ContactEntity();
		user.setId(6L);
		user.setStatus(2);
		try{
			System.out.println(service.update(user));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

	@Test
	public void testList() {
		ContactEntityExample cex = new ContactEntityExample();
		try{
			List<ContactEntity> list = service.findByExample(cex);
			System.out.println(list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
