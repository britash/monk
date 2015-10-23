package com.monk.customer.common.constants;

public class Constants {

	public static final String SQL_ORDER_ASC = "asc";
	public static final String SQL_ORDER_DESC = "desc";

	public static final String DATAFORMAT = "yyyy-MM-dd HH:mm:ss";

	//response code and msg
	public static final String RES_SUCC_CODE = "0";
	public static final String RES_SUCC_MSG = "Success";
	public static final String RES_ERROR_CODE_UNKNOWN_ERROR = "101";
	public static final String RES_ERROR_MSG_UNKNOWN_ERROR = "An unknown error has occurred";
	public static final String RES_ERROR_CODE_INVALID_ERROR = "102";
	public static final String RES_ERROR_MSG_INVALID_ERROR = "Parameter value is invalid";
	public static final String RES_ERROR_CODE_NO_LOGIN = "200";
	public static final String RES_ERROR_MSG_NO_LOGIN = "not login";
	public static final String RES_ERROR_CODE_USER_EXIST = "201";
	public static final String RES_ERROR_MSG_USER_EXIST = "username exist";
	public static final String RES_ERROR_CODE_NO_PRIVILEGE = "500";
	public static final String RES_ERROR_MSG_NO_PRIVILEGE = "current user is not authrized for resource";
	
	
	
	public static final int STATUS_CREATE = 0;
	public static final int STATUS_READ = 1;
	
	
	public static final int USER_STATUS_NORMAL = 0;
	public static final int USER_STATUS_FORBID = 1;
	public static final int USER_TYPE_ADMIN = 1;
	public static final int USER_TYPE_CUSTOMER = 2;
	
	
}
