package com.preschool.demo.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ReportDateUtil {

    public static Date nowMinus5Min() {
        Date date = new Date();
        return DateUtils.addMinutes(date, -5);
    }

    public static Date yesterdayMidNight() {
        LocalDateTime midnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        return Date.from(midnight.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date lastReportDate() {
        LocalTime limit = LocalTime.of(18, 30);

        LocalDateTime midnight = LocalDateTime.now().toLocalTime().compareTo(limit) > 0 ?
                LocalDateTime.of(LocalDate.now(), limit)
                : LocalDateTime.of(LocalDate.now().minus(1, ChronoUnit.DAYS), limit);
        return Date.from(midnight.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String convertToEnglish(String str) {
        if (str != null) {
            return Normalizer.normalize(str, Normalizer.Form.NFD);
        }
        return "";

    }
}
