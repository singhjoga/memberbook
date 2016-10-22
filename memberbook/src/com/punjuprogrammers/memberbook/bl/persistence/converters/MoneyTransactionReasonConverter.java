package com.punjuprogrammers.memberbook.bl.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction.Reason;

@Converter
public class MoneyTransactionReasonConverter implements AttributeConverter<Reason, Integer>{

	@Override
	public Integer convertToDatabaseColumn(Reason unit) {
		return unit.getValue();
	}

	@Override
	public Reason convertToEntityAttribute(Integer value) {
		return Reason.fromValue(value);
	}
}
