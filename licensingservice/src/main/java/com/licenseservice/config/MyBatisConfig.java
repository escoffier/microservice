package com.licenseservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.licenseservice.Mapper")
public class MyBatisConfig {
}
