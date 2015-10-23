package com.monk.customer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.vo.ProjectVo;
import com.monk.customer.service.dal.entity.ProjectEntity;
import com.monk.customer.service.dal.entity.ProjectEntityExample;
import com.monk.customer.service.dal.manager.ProjectManager;

@Service
public class ProjectService {
	private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

	@Resource
	private ProjectManager<ProjectEntity, ProjectEntityExample> projectManager;
	
	public Integer save(ProjectEntity entity){
		return this.projectManager.save(entity);
	}
	
	public Integer update(ProjectEntity entity){
		return this.projectManager.update(entity);
	}
	
	public Integer delete(Long id){
		return this.projectManager.delete(id);
	}
	
	public PageVo<ProjectEntity> find(ProjectVo paramComponent){
		log.debug("Enter criteria = {}", paramComponent);

		PageVo<ProjectEntity> page = new PageVo<>();
		List<ProjectEntity> list = new ArrayList<>();

		ProjectEntityExample example = new ProjectEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createNameLike(paramComponent.getName()))
			.addCriterion(example.createFactoryIdEquals(paramComponent.getFactory().getId()))
			.addCriterion(example.createNameLike(paramComponent.getName()));
		}
		
		example.setOrderWithCreateTime(Constants.SQL_ORDER_ASC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = projectManager.countByExample(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = projectManager.findByExample(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		log.debug("Exit page = {}", page);
		return page;
	}
	
}
