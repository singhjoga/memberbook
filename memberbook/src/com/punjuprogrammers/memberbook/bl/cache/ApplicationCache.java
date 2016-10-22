package com.punjuprogrammers.memberbook.bl.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.punjuprogrammers.memberbook.bl.model.CatalogItem;
import com.punjuprogrammers.memberbook.bl.model.Country;
import com.punjuprogrammers.memberbook.bl.service.CatalogService;

@Component
@Scope("singleton")
public class ApplicationCache {

	private Map<String, CatalogItem> catalogItems;
	private List<Country> countryList;
	
	@Inject
	private CatalogService catalogService;
	
	@PostConstruct
	public void refresh() {
		Map<String, CatalogItem> map = new HashMap<String,CatalogItem>();
		List<CatalogItem> list = catalogService.getAll();
		for (CatalogItem item: list) {
			map.put(item.getItemKey(), item);
		}
		catalogItems = map;
		
		countryList = catalogService.getCountryAll();
		
	}

	public Map<String, CatalogItem> getCatalogItems() {
		return catalogItems;
	}

	public List<Country> getCountryList() {
		return countryList;
	}
	
	
}
