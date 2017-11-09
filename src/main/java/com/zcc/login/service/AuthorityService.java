package com.zcc.login.service;

import java.util.List;

import com.zcc.login.model.Authority;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface AuthorityService {
	int deleteAllAuthority(int userId);

	List<Authority> getUserAuthorities(String userName);

	int addAuthority(SelectUserRequest request, Authority authority);

	int deleteAuthority(int userId, Authority authority);
}
