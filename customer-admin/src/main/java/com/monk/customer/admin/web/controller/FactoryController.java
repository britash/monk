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
import com.monk.customer.common.web.vo.FactoryVo;
import com.monk.customer.service.FactoryService;
import com.monk.customer.service.dal.entity.FactoryEntity;

@RestController
public class FactoryController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(FactoryController.class);

	@Autowired
	private FactoryService factoryService;
	
	//admin
	@RequestMapping(value = "/factory/list")
	public String list(@Valid FactoryVo factoryVo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", factoryVo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					factoryVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		try {
			PageVo<FactoryEntity> page = factoryService.find(factoryVo);
			result.setData(page);
		} catch (Exception e) {
			log.error("Exception in list()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/factory/update")
	public String update(FactoryEntity  entity, BindingResult error, HttpServletRequest request) {
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
			factoryService.update(entity);
		} catch (Exception e) {
			log.error("Exception in update()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/factory/save")
	public String save(FactoryEntity vo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", vo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					vo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		vo.setCreateTime(new Date());
		vo.setStatus(Constants.STATUS_CREATE);
		try {
			factoryService.save(vo);
		} catch (Exception e) {
			log.error("Exception in save()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
}
