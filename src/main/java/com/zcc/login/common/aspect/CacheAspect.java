package com.zcc.login.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcc.login.cache.Cache;
import com.zcc.login.cache.CacheManager;
import com.zcc.login.cache.CacheOperation;

/**
 * Created by ZhangChicheng on 2017/12/12.
 */
@Aspect
public class CacheAspect {

	@Autowired
	CacheManager cacheManager;

	@Around("execution(* com.zcc.login.service.impl.*Impl.*(..))")
	public Object cacheAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		Object returnVal = proceedingJoinPoint.proceed();
		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
		CacheOperation anno=methodSignature.getMethod().getAnnotation(CacheOperation.class);
		if(anno != null){
			String keyValue = anno.keyValue();
			String method = anno.method();
			Cache cache = cacheManager.getCache(keyValue);
			if(method.equals("put")){
				cache.put(returnVal);
			}
			else if(method.equals("del")){
				cache.del();
			}
		}
		return returnVal;
	}
}
