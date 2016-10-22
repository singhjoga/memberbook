package com.punjuprogrammers.memberbook.bl.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction;
import com.punjuprogrammers.memberbook.bl.persistence.dao.MoneyTransactionDao;

@Service
public class AccountService {
	private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);
	@Inject
	private MoneyTransactionDao dao;

	public List<MoneyTransaction> getAccountLedger(Long contactId) {
		return dao.findByContactId(contactId);
	}

	@Transactional
	public MoneyTransaction addMoneyTransaction(MoneyTransaction moneyTransaction) {
		dao.add(moneyTransaction);

		return moneyTransaction;
	}


	public MoneyTransaction findById(Long id) {
		return dao.findById(id);
	}

	@Transactional
	public MoneyTransaction save(final MoneyTransaction tr) {

		if (tr.getContactId() == 0) {
			LOG.info("Adding new");
			dao.add(tr);
		} else {
			LOG.info("Updating");
			dao.update(tr);
		}

		return tr;
	}

}
