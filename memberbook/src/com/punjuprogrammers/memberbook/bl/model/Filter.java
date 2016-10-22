package com.punjuprogrammers.memberbook.bl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Filter implements IdObject<Long>{

	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "filterId", table = "id_gen", pkColumnName = "id_key", valueColumnName = "id_value", allocationSize = 1,initialValue=1000)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "filterId")	
	private Long id;
	@Column()
	private String name;
	@Column()
	private String contactType;
	@Column()
	private String title;
	@Column()
	private String firstName;
	@Column()
	private String lastName;
	@Column()
	private String address1;
	@Column()
	private String address2;
	@Column()
	private String city;
	@Column()
	private String zipCode;
	@Column()
	private String countryCode;
	@Column()
	private String phone;
	@Column()
	private String email;
	private Date startDateFrom;
	private Date startDateTo;
	private Date renewDateFrom;
	private Date renewDateTo;
	private Date expiryDateFrom;
	private Date expiryDateTo;
	private String status;
	private Date updateDateFrom;
	private Date updateDateTo;
	private String updateBy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getStartDateFrom() {
		return startDateFrom;
	}
	public void setStartDateFrom(Date startDateFrom) {
		this.startDateFrom = startDateFrom;
	}
	public Date getStartDateTo() {
		return startDateTo;
	}
	public void setStartDateTo(Date startDateTo) {
		this.startDateTo = startDateTo;
	}
	public Date getRenewDateFrom() {
		return renewDateFrom;
	}
	public void setRenewDateFrom(Date renewDateFrom) {
		this.renewDateFrom = renewDateFrom;
	}
	public Date getRenewDateTo() {
		return renewDateTo;
	}
	public void setRenewDateTo(Date renewDateTo) {
		this.renewDateTo = renewDateTo;
	}
	public Date getExpiryDateFrom() {
		return expiryDateFrom;
	}
	public void setExpiryDateFrom(Date expiryDateFrom) {
		this.expiryDateFrom = expiryDateFrom;
	}
	public Date getExpiryDateTo() {
		return expiryDateTo;
	}
	public void setExpiryDateTo(Date expiryDateTo) {
		this.expiryDateTo = expiryDateTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdateDateFrom() {
		return updateDateFrom;
	}
	public void setUpdateDateFrom(Date updateDateFrom) {
		this.updateDateFrom = updateDateFrom;
	}
	public Date getUpdateDateTo() {
		return updateDateTo;
	}
	public void setUpdateDateTo(Date updateDateTo) {
		this.updateDateTo = updateDateTo;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	@Override
	public String toString() {
		return "Filter [id=" + id + ", name=" + name + ", contactType=" + contactType + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", address1="
				+ address1 + ", address2=" + address2 + ", city=" + city + ", zipCode=" + zipCode + ", countryCode=" + countryCode + ", phone=" + phone + ", email=" + email
				+ ", startDateFrom=" + startDateFrom + ", startDateTo=" + startDateTo + ", renewDateFrom=" + renewDateFrom + ", renewDateTo=" + renewDateTo + ", expiryDateFrom="
				+ expiryDateFrom + ", expiryDateTo=" + expiryDateTo + ", status=" + status + ", updateDateFrom=" + updateDateFrom + ", updateDateTo=" + updateDateTo + ", updateBy="
				+ updateBy + "]";
	}

}
