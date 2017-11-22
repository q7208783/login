package com.zcc.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.login.common.constant.AuthorityEnum;
import com.zcc.login.common.constant.ErrorCodeEnum;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.mapper.AuthorityMapper;
import com.zcc.login.mapper.UserInfoMapper;
import com.zcc.login.model.Authority;
import com.zcc.login.model.UserAuthority;
import com.zcc.login.service.AuthorityService;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	AuthorityMapper authorityMapper;
	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public List<Authority> getUserAuthorities(String userName) {
		return authorityMapper.getUserAuthorities(userName);
	}

	@Override
	public int deleteAllAuthorities(int userId){
		return authorityMapper.deleteAllAuthorities(userId);
	}

	@Override
	@Transactional
	public boolean addAuthority(String userName, AuthorityEnum authority) throws ServiceException{
		Integer userId = userInfoMapper.getUserId(userName);
		int authId = authority.getAuthId();
		UserAuthority userAuthority = new UserAuthority(userId, authId);
		if(isAuthExist(userAuthority))
			throw new ServiceException(ErrorCodeEnum.USER_AUTH_ALREADY_EXIST);
		else
			return authorityMapper.addAuthority(userAuthority);
	}

	@Override
	public boolean isAuthExist(UserAuthority userAuthority) {
		return authorityMapper.isAuthExist(userAuthority);
	}

	@Override
	@Transactional
	public boolean deleteAuthority(String userName, AuthorityEnum authority) throws ServiceException {
		Integer userId = userInfoMapper.getUserId(userName);
		int authId = authority.getAuthId();
		UserAuthority userAuthority = new UserAuthority(userId, authId);
		if(isAuthExist(userAuthority))
			return authorityMapper.deleteAuthority(userAuthority);
		else
			throw new ServiceException(ErrorCodeEnum.USER_AUTH_NOT_EXIST);
	}
}
