package com.zcc.login.mapper;

import com.zcc.login.model.User;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
public interface UserInfoMapper {
	User getUser(SelectUserRequest request);

	Integer getUserId(String userName);

	int createUser(User user);

	int userNameExistNum(String userName);

	int deleteUser(String userName);

}
