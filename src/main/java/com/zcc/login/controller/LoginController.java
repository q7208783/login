package com.zcc.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.login.common.exception.ServiceException;
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
	private LoginService loginService;

	@PostMapping("/login")
	@ResponseBody
	public CommonResponse<Token> login(@RequestBody @Valid LoginRequest request, Device device,
		HttpServletResponse response) throws ServiceException{
		return new CommonResponse<>(loginService.login(request, device, response));
	}

	@PostMapping("/logout")
	@ResponseBody
	public CommonResponse<Void> logout(HttpServletResponse response){
		loginService.logout(response);
		return new CommonResponse();
	}

	@GetMapping("/userInfo")
	@ResponseBody
	public CommonResponse<User> user(@ApiIgnore @RequestAttribute("user")User user){
		return new CommonResponse(user);
	}
}
