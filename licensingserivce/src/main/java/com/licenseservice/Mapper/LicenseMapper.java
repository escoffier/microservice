package com.licenseservice.Mapper;

import com.licenseservice.model.License;
import org.apache.ibatis.annotations.Param;

public interface LicenseMapper {
    public License getLicense(@Param("organizationid") Long organizationId, @Param("licenseid") Long licenseId);
    public void saveLicense(License license);
}
