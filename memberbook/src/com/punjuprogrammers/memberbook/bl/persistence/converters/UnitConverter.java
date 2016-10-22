package com.punjuprogrammers.memberbook.bl.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.punjuprogrammers.memberbook.bl.model.Unit;

@Converter
public class UnitConverter implements AttributeConverter<Unit, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Unit unit) {
		return unit.getValue();
	}

	@Override
	public Unit convertToEntityAttribute(Integer value) {
		return Unit.fromValue(value);
	}
}
