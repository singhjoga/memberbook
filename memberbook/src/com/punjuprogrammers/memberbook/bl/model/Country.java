package com.punjuprogrammers.memberbook.bl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(
		@NamedQuery(name="Contact.findAllSorted",query="SELECT c FROM Country c ORDER BY c.name")
		)
public class Country implements Serializable{

	private static final long serialVersionUID = 2180155946025635777L;

	@Id
	@Column(length=3)
	private String code;
	
	@Column(length=2)
	private String iso2Code;
	@Column(length=50)
	private String name;
	@Column()
	private Integer isoNumCode;
	@Column(length=30)
	private String dialCode;
	@Column(length=3)
	private String currencyCode;
	@Column(length=50)
	private String currencyName;
	@Column
	private Integer currencyMinorUnit;
	@Column
	private Integer currencyNumCode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIso2Code() {
		return iso2Code;
	}
	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsoNumCode() {
		return isoNumCode;
	}
	public void setIsoNumCode(Integer isoNumCode) {
		this.isoNumCode = isoNumCode;
	}
	public String getDialCode() {
		return dialCode;
	}
	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public Integer getCurrencyMinorUnit() {
		return currencyMinorUnit;
	}
	public void setCurrencyMinorUnit(Integer currencyMinorUnit) {
		this.currencyMinorUnit = currencyMinorUnit;
	}
	public Integer getCurrencyNumCode() {
		return currencyNumCode;
	}
	public void setCurrencyNumCode(Integer currencyNumCode) {
		this.currencyNumCode = currencyNumCode;
	}
	
	
}
