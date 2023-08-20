package com.preschool.demo.controller.base;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ConverterController<DTO, Entity, Resource> {

    protected abstract Converter<DTO, Entity, Resource> getConverter();

    public List<Resource> toResource(List<Entity> all) {
        if (CollectionUtils.isNotEmpty(all)) {
            return all.stream().map(e -> getConverter().toResource(e)).collect(Collectors.toList());
        }
        return null;
    }

    public Resource toResource(Entity entity) {
        if (entity != null) {
            return getConverter().toResource(entity);
        }
        return null;
    }

    protected Resource toResource(Optional<Entity> entity) {
        return entity.map(this::toResource).orElse(null);
    }

}
