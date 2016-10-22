package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.punjuprogrammers.memberbook.bl.model.MailTemplate;
import com.punjuprogrammers.memberbook.bl.service.MailService;

@ManagedBean
@ViewScoped
public class MailTemplateListView extends BaseCrudListView<Long, MailTemplate, MailService> implements Serializable{

	private static final long serialVersionUID = -4784203639327353779L;

	private static final Logger LOG = LoggerFactory.getLogger(MailTemplateListView.class);
	@PostConstruct
	public void init() {
		super.init("MAILTEMPLATE","mailtemplate",600,1000);
	}
}