package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;
import java.util.Date;
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

import com.punjuprogrammers.memberbook.bl.cache.ApplicationCache;
import com.punjuprogrammers.memberbook.bl.model.Country;
import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction;
import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction.Mode;
import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction.Reason;
import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction.Type;
import com.punjuprogrammers.memberbook.bl.service.AccountService;
import com.punjuprogrammers.memberbook.common.CommonConstants;
import com.punjuprogrammers.memberbook.common.TechnicalException;
import com.punjuprogrammers.memberbook.ui.util.FacesHelper;
import com.punjuprogrammers.memberbook.ui.util.ViewHelper;

@ManagedBean
@ViewScoped
public class MoneyTransactionView  extends BaseView implements Serializable {

	private MoneyTransaction tr;
	private String mode;
	private static final long serialVersionUID = -1525980713303798765L;
	private static final Logger LOG = LoggerFactory.getLogger(MoneyTransactionView.class);
	private List<SelectItem> courrencyList;
	
	@PostConstruct
	public void init() {
		AccountService service = getServiceBean(AccountService.class);
		ApplicationCache cache = getServiceBean(ApplicationCache.class);
		
		String trId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("trId");
		mode = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mode");

		courrencyList = ViewHelper.convertCountryList2CurrencyList(cache.getCountryList());
		
		if (trId == null) {
			String contactId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactId");
			tr = new MoneyTransaction();
			tr.setTrType(Type.RECEIPT);
			tr.setMode(Mode.BANK);
			tr.setReason(Reason.MEMBERSHIP);
			tr.setTrDate(new Date());
			tr.setCurrency(findDefaultCurrency(cache.getCountryList()));
			tr.setContactId(Long.valueOf(contactId));
		} else {
			tr = service.findById(Long.parseLong(trId));
		}
	}

	public void actionSave(ActionEvent evt) {
		try {
			AccountService service = getServiceBean(AccountService.class);
			service.save(tr);
			RequestContext.getCurrentInstance().closeDialog(null);
		} catch (TechnicalException e) {
			FacesHelper.error(e.getMessage());
		}
	}

	public void actionCancel() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void onAddNewListReturn(SelectEvent event) {

	}

	private String findDefaultCurrency(List<Country> countryList) {
		String countryCode = Locale.getDefault().getISO3Country();
		for (Country country: countryList) {
			if (country.getCode().equals(countryCode)) {
				return country.getCurrencyCode();
			}
		}
		
		return null;
	}
	public MoneyTransaction getTr() {
		return tr;
	}

	public void setTr(MoneyTransaction tr) {
		this.tr = tr;
	}

	public boolean isViewOnly() {
		return CommonConstants.MODE_ADD.equals(mode) || CommonConstants.MODE_EDIT.equals(mode) ? false : true;
	}
	
	public List<Type> getTypeList() {
		return Type.getList();

	}
	public List<Mode> getModeList() {
		return Mode.getList();
	}
	public List<Reason> getReasonList() {
		return Reason.getList();
		//return ViewHelper.convertEnumList2SelectItemList(Reason.getList());
	}
	public List<SelectItem> getCurrencyList() {
		return courrencyList;
	}
}
