package com.organizationservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.organizationservice.Mapper")
public class ServiceConfig {
}
