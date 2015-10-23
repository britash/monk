package com.monk.customer.admin.web.controller;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_UNKNOWN_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_INVALID_ERROR;
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
import com.monk.customer.common.web.vo.CalenderVo;
import com.monk.customer.service.CalenderService;
import com.monk.customer.service.dal.entity.CalenderEntity;
import com.monk.customer.service.dal.entity.UserEntity;

@RestController
public class CalenderController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(CalenderController.class);

	@Autowired
	private CalenderService calenderService;
	
	@RequestMapping(value = "/calender/myCreate")
	public String myCreate(@Valid CalenderVo calenderVo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", calenderVo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					calenderVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		calenderVo.setClient(LoginUtil.getLoginUser(request));
		try {
			
			PageVo<CalenderEntity> page = calenderService.findMyCreate(calenderVo);
			result.setData(page);
		} catch (Exception e) {
			log.error("Exception in list()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	

	@RequestMapping(value = "/calender/canRead")
	public String canRead(@Valid CalenderVo calenderVo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", calenderVo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					calenderVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		calenderVo.setAdmin(LoginUtil.getLoginUser(request));
		try {
			
			PageVo<CalenderEntity> page = calenderService.findICanRead(calenderVo);
			result.setData(page);
		} catch (Exception e) {
			log.error("Exception in list()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/calender/update")
	public String update(CalenderEntity  entity, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", entity);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					entity);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		if(entity.getId() == null){
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					entity);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		//need add check if user can update @todo
		try {
			calenderService.update(entity);
		} catch (Exception e) {
			log.error("Exception in update()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/calender/save")
	public String save(CalenderEntity vo, BindingResult error, HttpServletRequest request) {
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
			calenderService.save(vo);
		} catch (Exception e) {
			log.error("Exception in save()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	

	@RequestMapping(value = "/calender/delete")
	public String delete(CalenderVo vo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", vo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		//need add check if user can delete @todo
		
		try {
			calenderService.delete(vo.getId());
		} catch (Exception e) {
			log.error("Exception in save()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}
		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
}
