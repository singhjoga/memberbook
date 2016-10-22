package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.punjuprogrammers.memberbook.bl.model.Contact;
import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction;
import com.punjuprogrammers.memberbook.bl.service.AccountService;
import com.punjuprogrammers.memberbook.bl.service.ContactService;
import com.punjuprogrammers.memberbook.common.CommonConstants;
import com.punjuprogrammers.memberbook.ui.util.ViewHelper;

@ManagedBean
@ViewScoped
public class AccountView  extends BaseView implements Serializable {

	private Contact contact;
	private String mode;
	private static final long serialVersionUID = -1525980713303798765L;
	private static final Logger LOG = LoggerFactory.getLogger(AccountView.class);
	private MoneyTransaction selectedItem;
	
	@PostConstruct
	public void init() {
		ContactService service = getServiceBean(ContactService.class);
		String contactId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactId");
		mode = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mode");
		contact = service.findById(Long.parseLong(contactId));
	}

	public void actionClose() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}
	public void actionShowAddDialog() {
		ViewHelper.showMoneyTransactionDialog(contact.getContactId(),null,CommonConstants.MODE_ADD);
	}
	public void onAddNewListReturn(SelectEvent event) {

	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<MoneyTransaction> getLedger() {
		AccountService service = getServiceBean(AccountService.class);
		return service.getAccountLedger(contact.getContactId());
	}

	public MoneyTransaction getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(MoneyTransaction selectedItem) {
		this.selectedItem = selectedItem;
	}
	

}
