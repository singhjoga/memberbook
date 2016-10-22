package com.punjuprogrammers.memberbook.bl.model;

public enum Unit {
	Minutes(1),
	Hours(2),
	Days(3),
	Weeks(4),
	Months(5),
	Years(6);
	private final int value;
	private Unit(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static Unit fromValue(int value) {
		if (value == Minutes.getValue()) {
			return Minutes;
		}else if (value == Hours.getValue()) {
			return Hours;
		}else if (value == Days.getValue()) {
			return Days;
		}else if (value == Weeks.getValue()) {
			return Weeks;
		}else if (value == Months.getValue()) {
			return Months;
		}else if (value == Years.getValue()) {
			return Years;
		}else{
			throw new IllegalArgumentException("Not a know unit value: "+value);
		}
	}
}
