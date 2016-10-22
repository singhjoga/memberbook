package com.punjuprogrammers.memberbook.bl.service;

import java.util.Map;

import com.punjuprogrammers.memberbook.bl.model.ConfigItem;

public interface ConfigService extends CrudService<String, ConfigItem>{
	Map<String, ConfigItem> getAllAsMap();
	void saveAll(Map<String, ConfigItem> map);
}