package com.licenseservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(UserContextInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();
        logger.info("-----UserContextInterceptor: ", headers.toString());
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());

//        if (headers.getFirst("Authorization").isEmpty()){
//            logger.info("-----UserContextInterceptor set Authorization header ", UserContextHolder.getContext().getAuthToken());
//            headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
//        }
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
        return execution.execute(request, body);
    }
}
