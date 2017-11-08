package com.zcc.login.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZhangChicheng on 2017/11/8.
 */
@Configuration
@MapperScan("com.zcc.login.mapper")
public class MybatisConfig {

}
