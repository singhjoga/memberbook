package com.punjuprogrammers.memberbook.ui.view;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.punjuprogrammers.memberbook.bl.cache.ApplicationCache;
import com.punjuprogrammers.memberbook.bl.model.Contact;
import com.punjuprogrammers.memberbook.bl.model.Filter;
import com.punjuprogrammers.memberbook.bl.service.ContactService;
import com.punjuprogrammers.memberbook.bl.service.FilterService;
import com.punjuprogrammers.memberbook.common.CommonConstants;
import com.punjuprogrammers.memberbook.ui.util.ExcelHelper;
import com.punjuprogrammers.memberbook.ui.util.FacesHelper;
import com.punjuprogrammers.memberbook.ui.util.ViewHelper;

@ManagedBean
@ViewScoped
public class ContactListView extends BaseView implements Serializable {

	private static final long serialVersionUID = 7032259578112646978L;
	private static final Logger LOG = LoggerFactory.getLogger(ContactListView.class);

	private FilterType filterType;
	private Long selectedFilterId;

	private List<Contact> contactList;
	private Contact selectedContact;

	@PostConstruct
	public void init() {
		getSessionContext().setContentType("CONTACT");
	}

	public void actionShowAll() {
		getSessionContext().setContentType("CONTACT");
		filterType = FilterType.ALL_ACTIVE;
		getSessionContext().setListDirty(true);
	}

	private void loadList() {
		ContactService service = getServiceBean(ContactService.class);
		if (selectedFilterId != null && selectedFilterId > 0) {
			contactList = service.findByFilter(selectedFilterId);
		} else {
			contactList = service.findAllActive();
		}
		getSessionContext().setListDirty(false);
	}

	public void actionShowAddDialog() {
		ViewHelper.showContactDialog(null, CommonConstants.MODE_ADD);
	}

	public void actionShowEditDialog() {
		if (selectedContact == null) {
			FacesHelper.warn("Select a contact");
			return;
		}
		ViewHelper.showContactDialog(selectedContact.getContactId(), CommonConstants.MODE_EDIT);
	}

	public void actionShowAccountDialog() {
		if (selectedContact == null) {
			FacesHelper.warn("Select a contact");
			return;
		}
		ViewHelper.showAccountDialog(selectedContact.getContactId(), CommonConstants.MODE_EDIT);
	}

	public void actionShowPaymentsDialog() {

	}

	public void actionShowMailDialog() {

	}

	public StreamedContent getExportFile() {
		List<Contact> contactList = getContactList();
		XSSFWorkbook workbook = ExcelHelper.exportContacts(contactList);
		ByteArrayOutputStream pout = new ByteArrayOutputStream();
		try {
			workbook.write(pout);
			ByteArrayInputStream bis = new ByteArrayInputStream(pout.toByteArray());
			StreamedContent file = new DefaultStreamedContent(bis, "application/xls", "contacts.xls");
			return file;
		} catch (IOException e) {
			FacesHelper.error(e.getMessage());
		}
		return null;
	}

	public List<Contact> getContactList() {
		if (getSessionContext().isListDirty()) {
			loadList();
		}
		return contactList;
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}

	public void onRowDblClck(final SelectEvent event) {
		Contact obj = (Contact) event.getObject();
		ViewHelper.showContactDialog(obj.getContactId(), CommonConstants.MODE_VIEW);
	}

	public String getContactTypeDesc(String contactType) {
		ApplicationCache cache = getServiceBean(ApplicationCache.class);
		return cache.getCatalogItems().get(contactType).getItemValue();
	}

	private static enum FilterType {
		ALL_ACTIVE, LIFE_MEMBERS, ACTIVE_MEMBERS
	}

	public List<Filter> getFilterList() {
		FilterService filterService = getServiceBean(FilterService.class);

		return filterService.getAll();
	}

	public Long getSelectedFilterId() {
		return selectedFilterId;
	}

	public void setSelectedFilterId(Long selectedFilterId) {
		this.selectedFilterId = selectedFilterId;
	}

	public void onFilterChange() {
		loadList();
	}
}