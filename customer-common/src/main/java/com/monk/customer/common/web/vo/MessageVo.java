package com.monk.customer.common.web.vo;

public class MessageVo extends ParentParamComponent{

	
	
	private String subject;
	
	private String content;
	
	
	private UserVo from = new UserVo();

	private UserVo to = new UserVo();
	

	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public UserVo getFrom() {
		return from;
	}

	public void setFrom(UserVo from) {
		this.from = from;
	}

	public UserVo getTo() {
		return to;
	}

	public void setTo(UserVo to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
