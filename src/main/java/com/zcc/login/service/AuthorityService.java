package com.zcc.login.service;

import java.util.List;

import com.zcc.login.common.constant.AuthorityEnum;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.Authority;
import com.zcc.login.model.UserAuthority;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface AuthorityService {
	int deleteAllAuthorities(int userId)throws ServiceException;

	List<Authority> getUserAuthorities(String userName);

	boolean addAuthority(String userName, AuthorityEnum authority)throws ServiceException;

	boolean deleteAuthority(String userName, AuthorityEnum authority)throws ServiceException;

	boolean isAuthExist(UserAuthority userAuthority);
}
