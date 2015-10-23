package com.monk.customer.service.dal.entity;


import org.springframework.util.StringUtils;

import com.monk.customer.common.util.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class CompanyEntityExample  extends CriteriaOr{
	
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
		
	public Criterion createUserIdEquals(Long userId) {
		if (userId != null) {
			return new Criterion("b.user_id =", userId);
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
