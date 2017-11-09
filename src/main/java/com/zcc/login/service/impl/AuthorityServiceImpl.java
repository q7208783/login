package com.zcc.login.service.impl;

import java.util.List;

import com.zcc.login.mapper.UserInfoMapper;
import com.zcc.login.model.User;
import com.zcc.login.vo.SelectUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.login.mapper.AuthorityMapper;
import com.zcc.login.model.Authority;
import com.zcc.login.service.AuthorityService;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityMapper authorityMapper;
	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public List<Authority> getUserAuthorities(String userName) {
		return authorityMapper.getUserAuthorities(userName);
	}

	@Override
	public int deleteAllAuthority(int userId) {
		return authorityMapper.deleteAllAuthority(userId);
	}

	@Override
	public int addAuthority(SelectUserRequest request, Authority authority) {
		User user = userInfoMapper.getUser(request);
		List<Authority> authorities = user.getAuthorities();
		if(!authorities.contains(authority)){
			authorities.add(authority);
		}
		return authorityMapper.addAuthority(user);
	}

	@Override
	public int deleteAuthority(int userId, Authority authority) {
		return 0;
	}
}
