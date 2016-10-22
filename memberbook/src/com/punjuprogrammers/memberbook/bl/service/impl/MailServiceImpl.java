package com.punjuprogrammers.memberbook.bl.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.punjuprogrammers.memberbook.bl.model.MailTemplate;
import com.punjuprogrammers.memberbook.bl.persistence.dao.BaseDao;
import com.punjuprogrammers.memberbook.bl.persistence.dao.MailTemplateDao;
import com.punjuprogrammers.memberbook.bl.service.MailService;

@Service
public class MailServiceImpl extends CrudServiceImpl<Long, MailTemplate> implements MailService{
	private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Inject
	private MailTemplateDao dao;
	
	/* (non-Javadoc)
	 * @see com.punjuprogrammers.memberbook.bl.service.MailService#findByName(java.lang.String)
	 */
	@Override
	public MailTemplate findByName(String templateName) {
		return dao.findByName(templateName);
	}

	@Override
	public BaseDao<MailTemplate> getDao() {
		return dao;
	}

}
