package com.microservicios.wastemanageraddressservice.infra.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GlobalJsonNumberSerializer extends JsonSerializer<Number> {

    @Override
    public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (number == null)
            return;
        final DecimalFormat formatter = new DecimalFormat("#.##");
        String output;
        if (number instanceof Float) {
            BigDecimal value = new BigDecimal(number.floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
            output = formatter.format(value);
        } else if (number instanceof Double) {
            BigDecimal value = new BigDecimal(number.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
            output = formatter.format(value);
        } else if (number instanceof BigDecimal) {
            BigDecimal value = ((BigDecimal) number).setScale(2, BigDecimal.ROUND_HALF_UP);
            output = formatter.format(value);
        } else {
            output = number.toString();
        }
        jsonGenerator.writeNumber(output);
    }
}
