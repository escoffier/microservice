package com.organizationservice.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import utils.UserContextHolder;

@Component
@EnableBinding(Source.class)
public class SimpleSourceBean {
    private Source source;

    private final static Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishOrg(String action, Long orgId) {
        logger.info("Sending kafka message {} for org id {}", action, orgId);

        OrgChangeModel changeModel = new OrgChangeModel(OrgChangeModel.class.getTypeName(), action, orgId, UserContextHolder.getContext().getCorrelationId());
        source.output().send(MessageBuilder.withPayload(changeModel).build());

    }
}
