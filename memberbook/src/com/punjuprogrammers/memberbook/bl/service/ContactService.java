package com.punjuprogrammers.memberbook.bl.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.punjuprogrammers.memberbook.bl.model.Contact;
import com.punjuprogrammers.memberbook.bl.model.Filter;
import com.punjuprogrammers.memberbook.bl.persistence.dao.ContactDao;
import com.punjuprogrammers.memberbook.bl.persistence.dao.FilterDao;

@Service
public class ContactService {
	private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);
	@Inject
	private ContactDao contactDao;

	@Inject FilterDao filterDao;
	
	public List<Contact> findAllActive() {
		return contactDao.findAllActive();
	}
	public List<Contact> findByFilter(Long filterId) {
		Filter filter = filterDao.findById(filterId);
		return contactDao.findByFilter(filter);
	}

	public boolean exists(Long contactId) {
		return contactDao.exists(contactId);
	}

	@Transactional
	public Contact add(final Contact contact) {
		contactDao.add(contact);
		return contact;
	}

	public Contact findById(Long id) {
		return contactDao.findById(id);
	}

	@Transactional
	public Contact save(final Contact contact) {

		if (contact.getContactId() == 0) {
			LOG.info("Adding new");
			contactDao.add(contact);
		} else {
			LOG.info("Updating");
			contactDao.update(contact);
		}

		return contact;
	}
}
