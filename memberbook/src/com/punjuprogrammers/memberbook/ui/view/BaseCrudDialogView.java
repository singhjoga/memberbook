package com.punjuprogrammers.memberbook.ui.view;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.punjuprogrammers.memberbook.bl.model.IdObject;
import com.punjuprogrammers.memberbook.bl.service.CrudService;
import com.punjuprogrammers.memberbook.common.TechnicalException;
import com.punjuprogrammers.memberbook.ui.util.FacesHelper;

public abstract class BaseCrudDialogView<ID, OBJ extends IdObject<ID>, SERVICE extends CrudService<ID,OBJ>> extends BaseCrudView<ID, OBJ, SERVICE>{
	private static final long serialVersionUID = -1525980713303798765L;
	private OBJ obj;
	private String mode;

	public void init() {
		super.init();
		
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		mode = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mode");
		if (id == null) {
			try {
				obj = getObjClass().newInstance();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		} else {
			obj = getService().findById(castId(id));
		}
	}
	public void actionSave(ActionEvent evt) {
		try {
			SERVICE service = getService();
			service.save(obj);
			RequestContext.getCurrentInstance().closeDialog(null);
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

	public OBJ getObject() {
		return obj;
	}

	public void setObject(OBJ obj) {
		this.obj = obj;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

}
