package com.monk.customer.service.dal.entity;


import java.util.Date;

import org.springframework.util.StringUtils;

import com.monk.customer.common.util.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class ContactEntityExample  extends CriteriaOr{
	
	 public Criterion createContentsLike(String contents) {
     	if(!StringUtils.isEmpty(contents)){
     		return new Criterion("a.content like", "%" + EscapeUtils.escapeSql(contents) + "%");
     	}
     	return null;
     }

	public Criterion createStatusEquals(Integer status) {
		if (status != null) {
			return new Criterion("a.status =", status);
		}
		return null;
	}
	
	public Criterion createIdEquals(Long id) {
		if (id != null) {
			return new Criterion("a.id =", id);
		}
		return null;
	}
		
	public Criterion createClientIdEquals(Long clientId) {
		if (clientId != null) {
			return new Criterion("a.client_id =", clientId);
		}
		return null;
	}
	//from   contact a, user b, user_company c,user_company d
	public Criterion createAdminIdEquals(Long adminId) {
		
		if (adminId != null) {
			return new Criterion( "c.user_id =" + adminId + " and c.company_id = d.company_id and d.user_id = a.client_id and d.user_id != " + adminId);
		}
		return null;
		
	}
	 public Criterion createCreationDateBetween(Date value1, Date value2) {
     	if(!StringUtils.isEmpty(value1)  &&  !StringUtils.isEmpty(value2)){
     		return new Criterion("a.create_time between", value1, value2);
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
