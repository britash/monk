package com.monk.customer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.vo.MessageVo;
import com.monk.customer.service.dal.entity.MessageEntity;
import com.monk.customer.service.dal.entity.MessageEntityExample;
import com.monk.customer.service.dal.manager.MessageManager;

@Service
public class MessageService {
	private static final Logger log = LoggerFactory.getLogger(MessageService.class);

	@Resource
	private MessageManager<MessageEntity, MessageEntityExample> messageManager;
	
	public Integer save(MessageEntity entity){
		return this.messageManager.save(entity);
	}
	
	public Integer update(MessageEntity entity){
		return this.messageManager.update(entity);
	}
	
	public PageVo<MessageEntity> find(MessageVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		PageVo<MessageEntity> page = new PageVo<>();
		List<MessageEntity> list = new ArrayList<>();

		MessageEntityExample example = new MessageEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr()
			.addCriterion(example.createContentLike(paramComponent.getContent()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createSubjectLike(paramComponent.getSubject()))
			.addCriterion(example.createFromOrToEquals(paramComponent.getFrom().getId(), paramComponent.getTo().getId()))
			.addCriterion(example.createSendTimeBetween(paramComponent.getStart(), paramComponent.getEnd()));
		}
		example.setOrderWithSendTime(Constants.SQL_ORDER_DESC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = messageManager.countByExample(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = messageManager.findByExample(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		log.debug("Exit page = {}", page);
		return page;
	}
	
	public Integer count(MessageVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		MessageEntityExample example = new MessageEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createContentLike(paramComponent.getContent()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createSubjectLike(paramComponent.getSubject()))
			.addCriterion(example.createFromEquals(paramComponent.getFrom().getId()))
			.addCriterion(example.createSendTimeBetween(paramComponent.getStart(), paramComponent.getEnd()));
		}
		
		Integer count = messageManager.countByExample(example);
		log.debug("Exit page = {}", count);
		return count;
	}
	
	public MessageEntity getDetail(MessageVo entity){
		log.debug("Enter criteria = {}", entity);
		MessageEntity detail = messageManager.get(entity.getId());
		log.debug("Exit page = {}", detail);
		return detail;
	}

}
