package com.monk.customer.admin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.monk.customer.common.constants.Constants;
import com.monk.customer.common.util.IpUtils;
import com.monk.customer.common.web.login.LoginUtil;
import com.monk.customer.common.web.vo.UserVo;



public class BaseController {
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(
				Constants.DATAFORMAT), true));
	}
	
	private String escapeString(String str){
		return str;
	}
	
	public String logAndEscapeResult(String result, HttpServletRequest request, Logger log) {
		UserVo user = LoginUtil.getLoginUser(request);
		String loginId = user.getUserName();
		String uri = request.getRequestURI();
		String ip = IpUtils.getIp(request);
		// TODO get header from request
		String header = "header";
		String params = getParameters(request);
		log.info("{},{},{},{},{}", new Object[] { uri, ip, loginId, header, params });
		result = escapeString(result);
		log.debug("result = {}", result);
		return result;
	}
	
	public String getParameters(HttpServletRequest request) {
		String method = request.getMethod();
		if (method.equalsIgnoreCase("GET")) {
			return request.getQueryString();
		} else if (method.equalsIgnoreCase("POST")) {
			Map<String, String[]> map = request.getParameterMap();
			StringBuilder builder = new StringBuilder();
			for (String paramName : map.keySet()) {
				builder.append(paramName);
				builder.append("=");
				String[] paramValues = request.getParameterValues(paramName);
				builder.append("[").append(Arrays.toString(paramValues)).append("]&");
			}
			String paramPost = builder.toString();
			if (paramPost.endsWith("&")) {
				paramPost = paramPost.substring(0, paramPost.length() - 1);
			}
			return paramPost;
		}
		return "Not support method: " + method;
	}
}
