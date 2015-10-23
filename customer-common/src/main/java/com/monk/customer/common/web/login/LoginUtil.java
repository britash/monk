package com.monk.customer.common.web.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.monk.customer.common.web.vo.UserVo;

public class LoginUtil {
	private static final String sessionKey = "login_user";
	public static UserVo getLoginUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object user = session.getAttribute(sessionKey);
		if(user != null){
			return (UserVo)user;
		}
		return null;
	}
	
	public static void login(HttpServletRequest req,UserVo user) {
		HttpSession session = req.getSession();
		session.setAttribute(sessionKey, user);
		//十分钟过期
		session.setMaxInactiveInterval(60);
	}
	
	public static void logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute(sessionKey);
	}
}