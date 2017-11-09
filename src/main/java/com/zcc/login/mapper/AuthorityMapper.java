package com.zcc.login.mapper;

import java.util.List;

import com.zcc.login.model.Authority;
import com.zcc.login.model.User;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface AuthorityMapper {
	int deleteAllAuthority(int userId);

	List<Authority> getUserAuthorities(String userName);

	int addAuthority(User user);
}
