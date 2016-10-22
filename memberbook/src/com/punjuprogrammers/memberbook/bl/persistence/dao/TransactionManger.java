package com.punjuprogrammers.memberbook.bl.persistence.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.punjuprogrammers.memberbook.common.TechnicalException;

//@Service
public class TransactionManger {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionManger.class);
	
	private EntityManager em;

	//@Inject
	public TransactionManger(EntityManager em) {
		super();
		this.em = em;
	}

	public void executeIntransaction(Transaction tr) throws TechnicalException {
		em.getTransaction().begin();
		UserTransaction transaction = null;
		try {
			//transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			//transaction.begin();
			tr.execute();
			em.flush();
			em.getTransaction().commit();
			//transaction.commit();
			LOG.info("COMMITED");
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			if (transaction!= null) {
				try {
					transaction.rollback();
				} catch (Exception e1) {
					LOG.error(e.getMessage(),e);
					throw new TechnicalException(e.getMessage(), e1);
				}
			}
			LOG.error(e.getMessage(),e);
	
			throw new TechnicalException(e.getMessage(), e);
		}
	}
}
