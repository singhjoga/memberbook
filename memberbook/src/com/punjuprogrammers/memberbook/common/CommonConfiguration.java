package com.punjuprogrammers.memberbook.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CommonConfiguration {
	public static final String PATTERN_DATE_DEFAULT = "dd.MM.yyyy";
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN_DATE_DEFAULT);
}
