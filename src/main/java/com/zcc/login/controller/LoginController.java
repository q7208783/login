package com.zcc.login.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.common.utils.JwtTokenUtil;
import com.zcc.login.model.ExpireTime;
import com.zcc.login.model.Token;
import com.zcc.login.model.User;
import com.zcc.login.service.LoginService;
import com.zcc.login.vo.CommonResponse;
import com.zcc.login.vo.LoginRequest;
import lombok.extern.apachecommons.CommonsLog;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Controller
@CommonsLog
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	@ResponseBody
	public CommonResponse<Token> login(@RequestBody @Valid LoginRequest request, Device device,
		HttpServletResponse response) throws ServiceException {
		return new CommonResponse<>(loginService.login(request, device, response));
	}

	@PostMapping("/logout")
	@ResponseBody
	public CommonResponse<String> logout(@ApiIgnore @RequestAttribute("user") User user, HttpServletResponse response) {
		loginService.logout(response);
		return new CommonResponse(user.getUserName() + " is logout");
	}

	@GetMapping("/userInfo")
	@ResponseBody
	public CommonResponse<User> user(@ApiIgnore @RequestAttribute("user") User user) {
		return new CommonResponse(user);
	}

	@GetMapping("/expire")
	@ResponseBody
	public CommonResponse<ExpireTime> expire(@ApiIgnore @RequestAttribute("user") User user) {
		String token = user.getToken();
		ExpireTime expireTime = new ExpireTime();
		expireTime.setIsExpire(jwtTokenUtil.isTokenExpired(token));
		expireTime.setRemainTimeStamp(jwtTokenUtil.getRemainExpireTime(token));
		return new CommonResponse<>(expireTime);
	}
}
