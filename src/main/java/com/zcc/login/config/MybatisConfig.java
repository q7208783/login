package com.zcc.login.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
@Configuration
@MapperScan(basePackages = "com.zcc.login.mapper")
public class MybatisConfig {
	@Primary
	@Bean("loginDs")
	@ConfigurationProperties("login.datasource")
	public DataSource loginDataSource(){
		return HikariDataSource();
	}
}
