package com.licenseservice.controller;

import com.licenseservice.model.License;
import com.licenseservice.service.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/organizations/{organizationId}/licenses")
public class LicenseController {
    private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

    @Autowired
    private LicenseService licenseService = null;

    @GetMapping("/{licenseId}/{clientType}")
    public License getLicense(@PathVariable("organizationId") Long organizationId,
                              @PathVariable("licenseId") Long licenseId,
                              @PathVariable("clientType") String clientType) {

        logger.info("Entering the license-service-controller  ");
        return licenseService.getLicense(organizationId, licenseId, clientType);
    }

}
