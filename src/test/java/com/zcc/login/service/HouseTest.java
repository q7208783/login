package com.zcc.login.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.vo.BindHouseRequest;

/**
 * Created by ZhangChicheng on 2017/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseTest {
	@Autowired
	HouseService houseService;

	@Test
	public void getAllHouseBindTest(){
		try {
			List<BindHouseRequest> requests = houseService.selectAllCondition();
			Assert.assertNotNull(requests);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
