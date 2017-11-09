package com.zcc.login.mapper;

import com.zcc.login.model.User;
import com.zcc.login.vo.CreateUserRequest;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
public interface UserInfoMapper {
	User getUser(SelectUserRequest request);
	void createUser(User user);
	void addAuthority(User user);
	int userNameExist(String userName);
	void deleteUser(String userName);
}
