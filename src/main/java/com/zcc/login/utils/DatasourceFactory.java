package com.zcc.login.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by ZhangChicheng on 2017/11/9.
 */
public class DatasourceFactory {
	private final static Logger log = LoggerFactory.getLogger(DatasourceFactory.class);
	private static Map<String, HikariDataSource> dataSourceMap;

	static {
		dataSourceMap = new HashMap<>();
	}
	private static HikariConfig loadConfig(String configuration) {
		Properties properties = new Properties();

		try {
			properties.load(DatasourceFactory.class.getClassLoader().getResourceAsStream(configuration));
		} catch (IOException e) {
			log.error("an error occurred", e);
		}
		HikariConfig config = new HikariConfig(properties);
		return config;
	}

	private static HikariDataSource getDataSourceInternal(String configuration) {
		HikariConfig config = loadConfig(configuration);
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}

	public static HikariDataSource getDataSource(String configuration){
		log.debug("retrieving data source from Mysql");
		if (dataSourceMap.get(configuration) == null) {
			synchronized (DatasourceFactory.class) {
				if (dataSourceMap.get(configuration) == null) {
					HikariDataSource dataSource = getDataSourceInternal(configuration);
					dataSourceMap.put(configuration, dataSource);
				}
			}
		}
		return dataSourceMap.get(configuration);
	}
}
