package com.zcc.login.mapper;

import java.util.List;

import com.zcc.login.model.Authority;
import com.zcc.login.model.UserAuthority;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface AuthorityMapper {
	int deleteAllAuthorities(int userId);

	boolean deleteAuthority(UserAuthority userAuthority);

	List<Authority> getUserAuthorities(String userName);

	boolean addAuthority(UserAuthority userAuthority);

	boolean isAuthExist(UserAuthority userAuthority);
}
