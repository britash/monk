package com.monk.customer.service.dal.entity;

import java.util.Date;

public class MessageEntity extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;// ID
	private Date sendTime;//send_time;
	private Date receiveTime;//receive_time;
	private Integer status;
	private String subject;
	private String content;
	private String attachment;
	
	private UserEntity from;
	private UserEntity to;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public UserEntity getFrom() {
		return from;
	}
	public void setFrom(UserEntity from) {
		this.from = from;
	}
	public UserEntity getTo() {
		return to;
	}
	public void setTo(UserEntity to) {
		this.to = to;
	}

	
}
