package com.zcc.login.vo;

import com.zcc.login.model.PageInfo;
import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/11/28.
 */
@Data
public class CommonResponse<T> {
	private int returnCode;
	private String returnMsg;
	private T data;
	private String procTime;
	private PageInfo pageInfo;

	public CommonResponse(){}

	public CommonResponse(T data) {
		this.data = data;
	}

	public CommonResponse(int returnCode, T data) {
		this.returnCode = returnCode;
		this.data = data;
	}

	public CommonResponse(int returnCode, String returnMsg, T data) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.data = data;
	}
}
