/**
 * copy right 2012 sctiyi all rights reserved
 * create time:下午03:08:21
 * author:ftd
 */
package com.security.manage.util;


/**
 * @author ftd
 *
 */
public class Constants {

	/**
	 * 系统默认管理员账户
	 */
	public static final String ADMINISTRATOR_ACCOUNT="admin";
	
	public static final String USER_SESSION_NAME = "userInfo";
	public static final String USER_SESSION_FUNCTION = "userFunctions";
	public static final String CURRENT_MENU_ID = "__currentMenuId";
	
	public static final String THE_REALM_NAME="userRealm";
	public final static  String USER_INFO = "USER_INFO";
	
	public static final int IMAGE_RESIZE_WIDTH = 150;
	public static final int IMAGE_RESIZE_HEIGHT = 150;

	public static final int DEFAULT_PAGE_SIZE = 10; 
	public static final Integer API_RESULT_SUCCESS = 0;
	public static final Integer API_RESULT_FAILURE = 1;
	public static final Integer API_RESULT_TIMEOUT = 95;
	public static final Integer API_RESULT_SUBMIT_DUPLICATE = 96;
	public static final Integer API_RESULT_PARAMTER_ERROR = 97;
	public static final Integer API_RESULT_TOKEN_ERROR = 98;
	public static final Integer API_RESULT_ORTHER_ERROR = 99; 
	public static final Integer STATUS_ENABLE = 0;
	public static final Integer STATUS_DISABLE = 1; 
	public static final Integer LATEST_TIME =10;
	public static final String INNER_ERROR_MESSAGE ="内部方法调用异常！";
	public static final String PARAM_ERROR_MESSAGE ="参数传递错误！";
	public static final String LOAD_OK_MESSAGE ="获取数据成功！";
	public static final String LOAD_FAIL_MESSAGE ="获取数据失败！";
	public static final String SAVE_OK_MESSAGE ="保存成功！";
	public static final String SAVE_FAIL_MESSAGE ="保存失败！";
	public static final String LOGIN_SUCCESS ="登陆成功！";
	public static final String LOGIN_FAIL ="登陆失败！";
	public static final String ID_CARD_EXIST ="身份证号已存在！";
	public static final String DELETE_SUCCESS = "删除成功！";
	public static final String DELETE_FAIL = "删除失败！";
	public static final String PROJECT_URL = "http://121.40.240.177:8080/case";
}
