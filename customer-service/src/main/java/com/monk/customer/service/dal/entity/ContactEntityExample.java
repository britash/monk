package com.monk.customer.service.dal.entity;


import org.springframework.util.StringUtils;

import com.monk.customer.common.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class ContactEntityExample  extends CriteriaOr{
	
	 public Criterion createContentsLike(String contents) {
     	if(!StringUtils.isEmpty(contents)){
     		return new Criterion("a.contents like", "%" + EscapeUtils.escapeSql(contents) + "%");
     	}
     	return null;
     }

	public Criterion createStatusEquals(Integer status) {
		if (status != null) {
			return new Criterion("a.status =", status);
		}
		return null;
	}
		
	public Criterion createClientIdEquals(Integer clientId) {
		if (clientId != null) {
			return new Criterion("a.client_id =", clientId);
		}
		return null;
	}
	 public void setOrderWithCreateTime(String order){
     	if(!StringUtils.isEmpty(order)){
     		this.orderByClause = "create_time " + order; 
     	}else{
     		this.orderByClause = "create_time desc";
     	}
     }
     
	
}
