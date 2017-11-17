package com.zcc.login.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.common.constant.AuthorityEnum;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.model.Authority;
import com.zcc.login.model.User;
import com.zcc.login.model.UserAuthority;
import com.zcc.login.vo.CreateUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */

public class AuthorityMapperTest {
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private UserService userService;

	@Test
	public void getAuthorityTest() {
		CreateUserRequest request = new CreateUserRequest();
		request.setUserName("test");
		request.setPassword("123");
		request.setEmail("abc@123.com");
		request.setPhoneNum("111");
		try {
			userService.deleteUser(request.getUserName());
			User user = userService.createUser(request);
			List<Authority> authorityList = authorityService.getUserAuthorities(request.getUserName());
			boolean res1 = authorityService.addAuthority(user.getUserName(), AuthorityEnum.ADMIN);
			boolean res2 = authorityService.addAuthority(user.getUserName(), AuthorityEnum.USER);
			assertFalse(res2);
			assertTrue(res1);
			assertTrue(authorityService.isAuthExist(new UserAuthority(user.getUserId(), AuthorityEnum.ADMIN.getAuthId())));
			assertTrue(authorityList.size() > 0);
			boolean res3 = authorityService.deleteAuthority(request.getUserName(),AuthorityEnum.ADMIN);
			assertTrue(res3);
			userService.deleteUser(request.getUserName());
			authorityList = authorityService.getUserAuthorities(request.getUserName());
			assertTrue(authorityList.size() == 0);
			assertFalse(
				authorityService.isAuthExist(new UserAuthority(user.getUserId(), AuthorityEnum.ADMIN.getAuthId())));
		} catch (ServiceException e) {

		}
	}
}
