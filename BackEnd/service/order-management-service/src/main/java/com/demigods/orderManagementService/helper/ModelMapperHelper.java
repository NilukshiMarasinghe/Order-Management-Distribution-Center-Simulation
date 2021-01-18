package com.demigods.orderManagementService.helper;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@NoArgsConstructor
public class ModelMapperHelper {

    @Autowired
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <D, T> List<D> mapList(final Collection<T> entityList, Class<D> outClass) {
        return entityList.stream().map(entity -> mapObject(entity, outClass)).collect(Collectors.toList());
    }

    public <E, T> T mapObject(E source, Class<T> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }


    public <E, T> T mapObjectWithCustomMapping(E source, Class<T> destinationClass, PropertyMap<E, T> propertyMap) {
        ModelMapper tempModelMapper = new ModelMapper();
        tempModelMapper.addMappings(propertyMap);
        return tempModelMapper.map(source, destinationClass);
    }
}