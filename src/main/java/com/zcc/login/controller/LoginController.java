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
import org.springframework.web.bind.annotation.RequestBody;

import com.zcc.login.common.constant.CommonConstant;
import com.zcc.login.common.utils.CookieUtil;
import com.zcc.login.common.utils.JwtTokenUtil;
import com.zcc.login.model.User;
import com.zcc.login.service.UserService;
import com.zcc.login.user.AuthUser;
import com.zcc.login.vo.LoginRequest;
import com.zcc.login.vo.LoginResponse;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Controller
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/")
	public String index(HttpServletRequest request,
		Model model){
		return "login";
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request, Device device,
		HttpServletResponse response) {
		final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getUsername(),
				request.getPassword()
			)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		AuthUser authUser = userService.getAuthUser(request.getUsername());
		String token = jwtTokenUtil.generateToken(authUser, device);
		CookieUtil.addCookie(response, CommonConstant.X_ZCC_TOKEN, token);
		return ResponseEntity.ok(new LoginResponse(token));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletResponse response, HttpServletRequest request){
		String token = CookieUtil.getCookieValue(request, CommonConstant.X_ZCC_TOKEN);
		CookieUtil.addCookie(response, CommonConstant.X_ZCC_TOKEN, null);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<User> user(HttpServletResponse response, HttpServletRequest request){
		String token = CookieUtil.getCookieValue(request, CommonConstant.X_ZCC_TOKEN);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.getUser(username);
		return new ResponseEntity(user, HttpStatus.OK);
	}
}
