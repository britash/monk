package com.monk.customer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.vo.FactoryVo;
import com.monk.customer.service.dal.entity.FactoryEntity;
import com.monk.customer.service.dal.entity.FactoryEntityExample;
import com.monk.customer.service.dal.manager.FactoryManager;

@Service
public class FactoryService {
	private static final Logger log = LoggerFactory.getLogger(FactoryService.class);

	@Resource
	private FactoryManager<FactoryEntity, FactoryEntityExample> factoryManager;
	
	public Integer save(FactoryEntity entity){
		FactoryEntityExample example = new FactoryEntityExample();
		example.createNameEquals(entity.getName());
		Integer count = factoryManager.countByExample(example);
		if(count > 0){
			return 0;
		}
		return this.factoryManager.save(entity);
	}
	
	public Integer update(FactoryEntity entity){
		return this.factoryManager.update(entity);
	}
	
	public Integer delete(Long id){
		return this.factoryManager.delete(id);
	}
	
	public PageVo<FactoryEntity> find(FactoryVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		PageVo<FactoryEntity> page = new PageVo<>();
		List<FactoryEntity> list = new ArrayList<>();

		FactoryEntityExample example = new FactoryEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createNameLike(paramComponent.getName()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createClientIdEquals(paramComponent.getClient().getId()));
		}
		
		example.setOrderWithname(Constants.SQL_ORDER_ASC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = factoryManager.countByExample(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = factoryManager.findByExample(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		log.debug("Exit page = {}", page);
		return page;
	}
	
}
