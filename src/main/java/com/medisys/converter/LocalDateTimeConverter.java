package com.medisys.converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDate) {
        return null == localDate ? null : Timestamp.valueOf(localDate);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return null == timestamp ? null : timestamp.toLocalDateTime();
    }
}