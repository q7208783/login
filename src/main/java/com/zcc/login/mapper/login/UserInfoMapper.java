package com.zcc.login.mapper.login;

import com.zcc.login.model.User;
import com.zcc.login.vo.ChangePasswordRequest;
import com.zcc.login.vo.NotificationRequest;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
public interface UserInfoMapper {
	User getUser(SelectUserRequest request);

	Integer getUserId(String userName);

	boolean createUser(User user);

	boolean userNameExist(String userName);

	boolean deleteUser(String userName);

	boolean changePassword(ChangePasswordRequest request);

	Boolean changeNotification(NotificationRequest request);

	NotificationRequest getNotificationInfo(Integer userId);
}
