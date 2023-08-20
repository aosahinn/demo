package com.preschool.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal multiplyWithOneHundred(BigDecimal value) {
        return value != null ? value.multiply(ONE_HUNDRED) : BigDecimal.ZERO;
    }

    public static BigDecimal zeroIfNull(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    public static BigDecimal zeroIfNull(String value) {
        return StringUtils.isBlank(value) ? BigDecimal.ZERO : new BigDecimal(value);
    }

    public static BigDecimal zeroIfNull(Integer value) {
        return value == null ? BigDecimal.ZERO : new BigDecimal(value);
    }

    public static BigDecimal multiplyWithOneHundredAndRoundUp(BigDecimal value) {
        return multiplyWithOneHundred(value).setScale(0, RoundingMode.HALF_UP);
    }
}
