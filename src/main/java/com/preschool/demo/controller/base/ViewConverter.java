package com.preschool.demo.controller.base;

/**
 * Entity-Resource Converter Interface.
 */
public interface ViewConverter<Entity, Resource> {

    Resource toResource(Entity entity);

}
