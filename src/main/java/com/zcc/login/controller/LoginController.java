package com.zcc.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.zcc.login.common.constant.CommonConstant;
import com.zcc.login.common.utils.CookieUtil;
import com.zcc.login.common.utils.JwtTokenUtil;
import com.zcc.login.model.User;
import com.zcc.login.service.UserService;
import com.zcc.login.user.AuthUser;
import com.zcc.login.vo.LoginRequest;
import com.zcc.login.vo.LoginResponse;
import lombok.extern.apachecommons.CommonsLog;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Controller
@CommonsLog
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/")
	public String login(HttpServletRequest request,
		Model model){
		return "login";
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody @Valid LoginRequest request, Device device,
		HttpServletResponse response) {
		final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getUsername(),
				request.getPassword()
			)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userService.getUser(request.getUsername());
		String token = jwtTokenUtil.generateToken(user.getUserName(), device);
		CookieUtil.addCookie(response, CommonConstant.X_ZCC_TOKEN, token);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletResponse response){
		CookieUtil.cancelCookie(response, CommonConstant.X_ZCC_TOKEN);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/userInfo")
	public ResponseEntity<User> user(@ApiIgnore @RequestAttribute("user")User user){
		return new ResponseEntity(user, HttpStatus.OK);
	}
}
