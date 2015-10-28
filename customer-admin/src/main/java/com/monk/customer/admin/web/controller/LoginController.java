package com.monk.customer.admin.web.controller;

import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_CODE_USER_EXIST;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_INVALID_ERROR;
import static com.monk.customer.common.constants.Constants.RES_ERROR_MSG_USER_EXIST;
import static com.monk.customer.common.constants.Constants.RES_SUCC_CODE;
import static com.monk.customer.common.constants.Constants.RES_SUCC_MSG;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.CheckStyleUtil;
import com.monk.customer.common.util.IpUtils;
import com.monk.customer.common.util.JsonResult;
import com.monk.customer.common.util.Md5Util;
import com.monk.customer.common.web.login.LoginUtil;
import com.monk.customer.common.web.vo.UserVo;
import com.monk.customer.service.dal.entity.CompanyEntity;
import com.monk.customer.service.dal.entity.CompanyEntityExample;
import com.monk.customer.service.dal.entity.UserEntity;
import com.monk.customer.service.dal.entity.UserEntityExample;
import com.monk.customer.service.dal.manager.CompanyManager;
import com.monk.customer.service.dal.manager.UserManager;

@RestController
public class LoginController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserManager<UserEntity, UserEntityExample> usermanager;
	
	@Autowired
	private CompanyManager<CompanyEntity, CompanyEntityExample> companyManager;
	
	@RequestMapping("/login/admin")
	public String admin(HttpServletRequest request,HttpServletResponse response,UserEntity loginUser) {
		log.debug("Enter criteria[{}]", loginUser);
		UserEntityExample uee = new UserEntityExample();
		uee.createUserNameEquals(loginUser.getUsername());
		List<UserEntity> userEntityList = usermanager.findByExample(uee);
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if(userEntityList.size() > 0){
			UserEntity existUser = userEntityList.get(0);
			if(existUser != null && existUser.getPassword().equalsIgnoreCase(Md5Util.getMd5str(loginUser.getPassword()))
					&& existUser.getType() == Constants.USER_TYPE_ADMIN
					){
				UserVo user = new UserVo();
				user.setId(existUser.getId());
				user.setUserName(existUser.getUsername());
				user.setEmail(existUser.getEmail());
				user.setName(existUser.getName());
				user.setPhone(existUser.getTelephone());
				LoginUtil.login(request, user);
				log.debug("Exit result = {}", result);
				return logAndEscapeResult(result.toJsonString(), request, log);
			}
		}
		result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
				loginUser);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping("/login/customer")
	public String customer(HttpServletRequest request,HttpServletResponse response,UserEntity loginUser) {
		log.debug("Enter criteria[{}]", loginUser);
		
		//加账号封禁检验 todo
		
		UserEntityExample uee = new UserEntityExample();
		uee.createUserNameEquals(loginUser.getUsername());
		List<UserEntity> userEntityList = usermanager.findByExample(uee);
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		if(userEntityList.size() > 0){
			UserEntity existUser = userEntityList.get(0);
			if(existUser != null && existUser.getPassword().equalsIgnoreCase(Md5Util.getMd5str(loginUser.getPassword()))
					&& existUser.getType() == Constants.USER_TYPE_CUSTOMER
					){
				UserVo user = new UserVo();
				user.setId(existUser.getId());
				user.setUserName(existUser.getUsername());
				user.setEmail(existUser.getEmail());
				user.setName(existUser.getName());
				user.setPhone(existUser.getTelephone());
				LoginUtil.login(request, user);
				log.debug("Exit result = {}", result);
				return logAndEscapeResult(result.toJsonString(), request, log);
			}
		}
		result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR,
				loginUser);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		log.debug("Enter criteria[{}]", "");
		
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		LoginUtil.logout(request);
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
	
	@RequestMapping("/register/customer")
	public String registerAdmin(HttpServletRequest request,HttpServletResponse response, UserEntity loginUser) {
		log.debug("Enter criteria[{}]", loginUser);
		JsonResult result = new JsonResult(RES_SUCC_CODE, RES_SUCC_MSG, null);
		
		if(loginUser.getUsername() == null){
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, loginUser);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		if(loginUser.getName() == null){
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, loginUser);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		if(loginUser.getEmail() == null){
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, loginUser);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		//^[A-Za-z][A-Za-z0-9]{5,31}$
		if(CheckStyleUtil.checkStyle(loginUser.getUsername(), CheckStyleUtil.Style.PATTERN_SKYPE)){
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, loginUser);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		if(loginUser.getPassword() == null){
			result = new JsonResult(RES_ERROR_CODE_INVALID_ERROR, RES_ERROR_MSG_INVALID_ERROR, loginUser);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		UserEntityExample uee = new UserEntityExample();
		uee.createUserNameEquals(loginUser.getUsername());
		List<UserEntity> userEntityList = usermanager.findByExample(uee);
		if(userEntityList.size() > 0){
			result = new JsonResult(RES_ERROR_CODE_USER_EXIST, RES_ERROR_MSG_USER_EXIST, loginUser);
			return logAndEscapeResult(result.toJsonString(), request, log);
		}
		
		CompanyEntityExample cee = new CompanyEntityExample();
		cee.createNameEquals(loginUser.getOtherCompany());
		List<CompanyEntity> clist = this.companyManager.findByExample(cee);
		CompanyEntity company = null;
		if(clist.size() > 0){
			company = clist.get(0);
		}
		
		loginUser.setType(Constants.USER_TYPE_CUSTOMER);
		loginUser.setStatus(Constants.USER_STATUS_NORMAL);
		loginUser.setRegisterTime(new Date());
		loginUser.setRegisterIp(IpUtils.getIp(request));
		if(company != null){
			loginUser.setOtherCompany("");
		}
		usermanager.save(loginUser);
		if(company != null){
			uee.createUserNameEquals(loginUser.getUsername());
			List<UserEntity> userList = usermanager.findByExample(uee);
			if(userEntityList.size() > 0){
				companyManager.insertUser(userList.get(0).getId(), company.getId());
			}
		}
		return logAndEscapeResult(result.toJsonString(), request, log);
	}
}
