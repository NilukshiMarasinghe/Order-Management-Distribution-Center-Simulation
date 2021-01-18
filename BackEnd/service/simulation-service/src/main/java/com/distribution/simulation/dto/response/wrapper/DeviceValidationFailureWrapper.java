package com.distribution.simulation.dto.response.wrapper;


import com.distribution.simulation.dto.validation.DeviceValidationFailure;
import com.distribution.simulation.enums.RestApiResponseStatus;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class DeviceValidationFailureWrapper extends BaseResponseWrapper {

    private List<DeviceValidationFailure> DeviceValidationFailures;


    public DeviceValidationFailureWrapper(List<DeviceValidationFailure> deviceValidationFailures) {
        super(RestApiResponseStatus.OK.VALIDATION_FAILURE);
        this.DeviceValidationFailures = deviceValidationFailures;
    }

    public DeviceValidationFailureWrapper(String field, String code, String source) {
        super(RestApiResponseStatus.VALIDATION_FAILURE);
        DeviceValidationFailure vf = new DeviceValidationFailure(field, code, source);
        this.DeviceValidationFailures = Collections.singletonList(vf);
    }

    public DeviceValidationFailureWrapper(String field, String code) {
        super(RestApiResponseStatus.VALIDATION_FAILURE);
        DeviceValidationFailure vf = new DeviceValidationFailure(field, code);
        this.DeviceValidationFailures = Collections.singletonList(vf);
    }


}
