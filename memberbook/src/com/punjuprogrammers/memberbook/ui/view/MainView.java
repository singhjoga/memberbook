package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.punjuprogrammers.memberbook.bl.persistence.dao.ContactDao;

@ManagedBean
@ViewScoped
public class MainView implements Serializable{

	private static final long serialVersionUID = 7032259578112646978L;
	
	@Inject
	private ContactDao dao;
	
	public String getHello() {
		dao.findAll();
		return "Hello All";
	}
}