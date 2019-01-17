package com.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;

    @Autowired
    private FilterUtils filterUtil;

    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private boolean isCorrelationIdPresent() {
        if (filterUtil.getCorrelationId() != null){
            return true;
        }

        return false;
    }

    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public Object run() throws ZuulException {
        if (isCorrelationIdPresent()) {
            logger.info("tmx-correlation-id found in tracking filter: {}.", filterUtil.getCorrelationId());
        } else {
            filterUtil.setCorrelationId(generateCorrelationId());
            logger.info("tmx-correlation-id generated in tracking filter: {}.", filterUtil.getCorrelationId());
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        logger.info("Processing incoming request for: {}.", ctx.getRequest().getRequestURI());
        return null;
    }
}
