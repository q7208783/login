package com.zcc.login.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.User;
import com.zcc.login.service.UserService;
import com.zcc.login.vo.ChangePasswordRequest;
import com.zcc.login.vo.CommonResponse;
import com.zcc.login.vo.CreateUserRequest;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/11/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/changePassword")
	@ResponseBody
	public CommonResponse<Boolean> changePassword(@RequestBody @Valid ChangePasswordRequest request) throws ServiceException{
		return new CommonResponse(userService.changePassword(request));
	}

	@PostMapping("/register")
	@ResponseBody
	public CommonResponse<User> registerUser(@RequestBody @Valid CreateUserRequest request) throws ServiceException{
		return new CommonResponse<>(userService.createUser(request));
	}
}
