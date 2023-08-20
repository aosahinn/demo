package com.preschool.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
public final class MyDateUtils {

    public static final String DATE = "yyyy-MM-dd";

    private MyDateUtils() {
    }

    /**
     * @return year information
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * starts from 1
     *
     * @return month infromation
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * Aksigorta services uses this format
     *
     * @return year*100+month
     */
    public static int getCurrentDate() {
        return getCurrentYear() * 100 + getCurrentMonth();
    }

    public static int getPreviousDate() {
        return getCurrentDate() % 100 == 1 ? (getCurrentYear() - 1) * 100 + 12 : getCurrentDate() - 1;
    }  //returns one month ago in year+month format

    public static String getYearForInput(Integer var) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        if (var == null) {
            LocalDateTime curDate = LocalDateTime.now();
            return formatter.format(curDate);
        }
        LocalDateTime curDate = LocalDateTime.now().minusYears(var);
        return formatter.format(curDate);
    }

    public static int getNextDate() {
        return getCurrentDate() % 100 == 12 ? (getCurrentYear() + 1) * 100 + 1 : getCurrentDate() + 1;
    }  //returns one month later in year+month format

    public static int getStartDate() {
        return MyDateUtils.getCurrentYear() * 100 + 1;
    }

    public static int getQuarter(int month) {
        if (month == 1 || month == 2 || month == 3)
            return 0;
        else if (month == 4 || month == 5 || month == 6)
            return 1;
        else if (month == 7 || month == 8 || month == 9)
            return 2;
        else
            return 3;
    }

    public static List<Integer> getQuartersLastMonthOnCurrentYear(int month) {
        int currentQuarter = getQuarter(month);
        List<Integer> months = new ArrayList<>();

        for (int i = 1; i < currentQuarter + 1; i++) {
            months.add(i * 3);
        }
        months.add(month);

        return months;
    }

    public static Integer getQuarterLastMonthOnCurrentQuarter() {
        return (getQuarter(getCurrentMonth()) + 1) * 3;
    }

    public static String getDateWithFormat() {
        return format(new Date());
    }

/*    public static Date parseDate(String dateStr) {

        Date dt = null;
        try {
            dt = DateUtils.parseDateStrictly(dateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            log.error("Error while parsing date string: {}", dateStr, e);
            throw new BadRequestException("Tarih formati yanlış");
        }

        return dt;
    }*/

    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static int parseMonth(int yearMonth) {
        return yearMonth - MyDateUtils.getStartDate() + 1;
    }

    public static String format(Date date) {
        return format(date, "yyyy-MM-dd'T'HH:mm:ssZ");
    }

    public static String format(ZonedDateTime date) {
        return format(Date.from(date.toInstant()), "yyyy-MM-dd'T'HH:mm:ssZ");
    }

    public static String format(Date date, String formatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return dateFormat.format(date);
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getLastDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static Date getFirstDateOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
        return cal.getTime();
    }

    public static Date getLastDateOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static Date getBeforeMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    public static Date getDateForInputDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String dateToString(Date date) {
        if (date != null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(date);
        } else {
            return StringUtils.EMPTY;
        }
    }

    public static Date XMLGregorianCalendartoDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    public static int getStartDate(int year) {
        return year * 100 + 1;
    }

    public static int getEndDate(int year) {
        return year * 100 + 12;
    }

    public static ZonedDateTime addWorkdays(ZonedDateTime date, int workdays) {
        if (workdays < 1) {
            return date;
        }

        ZonedDateTime result = date;
        int addedDays = 0;
        while (addedDays < workdays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }

        return result;
    }

    /**
     * The result will be negative if the end is before the start.
     */
    public static long zonedDateTimeDifference(ZonedDateTime start, ZonedDateTime end, ChronoUnit unit) {
        return unit.between(start, end);
    }

    /**
     * The result will be negative if the end is before the start.
     */
    public static long localDateDifference(LocalDate start, LocalDate end, ChronoUnit unit) {
        return unit.between(start, end);
    }

    public static int prepareDate(int year, int month) {
        return year * 100 + month;
    }

    public static int getDateWithCurrentQuarterOfLastYear() {
        return prepareDate(getCurrentYear() - 1, (getQuarter(getCurrentMonth()) + 1) * 3);
    }

    public static int getPreviousYearMonthDate() {
        return getCurrentDate() - 100;
    }
}
