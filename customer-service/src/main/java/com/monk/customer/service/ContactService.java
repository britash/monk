package com.monk.customer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.vo.ContactVo;
import com.monk.customer.service.dal.entity.ContactEntity;
import com.monk.customer.service.dal.entity.ContactEntityExample;
import com.monk.customer.service.dal.manager.ContactManager;

@Service
public class ContactService {
	private static final Logger log = LoggerFactory.getLogger(ContactService.class);

	@Resource
	private ContactManager<ContactEntity, ContactEntityExample> contactManager;
	
	public Integer save(ContactEntity entity){
		return this.contactManager.save(entity);
	}
	
	public Integer update(ContactEntity entity){
		return this.contactManager.update(entity);
	}
	
	public PageVo<ContactEntity> find(ContactVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		PageVo<ContactEntity> page = new PageVo<>();
		List<ContactEntity> list = new ArrayList<>();

		ContactEntityExample example = new ContactEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createContentsLike(paramComponent.getContent()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createClientIdEquals(paramComponent.getClient().getId()))
			.addCriterion(example.createAdminIdEquals(paramComponent.getAdmin().getId()))
			.addCriterion(example.createCreationDateBetween(paramComponent.getStart(), paramComponent.getEnd()));
		}
		example.setOrderWithCreateTime(Constants.SQL_ORDER_DESC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = contactManager.countByExample(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = contactManager.findByExample(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		log.debug("Exit page = {}", page);
		return page;
	}
	
	public Integer count(ContactVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		ContactEntityExample example = new ContactEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createContentsLike(paramComponent.getContent()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createClientIdEquals(paramComponent.getClient().getId()))
			.addCriterion(example.createAdminIdEquals(paramComponent.getAdmin().getId()))
			.addCriterion(example.createCreationDateBetween(paramComponent.getStart(), paramComponent.getEnd()));
		}
		
		Integer count = contactManager.countByExample(example);
		log.debug("Exit page = {}", count);
		return count;
	}
	
	public ContactEntity getDetail(ContactVo entity){
		log.debug("Enter criteria = {}", entity);
		ContactEntity detail = contactManager.get(entity.getId());
		log.debug("Exit page = {}", detail);
		return detail;
	}

}
