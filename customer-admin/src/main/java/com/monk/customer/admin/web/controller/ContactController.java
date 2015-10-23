package com.monk.customer.admin.web.controller;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_NO_PRIVILEGE;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_UNKNOWN_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_NO_PRIVILEGE;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_UNKNOWN_ERROR;
import static com.monk.customer.common.constants.Constants.RES_SUCC_CODE;
import static com.monk.customer.common.constants.Constants.RES_SUCC_MSG;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.JsonResult;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.login.LoginUtil;
import com.monk.customer.common.web.vo.ContactVo;
import com.monk.customer.common.web.vo.UserVo;
import com.monk.customer.service.ContactService;
import com.monk.customer.service.dal.entity.ContactEntity;
import com.monk.customer.service.dal.entity.UserEntity;

@RestController
public class ContactController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = "/contact/list")
	public String list(@Valid ContactVo contactVo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", contactVo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					contactVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		contactVo.setAdmin(LoginUtil.getLoginUser(request));
		try {
			
			PageVo<ContactEntity> page = contactService.find(contactVo);
			result.setData(page);
		} catch (Exception e) {
			log.error("Exception in list()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/contact/update")
	public String update(ContactVo contactVo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", contactVo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					contactVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		contactVo.setAdmin(LoginUtil.getLoginUser(request));
		
		
		boolean check = checkIfAdminCanAccessContact(contactVo,LoginUtil.getLoginUser(request));
		
		if(!check){
			result = new JsonResult(RES_ERROR_CODE_NO_PRIVILEGE, RES_ERROR_MSG_NO_PRIVILEGE,
					contactVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		try {
			ContactEntity  entity = new ContactEntity();
			entity.setId(contactVo.getId());
			entity.setStatus(contactVo.getStatus());
			entity.setUpdateTime(new Date());
			contactService.update(entity);
		} catch (Exception e) {
			log.error("Exception in update()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/contact/save")
	public String save(ContactEntity vo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", vo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		UserEntity ue = new UserEntity();
		ue.setId(LoginUtil.getLoginUser(request).getId());
		vo.setClient(ue);
		vo.setStatus(Constants.STATUS_CREATE);
		vo.setCreateTime(new Date());
		try {
			contactService.save(vo);
		} catch (Exception e) {
			log.error("Exception in save()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/contact/get")
	public String get(ContactVo contactVo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", contactVo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					contactVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		contactVo.setAdmin(LoginUtil.getLoginUser(request));
		
		boolean check = checkIfAdminCanAccessContact(contactVo,LoginUtil.getLoginUser(request));
		
		if(!check){
			result = new JsonResult(RES_ERROR_CODE_NO_PRIVILEGE, RES_ERROR_MSG_NO_PRIVILEGE,
					contactVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		try {
			ContactEntity contact = contactService.getDetail(contactVo);
			result.setData(contact);
		} catch (Exception e) {
			log.error("Exception in get()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	
	private boolean checkIfAdminCanAccessContact(ContactVo contactVo,UserVo amdin){
		ContactVo vo = new ContactVo();
		vo.setId(contactVo.getId());
		vo.setAdmin(amdin);
		Integer num = contactService.count(vo);
		return num > 0;
	}
}
