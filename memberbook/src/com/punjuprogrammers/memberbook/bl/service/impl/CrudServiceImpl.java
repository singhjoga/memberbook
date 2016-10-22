package com.punjuprogrammers.memberbook.bl.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.punjuprogrammers.memberbook.bl.model.IdObject;
import com.punjuprogrammers.memberbook.bl.service.CrudService;

public abstract class CrudServiceImpl<ID, T extends IdObject<ID>> implements CrudService<ID, T> {

	@Override
	public T findById(ID id) {
		return getDao().findById(id);
	}

	@Override
	@Transactional
	public T add(T obj) {
		getDao().add(obj);
		return obj;
	}

	@Override
	@Transactional
	public T save(T obj) {
		if (obj.getId() == null) {
			return add(obj);
		} else {
			return getDao().update(obj);
		}
	}

	@Override
	@Transactional
	public T delete(ID id) {
		return getDao().deleteById(id);
	}

	@Override
	public List<T> getAll() {
		return getDao().findAll();
	}

	@Override
	public boolean exists(ID id) {
		return getDao().exists(id);
	}

}
