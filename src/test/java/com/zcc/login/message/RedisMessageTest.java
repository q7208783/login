package com.zcc.login.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisMessageTest {
	@Autowired
	JedisPool jedisPool;

	@Autowired
	HouseReceiver houseReceiver;

	@Test
	public void subscripTest(){
		Jedis jedis = jedisPool.getResource();
		houseReceiver.register(jedis);
	}
}
