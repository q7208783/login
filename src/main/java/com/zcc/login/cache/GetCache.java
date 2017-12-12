package com.zcc.login.cache;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
public @interface GetCache{
	String keyValue() default "defaultKey";
}
