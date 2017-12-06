package com.zcc.login.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.common.utils.EmailSender;

/**
 * Created by ZhangChicheng on 2017/12/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSenderUtilTest {
	@Autowired
	EmailSender emailSender;

	@Test
	public void sendEmailTest(){
		emailSender.send();
	}
}
