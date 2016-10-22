package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.mail.EmailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.punjuprogrammers.memberbook.bl.model.ConfigItem;
import com.punjuprogrammers.memberbook.bl.model.config.MailingConfig;
import com.punjuprogrammers.memberbook.bl.model.config.MembershipConfig;
import com.punjuprogrammers.memberbook.bl.service.ConfigService;
import com.punjuprogrammers.memberbook.ui.util.ViewHelper;

@ManagedBean
@ViewScoped
public class ConfigView  extends BaseView implements Serializable{

	private static final long serialVersionUID = 3769222470531397685L;
	private static final Logger LOG = LoggerFactory.getLogger(ConfigView.class);
	
	private boolean edit;
	private MembershipConfig membershipConfig;
	private MailingConfig mailingConfig;
	
	private Map<String, Object> config = new HashMap<String, Object>();
	Map<String, ConfigItem> items;
	public void showConfig() {
		getSessionContext().setContentType("CONFIG");
		ConfigService service = getServiceBean(ConfigService.class);
		items = service.getAllAsMap();
		for (String key: items.keySet()) {
			ConfigItem item = items.get(key);
			config.put(item.getConfigKey(),item.getValue());
		}
	}
	public Map<String, Object> getConfig() {
		return config;
	}
	public void setConfig(Map<String, Object> config) {
		this.config = config;
	}
	public void actionEdit(ActionEvent evt) {
		edit=true;
	}
	public void actionSave(ActionEvent evt) {
		for (String key: config.keySet()) {
			if (items.get(key) == null) {
				items.put(key, new ConfigItem(key, "GENERAL", "string", null));
			}
			items.get(key).setValue(config.get(key).toString());
		}
		ConfigService service = getServiceBean(ConfigService.class);
		service.saveAll(items);
		edit=false;
	}
	public void actionSubmit(ActionEvent evt) {
		
	}
	public void actionCancel() {
		edit=false;
	}
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public boolean isReadOnly() {
		return !edit;
	}
	public boolean isDisableYearlySubsType() {
		Object obj = getConfig().get("YEARLY_SUBS_ON");
		return isReadOnly() || !Boolean.TRUE.equals(obj);
	}
	public boolean isDisableYearlyDay() {
		Object obj = getConfig().get("YEARLY_SUBS_TYPE");
		return isDisableYearlySubsType() ||(! ("YEARLY_START_FIXED_DAY_FIXED_MONTH".equals(obj) || "YEARLY_START_FIXED_DAY_ANY_MONTH".equals(obj)));
	}
	public boolean isDisableYearlyMonth() {
		Object obj = getConfig().get("YEARLY_SUBS_TYPE");
		return isDisableYearlySubsType() || !("YEARLY_START_FIXED_DAY_FIXED_MONTH".equals(obj));
	}
	public boolean isDisableMonthlySubsType() {
		Object obj = getConfig().get("MONTHLY_SUBS_ON");
		return isReadOnly() || !Boolean.TRUE.equals(obj);
	}
	public boolean isDisableMonthlyDay() {
		Object obj = getConfig().get("MONTHLY_SUBS_TYPE");
		return isDisableMonthlySubsType() ||(! ("MONTHLY_START_FIXED_DAY".equals(obj)));
	}
	public List<SelectItem> getMonthDateList() {
		return ViewHelper.getMonthDateList();
	}
	public List<SelectItem> getMonthNameList() {
		return ViewHelper.getMonthNameList();
	}
	@Override
	public void init() {

	}
	
	public void update(){
		
	}
	public MembershipConfig getMembershipConfig() {
		return membershipConfig;
	}
	public void setMembershipConfig(MembershipConfig membershipConfig) {
		this.membershipConfig = membershipConfig;
	}
	public MailingConfig getMailingConfig() {
		return mailingConfig;
	}
	public void setMailingConfig(MailingConfig mailingConfig) {
		this.mailingConfig = mailingConfig;
	}
	
}
