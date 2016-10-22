package com.punjuprogrammers.memberbook.bl.persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.punjuprogrammers.memberbook.bl.model.Contact;
import com.punjuprogrammers.memberbook.bl.model.Filter;

@Repository
public class ContactDao extends BaseDao<Contact>{

	private static final long serialVersionUID = 4559171325483980637L;

	public ContactDao() {
		super();
	}

	public List<Contact> findAllActive() {
		return fetchNamedQueryResultList("Contact.findAllActive");
	}
	
	public List<Contact> findByFilter(Filter filter) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
		Root<Contact> contact = cq.from(Contact.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		appendInWhereClaus(contact,"title", filter.getTitle(),cb, predicates);
		appendInWhereClaus(contact,"contactType", filter.getContactType(),cb, predicates);
		appendInWhereClaus(contact,"status", filter.getStatus(),cb, predicates);
		appendLikeWhereClaus(contact,"firstName", filter.getFirstName(),cb, predicates);
		appendLikeWhereClaus(contact,"lastName", filter.getLastName(),cb, predicates);
		appendLikeWhereClaus(contact,"address1", filter.getAddress1(),cb, predicates);
		appendLikeWhereClaus(contact,"address2", filter.getAddress2(),cb, predicates);
		appendLikeWhereClaus(contact,"zipCode", filter.getZipCode(),cb, predicates);
		appendLikeWhereClaus(contact,"city", filter.getCity(),cb, predicates);
		appendLikeWhereClaus(contact,"phone", filter.getPhone(),cb, predicates);
		appendLikeWhereClaus(contact,"email", filter.getEmail(),cb, predicates);
		appendLikeWhereClaus(contact,"updateBy", filter.getUpdateBy(),cb, predicates);
		appendGEWhereClaus(contact,"renewDate", filter.getRenewDateFrom(),cb, predicates);
		appendGEWhereClaus(contact,"expiryDate", filter.getExpiryDateFrom(),cb, predicates);
		appendGEWhereClaus(contact,"startDate", filter.getStartDateFrom(),cb, predicates);
		appendGEWhereClaus(contact,"updateTime", filter.getUpdateDateFrom(),cb, predicates);
		appendLEWhereClaus(contact,"renewDate", filter.getRenewDateTo(),cb, predicates);
		appendLEWhereClaus(contact,"expiryDate", filter.getExpiryDateTo(),cb, predicates);
		appendLEWhereClaus(contact,"startDate", filter.getStartDateTo(),cb, predicates);
		appendLEWhereClaus(contact,"updateTime", filter.getUpdateDateTo(),cb, predicates);

		cq.where((predicates.toArray(new Predicate[predicates.size()])));
		
		return getEntityManager().createQuery(cq).getResultList();
	}
	
	private void appendInWhereClaus(Root<Contact> contact, String filedName, String values, CriteriaBuilder cb, List<Predicate> predicates) {
		if (values != null && !values.isEmpty()) {
			Predicate p = contact.get(filedName).in(values.split(","));
			predicates.add(p);
		}
	}
	private void appendLikeWhereClaus(Root<Contact> contact, String filedName, String value,CriteriaBuilder cb, List<Predicate> predicates) {
		if (value != null && !value.isEmpty()) {
			predicates.add(cb.like(contact.<String>get(filedName), value));
		}
	}
	private void appendEqualsWhereClaus(Root<Contact> contact, String filedName, String value,CriteriaBuilder cb, List<Predicate> predicates) {
		if (value != null && !value.isEmpty()) {
			predicates.add(cb.equal(contact.get(filedName), value));
		}
	}
	private void appendGEWhereClaus(Root<Contact> contact, String filedName, Date value,CriteriaBuilder cb, List<Predicate> predicates) {
		if (value != null) {
			predicates.add(cb.greaterThanOrEqualTo(contact.<Date>get(filedName), value));
		}
	}
	private void appendLEWhereClaus(Root<Contact> contact, String filedName, Date value,CriteriaBuilder cb, List<Predicate> predicates) {
		if (value != null) {
			predicates.add(cb.lessThanOrEqualTo(contact.<Date>get(filedName), value));
		}
	}
	
}
