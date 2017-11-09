package com.zcc.login.service;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.User;
import com.zcc.login.vo.CreateUserRequest;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface UserService {
	User findUser(SelectUserRequest request);

	User createUser(CreateUserRequest request) throws ServiceException;

	boolean isUserNameExist(String userName);

	boolean deleteUser(String userName);

	int getUserId(String userName);

}
