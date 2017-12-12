package com.zcc.login.cache;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
@Component
public class CacheManager {
	@Autowired
	JedisPool jedisPool;

	private Map<String,Cache> cacheMap = new HashMap<>();

	public Cache getCache(String key){
		if(cacheMap.containsKey(key)){
			return cacheMap.get(key);
		}
		Cache cache = RedisCache.builder().cacheKey(key).jedis(jedisPool.getResource()).build();
		cacheMap.put(key, cache);
		return cache;
	}
}
