package com.zcc.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ZhangChicheng on 2017/11/15.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
//	@Autowired
//	AuthenticationTokenInterceptor authenticationTokenInterceptor;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(authenticationTokenInterceptor)
//			.addPathPatterns("/**")
//			.excludePathPatterns("/swagger**/**", "/v2/api-docs", "/demo", "/", "/login", "/logout")	// swagger, demo page
//			.excludePathPatterns("/auth/login", "/auth/newMember")										// no block
//			.excludePathPatterns("/apigw/**")
//			.excludePathPatterns("/mig/**")
//			.excludePathPatterns("/git/login")
//			.excludePathPatterns("/monitor/l7check");
//	}
}
