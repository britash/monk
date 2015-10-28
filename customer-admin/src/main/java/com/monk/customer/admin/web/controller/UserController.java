package com.monk.customer.admin.web.controller;

import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_SUCC_CODE;
import static com.monk.customer.common.constants.Constants.RES_SUCC_MSG;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.JsonResult;
import com.monk.customer.common.util.Md5Util;
import com.monk.customer.common.util.PageVo;
import com.monk.customer.common.web.login.LoginUtil;
import com.monk.customer.common.web.vo.CompanyVo;
import com.monk.customer.common.web.vo.UserVo;
import com.monk.customer.service.CompanyService;
import com.monk.customer.service.dal.entity.CompanyEntity;
import com.monk.customer.service.dal.entity.CompanyEntityExample;
import com.monk.customer.service.dal.entity.UserEntity;
import com.monk.customer.service.dal.entity.UserEntityExample;
import com.monk.customer.service.dal.manager.CompanyManager;
import com.monk.customer.service.dal.manager.UserManager;

@RestController
public class UserController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserManager<UserEntity, UserEntityExample> usermanager;
	
	@Autowired
	private CompanyService companyService;
	@Resource
	private CompanyManager<CompanyEntity, CompanyEntityExample> companyManager;
	/**
	 * companyId
	 * @param paramComponent
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/user/list/customer/")
	public String admin(UserVo paramComponent, HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		PageVo<UserEntity> page = new PageVo<>();
		List<UserEntity> list = new ArrayList<>();
		UserVo loginUser = LoginUtil.getLoginUser(request);
		UserEntityExample example = new UserEntityExample();
		if(paramComponent != null){
			example.addCriteriaOr().addCriterion(example.createUserNameEquals(paramComponent.getUserName()))
			.addCriterion(example.createStatusEquals(paramComponent.getStatus()))
			.addCriterion(example.createAdminIdEquals(loginUser.getId(),paramComponent.getCommpanyId())
			);
		}
		example.setOrderWithRegisterTime(Constants.SQL_ORDER_ASC);
		example.setStart(paramComponent.getPageStart());
		example.setLimit(paramComponent.getPageLimit());
		
		Integer count = usermanager.countCustomerForAdmin(example);
		if (count == 0) {
			page = new PageVo<>(0, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		} else {
			list = usermanager.findCustomerForAdmin(example);
			page = new PageVo<>(count, paramComponent.getPageSize(), paramComponent.getPageNo(), list);
		}
		result.setData(page);
		log.debug("Exit page = {}", page);
		
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	

	@RequestMapping("/user/changePwd")
	public String changePwd(String oldPwd,String newPwd, HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		UserVo loginUser = LoginUtil.getLoginUser(request);
		UserEntity user = usermanager.get(loginUser.getId());
		
		if(user.getPassword().equalsIgnoreCase(Md5Util.getMd5str(oldPwd))){
			user.setPassword(Md5Util.getMd5str(newPwd));
			usermanager.update(user);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, "");
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping("/user/resetCustomerPwd")
	public String resetCustomerPwd(UserVo paramComponent, HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		UserVo loginUser = LoginUtil.getLoginUser(request);
		//需要检查当前管理员是否有修改该账号密码的权限
		//@todo
		
		UserEntity user = usermanager.get(paramComponent.getId());
		
		if(user != null){
			user.setPassword(Md5Util.getMd5str(paramComponent.getPassword()));
			usermanager.update(user);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, "");
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	
	/**
	 * USER_STATUS_NORMAL = 0;
	   USER_STATUS_FORBID = 1;
	 * @param paramComponent
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/user/customer/status")
	public String forbidCustomer(UserVo paramComponent, HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		UserVo loginUser = LoginUtil.getLoginUser(request);
		//需要检查当前管理员是否有修改该账号密码的权限
		//@todo
		
		UserEntity user = usermanager.get(paramComponent.getId());
		
		if(user != null){
			user.setStatus(paramComponent.getStatus());
			usermanager.update(user);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, "");
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping("/user/customer/changeCompany")
	public String changeCompany(UserVo paramComponent, HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		UserVo loginUser = LoginUtil.getLoginUser(request);
		//需要检查当前管理员是否有修改该账号密码的权限
		//@todo
		
		UserVo svo = new UserVo();
		
		CompanyVo cvo = new CompanyVo();
		cvo.setUser(svo );
		List<CompanyEntity> cList = companyService.find(cvo).getPageData();
		CompanyEntity cEntity = cList.get(0);
		companyManager.updateUser(paramComponent.getId(), paramComponent.getCommpanyId(), paramComponent.getId(), cEntity.getId());
		
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping("/user/get")
	public String get( HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		
		UserVo loginUser = LoginUtil.getLoginUser(request);
		
		UserEntity user = usermanager.get(loginUser.getId());
		result.setData(user);
		
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping("/user/update")
	public String update(UserEntity user, HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		
		UserVo loginUser = LoginUtil.getLoginUser(request);
		
		UserEntity existUser = usermanager.get(loginUser.getId());
		if(StringUtils.isNotBlank(user.getEmail())){
			existUser.setEmail(user.getEmail());
		}
		if(StringUtils.isNotBlank(user.getName())){
			existUser.setEmail(user.getName());
		}
		if(StringUtils.isNotBlank(user.getTelephone())){
			existUser.setEmail(user.getTelephone());
		}
		result.setData(user);
		
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
}
