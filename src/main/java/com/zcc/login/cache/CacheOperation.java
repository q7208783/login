package com.zcc.login.cache;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
public @interface CacheOperation {
	String keyValue() default "defaultKey";
	String method() default "get";
}
