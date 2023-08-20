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
import java.util.Date;

@Slf4j
public class SimpleDateDeserializer extends JsonDeserializer<Date> {


    public static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

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
            date = DateUtils.parseDateStrictly(msDateString, SIMPLE_DATE_FORMAT_PATTERN);
        } catch (ParseException e) {
            log.error("Error while parsing date string: {}", msDateString, e);
            throw new IOException(e.getMessage());
        }
        return date;
    }
}
