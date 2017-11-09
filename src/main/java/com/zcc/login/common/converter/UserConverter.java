package com.zcc.login.common.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcc.login.model.User;
import com.zcc.login.vo.CreateUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@Component
public class UserConverter {
	@Autowired
	private ModelMapper modelMapper;

	public User convertCreateRequestToUser(CreateUserRequest request){
		return modelMapper.map(request,User.class);
	}
}
