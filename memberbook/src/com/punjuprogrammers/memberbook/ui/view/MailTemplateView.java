package com.punjuprogrammers.memberbook.ui.view;

import java.io.Serializable;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import com.punjuprogrammers.memberbook.bl.model.MailAttachment;
import com.punjuprogrammers.memberbook.bl.model.MailTemplate;
import com.punjuprogrammers.memberbook.bl.service.MailService;

@ManagedBean
@ViewScoped
public class MailTemplateView  extends BaseCrudDialogView<Long, MailTemplate, MailService> implements Serializable {
	private static final long serialVersionUID = -1525980713303798765L;

	@PostConstruct
	public void init() {
		super.init();
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		MailAttachment attachment = new MailAttachment();
		attachment.setFileName(event.getFile().getFileName());
		attachment.setContents(event.getFile().getContents());
		getObject().getAttachments().add(attachment);
	}
	
	public void actionRemoveAttachment(ActionEvent event) {
		String fileName = (String)event.getComponent().getAttributes().get("fileName");
		Iterator<MailAttachment> itr = getObject().getAttachments().iterator();
		
		while (itr.hasNext()) {
			MailAttachment attachment = itr.next();
			if (attachment.getFileName().equals(fileName)) {
				itr.remove();
				break;
			}
		}
	}
}
