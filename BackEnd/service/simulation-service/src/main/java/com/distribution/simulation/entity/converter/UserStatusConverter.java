package com.distribution.simulation.entity.converter;

import com.distribution.simulation.enums.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus,String> {

    @Override
    public String convertToDatabaseColumn(UserStatus userStatus) {
        return userStatus.getValue();
    }

    @Override
    public UserStatus convertToEntityAttribute(String s) {
        return UserStatus.getEnum(s);
    }
}
