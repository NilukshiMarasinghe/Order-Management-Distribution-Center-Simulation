package com.distribution.simulation.dto.response.wrapper;



import java.util.Collections;
import java.util.List;
import com.distribution.simulation.dto.validation.ValidationFailure;
import com.distribution.simulation.enums.RestApiResponseStatus;

public class ValidationFailureResponseWrapper extends BaseResponseWrapper {

    private List<ValidationFailure> ValidationFailures;


    public ValidationFailureResponseWrapper(List<ValidationFailure> ValidationFailures) {
        super(RestApiResponseStatus.OK.VALIDATION_FAILURE);
        this.ValidationFailures = ValidationFailures;
    }

    public ValidationFailureResponseWrapper(String field, String code) {
        super(RestApiResponseStatus.VALIDATION_FAILURE);
        ValidationFailure vf = new ValidationFailure(field, code);
        this.ValidationFailures = Collections.singletonList(vf);
    }

    public List<ValidationFailure> getValidationFailures() {
        return ValidationFailures;
    }

    public void setValidationFailures(List<ValidationFailure> validationFailures) {
        ValidationFailures = validationFailures;
    }


}
