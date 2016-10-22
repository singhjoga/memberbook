package com.punjuprogrammers.memberbook.bl.service;

import java.util.List;

import com.punjuprogrammers.memberbook.bl.model.IdObject;
import com.punjuprogrammers.memberbook.bl.persistence.dao.BaseDao;

public interface CrudService<ID, T extends IdObject<ID>> {
	T findById(ID id);
	
	T add(T obj);

	T save(T obj);
	
	T delete(ID id);

	List<T> getAll();

	boolean exists(ID id);

	BaseDao<T> getDao();
}
