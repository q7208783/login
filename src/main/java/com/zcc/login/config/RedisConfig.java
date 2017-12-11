package com.zcc.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

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
	JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(8);
		jedisPoolConfig.setMaxWaitMillis(-1);
		return jedisPoolConfig;
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		jedisConnectionFactory.setPassword(passwordConfig.getRedisPassword());
		jedisConnectionFactory.setHostName(passwordConfig.getRedisHost());
		jedisConnectionFactory.setPort(passwordConfig.getRedisPort());
		jedisConnectionFactory.setTimeout(15000);
		jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}

	@Bean
	StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory){
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory);
		return stringRedisTemplate;
	}

	@Bean
	JedisPool jedisPool(JedisPoolConfig jedisPoolConfig,JedisConnectionFactory jedisConnectionFactory) {
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, passwordConfig.getRedisHost(), passwordConfig.getRedisPort(), 0,
			passwordConfig.getRedisPassword());
		return jedisPool;
	}
}
