package com.zcc.login.common.exception;

import com.zcc.login.common.constant.ErrorCodeEnum;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public class ServiceException extends Exception{

	private int errorCode;
	private String errorMsg;

	public ServiceException(ErrorCodeEnum errorCodeEnum){
		this.errorCode = errorCodeEnum.getErrorCode();
		this.errorMsg = errorCodeEnum.getErrorMsg();
	}

	public ServiceException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
