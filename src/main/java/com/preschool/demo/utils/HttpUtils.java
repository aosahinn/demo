package com.preschool.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class HttpUtils {

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("client-ip");
        if (StringUtils.isBlank(ipAddress)) {
            ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (StringUtils.isBlank(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            } else {
                log.info("IP read from {} header", "X-FORWARDED-FOR");
            }
        } else {
            log.info("IP read from {} header", "client-ip");
        }
        return ipAddress;
    }
}
