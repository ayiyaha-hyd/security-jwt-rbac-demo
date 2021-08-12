package com.hyd.bean;

/**
 * 通用返回结果
 */
public class CommonResult<T> {
	private String code;
	private String msg;
	private T data;

	private CommonResult(){
		this.code = "200";
		this.msg ="操作成功";
	}
	private CommonResult(String msg){
		this.code = "200";
		this.msg = msg;
	}
	private CommonResult(T data){
		this.code = "200";
		this.msg = "操作成功";
		this.data = data;
	}
	private CommonResult(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	private CommonResult(String code,String msg,T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	private CommonResult(ErrorCode code){
		this.code = code.getCode();
		this.msg = code.getMsg();
	}

	public static CommonResult success(){
		return new CommonResult();
	}
	public static CommonResult success(String msg){
		return new CommonResult(msg);
	}
	public static CommonResult success(Object data){
		return new CommonResult(data);
	}
	public static CommonResult success(String msg,Object data){
		return new CommonResult(ErrorCode.SUCCESS_OPTION.getCode(),msg,data);
	}
	public static CommonResult failure(ErrorCode code){
		return new CommonResult(code);
	}
	public static CommonResult failure(String msg){
		return new CommonResult(ErrorCode.FAIL_OPTION.getCode(), msg);
	}

	public static CommonResult failure(ErrorCode code,String msg){
		return new CommonResult(code.getCode(), code.getMsg()+"--> "+msg);
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
