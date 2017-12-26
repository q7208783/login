package com.zcc.login.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zcc.login.common.constant.DataSourceEnum;
import com.zcc.login.common.contextholder.DynamicDataSource;
import com.zcc.login.common.interceptor.PageInfoInterceptor;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
@Configuration
@MapperScan(basePackages = "com.zcc.login.mapper")
public class MybatisConfig {
	@Value("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;

	@Autowired
	PageInfoInterceptor infoInterceptor;

	@Bean("dynamicDataSource")
	public DynamicDataSource dynamicDataSource(@Qualifier("dsMap") Map<DataSourceEnum, DataSource> dsMap) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.putAll(dsMap);
		DataSource defaultDs = dsMap.get(DataSourceEnum.LOGIN);
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(defaultDs);// 默认的datasource设置为myTestDbDataSource

		return dataSource;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource,
		@Value("mybatis.mapperLocations") String mapperLocations) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dynamicDataSource);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		factoryBean.setTypeAliasesPackage(typeAliasesPackage);// 指定基包
		factoryBean.setPlugins(new Interceptor[]{infoInterceptor});
		factoryBean.setVfs(SpringBootVFS.class);
		//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));//
		return factoryBean.getObject();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}
}
