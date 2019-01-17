package com.organizationservice.Mapper;

import com.organizationservice.model.Organization;

public interface OrganizationMapper {

    public Organization getOrg(Long organizationId);

    public void saveOrg(Organization org);

    public void updateOrg(Organization org);

    public void deleteOrg(Long organizationId);
}
