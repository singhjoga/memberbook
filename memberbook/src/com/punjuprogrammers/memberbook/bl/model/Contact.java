package com.punjuprogrammers.memberbook.bl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(indexes={
		@Index(name="firstName",columnList="firstName,lastName"),
		@Index(name="lastName",columnList="lastName, firstName"),
		@Index(name="contactType",columnList="contactType,lastName, firstName"), 
		@Index(name="status",columnList="status,lastName, firstName")
		})
@NamedQueries(
		@NamedQuery(name="Contact.findAllActive",query="SELECT c FROM Contact c WHERE c.status='A'")
		)
public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String STATUS_ACTIVE="A";
	public static final String STATUS_INACTIVE="I";
	public static final String STATUS_DELETED="D";
	
	@TableGenerator(name = "contactId", table = "id_gen", pkColumnName = "id_key", valueColumnName = "id_value", allocationSize = 1,initialValue=1000)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "contactId")	
	private long contactId;
	
	@Column(length=15)
	private String contactType;
	@Column(length=15)
	private String title;
	@Column(length=50)
	private String firstName;
	@Column(length=50)
	private String lastName;
	@Column(length=300)
	private String address1;
	@Column(length=300)
	private String address2;
	@Column(length=50)
	private String city;
	@Column(length=10)
	private String zipCode;
	@Column(length=5)
	private String countryCode;
	@Column(length=100)
	private String phone;
	@Column(length=100)
	private String email;
	private Date startDate;
	private Date renewDate;
	private Date expiryDate;
	@Column(length=1, nullable=false)
	private String status;
	private Date updateTime;
	private String updateBy;
	private String comments;
	
	public Contact() {
		status="A"; //Active
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(Date renewDate) {
		this.renewDate = renewDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getFullAddress() {
		StringBuilder sb = new StringBuilder();
		if (address1 != null && !address1.isEmpty()) {
			sb.append(sb.length()==0?"":", ");
			sb.append(address1);
		}
		if (address2 != null && !address2.isEmpty()) {
			sb.append(sb.length()==0?"":", ");
			sb.append(address2);
		}
		if (zipCode != null && !zipCode.isEmpty()) {
			sb.append(sb.length()==0?"":", ");
			sb.append(zipCode);
		}
		if (city != null && !city.isEmpty()) {
			sb.append(sb.length()==0?"":", ");
			sb.append(city);
		}
		
		return sb.toString();
	}
	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", contactType=" + contactType + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName + ", address1="
				+ address1 + ", address2=" + address2 + ", city=" + city + ", zipCode=" + zipCode + ", countryCode=" + countryCode + ", phone=" + phone + ", email=" + email
				+ ", startDate=" + startDate + ", renewDate=" + renewDate + ", expiryDate=" + expiryDate + ", status=" + status + ", updateTime=" + updateTime + ", updateBy="
				+ updateBy + ", comments=" + comments + "]";
	}
	
}
