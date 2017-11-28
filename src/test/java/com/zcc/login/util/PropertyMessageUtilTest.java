package com.zcc.login.util;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.common.utils.PropertyMessageUtils;

/**
 * Created by ZhangChicheng on 2017/11/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyMessageUtilTest {
	@Test
	public void getMessageTest(){
		PropertyMessageUtils.getMessage("common.message.0", Locale.CHINESE);
	}
}
