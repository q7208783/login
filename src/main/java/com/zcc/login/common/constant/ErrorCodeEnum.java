package com.zcc.login.common.constant;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public enum ErrorCodeEnum {
	USER_NAME_INVALID(10001,"User Name Is Already Exist"),
	USER_NAME_NOT_EXIST(10002,"User Name Not Exist"),

	AUTH_FAILURE(10010,"AUTHORIZED FAILED!");
	private int errorCode;
	private String errorMsg;

	ErrorCodeEnum(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
