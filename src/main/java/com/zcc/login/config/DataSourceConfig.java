package com.zcc.login.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZhangChicheng on 2017/11/29.
 */
@Configuration
public class DataSourceConfig {

	@Bean(name="loginDS")
	@ConfigurationProperties(prefix = "spring.datasource.login")
	public DataSource dataSource1(){
		return DataSourceBuilder.create().build();
	}

	@Bean(name="linkhomeDS")
	@ConfigurationProperties(prefix = "spring.datasource.linkhome")
	public DataSource dataSource2(){
		return DataSourceBuilder.create().build();
	}
}
