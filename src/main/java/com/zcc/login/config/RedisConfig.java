package com.zcc.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
@Configuration
public class RedisConfig {

	@Autowired
	private PasswordConfig passwordConfig;

	@Bean
	JedisPool jedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(8);
		config.setMaxWaitMillis(-1);
		JedisPool jedisPool = new JedisPool(config, passwordConfig.getRedisHost(), 6379, 0,
			passwordConfig.getRedisPassword());
		return jedisPool;
	}

}
