package com.preschool.demo.utils;

import org.springframework.http.HttpHeaders;

public class FileHeaderUtil {
    public static HttpHeaders fileHttpHeaders(String fileName, String fileExtension) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-control", "must-revalidate, post-check=0, pre-check=0");
        headers.add("Expires", "0");
        headers.add("Pragma", "public");
        headers.add("Content-Disposition", "inline; filename=" + fileName + fileExtension);

        return headers;
    }

    public static HttpHeaders fileHttpHeaders(String fullFileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-control", "must-revalidate, post-check=0, pre-check=0");
        headers.add("Expires", "0");
        headers.add("Pragma", "public");
        headers.add("Content-Disposition", "inline; filename=" + fullFileName);

        return headers;
    }
}
