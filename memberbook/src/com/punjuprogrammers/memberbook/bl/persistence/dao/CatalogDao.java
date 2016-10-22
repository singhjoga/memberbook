package com.punjuprogrammers.memberbook.bl.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.punjuprogrammers.memberbook.bl.model.CatalogItem;

@Repository
public class CatalogDao extends BaseDao<CatalogItem> {
	private static final long serialVersionUID = -9076988904435848292L;

	public List<CatalogItem> getByCatalog(String catalog) {
		return super.fetchNamedQueryResultList("CatalogItem.getByCatalog", catalog);
	}
}
