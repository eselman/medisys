package com.medisys.converter;
import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        return null == localTime ? null : Time.valueOf(localTime);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        return null == time ? null : time.toLocalTime();
    }
}