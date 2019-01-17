package com.licenseservice.Client;

import com.licenseservice.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("organizationservice")
public interface OrganizationFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/organizations/{organizationId}", consumes = "application/json")
    public Organization getOrganization(@PathVariable("organizationId") Long organizationId);
}
