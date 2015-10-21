package com.monk.customer.service.dal.entity;


import org.springframework.util.StringUtils;

import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class UserEntityExample extends CriteriaOr{

        public Criterion createUserNameEquals(String userName) {
        	if(!StringUtils.isEmpty(userName)){
        		return new Criterion("a.username =", userName);
        	}
        	return null;
        }
        
        public Criterion createEmailEquals(String email) {
        	if(!StringUtils.isEmpty(email)){
        		return new Criterion("a.email =", email);
        	}
        	return null;
        }
        
        public Criterion createStatusEquals(Integer status) {
        	if(!StringUtils.isEmpty(status)){
        		return new Criterion("a.status =", status);
        	}
        	return null;
        }
        
        public Criterion createTypeEquals(Integer type) {
        	if(!StringUtils.isEmpty(type)){
        		return new Criterion("a.type =", type);
        	}
        	return null;
        }
        
        public void setOrderWithRegisterTime(String order){
        	if(!StringUtils.isEmpty(order)){
        		this.orderByClause = "register_time " + order; 
        	}else{
        		this.orderByClause = "register_time desc";
        	}
        }
        
}