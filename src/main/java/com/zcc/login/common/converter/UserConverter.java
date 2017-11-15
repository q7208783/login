package com.zcc.login.common.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.zcc.login.model.User;
import com.zcc.login.user.AuthUser;
import com.zcc.login.vo.CreateUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@Component
public class UserConverter {
	@Autowired
	private ModelMapper modelMapper;

	public User convertCreateRequestToUser(CreateUserRequest request) {
		return modelMapper.map(request, User.class);
	}

	public AuthUser covertUser2AuthUser(User user) {
		String userName = user.getUserName();
		String password = user.getPassword();
		List<SimpleGrantedAuthority> authorityList = user.getAuthorities().stream()
			.map(authority -> new SimpleGrantedAuthority(authority.getAuthName())).collect(Collectors.toList());
		return new AuthUser(userName, password, user.getLastrResetPwYmdt(), authorityList);
	}
}
