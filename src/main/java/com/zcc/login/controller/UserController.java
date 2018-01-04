package com.zcc.login.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.zcc.login.vo.NotificationRequest;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/11/17.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/changePassword")
	@ResponseBody
	public CommonResponse<Boolean> changePassword(@RequestBody @Valid ChangePasswordRequest request) throws
		ServiceException {
		return new CommonResponse(userService.changePassword(request));
	}

	@PostMapping("/register")
	@ResponseBody
	public CommonResponse<User> registerUser(@RequestBody @Valid CreateUserRequest request) throws ServiceException {
		return new CommonResponse<>(userService.createUser(request));
	}

	@PostMapping("/changeNotification")
	@ResponseBody
	public CommonResponse<Boolean> changeNotification(@ApiIgnore @RequestAttribute("user") User user, @RequestBody @Valid NotificationRequest request) throws
		ServiceException {
		request.setUserId(user.getUserId());
		return new CommonResponse<>(userService.changeNotification(request));
	}

	@GetMapping("/getNotificationInfo")
	@ResponseBody
	public CommonResponse<NotificationRequest> getNotificationInfo(@ApiIgnore @RequestAttribute("user") User user) throws
		ServiceException {
		return new CommonResponse<>(userService.getNotificationInfo(user.getUserId()));
	}
}
