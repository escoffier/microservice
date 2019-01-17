package com.licenseservice.events;

public class OrgChangeModel {
    private String typeName;
    private String action;
    private Long orgId;
    private String correlationId;

    public OrgChangeModel(String typeName, String action, Long orgId, String correlationId) {
        this.typeName = typeName;
        this.action = action;
        this.orgId = orgId;
        this.correlationId = correlationId;
    }

    public  OrgChangeModel(){

    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
