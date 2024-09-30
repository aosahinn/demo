package com.preschool.demo.type;

import java.math.BigDecimal;

/**
 * Created by kamuranyilmaz on 04/06/2017.
 */
public enum DefaultSettingType {

    THRESHOLD_PERCENTAGE_OF_ACADEMICIAN_ROLL_CALL(FieldType.INTEGER, 80),
    THRESHOLD_PERCENTAGE_OF_STUDENT_ROLL_CALL(FieldType.INTEGER, 60),
    ;

    private FieldType type;
    private String defaultVal;

    DefaultSettingType(FieldType type, Boolean defaultVal) {
        this.type = type;
        this.defaultVal = String.valueOf(defaultVal);
    }

    DefaultSettingType(FieldType type, BigDecimal decimal) {
        this.type = type;
        this.defaultVal = decimal.toString();
    }

    DefaultSettingType(FieldType type, Integer integer) {
        this.type = type;
        this.defaultVal = String.valueOf(integer);
    }

    DefaultSettingType(FieldType type, Double val) {
        this.type = type;
        this.defaultVal = String.valueOf(val);
    }

    DefaultSettingType(FieldType type, Long val) {
        this.type = type;
        this.defaultVal = String.valueOf(val);
    }

    DefaultSettingType(FieldType type, String defaultVal) {
        this.type = type;
        this.defaultVal = defaultVal;
    }

    public FieldType getType() {
        return type;
    }

    public String getDefaultVal() {
        return defaultVal;
    }
}
