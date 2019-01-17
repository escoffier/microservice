package com.licenseservice.service;

import com.licenseservice.Client.OrganizationDiscoveryClient;
import com.licenseservice.Client.OrganizationFeignClient;
import com.licenseservice.Client.OrganizationRestTemplateClient;
import com.licenseservice.Config.ServiceConfig;
import com.licenseservice.Mapper.LicenseMapper;
import com.licenseservice.model.License;
import com.licenseservice.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    private Logger logger = LoggerFactory.getLogger(LicenseService.class);

    @Autowired
    private LicenseMapper licenseMapper = null;

    @Autowired
    private OrganizationDiscoveryClient discoveryClient  = null;

    @Autowired
    private OrganizationRestTemplateClient restTemplateClient = null;

    @Autowired
    private OrganizationFeignClient feignClient = null;

    @Autowired
    private ServiceConfig serviceConfig = null;

    private Organization retrieveOrgInfo(Long organizationId, String clientType){
        Organization organization = null;

        switch (clientType){
            case "discovery":
                logger.info("using discovery client");
                System.out.println("---discovery client");
                organization = discoveryClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("---rest client");
                organization = restTemplateClient.getOrganization(organizationId);
                break;
            case "feign":
                System.out.println("---feign client");
                organization = feignClient.getOrganization(organizationId);
        }

        return organization;

    }

    public License getLicense(Long organizationId, Long licenseId, String clientType) {
        License license = licenseMapper.getLicense(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        license.setOrganizationName(org.getName());
        license.setContactName(org.getContactName());
        license.setContactEmail(org.getContactEmail());
        license.setContactPhone(org.getContactPhone());
        license.setComment(serviceConfig.getExampleProperty());

        return license;
    }
}
