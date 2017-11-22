package com.zcc.login.common.exception;

import com.zcc.login.common.constant.ErrorCodeEnum;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public class ServiceException extends CommonException{

	private final static String DEFAULT_MSG = "数据暂时异常，请稍后再试！";
	private final static int DEFAULT_CODE = 10000;

	public ServiceException(){
		this.errorCode = DEFAULT_CODE;
		this.errorMsg = DEFAULT_MSG;
	}

	public ServiceException(ErrorCodeEnum errorCodeEnum){
		this.errorCode = errorCodeEnum.getErrorCode();
		this.errorMsg = errorCodeEnum.getErrorMsg();
	}

	public ServiceException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
