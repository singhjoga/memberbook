package com.punjuprogrammers.memberbook.bl.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.punjuprogrammers.memberbook.bl.model.MoneyTransaction;
@Repository
public class MoneyTransactionDao extends BaseDao<MoneyTransaction> {

	private static final long serialVersionUID = -656760742200354513L;

	public List<MoneyTransaction> findByContactId(Long contactId) {
		return fetchNamedQueryResultList("MoneyTransaction.findByContactId", contactId);
	}
}
