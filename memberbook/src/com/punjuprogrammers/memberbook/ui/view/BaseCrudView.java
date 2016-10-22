package com.punjuprogrammers.memberbook.ui.view;

import java.lang.reflect.ParameterizedType;

import com.punjuprogrammers.memberbook.bl.model.IdObject;
import com.punjuprogrammers.memberbook.bl.service.CrudService;

public abstract class BaseCrudView<ID, OBJ extends IdObject<ID>, SERVICE extends CrudService<ID,OBJ>> extends BaseView{
	private Class<SERVICE> serviceClass;
	private Class<OBJ> objClass;
	private Class<ID> idClass;
	
	private SERVICE service;
	
	public void init() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		serviceClass = (Class<SERVICE>) parameterizedType.getActualTypeArguments()[2];
		objClass = (Class<OBJ>) parameterizedType.getActualTypeArguments()[1];
		idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[0];
		service = getServiceBean(serviceClass);
	}
	public SERVICE getService() {
		return service;
	}
	public ID castId(String id) {
		if (idClass.equals(String.class)) {
			return (ID)id;
		}else if(idClass.equals(Long.class)) {
			Long l = Long.parseLong(id);
			return (ID)l;
		}else{
			throw new IllegalArgumentException("Type "+idClass.getName()+" not supported");
		}
	}
	public Class<SERVICE> getServiceClass() {
		return serviceClass;
	}
	public Class<OBJ> getObjClass() {
		return objClass;
	}
	public Class<ID> getIdClass() {
		return idClass;
	}
	
}
