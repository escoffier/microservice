package com.licenseservice.Client;

import com.licenseservice.model.Organization;
import com.licenseservice.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {

    private final static Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate = null;

    @Autowired
    OrganizationRedisRepository redisRepository;

    private Organization checkRedisCache(Long orgId){
        try {
            return redisRepository.findOrganization(orgId);
        } catch (Exception ex) {
            logger.error("error occurs when retrieve redis cache");
            return null;
        }
    }

    private void cacheOrgObject(Organization organization){
        try {
            redisRepository.saveOrganization(organization);
        } catch (Exception ex){
            logger.error("error occurs when saving redis cache");
        }
    }


    public Organization getOrganization(Long organizationId) {

//        Organization organization = checkRedisCache(organizationId);
//        if (organization != null){
//            logger.info("retrieve data from redis cache successfully");
//            return organization;
//        }

        //logger.debug("In Licensing service.getOrganization: {}", UserContext.getCorrelationId());

        ResponseEntity<Organization> responseEntity = restTemplate.exchange(
                "http://organizationservice/v1/organizations/{organizationId}",
                //"http://zuulservice/api/organization/v1/organizations/{organizationId}",
                HttpMethod.GET, null, Organization.class, organizationId);

//        organization = responseEntity.getBody();
//        if (organization != null){
//            cacheOrgObject(responseEntity.getBody());
//        }
//
//        return organization;
        return responseEntity.getBody();
    }
}
