package com.licenseservice.client;

import com.licenseservice.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;

@Component
public class OrganizationAuthRestTemplateClient extends OrganizationClient{


        private final static Logger logger = LoggerFactory.getLogger(com.licenseservice.client.OrganizationRestTemplateClient.class);

        @Autowired
        @Qualifier("oAuth2RestTemplate")
        private OAuth2RestOperations auth2RestTemplate = null;

        public Organization getOrganization(Long organizationId) {
            //logger.debug("In Licensing service.getOrganization: {}", UserContext.getCorrelationId());
            Organization organization = checkRedisCache(organizationId);
            if (organization != null){
                logger.info("retrieve data from redis cache successfully");
                return organization;
            }

            ResponseEntity<Organization> responseEntity = auth2RestTemplate.exchange(
                    "http://organizationservice/v1/organizations/{organizationId}",
                    //"http://zuulservice/api/organization/v1/organizations/{organizationId}",
                    HttpMethod.GET, null, Organization.class, organizationId);

            //return responseEntity.getBody();
            organization = responseEntity.getBody();
            if (organization != null){
                cacheOrgObject(responseEntity.getBody());
            }

            return organization;
        }
}
