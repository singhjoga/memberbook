package com.punjuprogrammers.memberbook.bl.model;

import java.io.Serializable;

public interface IdObject<T> extends Serializable {
	public T getId();
	public void setId(T id);
}
