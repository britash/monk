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
import com.monk.customer.common.web.vo.MessageVo;
import com.monk.customer.common.web.vo.UserVo;
import com.monk.customer.service.MessageService;
import com.monk.customer.service.dal.entity.MessageEntity;
import com.monk.customer.service.dal.entity.UserEntity;

@RestController
public class MessageController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/message/listSend")
	public String listSend(@Valid MessageVo vo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", vo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		UserVo user = LoginUtil.getLoginUser(request);
		
		vo.setFrom(user);
		try {
			
			PageVo<MessageEntity> page = messageService.find(vo);
			result.setData(page);
		} catch (Exception e) {
			log.error("Exception in listSend()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/message/listReceive")
	public String listReceive(@Valid MessageVo vo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", vo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		UserVo user = LoginUtil.getLoginUser(request);
		vo.setTo(user);
		try {
			
			PageVo<MessageEntity> page = messageService.find(vo);
			result.setData(page);
		} catch (Exception e) {
			log.error("Exception in listReceive()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/message/update")
	public String update(MessageVo vo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", vo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		boolean check = checkIfCanAccessContact(vo,LoginUtil.getLoginUser(request));
		
		if(!check){
			result = new JsonResult(RES_ERROR_CODE_NO_PRIVILEGE, RES_ERROR_MSG_NO_PRIVILEGE,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		try {
			MessageEntity  entity = new MessageEntity();
			entity.setStatus(Constants.STATUS_READ);
			entity.setReceiveTime(new Date());
			entity.setId(vo.getId());
			messageService.update(entity);
		} catch (Exception e) {
			log.error("Exception in update()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/message/send")
	public String send(MessageEntity vo, BindingResult error, HttpServletRequest request) {
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
		vo.setFrom(ue);
		vo.setSendTime(new Date());
		vo.setStatus(Constants.STATUS_CREATE);
		try {
			messageService.save(vo);
		} catch (Exception e) {
			log.error("Exception in send()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/message/get")
	public String get(MessageVo vo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", vo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		UserVo admin  =  LoginUtil.getLoginUser(request);
		
		try {
			MessageEntity entity = messageService.getDetail(vo);
			if(entity == null || (entity.getTo().getId() != admin.getId().longValue() && entity.getFrom().getId() != admin.getId().longValue())){
				result = new JsonResult(RES_ERROR_CODE_NO_PRIVILEGE, RES_ERROR_MSG_NO_PRIVILEGE,
						vo);
				return logAndEscapeResult(result.toJsonString(), request, log);
			}
			
			result.setData(entity);
		} catch (Exception e) {
			log.error("Exception in get()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	private boolean checkIfCanAccessContact(MessageVo messageVo,UserVo admin){
		MessageEntity entity = messageService.getDetail(messageVo);
		if(entity == null || (entity.getTo().getId() != admin.getId().longValue() )){
			return false;
		}
		return true;
	}
}
