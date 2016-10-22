package com.punjuprogrammers.memberbook.ui.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userSessionContext", eager=true)
@SessionScoped
public class UserSessionContext implements Serializable{
	private boolean listDirty;
	private String contentType;
	
	private static final long serialVersionUID = -4597200458955692945L;

	public UserSessionContext() {
		
	}

	public boolean isListDirty() {
		return listDirty;
	}

	public void setListDirty(boolean contactListDirty) {
		this.listDirty = contactListDirty;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}