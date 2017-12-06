package com.zcc.login.util;

import java.util.ArrayList;
import java.util.List;

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
	public void sendEmailTest() throws Exception{
		List<String> add = new ArrayList<>();
		add.add("576926338@qq.com");
		emailSender.send(add,"新房监控发现适合您的新房源","https://cd.lianjia.com/ershoufang/106100790393.html");
	}
}
