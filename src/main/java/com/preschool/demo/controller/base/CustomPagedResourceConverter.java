package com.preschool.demo.controller.base;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomPagedResourceConverter {

    public static <T> CustomPagedResource<List<T>> map(Page<T> page) {

        return map(page, null, null);
    }

    public static <T> CustomPagedResource<List<T>> map(Page<T> page, String sort, String direction) {

        return new CustomPagedResource<>(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize(), sort, direction, page.getContent());
    }

    public static <T> CustomPagedResource<List<T>> pagination(List<T> data, int page, int size, String sort, String direction, Comparator comparator) {

        int totalElements = 0;
        int pageCount = 0;
        List<T> paged = new ArrayList<>();
        Field f;
        if (CollectionUtils.isNotEmpty(data)) {
            totalElements = data.size();
            pageCount = totalElements / size + 1;
            if(comparator != null) {
                data.sort("ASC".equalsIgnoreCase(direction) ? comparator : comparator.reversed());
            }
            if (page <= pageCount) {
                int start = page * size;
                int end = start + size > data.size() ? data.size() : start + size;
                paged = data.subList(start, end);
            }
        }

        return new CustomPagedResource((long) totalElements, pageCount, page, size, sort, direction, paged);

    }


}
