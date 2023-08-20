package com.preschool.demo.controller.base;

import com.preschool.demo.data.entity.BaseEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public abstract class AbstractController<DTO extends AbstractDto, Entity extends BaseEntity, Resource extends AbstractResource, PK extends Serializable>
        extends ConverterController<DTO, Entity, Resource> {

    protected abstract Converter<DTO, Entity, Resource> getConverter();

    protected void clearAuditing(AbstractResource resource) {
        if (resource != null) {
            resource.setLastModifiedDate(null);
            resource.setCreatedDate(null);
            resource.setRowVersion(null);
        }
    }

    protected Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    protected Pageable getPageable(int page, int size, String direction, String... sort) {
        if (ArrayUtils.isEmpty(sort) || StringUtils.isBlank(direction)) {
            return getPageable(page, size);
        } else {
            return  PageRequest.of(page, size, Sort.Direction.valueOf(direction.toUpperCase()), sort);
        }
    }

}
