package com.zcc.login.cache;

import org.springframework.data.redis.connection.Pool;

import com.zcc.login.common.utils.JsonUtil;
import redis.clients.jedis.Jedis;
import springfox.documentation.spring.web.json.Json;

/**
 * Created by ZhangChicheng on 2017/12/11.
 */
public abstract class RedisCache implements Cache {

	private Jedis jedis;

	public RedisCache(){}

	public RedisCache(Jedis jedis){
		this.jedis = jedis;
	}

	@Override
	public Object get(String key) {
		return convert(jedis.get(key));
	}

	@Override
	public String put(String key, Object value) {
		return jedis.set(key,convert(value));
	}

	@Override
	public Long del(String key) {
		return jedis.del(key);
	}

	@Override
	public boolean isExpire(String key) {
		return jedis.ttl(key)<0;
	}

	public Object convert(String value){
		return JsonUtil.toObject(value, Object.class);
	}

	public String convert(Object value){
		return JsonUtil.toJson(value);
	}
}
