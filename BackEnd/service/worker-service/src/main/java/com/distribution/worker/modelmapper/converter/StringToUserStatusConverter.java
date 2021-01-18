package com.distribution.worker.modelmapper.converter;


import com.distribution.worker.enums.UserStatus;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@ConfigurationPropertiesBinding
@Component
public class StringToUserStatusConverter implements Converter<String, UserStatus> {

    @Override
    public UserStatus convert(MappingContext<String, UserStatus> context) {
        return UserStatus.getEnum(context.getSource());
    }

}
