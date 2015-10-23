package com.monk.customer.service.dal.entity;


import org.springframework.util.StringUtils;

import com.monk.customer.common.util.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class ProjectEntityExample  extends CriteriaOr{
	
	 public Criterion createNameLike(String name) {
     	if(!StringUtils.isEmpty(name)){
     		return new Criterion("a.name like", "%"+ EscapeUtils.escapeSql(name)+"%");
     	}
     	return null;
     }

	public Criterion createFactoryIdEquals(Long factoryId) {
		if (null != factoryId) {
			return new Criterion("a.factory_id =", factoryId);
		}
		return null;
	}
		
	 public void setOrderWithCreateTime(String order){
     	if(!StringUtils.isEmpty(order)){
     		this.orderByClause = "create_time " + order; 
     	}else{
     		this.orderByClause = "create_time asc";
     	}
     }
     
	
}
