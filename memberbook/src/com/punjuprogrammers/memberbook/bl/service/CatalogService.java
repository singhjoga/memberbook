package com.punjuprogrammers.memberbook.bl.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.punjuprogrammers.memberbook.bl.model.CatalogItem;
import com.punjuprogrammers.memberbook.bl.model.Country;
import com.punjuprogrammers.memberbook.bl.persistence.dao.CatalogDao;
import com.punjuprogrammers.memberbook.bl.persistence.dao.CountryDao;

@Service
public class CatalogService {
	private static final Logger LOG = LoggerFactory.getLogger(CatalogService.class);
	@Inject
	private CatalogDao dao;

	@Inject
	private CountryDao countryDao;
	
	public List<CatalogItem> getByCatalog(String catalog) {
		return dao.getByCatalog(catalog);
	}
	public List<CatalogItem> getAll() {
		return dao.findAll();
	}
	public boolean exists(String catalogItemKey) {
		return dao.exists(catalogItemKey);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public CatalogItem add(final CatalogItem item) {
		dao.add(item);
		LOG.info("Catalog item added: "+item.toString());
		return item;
	}

	public CatalogItem findById(String catalogItemKey) {
		return dao.findById(catalogItemKey);
	}

	@Transactional
	public CatalogItem save(final CatalogItem item) {
		dao.update(item);
		LOG.info("Catalog item saved: "+item.toString());
		return item;
	}

	public List<Country> getCountryAll() {
		return countryDao.findAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Country addCountry(final Country item) {
		countryDao.add(item);
		return item;
	}

	public Country findCountryById(String catalogItemKey) {
		return countryDao.findById(catalogItemKey);
	}

	@Transactional
	public Country saveCountry(final Country item) {
		countryDao.update(item);
		return item;
	}

	
}
