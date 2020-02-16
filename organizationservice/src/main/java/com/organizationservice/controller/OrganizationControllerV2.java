package com.organizationservice.controller;

import com.organizationservice.model.Organization;
import com.organizationservice.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/organizations")
public class OrganizationControllerV2 {
    Logger logger = LoggerFactory.getLogger(OrganizationControllerV2.class);

    @Autowired
    private OrganizationService service = null;

    @GetMapping(value = "/{organizationId}")
    public Organization get(@PathVariable("organizationId") Long organizationId, @RequestHeader HttpHeaders headers) {
        logger.info("Get organization by id: " + organizationId);
        //logger.info("tmx-correlation-id: ", headers.toString());
        return service.getOrg(organizationId);
    }
}
