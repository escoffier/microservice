package com.licenseservice.events;

import com.licenseservice.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {

    private final static Logger logger = LoggerFactory.getLogger(OrganizationChangeHandler.class);

    @Autowired
    OrganizationRedisRepository redisRepository;

    @StreamListener("inboundOrgChanges")
    public void loggerSink(OrgChangeModel orgChange) {
        logger.info("----Received a message of type " + orgChange.getTypeName());

        switch (orgChange.getTypeName()){
            case "get":
                logger.debug("Received a GET event from the organization service for organization id {}", orgChange.getOrgId());
                break;
            case "save":
                logger.debug("Received a SAVE event from the organization service for organization id {}", orgChange.getOrgId());
                //redisRepository.saveOrganization(orgChange);
            case "update":
                logger.debug("Received a UPDATE event from the organization service for organization id {}", orgChange.getOrgId());
                redisRepository.deleteOrganization(orgChange.getOrgId());
                break;
            case "delete":
                logger.debug("Received a DELETE event from the organization service for organization id {}", orgChange.getOrgId());
                redisRepository.deleteOrganization(orgChange.getOrgId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the organization service of type {}", orgChange.getTypeName());
                break;

        }
    }

}
