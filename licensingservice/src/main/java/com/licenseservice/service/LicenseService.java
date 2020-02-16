package com.licenseservice.service;

import com.licenseservice.client.FeignClient.OrganizationFeignClient;
import com.licenseservice.client.OrganizationAuthRestTemplateClient;
import com.licenseservice.client.OrganizationDiscoveryClient;
import com.licenseservice.client.OrganizationRestTemplateClient;
import com.licenseservice.config.ServiceConfig;
import com.licenseservice.mapper.LicensingMapper;
import com.licenseservice.model.License;
import com.licenseservice.model.Organization;
import com.licenseservice.exceptions.LicensingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    private Logger logger = LoggerFactory.getLogger(LicenseService.class);

    @Autowired
    private LicensingMapper licensingMapper = null;

    @Autowired
    private OrganizationDiscoveryClient discoveryClient  = null;

    @Autowired
    private OrganizationRestTemplateClient restTemplateClient = null;

    @Autowired
    private OrganizationAuthRestTemplateClient authRestTemplateClient = null;

    //@Autowired
    private OrganizationFeignClient feignClient = null;

    @Autowired
    public void FeignClient(OrganizationFeignClient cleint) {
        feignClient = cleint;

    }

//    @Autowired
//    public void setFeignClient() {
//        feignClient = Feign.builder().requestInterceptor(OrganizationFeignClient.authRequestInterceptor()).target(OrganizationFeignClient.class, "http://organizationservice");
//    }

    @Autowired
    private ServiceConfig serviceConfig = null;

    private Organization retrieveOrgInfo(Long organizationId, String clientType){
        Organization organization = null;

        switch (clientType){
            case "discovery":
                logger.info("---discovery client");
                organization = discoveryClient.getOrganization(organizationId);
                break;
            case "rest":
                logger.info("---rest client");
                organization = restTemplateClient.getOrganization(organizationId);
                break;
            case "feign":
                logger.info("---feign client");
                organization = feignClient.getOrganization(organizationId.toString());
                break;
            case "authrest":
                logger.info("---auth rest client");
                organization = authRestTemplateClient.getOrganization(organizationId);
        }

        return organization;

    }

    public License getLicense(Long organizationId, Long licenseId, String clientType) {
        //License license = new License();
        License license = licensingMapper.getLicense(organizationId, licenseId);
        if (license == null) {
            throw new LicensingException(organizationId, licenseId);
        }

        Organization org = retrieveOrgInfo(organizationId, clientType);
        if (org == null) {
            throw new LicensingException(organizationId, licenseId);
        }

        license.setOrganizationName(org.getName());
        license.setContactName(org.getContactName());
        license.setContactEmail(org.getContactEmail());
        license.setContactPhone(org.getContactPhone());
        license.setComment(serviceConfig.getExampleProperty());

        return license;
    }
}
