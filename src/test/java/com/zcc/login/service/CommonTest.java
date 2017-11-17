package com.zcc.login.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.common.utils.DateUtil;

/**
 * Created by ZhangChicheng on 2017/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {
	@Test
	public void commonTest(){
		String now = DateUtil.timeStamp();
	}
}
