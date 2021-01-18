package com.distribution.simulation.entity.converter;


import com.distribution.simulation.enums.WorkerEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class WorkerConverter implements AttributeConverter<WorkerEnum, String> {
    @Override
    public String convertToDatabaseColumn(WorkerEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public WorkerEnum convertToEntityAttribute(String dbData) {
        return WorkerEnum.getWorkerEnum(dbData);
    }
}
