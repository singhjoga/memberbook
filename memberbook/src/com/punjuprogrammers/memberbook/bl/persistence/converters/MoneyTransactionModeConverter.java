package com.punjuprogrammers.memberbook.bl.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction.Mode;

@Converter
public class MoneyTransactionModeConverter implements AttributeConverter<Mode, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Mode unit) {
		return unit.getValue();
	}

	@Override
	public Mode convertToEntityAttribute(Integer value) {
		return Mode.fromValue(value);
	}
}
