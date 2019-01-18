package com.licenseservice.Client;

import com.licenseservice.model.Organization;
import com.licenseservice.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class OrganizationClient {

    private final static Logger logger = LoggerFactory.getLogger(OrganizationClient.class);


    @Autowired
    private OrganizationRedisRepository redisRepository;

    protected Organization checkRedisCache(Long orgId){
        try {
            return redisRepository.findOrganization(orgId);
        } catch (Exception ex) {
            logger.error("error occurs when retrieve redis cache");
            return null;
        }
    }

    protected void cacheOrgObject(Organization organization){
        try {
            redisRepository.saveOrganization(organization);
        } catch (Exception ex){
            logger.error("error occurs when saving redis cache : {}", ex);
        }
    }

}
