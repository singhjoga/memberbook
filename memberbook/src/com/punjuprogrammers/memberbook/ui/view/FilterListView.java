package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.punjuprogrammers.memberbook.bl.model.Filter;
import com.punjuprogrammers.memberbook.bl.service.FilterService;

@ManagedBean
@ViewScoped
public class FilterListView extends BaseCrudListView<Long, Filter, FilterService> implements Serializable{

	private static final long serialVersionUID = 2865083753491890830L;

	@PostConstruct
	public void init() {
		super.init("FILTER","filter",600,800);
	}
}