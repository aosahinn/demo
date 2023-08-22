package com.preschool.demo.utils.enums;

public enum PeriodType {
    MONTHLY("A"),
    YEARLY("Y");


    private String value;

    PeriodType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
