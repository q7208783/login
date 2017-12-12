package com.zcc.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcc.login.message.HouseReceiver;
import com.zcc.login.message.ReceiverManager;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by ZhangChicheng on 2017/12/8.
 */
@Configuration
@EnableCaching
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
		setSerializer(stringRedisTemplate);
		stringRedisTemplate.afterPropertiesSet();
		return stringRedisTemplate;
	}

	@Bean
	public CacheManager cacheManager(StringRedisTemplate redisTemplate){
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(10000);
		return cacheManager;
	}

	@Bean
	JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, passwordConfig.getRedisHost(), passwordConfig.getRedisPort(), 0,
			passwordConfig.getRedisPassword());
		return jedisPool;
	}

	private void setSerializer(StringRedisTemplate template){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
	}

	@Bean
	ReceiverManager receiverManager(HouseReceiver houseReceiver,JedisPool jedisPool){
		ReceiverManager receiverManager = new ReceiverManager(jedisPool);
		receiverManager.addReceiver(houseReceiver);
		receiverManager.startHandleMessage();
		return receiverManager;
	}
}
