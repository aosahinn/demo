package com.preschool.demo.controller.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomPagedResource<T> {

    private PageResource page;
    private T data;

    private Map<String, Object> extras;

    public CustomPagedResource(Long totalElements, Integer totalPages, Integer page, Integer size, String sort, String direction, T data) {
        this.data = data;
        this.page = new PageResource(totalElements, totalPages, page, size, sort, direction);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class PageResource {
        private Long totalElements;
        private Integer totalPages;
        private Integer page;
        private Integer size;
        private String sort;
        private String direction;
    }
}
