package com.distribution.simulation.modelmapper.converter;

import com.distribution.simulation.enums.RoleStatus;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;


@Component
public class RoleStatusToStringConverter implements Converter<RoleStatus, String> {

	@Override
	public String convert(MappingContext<RoleStatus, String> context) {
		if (context.getSource() == null) {
			return null;
		}
		return context.getSource().getValue();
	}
}
