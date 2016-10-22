package com.punjuprogrammers.memberbook.bl.service;

import com.punjuprogrammers.memberbook.bl.model.MailTemplate;

public interface MailService extends CrudService<Long, MailTemplate>{
	MailTemplate findByName(String templateName);
}