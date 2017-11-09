package com.zcc.login.constant;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public enum ErrorCodeEnum {
	USER_NAME_INVALID(10001,"user name has been used, plz use another user name");

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
