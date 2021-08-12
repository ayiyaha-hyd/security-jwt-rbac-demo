package com.hyd.bean;

/**
 * 统一错误码封装
 */
public enum ErrorCode {
	/**
	 * 操作成功
	 */
	SUCCESS_OPTION("200", "操作成功"),
	//系统级错误
	AUTH_FAIL("201", "认证失败"),
	NO_PERMISSION("202", "权限不足"),
	REQUESTS_OUT_OF_RATE_LIMIT("203", "请求频次超过上限"),
	IP_LIMIT("204", "IP限制不能请求该资源"),
	REQUEST_BODY_LENGTH_OVER_LIMIT("205", "请求长度超过限制   "),
	//服务器级错误
	FAIL_OPTION("300", "操作失败"),
	MISS_REQUIRED_PARAMETER("301", "缺少参数"),
	PARAMETER_INVALID("3042", "参数非法"),
	REQUEST_API_NOT_FOUND("303", "请求接口不存在"),
	LOGIN_FAIL("400","登录失败"),
	TOKEN_EXPIRE("402", "Token过期"),
	SYSTEM_ERROR("500", "系统出错");

	private String code;

	private String msg;
	private ErrorCode(){}
	private ErrorCode(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
