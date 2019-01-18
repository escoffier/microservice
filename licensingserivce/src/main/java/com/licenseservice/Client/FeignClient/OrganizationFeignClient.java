package com.licenseservice.Client.FeignClient;

import com.licenseservice.model.Organization;
import com.licenseservice.utils.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.cloud.openfeign.FeignClientsConfiguration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.bind.annotation.PathVariable;

//@Component
//@Import(FeignClientsConfiguration.class)
@FeignClient(name = "organizationservice", configuration = FeignConfig.class)
public interface OrganizationFeignClient{
    Logger logger = LoggerFactory.getLogger(OrganizationFeignClient.class);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/organizations/{organizationId}", consumes = "application/json")
    //@RequestLine("GET /v1/organizations/{organizationId}")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);

     static RequestInterceptor authRequestInterceptor()
    {
        return new RequestInterceptor(){
            @Override
            public void apply(RequestTemplate requestTemplate) {
                logger.info("OrganizationFeignClient add Authorization header: {UserContextHolder.getContext().getAuthToken()}");
                requestTemplate.header("Authorization", UserContextHolder.getContext().getAuthToken());
            }
        };
    }

}
