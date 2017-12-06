package com.zcc.login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by ZhangChicheng on 2017/12/6.
 */
@Component
@Data
@PropertySource(value = {"classpath:application-pwd.properties"})
public class PasswordConfig {
	@Value("${password.aliyun.accessKeyId}")
	private String aliyunAccessKeyId;
	@Value("${password.aliyun.accessSecret}")
	private String aliyunAccessSecret;

	@Value("${password.datasource.username}")
	private String datasourceUsername;
	@Value("${password.datasource.password}")
	private String datasourcePassword;
}
