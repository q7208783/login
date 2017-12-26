package com.zcc.login.service;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.User;
import com.zcc.login.user.AuthUser;
import com.zcc.login.vo.ChangePasswordRequest;
import com.zcc.login.vo.CreateUserRequest;
import com.zcc.login.vo.NotificationRequest;
import com.zcc.login.vo.SelectUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public interface UserService {
	User getUser(String userName);

	User getUser(int userId);

	AuthUser getAuthUser(String userName);

	User createUser(CreateUserRequest request) throws ServiceException;

	boolean isUserNameExist(String userName);

	boolean deleteUser(String userName)throws ServiceException;

	int getUserId(String userName)throws ServiceException;

	boolean changePassword(ChangePasswordRequest request)throws ServiceException;

	Boolean changeNotification(NotificationRequest request)throws ServiceException;

	NotificationRequest getNotificationInfo(Integer userId)throws ServiceException;
}
