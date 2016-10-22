package com.punjuprogrammers.memberbook.bl.persistence.dao;

import org.springframework.stereotype.Repository;

import com.punjuprogrammers.memberbook.bl.model.MailTemplate;

@Repository
public class MailTemplateDao extends BaseDao<MailTemplate>{
	private static final long serialVersionUID = 6766840056887484887L;

	public MailTemplate findByName(String templateName) {
		return fetchNamedQueryResult("MailTemplate.findByName",templateName);
	}
}
