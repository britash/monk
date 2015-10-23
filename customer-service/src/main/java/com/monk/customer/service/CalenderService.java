package com.monk.customer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.vo.CalenderVo;
import com.monk.customer.service.dal.entity.CalenderEntity;
import com.monk.customer.service.dal.entity.CalenderEntityExample;
import com.monk.customer.service.dal.manager.CalenderManager;

@Service
public class CalenderService {
	private static final Logger log = LoggerFactory.getLogger(CalenderService.class);

	@Resource
	private CalenderManager<CalenderEntity, CalenderEntityExample> calenderManager;
	
	public Integer save(CalenderEntity entity){
		return this.calenderManager.save(entity);
	}
	
	public Integer update(CalenderEntity entity){
		return this.calenderManager.update(entity);
	}
	
	
	public Integer delete(Long id){
		return this.calenderManager.delete(id);
	}
	
	public PageVo<CalenderEntity> findMyCreate(CalenderVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		PageVo<CalenderEntity> page = new PageVo<>();
		List<CalenderEntity> list = new ArrayList<>();

		CalenderEntityExample example = new CalenderEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createTitleLike(paramComponent.getTitle()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createClientEquals(paramComponent.getClient().getId()))
			.addCriterion(example.createStartTimeBetween(paramComponent.getStart(), paramComponent.getEnd()));
		}
		example.setOrderWithStartTime(Constants.SQL_ORDER_ASC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = calenderManager.countByExample(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = calenderManager.findByExample(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		log.debug("Exit page = {}", page);
		return page;
	}
	
	public PageVo<CalenderEntity> findICanRead(CalenderVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		PageVo<CalenderEntity> page = new PageVo<>();
		List<CalenderEntity> list = new ArrayList<>();

		CalenderEntityExample example = new CalenderEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createTitleLike(paramComponent.getTitle()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createAdminEquals(paramComponent.getAdmin().getId()))
			.addCriterion(example.createStartTimeBetween(paramComponent.getStart(), paramComponent.getEnd()));
		}
		
		example.setOrderWithStartTime(Constants.SQL_ORDER_ASC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = calenderManager.countByExample(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = calenderManager.findByExample(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		log.debug("Exit page = {}", page);
		return page;
	}
}
