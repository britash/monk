package com.monk.customer.service.login;

import static com.monk.customer.common.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.monk.customer.common.util.JsonResult;
import com.monk.customer.common.web.login.LoginUtil;

/**
* This class should preserve.
* @preserve
*/
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	  private String loginUrl = "";
	  private static final String[] IGNORE_URI = {"/login.jsp", "/login","js/","css/"};

		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			boolean flag = false;
			String url = request.getRequestURL().toString();
			for (String s : IGNORE_URI) {
				if (url.contains(s)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				flag = LoginInterceptor.volidate(request);
				if(!flag){
					JsonResult result = new JsonResult(RES_ERROR_CODE_NO_LOGIN, RES_ERROR_MSG_NO_LOGIN, null);
					response.getWriter().write(result.toJsonString());
				}
			}
			return flag;
		}
		
		public static boolean volidate(HttpServletRequest request){
			if(LoginUtil.getLoginUser(request) != null){
				return true;
			}
			return false;
		}
}
