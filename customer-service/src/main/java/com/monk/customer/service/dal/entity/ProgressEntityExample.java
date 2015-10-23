package com.monk.customer.service.dal.entity;


import org.springframework.util.StringUtils;

import com.monk.customer.common.util.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class ProgressEntityExample  extends CriteriaOr{
	
	 public Criterion createNameLike(String name) {
     	if(!StringUtils.isEmpty(name)){
     		return new Criterion("a.name like","%"+ EscapeUtils.escapeSql(name)+"%");
     	}
     	return null;
     }

	public Criterion createShortNameLike(String shortName) {
		if (!StringUtils.isEmpty(shortName)) {
			return new Criterion("a.short_name =", "%"+ EscapeUtils.escapeSql(shortName)+"%");
		}
		return null;
	}
		
	 public void setOrderWithPrority(String order){
     	if(!StringUtils.isEmpty(order)){
     		this.orderByClause = "priority " + order; 
     	}else{
     		this.orderByClause = "priority asc";
     	}
     }
     
	
}
