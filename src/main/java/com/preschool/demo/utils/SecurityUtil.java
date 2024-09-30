package com.preschool.demo.utils;

public class SecurityUtil {

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
        builder.append(",/actuator/**");
        builder.append(",/actuator");
        builder.append(",/*/actuator/**");
        builder.append(",/webjars/**");
        builder.append(",/lib/**");
        builder.append(",/fonts/**");
        builder.append(",index.html");
        builder.append(",/login");
        builder.append(",/login/form**");
        builder.append(",/register");
        builder.append(",/logout");
        builder.append(",/languages/all");
        builder.append(",/i18ns/summary");
        builder.append(",/oauth/token");
        builder.append(",/backoffice");
        builder.append(",/backoffice/**");
        builder.append(",/static/**");
        builder.append(",/resources/static/**");
        builder.append(",/");

        return builder.toString().split(",");
    }
}
