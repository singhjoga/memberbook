package com.punjuprogrammers.memberbook.bl.model.config;

public class MembershipConfig {

	private boolean yearlyEnabled;
	private YearlyStartType yearlyStartType;
	private int yearlyFixedMonth;
	private int yearlyFixedDay;
	private YearlyEndType yearlyEndType;
	private boolean monthlyEnabled;
	private MonthlyStartType monthlyStartType;
	private int monthylFixedDay;
	private MonthlyEndType monthlyEndType;
	
	public boolean isYearlyEnabled() {
		return yearlyEnabled;
	}
	public void setYearlyEnabled(boolean yearlyEnabled) {
		this.yearlyEnabled = yearlyEnabled;
	}
	public YearlyStartType getYearlyStartType() {
		return yearlyStartType;
	}
	public void setYearlyStartType(YearlyStartType yearlyStartType) {
		this.yearlyStartType = yearlyStartType;
	}
	public int getYearlyFixedMonth() {
		return yearlyFixedMonth;
	}
	public void setYearlyFixedMonth(int yearlyFixedMonth) {
		this.yearlyFixedMonth = yearlyFixedMonth;
	}
	public int getYearlyFixedDay() {
		return yearlyFixedDay;
	}
	public void setYearlyFixedDay(int yearlyFixedDay) {
		this.yearlyFixedDay = yearlyFixedDay;
	}
	public YearlyEndType getYearlyEndType() {
		return yearlyEndType;
	}
	public void setYearlyEndType(YearlyEndType yearlyEndType) {
		this.yearlyEndType = yearlyEndType;
	}
	public boolean isMonthlyEnabled() {
		return monthlyEnabled;
	}
	public void setMonthlyEnabled(boolean monthlyEnabled) {
		this.monthlyEnabled = monthlyEnabled;
	}
	public MonthlyStartType getMonthlyStartType() {
		return monthlyStartType;
	}
	public void setMonthlyStartType(MonthlyStartType monthlyStartType) {
		this.monthlyStartType = monthlyStartType;
	}
	public int getMonthylFixedDay() {
		return monthylFixedDay;
	}
	public void setMonthylFixedDay(int monthylFixedDay) {
		this.monthylFixedDay = monthylFixedDay;
	}
	public MonthlyEndType getMonthlyEndType() {
		return monthlyEndType;
	}
	public void setMonthlyEndType(MonthlyEndType monthlyEndType) {
		this.monthlyEndType = monthlyEndType;
	}
	public enum YearlyStartType {
		FixedDayFixedMonth, //
		FixedDayAnyMonth, //
		AnyDayAnyMonth
	}
	
	public enum YearlyEndType{
		OneDayBeforeStart,
		SameDayAsStart
	}
	
	public enum MonthlyStartType {
		FixedDay, //
		AnyDay
	}
	public enum MonthlyEndType{
		OneDayBeforeStart, //
		SameDayAsStart
	}	
}
