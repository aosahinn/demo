package com.preschool.demo.utils;

import java.util.stream.Stream;

public class IgnoredUriUtils {

    public static final Stream<String> URI_STREAM = Stream.of(getIgnoredMatchers());

    public static String[] getIgnoredMatchers() {
        StringBuilder builder = new StringBuilder();
        builder.append("/v2/api-docs");
        builder.append(",/configuration/**");
        builder.append(",/swagger-resources");
        builder.append(",/swagger-resources/**");
        builder.append(",/swagger-ui.html");
        builder.append(",/h2-console");
        builder.append(",/h2-console/");
        builder.append(",/h2-console/**");
        builder.append(",/webjars/**");
        builder.append(",/lib/**");
        builder.append(",/fonts/**");
        builder.append(",/api/external/**");
        builder.append(",/api/captcha/**");

        return builder.toString().split(",");
    }


}
