package com.organizationservice.controller;

import com.organizationservice.model.Organization;
import com.organizationservice.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/organizations")
public class OrganizationController {

    Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService service = null;

    @GetMapping(value = "/{organizationId}")
    public Organization get(@PathVariable("organizationId") Long organizationId, @RequestHeader HttpHeaders headers) {
        logger.info("Get organization by id: " + organizationId);
        //logger.info("tmx-correlation-id: ", headers.toString());
        return service.getOrg(organizationId);
    }

    @PostMapping(value = "/{organizationId}")
    public void post(@PathVariable("organizationId") String organizationId, @RequestBody Organization organization) {
        service.saveOrg(organization);
    }

    @PutMapping(value = "/{organizationId}")
    public void put(@PathVariable("organizationId") String organizationId, @RequestBody Organization organization) {
        logger.info("Update organization by id: " + organizationId);
        service.updateOrg(organization);
    }

    @DeleteMapping(value = "/{organizationId}")
    public void deleteOrg(@PathVariable("organizationId") Long organizationId) {
        service.deleteOrg(organizationId);
    }
}
