package com.zcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.dao.User;
import com.zcc.repository.UserRepository;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User findUserByUsername(String username){
		return userRepository.findUserByUsername(username);
	}
}
