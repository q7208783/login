package com.zcc.login.cache;

/**
 * Created by ZhangChicheng on 2017/12/11.
 */
public interface Cache {
	Object get(String key);
	String put(String key,Object value);
	Long del(String key);
	boolean isExpire(String key);
}
