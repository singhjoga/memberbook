package com.punjuprogrammers.memberbook.bl.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDao<T extends Serializable> implements Serializable{

	protected static final long serialVersionUID = 689380552190809831L;
	private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);
	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	public BaseDao() {
		super();
		clazz = getClassType();
	}
	//@Inject
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public boolean exists(Object id) {
		T entity = getEntityManager().find(clazz, id);

		return entity != null;
	}

	public T findById(Object id) {
		return getEntityManager().find(clazz, id);
	}

	public List<T> findById(Object ids[]) {
		List<T> list = new ArrayList<T>();
		for (Object id : ids) {
			list.add(findById(id));
		}

		return list;
	}

	public List<T> findAll() {
		return getEntityManager().createQuery("from " + clazz.getName()).getResultList();
	}

	public void add(T entity) {
		getEntityManager().persist(entity);
	}

	public T update(T entity) {
		return getEntityManager().merge(entity);
	}

	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	public T deleteById(Object entityId) {
		T entity = findById(entityId);
		delete(entity);
		return entity;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}
	public void commitTransaction() {
		getEntityManager().getTransaction().commit();
	}
	public void rollbackTransaction() {
		getEntityManager().getTransaction().rollback();
	}
	public List<T> fetchNamedQueryResultList(String namedQuery, Object... parameters) {
		EntityManager em = getEntityManager();
		TypedQuery<T> q = em.createNamedQuery (namedQuery,clazz);
		if (parameters != null) {
			int i=1;
			for (Object param: parameters) {
				q.setParameter(i, param);
				i++;
			}
		}
		return q.getResultList();
	}
	public T fetchNamedQueryResult(String namedQuery, Object... parameters) {
		EntityManager em = getEntityManager();
		TypedQuery<T> q = em.createNamedQuery (namedQuery,clazz);
		if (parameters != null) {
			int i=0;
			for (Object param: parameters) {
				q.setParameter(i, param);
				i++;
			}
		}
		return q.getSingleResult();
	}	
	
}