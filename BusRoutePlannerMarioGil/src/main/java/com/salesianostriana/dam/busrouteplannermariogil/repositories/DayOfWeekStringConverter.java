package com.salesianostriana.dam.busrouteplannermariogil.repositories;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//Source - https://stackoverflow.com/q/58663485
//Posted by juuubbb, modified by community. See post 'Timeline' for change history
//Retrieved 2026-06-01, License - CC BY-SA 4.0

@Converter(autoApply = true) // I tried (autoapply=true) already but did not change anything
public class DayOfWeekStringConverter implements AttributeConverter<DayOfWeek, String> {
	@Override
	public String convertToDatabaseColumn(DayOfWeek attribute) {
		System.err.println(
				"converting dayofweek to string: " + attribute.getDisplayName(TextStyle.FULL, Locale.getDefault()));
		return attribute.getDisplayName(TextStyle.FULL, Locale.US);
	}

	@Override
	public DayOfWeek convertToEntityAttribute(String dbData) {
		System.err.println("converting str to dayofweek: " + dbData);
		return dbData == null ? null : DayOfWeek.valueOf(dbData.toUpperCase());
	}
}
