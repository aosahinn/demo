package com.preschool.demo.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class RateSerializer extends JsonSerializer<BigDecimal> {

    private static BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {

        DecimalFormat df = new DecimalFormat("##");
        jgen.writeString("% " + df.format(value.multiply(ONE_HUNDRED)));
    }

}