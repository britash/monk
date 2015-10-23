package com.monk.customer.service.dal.entity;


import org.springframework.util.StringUtils;

import com.monk.customer.common.util.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class FactoryEntityExample extends CriteriaOr{
	
	public Criterion createNameEquals(String name) {
     	if(!StringUtils.isEmpty(name)){
     		return new Criterion("a.name =", name);
     	}
     	return null;
     }
	 public Criterion createNameLike(String name) {
	     	if(!StringUtils.isEmpty(name)){
	     		return new Criterion("a.name like", "%"+ EscapeUtils.escapeSql(name)+"%");
	     	}
	     	return null;
	 }

	public Criterion createStatusEquals(Integer status) {
		if (status != null) {
			return new Criterion("a.status =", status);
		}
		return null;
	}
		
	public Criterion createClientIdEquals(Long clientId) {
		if (clientId != null) {
			return new Criterion("a.client_id =", clientId);
		}
		return null;
	}
	 public void setOrderWithname(String order){
     	if(!StringUtils.isEmpty(order)){
     		this.orderByClause = "name " + order; 
     	}else{
     		this.orderByClause = "name desc";
     	}
     }
}
