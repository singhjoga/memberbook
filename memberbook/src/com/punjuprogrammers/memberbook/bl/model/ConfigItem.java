package com.punjuprogrammers.memberbook.bl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(indexes={@Index(name="idx_configGroup",columnList="configGroup")})
@NamedQueries({@NamedQuery(name="ConfigItem.getByGroup",query="SELECT c FROM ConfigItem c WHERE c.group=:group")})

public class ConfigItem implements Serializable, IdObject<String>{
	public static final String SUBS_TYPE="SUBS_TYPE";
	public static final String SUBS_TYPE_MONTHLY="M";
	public static final String SUBS_TYPE_YEARLY="Y";
	public static final String SUBS_MONTHLY_START_DATE="SUBS_MONTHLY_START_DATE";
	public static final String SUBS_MONTHLY_END_DATE="SUBS_MONTHLY_END_DATE";
	public static final String SUBS_YEARLY_START_MONTHDATE="SUBS_MONTHLY_START_MONTHDATE";
	public static final String SUBS_YEARLY_END_MONTHDATE="SUBS_MONTHLY_END_MONTHDATE";
	public static final String SMTP_SERVER="SMTP_SERVER";
	public static final String SMTP_USER="SMTP_USER";
	public static final String SMTP_PASSWORD="SMTP_PASSWORD";
	public static final String REPLY_EMAIL="REPLY_EMAIL";

	private static final long serialVersionUID = 5944797146261950883L;

	@Id
	@Column(length=50)
	private String configKey;

	@Column(name="configGroup",length=20)
	private String group;
	
	
	@Column(length=20)
	private String dataType;
	
	@Column(length=500)
	private String value;


	public ConfigItem() {
		super();
	}

	public ConfigItem(String configKey, String group, String dataType, String value) {
		super();
		this.configKey = configKey;
		this.group = group;
		this.dataType = dataType;
		this.value = value;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String getId() {
		return configKey;
	}

	@Override
	public void setId(String id) {
		configKey = id;
	}

	@Override
	public String toString() {
		return "ConfigItem [configKey=" + configKey + ", group=" + group + ", dataType=" + dataType + ", value=" + value + "]";
	}
	
	
}
