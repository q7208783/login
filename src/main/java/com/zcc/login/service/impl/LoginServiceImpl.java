package com.zcc.login.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zcc.login.common.annotation.DataSourceType;
import com.zcc.login.common.constant.CommonConstant;
import com.zcc.login.common.constant.DataSourceEnum;
import com.zcc.login.common.constant.ErrorCodeEnum;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.common.utils.CookieUtil;
import com.zcc.login.common.utils.JwtTokenUtil;
import com.zcc.login.model.Token;
import com.zcc.login.service.LoginService;
import com.zcc.login.vo.LoginRequest;

/**
 * Created by ZhangChicheng on 2017/11/27.
 */
@Service
@DataSourceType(DataSourceEnum.LOGIN)
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public Token login(LoginRequest request, Device device, HttpServletResponse response) throws ServiceException{
		try{
			final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					request.getUsername(),
					request.getPassword()
				)
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch (Exception e){
			throw new ServiceException(ErrorCodeEnum.AUTH_FAILURE);
		}
		String token0 = jwtTokenUtil.generateToken(request.getUsername(), device);
		CookieUtil.addCookie(response, CommonConstant.X_ZCC_TOKEN, token0);
		Token token = new Token();
		token.setToken(token0);
		return token;
	}

	@Override
	public void logout(HttpServletResponse response) {
		CookieUtil.deleleCookie(response, CommonConstant.X_ZCC_TOKEN);
	}
}
