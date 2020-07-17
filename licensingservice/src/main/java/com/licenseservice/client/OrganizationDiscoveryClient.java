package com.licenseservice.client;

import com.licenseservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrganizationDiscoveryClient {
    @Autowired
    private DiscoveryClient discoveryClient = null;

    public Organization getOrganization(Long organizationId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("organizationservice");

        if (serviceInstances.size() ==0) return  null;

        String serviceUrl = String.format("%s/v1/organizations/%d",
                serviceInstances.get(0).getUri().toString(), organizationId);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Organization> restExchange = restTemplate.exchange(serviceUrl, HttpMethod.GET, null,
                Organization.class, organizationId);

        return  restExchange.getBody();
    }
}
