package com.punjuprogrammers.memberbook.bl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(indexes={@Index(name="idx_catalog",columnList="catalog,sortOrder,itemValue")})
@NamedQueries({@NamedQuery(name="CatalogItem.getByCatalog",query="SELECT c FROM CatalogItem c WHERE c.catalog=:catalog ORDER BY c.sortOrder")})
public class CatalogItem implements Serializable{

	private static final long serialVersionUID = 6585248377616538570L;

	@Column (length=20)
	private String catalog;
	
	@Id
	@Column(length=20)
	private String itemKey;
	
	@Column(length=500)
	private String itemValue;
	
	@Column
	private int sortOrder=0;

	public CatalogItem() {
	}

	public CatalogItem(String catalog, String itemKey, String itemValue) {
		super();
		this.catalog = catalog;
		this.itemKey = itemKey;
		this.itemValue = itemValue;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return "CatalogItem [catalog=" + catalog + ", itemKey=" + itemKey + ", itemValue=" + itemValue + ", sortOrder=" + sortOrder + "]";
	}
	
	
}
