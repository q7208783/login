package com.zcc.login.mapper;

import java.util.List;

import com.zcc.login.model.Authority;
import com.zcc.login.model.UserAuthority;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface AuthorityMapper {
	int deleteAllAuthority(int userId);

	List<Authority> getUserAuthorities(String userName);

	int addAuthority(UserAuthority userAuthority);

	int isAuthExist(UserAuthority userAuthority);
}
