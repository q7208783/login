package com.zcc.login.cache;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
public interface Cache {
	Object get();

	String put(Object value);

	Long del();

	boolean isExpire();

	String getCacheKey();
}
