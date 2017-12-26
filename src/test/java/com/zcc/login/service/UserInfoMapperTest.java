package com.zcc.login.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.login.common.constant.CommonConstant;
import com.zcc.login.common.constant.ErrorCodeEnum;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.controller.UserController;
import com.zcc.login.model.User;
import com.zcc.login.vo.ChangePasswordRequest;
import com.zcc.login.vo.CommonResponse;
import com.zcc.login.vo.CreateUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {
	@Autowired
	UserService userService;

	@Autowired
	UserController userController;
	@Test
	public void getUserTest() {
		String userName = "zhangchicheng";
		User user = userService.getUser(userName);
		assertNotNull(user);
		assertEquals(user.getUserName(),userName);

		int userId = 2;
		user = userService.getUser(userId);
		assertNotNull(user);
		assertEquals(user.getUserId(),userId);

		userId =1;
		user = userService.getUser(userId);
		assertNotNull(user);
		assertEquals(user.getUserId(),userId);
		assertEquals(user.getUserName(),userName);
	}

	@Test
	@Transactional
	public void createUserTest() throws ServiceException{
		CreateUserRequest request = new CreateUserRequest();
		request.setUserName("test");
		request.setPassword("123");
		request.setEmail("abc@123.com");
		request.setPhoneNum("111");
		User user;
		try{
			try{
				userService.deleteUser(request.getUserName());
			}catch (ServiceException ex){
				assertEquals(ex.getErrorCode(),ErrorCodeEnum.USER_NAME_NOT_EXIST.getErrorCode());
			}
			user = userService.createUser(request);
			assertEquals(user.getUserId(),userService.getUser(request.getUserName()).getUserId());
			assertNotNull(user);
			assertNotNull(user.getAuthorities());
			assertEquals(request.getUserName(),user.getUserName());
			boolean isExist = userService.isUserNameExist(user.getUserName());
			assertTrue(isExist);
			userService.createUser(request);
		}catch (ServiceException e){
			assertEquals(e.getErrorCode(), ErrorCodeEnum.USER_NAME_ALREADY_EXIST.getErrorCode());
			userService.deleteUser(request.getUserName());
			boolean isExist = userService.isUserNameExist(request.getUserName());
			assertFalse(isExist);
		}
	}

	@Test
	public void getUserIdTest(){
		try{
			int userId = userService.getUserId("!@#$#%^");
		}catch (ServiceException ex){
			assertEquals(ex.getErrorCode(),ErrorCodeEnum.USER_NAME_NOT_EXIST.getErrorCode());
		}
	}

	@Test
	@Transactional
	public void changePassword(){
		ChangePasswordRequest request = new ChangePasswordRequest();
		request.setUserName("zhangchicheng");
		request.setOldPwd("123456");
		request.setNewPwd("1234");
		try{
			CommonResponse<Boolean> res = userController.changePassword(request);
			assertTrue(res.getData());
			try{
				userController.changePassword(request);
			}catch (ServiceException e){
				Assert.assertEquals(e.getErrorCode(), ErrorCodeEnum.AUTH_FAILURE.getErrorCode());
			}
			request.setNewPwd("123456");
			request.setOldPwd("1234");
			res = userController.changePassword(request);
			assertTrue(res.getData());
			request.setUserName("lihao");
			request.setOldPwd("123456");
			request.setNewPwd("1234");
			res = userController.changePassword(request);
			assertTrue(res.getData());
			try{
				userController.changePassword(request);
			}catch (ServiceException e){
				Assert.assertEquals(e.getErrorCode(), ErrorCodeEnum.AUTH_FAILURE.getErrorCode());
			}
			request.setNewPwd("123456");
			request.setOldPwd("1234");
			res = userController.changePassword(request);
			assertTrue(res.getData());
			userController.changePassword(request);
		}catch (ServiceException e){
			Assert.assertEquals(e.getErrorCode(), ErrorCodeEnum.AUTH_FAILURE.getErrorCode());
		}
	}
}
