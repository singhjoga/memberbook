package com.punjuprogrammers.memberbook.bl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.punjuprogrammers.memberbook.bl.model.ConfigItem;
import com.punjuprogrammers.memberbook.bl.persistence.dao.BaseDao;
import com.punjuprogrammers.memberbook.bl.persistence.dao.ConfigDao;
import com.punjuprogrammers.memberbook.bl.service.ConfigService;

@Service
public class ConfigServiceImpl extends CrudServiceImpl<String, ConfigItem> implements ConfigService{
	private static final Logger LOG = LoggerFactory.getLogger(ConfigServiceImpl.class);
	
	@Inject
	private ConfigDao dao;

	@Override
	public BaseDao<ConfigItem> getDao() {
		return dao;
	}

	@Override
	public Map<String, ConfigItem> getAllAsMap() {
		Map<String, ConfigItem> map = new HashMap<String, ConfigItem>();
		for (ConfigItem item: dao.findAll()) {
			map.put(item.getConfigKey(), item);
		}
		return map;
	}

	@Override
	@Transactional
	public void saveAll(Map<String, ConfigItem> map) {
		for (String key: map.keySet()) {
			ConfigItem item = map.get(key);
			if (dao.exists(key)) {
				dao.update(item);
			}else{
				dao.add(item);
			}
		}
	}

}
