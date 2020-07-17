package com.licenseservice.mapper;

import com.licenseservice.model.License;
import org.apache.ibatis.annotations.Param;

public interface LicensingMapper {
    public License getLicense(@Param("organizationid") Long organizationId, @Param("licenseid") Long licenseId);
    public void saveLicense(License license);
}
