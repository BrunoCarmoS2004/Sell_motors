package br.com.c137.project.sellmotors.leadcommand.utils;

import org.modelmapper.ModelMapper;

public class MapperUtil<S,T> {

    private final ModelMapper modelMapper;
    private final Class<S> sourceType;
    private final Class<T> targetType;

    public MapperUtil(ModelMapper modelMapper, Class<S> sourceType, Class<T> targetType) {
        this.modelMapper = modelMapper;
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    public T convertToTarget(S source) {
        return modelMapper.map(source, targetType);
    }

    public S convertToSource(T target) {
        return modelMapper.map(target,sourceType);
    }

    public void updateSource(T dto, S entity) {
        modelMapper.map(dto, entity);
    }
}
