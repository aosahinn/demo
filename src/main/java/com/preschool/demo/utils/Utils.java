package com.preschool.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)
                .enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ;


    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String toPrettyJsonString(Object object) throws Exception {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public static String serializeToString(Object value) {
        String result;
        try {
            result = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <O> O deserializeFromString(String str, Class<O> objClass) {
        O result = null;
        if (str != null && objClass != null) {
            try {

                result = objectMapper.readValue(str, objClass);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static <O> List<O> deserializeFromStringToList(String str, Class<O> objClass) {
        List<O> result = null;
        if (str != null && objClass != null) {
            try {

                result = objectMapper.readValue(str, objectMapper.getTypeFactory().constructCollectionType(List.class, objClass));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static <O> O converMapToObject(Map map, Class<O> objClass) {
        O result = null;
        if (MapUtils.isNotEmpty(map) && objClass != null) {
            try {

                result = objectMapper.convertValue(map, objClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

}
