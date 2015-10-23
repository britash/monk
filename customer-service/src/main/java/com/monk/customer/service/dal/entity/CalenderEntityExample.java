package com.monk.customer.service.dal.entity;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.monk.customer.common.util.EscapeUtils;
import com.monk.customer.service.dal.query.CriteriaOr;
import com.monk.customer.service.dal.query.Criterion;

public class CalenderEntityExample extends CriteriaOr {

	public Criterion createTitleLike(String title) {
		if (!StringUtils.isEmpty(title)) {
			return new Criterion("a.title like", "%" + EscapeUtils.escapeSql(title) + "%");
		}
		return null;
	}

	public Criterion createStartTimeGreatAndEquals(Date startTime) {
		if (startTime != null) {
			return new Criterion("a.start_time >=", startTime);
		}
		return null;
	}

	public Criterion createStatusEquals(Integer status) {
		if (status != null) {
			return new Criterion("a.status =", status);
		}
		return null;
	}
	
	public Criterion createAdminEquals(Integer adminId) {
		if (adminId != null) {
			return new Criterion("a.admin_id =", adminId);
		}
		return null;
	}
	
	public Criterion createClientEquals(Integer clientId) {
		if (clientId != null) {
			return new Criterion("a.client_id =", clientId);
		}
		return null;
	}

	public void setOrderWithStartTime(String order) {
		if (!StringUtils.isEmpty(order)) {
			this.orderByClause = "start_time " + order;
		} else {
			this.orderByClause = "start_time desc";
		}
	}

}
