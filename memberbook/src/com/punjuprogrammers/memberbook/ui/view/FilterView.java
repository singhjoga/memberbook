package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

import com.punjuprogrammers.memberbook.bl.model.Filter;
import com.punjuprogrammers.memberbook.bl.service.FilterService;
import com.punjuprogrammers.memberbook.common.CommonConstants;
import com.punjuprogrammers.memberbook.ui.util.ViewHelper;

@ManagedBean
@ViewScoped
public class FilterView  extends BaseCrudDialogView<Long, Filter, FilterService> implements Serializable {
	private static final long serialVersionUID = -1525980713303798765L;
	private List<SelectItem> countryList;
	private List<SelectItem> contactTypeList;
	private List<String> selectedTitles;
	private List<String> selectedContactTypes;
	private List<String> selectedStatuses;
	
	@PostConstruct
	public void init() {
		super.init();
		contactTypeList = ViewHelper.convertCatalogItems2SelectItemList(getCache().getCatalogItems().values(), CommonConstants.CATALOG_CONTACT_TYPE);
		countryList = ViewHelper.convertCountryList2SelectItemList(getCache().getCountryList());

		selectedTitles = asList(getObject().getTitle());
		selectedContactTypes = asList(getObject().getContactType());
		selectedStatuses = asList(getObject().getStatus());
	}
	private List<String> asList(String str) {
		if (str == null) {
			return new ArrayList<String>();
		}else{
			List<String> list = Arrays.asList(str.split(","));
			return list;
		}
	}
	public List<SelectItem> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<SelectItem> countryList) {
		this.countryList = countryList;
	}
	public List<SelectItem> getContactTypeList() {
		return contactTypeList;
	}
	public void setContactTypeList(List<SelectItem> contactTypeList) {
		this.contactTypeList = contactTypeList;
	}
	public List<String> getSelectedTitles() {
		return selectedTitles;
	}
	public void setSelectedTitles(List<String> selectedTitles) {
		this.selectedTitles = selectedTitles;
	}
	public List<String> getSelectedContactTypes() {
		return selectedContactTypes;
	}
	public void setSelectedContactTypes(List<String> selectedContactTypes) {
		this.selectedContactTypes = selectedContactTypes;
	}
	public List<String> getSelectedStatuses() {
		return selectedStatuses;
	}
	public void setSelectedStatuses(List<String> selectedStatuses) {
		this.selectedStatuses = selectedStatuses;
	}
	@Override
	public void actionSave(ActionEvent evt) {
		Filter f = getObject();
		f.setTitle(StringUtils.join(selectedTitles, ","));
		f.setContactType(StringUtils.join(selectedContactTypes, ","));
		f.setStatus(StringUtils.join(selectedStatuses, ","));
		super.actionSave(evt);
	}
	
}
