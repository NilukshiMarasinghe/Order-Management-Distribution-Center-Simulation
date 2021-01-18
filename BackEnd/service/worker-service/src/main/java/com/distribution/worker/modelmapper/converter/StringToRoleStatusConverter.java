package com.distribution.worker.modelmapper.converter;


import com.distribution.worker.enums.RoleStatus;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;


@Component
public class StringToRoleStatusConverter implements Converter<String, RoleStatus> {

    @Override
    public RoleStatus convert(MappingContext<String, RoleStatus> context) {
        return RoleStatus.getEnum(context.getSource());
    }
}
