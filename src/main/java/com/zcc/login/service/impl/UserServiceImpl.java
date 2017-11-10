package com.zcc.login.service.impl;

import static com.zcc.login.common.utils.CommonUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.login.common.constant.AuthorityEnum;
import com.zcc.login.common.constant.CommonConstant;
import com.zcc.login.common.constant.ErrorCodeEnum;
import com.zcc.login.common.converter.UserConverter;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.common.utils.CommonUtils;
import com.zcc.login.mapper.AuthorityMapper;
import com.zcc.login.mapper.UserInfoMapper;
import com.zcc.login.model.Authority;
import com.zcc.login.model.User;
import com.zcc.login.model.UserAuthority;
import com.zcc.login.service.AuthorityService;
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
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private AuthorityService authorityService;

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
		UserAuthority userAuthority = new UserAuthority(user.getUserId(),authority.getAuthId());
		user.addAuthority(authority);
		authorityMapper.addAuthority(userAuthority);
		return user;
	}

	public boolean isUserNameExist(String userName) {
		return userInfoMapper.userNameExist(userName);
	}

	public int getUserId(String userName) {
		Integer userId = userInfoMapper.getUserId(userName);
		if(userId == null)
			return CommonConstant.OPERATION_FAILED;
		else
			return userId.intValue();
	}

	@Transactional
	public boolean deleteUser(String userName) {
		int userId = getUserId(userName);
		if (userInfoMapper.deleteUser(userName)) {
			return opearationSuccess(authorityService.deleteAllAuthority(userId));
		}
		return false;
	}

}
