package com.zcc.login.cache;

import com.zcc.login.common.utils.JsonUtil;
import lombok.Builder;
import redis.clients.jedis.Jedis;

/**
 * Created by ZhangChicheng on 2017/12/11.
 */
@Builder
public class RedisCache implements Cache{

	private Jedis jedis;
	private String cacheKey;
	private String cacheValue;

	@Override
	public Object get() {
		return convert(jedis.get(cacheKey));
	}
	@Override
	public String put(Object value) {
		return jedis.set(cacheKey, convert(value));
	}
	@Override
	public Long del() {
		return jedis.del(cacheKey);
	}
	@Override
	public boolean isExpire() {
		return jedis.ttl(cacheKey) < 0;
	}
	@Override
	public String getCacheKey() {
		return this.cacheKey;
	}

	public String getCacheValue() {
		return cacheValue;
	}

	public void setCacheValue(String cacheValue) {
		this.cacheValue = cacheValue;
	}

	public Object convert(String value) {
		return JsonUtil.toObject(value, Object.class);
	}

	public String convert(Object value) {
		return JsonUtil.toJson(value);
	}

}
