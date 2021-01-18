package com.distribution.worker.entity.converter;


import com.distribution.worker.enums.WorkerEnum;

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
