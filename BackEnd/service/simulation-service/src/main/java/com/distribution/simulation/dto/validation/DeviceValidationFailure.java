package com.distribution.simulation.dto.validation;



import lombok.Data;


@Data
public class DeviceValidationFailure extends ValidationFailure {

    private String source;

    public DeviceValidationFailure(String field, String code, String source) {
        super(field, code);
        this.source = source;
    }

    public DeviceValidationFailure(String field, String code) {
        super(field, code);
    }
}
