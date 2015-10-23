package com.monk.customer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.vo.CompanyVo;
import com.monk.customer.service.dal.entity.CompanyEntity;
import com.monk.customer.service.dal.entity.CompanyEntityExample;
import com.monk.customer.service.dal.manager.CompanyManager;

@Service
public class CompanyService {
	private static final Logger log = LoggerFactory.getLogger(CompanyService.class);

	@Resource
	private CompanyManager<CompanyEntity, CompanyEntityExample> companyManager;
	
	public Integer save(CompanyEntity entity){
		CompanyEntityExample example = new CompanyEntityExample();
		example.createNameEquals(entity.getName());
		Integer count = companyManager.countByExample(example);
		if(count > 0){
			return 0;
		}
		return this.companyManager.save(entity);
	}
	
	public Integer update(CompanyEntity entity){
		return this.companyManager.update(entity);
	}
	
	public Integer delete(Long id){
		return this.companyManager.delete(id);
	}
	
	public PageVo<CompanyEntity> find(CompanyVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		PageVo<CompanyEntity> page = new PageVo<>();
		List<CompanyEntity> list = new ArrayList<>();

		CompanyEntityExample example = new CompanyEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createNameLike(paramComponent.getName()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createUserIdEquals(paramComponent.getUser().getId()));
		}
		
		example.setOrderWithname(Constants.SQL_ORDER_ASC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = companyManager.countByExample(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = companyManager.findByExample(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		log.debug("Exit page = {}", page);
		return page;
	}
	
}
