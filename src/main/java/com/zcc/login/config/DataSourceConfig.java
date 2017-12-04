package com.zcc.login.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zcc.login.common.constant.DataSourceEnum;

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

	@Bean("dsMap")
	public Map<DataSourceEnum, DataSource> dsMap(@Qualifier("loginDs") DataSource loginDs,
		@Qualifier("linkhomeDs") DataSource linkhomeDs) {
		Map<DataSourceEnum, DataSource> dsMap = new HashMap<>();
		dsMap.put(DataSourceEnum.LOGIN, loginDs);
		dsMap.put(DataSourceEnum.LINK_HOME,linkhomeDs);
		return dsMap;
	}
}
