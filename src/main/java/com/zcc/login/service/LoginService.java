package com.zcc.login.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.Token;
import com.zcc.login.vo.LoginRequest;

/**
 * Created by ZhangChicheng on 2017/11/27.
 */
public interface LoginService {
	Token login(LoginRequest request, Device device,
		HttpServletResponse response)throws ServiceException;

	void logout(HttpServletResponse response);
}
