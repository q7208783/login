package com.zcc.login.service.impl;

import static com.zcc.login.common.utils.CommonUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.login.common.constant.AuthorityEnum;
import com.zcc.login.common.constant.ErrorCodeEnum;
import com.zcc.login.common.converter.UserConverter;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.common.utils.DateUtil;
import com.zcc.login.mapper.AuthorityMapper;
import com.zcc.login.mapper.UserInfoMapper;
import com.zcc.login.model.Authority;
import com.zcc.login.model.User;
import com.zcc.login.model.UserAuthority;
import com.zcc.login.service.AuthorityService;
import com.zcc.login.service.UserService;
import com.zcc.login.user.AuthUser;
import com.zcc.login.vo.ChangePasswordRequest;
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
	@Autowired
	private AuthenticationManager authenticationManager;

	private User getUser(SelectUserRequest request) {
		return userInfoMapper.getUser(request);
	}

	public AuthUser getAuthUser(String userName){
		return converter.covertUser2AuthUser(this.getUser(userName));
	}

	public User getUser(String userName){
		SelectUserRequest request = new SelectUserRequest();
		request.setUserName(userName);
		return this.getUser(request);
	}

	public User getUser(int userId){
		SelectUserRequest request = new SelectUserRequest();
		request.setUserId(userId);
		return this.getUser(request);
	}

	@Transactional
	public User createUser(CreateUserRequest request) throws ServiceException {
		if (isUserNameExist(request.getUserName()))
			throw new ServiceException(ErrorCodeEnum.USER_NAME_ALREADY_EXIST);
		User user = converter.convertCreateRequestToUser(request);
		user.setCreateTimeYmdt(DateUtil.timeStamp());
		user.setLastrResetPwYmdt(DateUtil.timeStamp());
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

	public int getUserId(String userName) throws ServiceException{
		Integer userId = userInfoMapper.getUserId(userName);
		if(userId == null)
			throw new ServiceException(ErrorCodeEnum.USER_NAME_NOT_EXIST);
		else
			return userId.intValue();
	}

	@Transactional
	public boolean deleteUser(String userName) throws ServiceException{
		int userId = getUserId(userName);
		if (userInfoMapper.deleteUser(userName)) {
			return opearationSuccess(authorityService.deleteAllAuthorities(userId));
		}
		return false;
	}

	@Transactional
	public boolean changePassword(ChangePasswordRequest request)throws ServiceException{
		try{
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					request.getUserName(),
					request.getOldPwd()
				)
			);
		}catch (Exception e){
			throw new ServiceException(ErrorCodeEnum.AUTH_FAILURE);
		}
		request.setLastrResetPwYmdt(DateUtil.timeStamp());
		return userInfoMapper.changePassword(request);
	}
}
