package com.monk.customer.service.dal.entity;

import org.springframework.util.StringUtils;

import com.monk.customer.common.EscapeUtils;
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
	
	public Criterion createFromEquals(Integer from) {
		if (from != null) {
			return new Criterion("a.from =", from);
		}
		return null;
	}
	
	public Criterion createToEquals(Integer to) {
		if (to != null) {
			return new Criterion("a.to =", to);
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
