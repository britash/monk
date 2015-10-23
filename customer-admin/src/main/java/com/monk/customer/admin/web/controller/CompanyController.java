package com.monk.customer.admin.web.controller;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_UNKNOWN_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_UNKNOWN_ERROR;
import static com.monk.customer.common.constants.Constants.RES_SUCC_CODE;
import static com.monk.customer.common.constants.Constants.RES_SUCC_MSG;

import java.util.Date;

import javax.annotation.Resource;
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
import com.monk.customer.common.web.vo.CompanyVo;
import com.monk.customer.service.CompanyService;
import com.monk.customer.service.dal.entity.CompanyEntity;
import com.monk.customer.service.dal.entity.CompanyEntityExample;
import com.monk.customer.service.dal.manager.CompanyManager;

@RestController
public class CompanyController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;
	@Resource
	private CompanyManager<CompanyEntity, CompanyEntityExample> companyManager;
	
	//admin
	@RequestMapping(value = "/company/list")
	public String list(@Valid CompanyVo companyVo, BindingResult error, HttpServletRequest request) {
		log.debug("Enter criteria[{}]", companyVo);
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if (error.hasErrors()) {
			log.error("result.getFieldErrors() = {}", error.getFieldErrors());
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
					companyVo);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		companyVo.setUser(LoginUtil.getLoginUser(request));
		try {
			PageVo<CompanyEntity> page = companyService.find(companyVo);
			result.setData(page);
		} catch (Exception e) {
			log.error("Exception in list()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/company/update")
	public String update(CompanyEntity  entity, BindingResult error, HttpServletRequest request) {
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
			companyService.update(entity);
		} catch (Exception e) {
			log.error("Exception in update()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}

		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping(value = "/company/save")
	public String save(CompanyEntity vo, BindingResult error, HttpServletRequest request) {
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
			companyService.save(vo);
		} catch (Exception e) {
			log.error("Exception in save()", e);
			result = new JsonResult(RES_ERROR_CODE_UNKNOWN_ERROR, RES_ERROR_MSG_UNKNOWN_ERROR, null);
		}
		CompanyVo cvo = new CompanyVo();
		cvo.setName(vo.getName());

		PageVo<CompanyEntity> page = companyService.find(cvo);
		if(page.getPageData().size() > 0){
			CompanyEntity ce = page.getPageData().get(0);
			companyManager.insertUser(LoginUtil.getLoginUser(request).getId(), ce.getId());
		}
		
		log.debug("Exit result = {}", result);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	
}
