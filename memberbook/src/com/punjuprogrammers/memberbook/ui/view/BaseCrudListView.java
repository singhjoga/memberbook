package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.punjuprogrammers.memberbook.bl.model.IdObject;
import com.punjuprogrammers.memberbook.bl.service.CrudService;
import com.punjuprogrammers.memberbook.common.CommonConstants;
import com.punjuprogrammers.memberbook.ui.util.FacesHelper;
import com.punjuprogrammers.memberbook.ui.util.ViewHelper;

public abstract class BaseCrudListView<ID, OBJ extends IdObject<ID>, SERVICE extends CrudService<ID,OBJ>> extends BaseCrudView<ID, OBJ, SERVICE> implements Serializable{

	private static final long serialVersionUID = -4784203639327353779L;

	private static final Logger LOG = LoggerFactory.getLogger(FilterListView.class);
	
	private List<OBJ> list;
	private OBJ selected;
	private String contentType;
	private String dlgXhtmlFile;
	private int height;
	private int width;
	
	public void init(String contentType, String dlgXhtmlFile, int height, int width) {
		super.init();
		this.dlgXhtmlFile = dlgXhtmlFile;
		this.contentType = contentType;
		this.height=height;
		this.width=width;
	}
	public void actionShowAll() {
		getSessionContext().setContentType(contentType);
		getSessionContext().setListDirty(true);
	}
	private void loadList() {
			list = getService().getAll();
	}
	public void actionShowAddDialog() {
		ViewHelper.showDetailsDialog(null,CommonConstants.MODE_ADD,dlgXhtmlFile,height,width);
	}
	public void actionShowEditDialog() {
		if (selected == null) {
			FacesHelper.warn("Select a record");
			return;
		}
		ViewHelper.showDetailsDialog(selected.getId(),CommonConstants.MODE_EDIT,dlgXhtmlFile,height,width);
	}
	public void actionDelete() {
		if (selected == null) {
			FacesHelper.warn("Select a record");
			return;
		}
		getService().delete(selected.getId());
	}
	public List<OBJ> getList() {
		if (getSessionContext().isListDirty()) {
			loadList();
		}
		return list;
	}
	public OBJ getSelected() {
		return selected;
	}
	public void setSelected(OBJ selected) {
		this.selected = selected;
	}

}