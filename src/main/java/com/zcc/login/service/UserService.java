package com.zcc.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.login.mapper.UserInfoMapper;
import com.zcc.login.model.User;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Service
public class UserService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	public User findUserByUsername(String username){
		SelectUserRequest request = new SelectUserRequest();
		request.setUser_name(username);
		return userInfoMapper.getUser(request);
	}
}
