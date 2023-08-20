package com.preschool.demo.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateDeserializer extends JsonDeserializer<Date> {

    public static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String LOCAL_DATE_PATTERN_ORDERED = "dd.MM.yyyy";
    public static final String PATTERN = "yyyy.MM.dd HH:mm";
    public static final String PATTERN_WITH_ONLY_HOUR = "yyyy.MM.dd HH";
    public static final String LOCAL_DATE_PATTERN = "yyyy.MM.dd";
    public static final String PATTERN_BIRTH_DATE = "mm/dd/yyyy hh:mm:ss a";

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext arg1) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        String msDateString = node.asText();

        if (msDateString == null || msDateString.length() == 0)
            return null;

        // sample: 8/1/2018 12:00:00 AM
        Date date = null;
        try {
            date = DateUtils.parseDateStrictly(msDateString, PATTERN, PATTERN_BIRTH_DATE, PATTERN_WITH_ONLY_HOUR, LOCAL_DATE_PATTERN);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
            date = cal.getTime();
        } catch (ParseException e) {
            log.error("Error while parsing date string: {}", msDateString, e);
            throw new IOException(e.getMessage());
        }
        return date;
    }
}
