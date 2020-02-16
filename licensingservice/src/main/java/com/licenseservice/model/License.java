package com.licenseservice.model;

import org.apache.ibatis.type.Alias;
import org.springframework.data.annotation.Transient;

@Alias("license")
public class License {

    private Long licenseId;


    private Long organizationId;

    @Transient
    private String organizationName ="";

    @Transient
    private String contactName ="";

    @Transient
    private String contactPhone ="";

    @Transient
    private String contactEmail ="";


    private String productName;


    private String licenseType;


    private Integer licenseMax;


    private Integer licenseAllocated;


    private String comment;

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Integer getLicenseMax() {
        return licenseMax;
    }

    public void setLicenseMax(Integer licenseMax) {
        this.licenseMax = licenseMax;
    }

    public Integer getLicenseAllocated() {
        return licenseAllocated;
    }

    public void setLicenseAllocated(Integer licenseAllocated) {
        this.licenseAllocated = licenseAllocated;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
