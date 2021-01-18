package com.distribution.simulation.modelmapper.converter;

import com.distribution.simulation.enums.UserStatus;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@ConfigurationPropertiesBinding
@Component
public class UserStatusToStringConverter implements Converter<UserStatus, String> {

	@Override
	public String convert(MappingContext<UserStatus, String> context) {
		if (context.getSource() == null) {
			return null;
		}
		return context.getSource().getLabel();
	}

}
