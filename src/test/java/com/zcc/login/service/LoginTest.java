package com.zcc.login.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.controller.LoginController;

/**
 * Created by ZhangChicheng on 2017/11/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {
	@Autowired
	private LoginController loginController;

	@Test
	public void loginTest(){

	}
}
