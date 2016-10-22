package com.punjuprogrammers.memberbook.ui.converter;

import java.text.ParseException;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.punjuprogrammers.memberbook.common.CommonConfiguration;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
			
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.isEmpty()) return null;
		try {
			return CommonConfiguration.DATE_FORMAT.parse(value);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot parse date: "+value);
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) return "";
		Date date = (Date)obj;
		if (date.getTime()==0) return "";
		return CommonConfiguration.DATE_FORMAT.format(date);
	}

}
