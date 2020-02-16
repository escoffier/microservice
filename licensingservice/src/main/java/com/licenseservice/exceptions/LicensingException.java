package com.licenseservice.exceptions;

public class LicensingException extends RuntimeException {
    private Long organizationId;
    private Long licenseId;

    public LicensingException(String message, Long organizationId, Long licenseId) {
        super(message);
        this.organizationId = organizationId;
        this.licenseId = licenseId;
    }

    public LicensingException( Long organizationId, Long licenseId) {
        this.organizationId = organizationId;
        this.licenseId = licenseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }
}
