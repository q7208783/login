package com.zcc.login.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.exception.ServiceException;
import com.zcc.login.model.Authority;
import com.zcc.login.vo.CreateUserRequest;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
			userService.createUser(request);

			List<Authority> authorityList = authorityService.getUserAuthorities(request.getUserName());

			assertTrue(authorityList.size() > 0);

			userService.deleteUser(request.getUserName());
			authorityList = authorityService.getUserAuthorities(request.getUserName());

			assertTrue(authorityList.size() == 0);
		} catch (ServiceException e) {

		}
	}
}
