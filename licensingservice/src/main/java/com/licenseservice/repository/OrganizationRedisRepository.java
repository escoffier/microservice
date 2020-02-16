package com.licenseservice.repository;

import com.licenseservice.model.Organization;

public interface OrganizationRedisRepository {
    void saveOrganization(Organization org);
    void updateOrganization(Organization org);
    void deleteOrganization(Long organizationId);
    Organization findOrganization(Long organizationId);
}
