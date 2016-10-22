package com.punjuprogrammers.memberbook.bl.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.punjuprogrammers.memberbook.bl.model.Country;
@Repository
public class CountryDao extends BaseDao<Country> {

	private static final long serialVersionUID = -656760742200354513L;

	public List<Country> findAll() {
		return fetchNamedQueryResultList("Contact.findAllSorted");
	}
}
