package com.licenseservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
	
	@Value("${example.property}")
	private String exampleProperty;
	
	public String getExampleProperty() {
		return exampleProperty;
	}

    @Value("${redis.host}")
    private String redisServer="";

    @Value("${redis.port}")
    private String redisPort="";

    public void setExampleProperty(String exampleProperty) {
        this.exampleProperty = exampleProperty;
    }

    public String getRedisServer() {
        return redisServer;
    }

    public void setRedisServer(String redisServer) {
        this.redisServer = redisServer;
    }

    public Integer getRedisPort() {
        return new Integer(redisPort).intValue();
    }

    public void setRedisPort(String redisPort) {
        this.redisPort = redisPort;
    }

//    @Bean
//    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
//        return new ProtobufHttpMessageConverter();
//    }

    @Bean
    ProtobufJsonFormatHttpMessageConverter protobufJsonFormatHttpMessageConverter() {
        return new ProtobufJsonFormatHttpMessageConverter();
    }

}
