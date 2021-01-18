package com.distribution.simulation.validation.validator;


import com.distribution.simulation.componant.StaticApplicationContext;
import com.distribution.simulation.config.ConfigProperties;
import com.distribution.simulation.validation.annotation.PasswordPolicy;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;


public class PasswordPolicyValidator implements ConstraintValidator<PasswordPolicy, CharSequence> {

    private static final Log log = LoggerFactory.make(MethodHandles.lookup());

    private java.util.regex.Pattern pattern;

    @Override
    public void initialize(PasswordPolicy parameters) {
        PasswordPolicy.Flag[] flags = parameters.flags();
        int intFlag = 0;
        for (PasswordPolicy.Flag flag : flags) {
            intFlag = intFlag | flag.getValue();
        }

        try {
            pattern = java.util.regex.Pattern.compile(StaticApplicationContext.getApplicationContext()
                    .getBean(ConfigProperties.class).getPasswordPolicy(), intFlag);
        } catch (PatternSyntaxException e) {
            throw log.getInvalidRegularExpressionException(e);
        }
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
