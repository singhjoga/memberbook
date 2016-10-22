package com.punjuprogrammers.memberbook.ui.converter;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter("expiryDateConverter")
public class ExpiryDateConverter extends DateConverter {
			
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		//Do nothing. This converter is only for display
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) return "";
		Date date = (Date)obj;
		if (date.getTime()==0) return "";
		if (date.getYear() > 5000) return "---LM---";
		return super.getAsString(arg0, arg1, obj);
	}

}
