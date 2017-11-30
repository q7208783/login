package com.zcc.login.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by ZhangChicheng on 2017/11/29.
 */
@Configuration
public class DataSourceConfig {
	@Primary
	@Bean("loginDs")
	@ConfigurationProperties("datasource.login")
	public DataSource loginDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean("linkhomeDs")
	@ConfigurationProperties("datasource.linkhome")
	public DataSource linkhomeDs() {
		return DataSourceBuilder.create().build();
	}
}
