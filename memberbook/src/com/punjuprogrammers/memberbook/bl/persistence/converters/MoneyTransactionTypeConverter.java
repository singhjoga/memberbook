package com.punjuprogrammers.memberbook.bl.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction.Type;

@Converter
public class MoneyTransactionTypeConverter implements AttributeConverter<Type, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Type unit) {
		return unit.getValue();
	}

	@Override
	public Type convertToEntityAttribute(Integer value) {
		return Type.fromValue(value);
	}
}
