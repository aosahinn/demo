package com.preschool.demo.controller.mapper;

import java.util.List;
import java.util.Set;

public interface BaseMapper<DTO, Entity, Resource>
{

    Resource toResource(Entity entity);

    List<Resource> toResource(List<Entity> entities);

    Set<Resource> toResource(Set<Entity> entities);

    Entity toEntity(DTO dto);

    List<Entity> toEntity(List<DTO> dtos);

    Set<Entity> toEntity(Set<DTO> dtos);

}
