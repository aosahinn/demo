package com.preschool.demo.controller.base;

/**
 * DTO-Entity-Resource Converter Interface.
 */
public interface Converter<DTO, Entity, Resource> {

    Resource toResource(Entity entity);

    Entity toEntity(DTO dto);

}
