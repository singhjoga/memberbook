package com.punjuprogrammers.memberbook.bl.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.punjuprogrammers.memberbook.bl.model.Filter;
import com.punjuprogrammers.memberbook.bl.persistence.dao.BaseDao;
import com.punjuprogrammers.memberbook.bl.persistence.dao.FilterDao;
import com.punjuprogrammers.memberbook.bl.service.FilterService;

@Service
public class FilterServiceImpl extends CrudServiceImpl<Long, Filter> implements FilterService{
	private static final Logger LOG = LoggerFactory.getLogger(FilterServiceImpl.class);
	
	@Inject
	private FilterDao dao;
	
	@Override
	public BaseDao<Filter> getDao() {
		return dao;
	}

}
