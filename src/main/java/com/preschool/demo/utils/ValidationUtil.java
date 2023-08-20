package com.preschool.demo.utils;

import java.util.regex.Pattern;

public class ValidationUtil {

    public static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String ALPHANUMERIC_PATTERN = "[\\w]+";

    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern alphanumericPattern = Pattern.compile(ALPHANUMERIC_PATTERN);

    public static boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public static boolean isValidAlphanumeric(String param) {
        return alphanumericPattern.matcher(param).matches();
    }

}
