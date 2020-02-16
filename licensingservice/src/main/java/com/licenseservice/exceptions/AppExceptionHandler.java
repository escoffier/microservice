package com.licenseservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(LicensingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody  Error licensingHandler(LicensingException ex) {
        Long orgId = ex.getOrganizationId();
        Long licensingId = ex.getLicenseId();
        return new Error(4, "Licensing [ LicenseId: " + licensingId +
                ", OrganizationId: " + orgId + " ] not found");
    }
}
