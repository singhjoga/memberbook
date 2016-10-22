package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.punjuprogrammers.memberbook.bl.model.Contact;
import com.punjuprogrammers.memberbook.bl.service.ContactService;
import com.punjuprogrammers.memberbook.common.CommonConstants;
import com.punjuprogrammers.memberbook.common.TechnicalException;
import com.punjuprogrammers.memberbook.ui.util.FacesHelper;
import com.punjuprogrammers.memberbook.ui.util.ViewHelper;

@ManagedBean
@ViewScoped
public class ContactView extends BaseView implements Serializable {

	private Contact contact;
	private String mode;
	private static final long serialVersionUID = -1525980713303798765L;
	private static final Logger LOG = LoggerFactory.getLogger(ContactView.class);
	private List<SelectItem> countryList;
	private List<SelectItem> contactTypeList;

	@PostConstruct
	public void init() {
		ContactService service = getServiceBean(ContactService.class);
		String contactId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactId");
		mode = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mode");

		if (contactId == null) {
			contact = new Contact();
			contact.setContactType("INDV");
			contact.setCountryCode(Locale.getDefault().getISO3Country());
		} else {
			contact = service.findById(Long.parseLong(contactId));
		}
		contactTypeList = ViewHelper.convertCatalogItems2SelectItemList(getCache().getCatalogItems().values(), CommonConstants.CATALOG_CONTACT_TYPE);
		countryList = ViewHelper.convertCountryList2SelectItemList(getCache().getCountryList());
		
	}

	public void actionSave(ActionEvent evt) {
		try {
			ContactService service = getServiceBean(ContactService.class);
			service.save(contact);
			RequestContext.getCurrentInstance().closeDialog(null);
			FacesHelper.info("Word added.");
			getSessionContext().setListDirty(true);
		} catch (TechnicalException e) {
			FacesHelper.error(e.getMessage());
		}
	}

	public void actionCancel() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void onAddNewListReturn(SelectEvent event) {

	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<SelectItem> getContactTypeList() {
		return contactTypeList;
	}

	public List<SelectItem> getCountryList() {
		return countryList;
	}

	public boolean isViewOnly() {
		return CommonConstants.MODE_ADD.equals(mode) || CommonConstants.MODE_EDIT.equals(mode) ? false : true;
	}
}
