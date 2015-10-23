package com.monk.customer.service.dal.entity;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.monk.customer.common.util.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class MessageEntityExample extends CriteriaOr {

	public Criterion createSubjectLike(String subject) {
		if (!StringUtils.isEmpty(subject)) {
			return new Criterion("a.subject like", "%" + EscapeUtils.escapeSql(subject) + "%");
		}
		return null;
	}

	public Criterion createContentLike(String content) {
		if (!StringUtils.isEmpty(content)) {
			return new Criterion("a.content =", "%" + EscapeUtils.escapeSql(content) + "%");
		}
		return null;
	}

	public Criterion createStatusEquals(Integer status) {
		if (status != null) {
			return new Criterion("a.status =", status);
		}
		return null;
	}
	
	public Criterion createFromOrToEquals(Long from,Long to) {
		StringBuilder q = new StringBuilder();
		if(from != null && to != null){
			q.append("(a.from =").append(from)
			.append(" or ")
			.append("a.to = ").append(to)
			.append("");
		}
		return new Criterion(q.toString() );
	}
	
	public Criterion createFromEquals(Long from) {
		if (from != null) {
			return new Criterion("a.from =", from);
		}
		return null;
	}
	
	public Criterion createToEquals(Long to) {
		if (to != null) {
			return new Criterion("a.to =", to);
		}
		return null;
	}
	 public Criterion createSendTimeBetween(Date value1, Date value2) {
	     	if(!StringUtils.isEmpty(value1)  &&  !StringUtils.isEmpty(value2)){
	     		return new Criterion("a.send_time between", value1, value2);
	     	}
	     	return null;
			}
	public void setOrderWithSendTime(String order) {
		if (!StringUtils.isEmpty(order)) {
			this.orderByClause = "send_time " + order;
		} else {
			this.orderByClause = "send_time desc";
		}
	}

}
