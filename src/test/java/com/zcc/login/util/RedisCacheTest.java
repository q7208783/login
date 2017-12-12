package com.zcc.login.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.login.cache.RedisCache;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.service.HouseService;
import com.zcc.login.vo.BindHouseRequest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheTest {
	@Autowired
	JedisPool jedisPool;
	@Autowired
	HouseService houseService;
	@Test
	public void redisCacheTest(){
		Jedis jedis = jedisPool.getResource();
		RedisCache redisCache = RedisCache.builder().cacheKey("houseBindCache").expire(10000).jedis(jedis).build();

		try{
			redisCache.put(houseService.selectAllCondition());
		}catch (ServiceException e){

		}
		List<BindHouseRequest> list = (List<BindHouseRequest>)redisCache.get();
		Assert.assertTrue(list.size()>0);
	}
}
