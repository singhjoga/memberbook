package com.punjuprogrammers.memberbook.bl.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.punjuprogrammers.memberbook.bl.model.ConfigItem;

@Repository
public class ConfigDao extends BaseDao<ConfigItem> {
	private static final long serialVersionUID = -9076988904435848292L;

	public List<ConfigItem> getByGroup(String group) {
		return super.fetchNamedQueryResultList("ConfigItem.getByGroup", group);
	}
}
