package com.licenseservice.client.FeignClient;

import com.licenseservice.model.Organization;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient("organizationservice")
public interface CustomFeignClinet {

    //@RequestMapping(method = RequestMethod.GET, value = "/v1/organizations/{organizationId}", consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") Long organizationId);

//    default RequestInterceptor authRequestInterceptor()
//    {
//        return new RequestInterceptor(){
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                requestTemplate.header("Authorization", UserContextHolder.getContext().getAuthToken());
//            }
//        };
//    }
}
