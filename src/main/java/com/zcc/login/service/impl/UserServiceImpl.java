package com.zcc.login.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.login.constant.AuthorityEnum;
import com.zcc.login.constant.ErrorCodeEnum;
import com.zcc.login.converter.UserConverter;
import com.zcc.login.exception.ServiceException;
import com.zcc.login.mapper.UserInfoMapper;
import com.zcc.login.model.Authority;
import com.zcc.login.model.User;
import com.zcc.login.service.UserService;
import com.zcc.login.vo.CreateUserRequest;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/10/30.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserConverter converter;

	public User findUser(SelectUserRequest request) {
		return userInfoMapper.getUser(request);
	}

	@Transactional
	public User createUser(CreateUserRequest request) throws ServiceException {
		if (isUserNameExist(request.getUserName()))
			throw new ServiceException(ErrorCodeEnum.USER_NAME_INVALID);
		User user = converter.convertCreateRequestToUser(request);
		userInfoMapper.createUser(user);
		Authority authority = AuthorityEnum.getAuthority(AuthorityEnum.USER);
		user.addAuthority(authority);
		userInfoMapper.addAuthority(user);
		return user;
	}

	public boolean isUserNameExist(String userName) {
		return userInfoMapper.userNameExist(userName) > 0;
	}

	@Transactional
	public boolean deleteUser(String userName){
		if (!isUserNameExist(userName))
//			throw new ServiceException(ErrorCodeEnum.USER_NAME_NOT_EXIST);
			return false;
		userInfoMapper.deleteUser(userName);
		return true;
	}
}
