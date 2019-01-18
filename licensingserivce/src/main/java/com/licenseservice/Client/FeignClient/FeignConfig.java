package com.licenseservice.Client.FeignClient;

import com.licenseservice.utils.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    RequestInterceptor authRequestInterceptor()
    {
        return new RequestInterceptor(){
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("Authorization", UserContextHolder.getContext().getAuthToken());
            }
        };
    }
}
