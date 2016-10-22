package com.punjuprogrammers.memberbook.bl.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(indexes = { @Index(name = "contactId", columnList = "contactId,trDate") })
@NamedQueries(@NamedQuery(name = "MoneyTransaction.findByContactId", query = "SELECT t FROM MoneyTransaction t WHERE t.contactId=?1"))
public class MoneyTransaction implements Serializable {

	private static final long serialVersionUID = -5552412747595191011L;

	@TableGenerator(name = "trId", table = "id_gen", pkColumnName = "id_key", valueColumnName = "id_value", allocationSize = 1, initialValue = 1000)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "trId")
	private long trId;

	@Column
	private Type trType;
	@Column
	private long contactId;
	@Column
	private Date trDate;
	@Column
	private Reason reason;
	@Column(length = 200)
	private String description;
	@Column(length = 200)
	private String refNo;
	@Column
	private Date validTill;
	@Column
	private Mode mode;
	@Column
	private Double amount;
	@Column(length = 10)
	private String currency;

	@Column(length = 1000)
	private String comments;

	public MoneyTransaction() {

	}

	public long getTrId() {
		return trId;
	}

	public void setTrId(long trId) {
		this.trId = trId;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public Date getTrDate() {
		return trDate;
	}

	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Type getTrType() {
		return trType;
	}

	public void setTrType(Type trType) {
		this.trType = trType;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFullDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append(description);
		if (sb.length() > 0) {
			sb.append(" ");
		}
		sb.append("By ");
		sb.append(mode.getLabel());
		sb.append(" towards ");
		sb.append(reason.toString());
		if (refNo.length() > 0) {
			sb.append(". Ref. ");
			sb.append(refNo);
		}
		return sb.toString();
	}

	public static enum Reason implements ValuedEnum {
		MEMBERSHIP(1, "Membership"), DONATION(2, "Donation"), BILL_PAYMENT(3, "Bill Payment"), OTHERS(4, "Others");

		private final int value;
		private final String label;

		private Reason(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public Integer getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}

		public static List<Reason> getList() {
			List<Reason> list = new ArrayList<Reason>();
			list.add(MEMBERSHIP);
			list.add(DONATION);
			list.add(BILL_PAYMENT);
			list.add(OTHERS);

			return list;
		}

		public static Reason fromValue(int value) {
			if (value == MEMBERSHIP.getValue()) {
				return MEMBERSHIP;
			} else if (value == DONATION.getValue()) {
				return DONATION;
			} else if (value == BILL_PAYMENT.getValue()) {
				return BILL_PAYMENT;
			} else if (value == OTHERS.getValue()) {
				return OTHERS;
			} else {
				throw new IllegalArgumentException("Not a know MoneyTransaction.Reason value: " + value);
			}
		}
		public static Reason fromLabel(String label) {
			if (MEMBERSHIP.getLabel().equals(label)) {
				return MEMBERSHIP;
			} else if (DONATION.getLabel().equals(label)) {
				return DONATION;
			} else if (BILL_PAYMENT.getLabel().equals(label)) {
				return BILL_PAYMENT;
			} else if (OTHERS.getLabel().equals(label)) {
				return OTHERS;
			} else {
				throw new IllegalArgumentException("Not a know MoneyTransaction.Reason label: " + label);
			}
		}

	}

	public static enum Mode implements ValuedEnum {
		CASH(1, "Cash"), BANK(2, "Bank"), GIFT_VOUCHER(3, "Gift Voucher");
		private final int value;
		private final String label;

		private Mode(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public Integer getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}

		public static List<Mode> getList() {
			List<Mode> list = new ArrayList<Mode>();
			list.add(CASH);
			list.add(BANK);
			list.add(GIFT_VOUCHER);

			return list;
		}

		public static Mode fromValue(int value) {
			if (value == GIFT_VOUCHER.getValue()) {
				return GIFT_VOUCHER;
			} else if (value == CASH.getValue()) {
				return CASH;
			} else if (value == BANK.getValue()) {
				return BANK;
			} else {
				throw new IllegalArgumentException("Not a know MoneyTransaction.Mode value: " + value);
			}
		}
		public static Mode fromLabel(String label) {
			if (GIFT_VOUCHER.getLabel().equals(label)) {
				return GIFT_VOUCHER;
			} else if (CASH.getLabel().equals(label)) {
				return CASH;
			} else if (BANK.getLabel().equals(label)) {
				return BANK;
			} else {
				throw new IllegalArgumentException("Not a know MoneyTransaction.Mode label: " + label);
			}
		}

	}

	public static enum Type implements ValuedEnum {
		PAYMENT(1, "Payment"), RECEIPT(2, "Receipt");
		private final int value;
		private final String label;

		private Type(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public Integer getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}

		public static List<Type> getList() {
			List<Type> list = new ArrayList<Type>();
			list.add(PAYMENT);
			list.add(RECEIPT);

			return list;
		}

		public static Type fromValue(int value) {
			if (value == PAYMENT.getValue()) {
				return PAYMENT;
			} else if (value == RECEIPT.getValue()) {
				return RECEIPT;
			} else {
				throw new IllegalArgumentException("Not a know MoneyTransaction.Type value: " + value);
			}
		}
		public static Type fromLabel(String label) {
			if (PAYMENT.getLabel().equals(label)) {
				return PAYMENT;
			} else if (RECEIPT.getLabel().equals(label)) {
				return RECEIPT;
			} else {
				throw new IllegalArgumentException("Not a know MoneyTransaction.Type label: " + label);
			}
		}
		
		public boolean isPayment() {
			return this == Type.PAYMENT;
		}
		
		public boolean isReceipt() {
			return this == Type.RECEIPT;
		}
	}
}
