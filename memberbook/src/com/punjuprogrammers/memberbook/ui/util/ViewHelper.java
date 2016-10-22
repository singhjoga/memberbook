package com.punjuprogrammers.memberbook.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.punjuprogrammers.memberbook.bl.model.CatalogItem;
import com.punjuprogrammers.memberbook.bl.model.Country;
import com.punjuprogrammers.memberbook.bl.model.ValuedEnum;

public class ViewHelper {
	public static void showListAddEditDialog(Long listId) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 600);
		options.put("height", 150);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		if (listId != null) {
			List<String> ids = new ArrayList<String>();
			ids.add(listId.toString());
			params.put("listId", ids);
		}
		RequestContext.getCurrentInstance().openDialog("addlist", options, params);
	}

	public static void showAdd2ListDialog(Long... wordIds) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 650);
		options.put("height", 250);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		List<String> wordIdList = Util.array2StringList(wordIds);
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("wordId", wordIdList);
		RequestContext.getCurrentInstance().openDialog("add2list", options, params);
	}
	public static void showContactDialog(Long contactId, String mode) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 650);
		options.put("height", 410);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		List<String> contactIdList = Util.array2StringList(contactId);
		List<String> modeList = Util.array2StringList(mode);
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("contactId", contactIdList);
		params.put("mode", modeList);
		RequestContext.getCurrentInstance().openDialog("contact", options, params);
	}
	public static void showMoneyTransactionDialog(Long contactId, Long trId, String mode) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 650);
		options.put("height", 316);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		List<String> trIdList = Util.array2StringList(trId);
		List<String> modeList = Util.array2StringList(mode);
		List<String> contactIdList = Util.array2StringList(contactId);
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("trId", trIdList);
		params.put("contactId", contactIdList);
		params.put("mode", modeList);
		RequestContext.getCurrentInstance().openDialog("moneytransaction", options, params);
	}
	public static void showAccountDialog(Long contactId, String mode) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 650);
		options.put("height", 410);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		List<String> contactIdList = Util.array2StringList(contactId);
		List<String> modeList = Util.array2StringList(mode);
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("contactId", contactIdList);
		params.put("mode", modeList);
		RequestContext.getCurrentInstance().openDialog("account", options, params);
	}
	
	public static void showDetailsDialog(Object id, String mode, String view, int height, int width) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", width);
		options.put("height", height);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		List<String> contactIdList = Util.array2StringList(id);
		List<String> modeList = Util.array2StringList(mode);
		
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		params.put("id", contactIdList);
		params.put("mode", modeList);
		RequestContext.getCurrentInstance().openDialog(view, options, params);
	}
	public static List<SelectItem> getCountrySelectList() {
		String[] locales = Locale.getISOCountries();
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			list.add(new SelectItem(obj.getCountry(), obj.getDisplayCountry()));
		}
		Collections.sort(list,new Comparator<SelectItem>() {

			@Override
			public int compare(SelectItem o1, SelectItem o2) {
				String value11 = (String)o1.getLabel();
				String value12 = (String)o2.getLabel();
				return value11.compareTo(value12);
			}
		});
		return list;
	}

	public static List<SelectItem> convertCountryList2SelectItemList(List<Country> countryList) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (Country country : countryList) {
			Locale obj = new Locale("", country.getIso2Code());
			list.add(new SelectItem(country.getCode(), obj.getDisplayCountry()));
		}
		return list;
	}
	public static List<SelectItem> convertCountryList2CurrencyList(List<Country> countryList) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (Country country : countryList) {
			list.add(new SelectItem(country.getCurrencyCode(), country.getCurrencyCode()));
		}
		return list;
	}
	public static List<SelectItem> convertEnumList2SelectItemList(List<?> enumList) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		for (Object obj: enumList) {
			ValuedEnum valuedEnum = (ValuedEnum)obj;
			list.add(new SelectItem(valuedEnum.getValue(), valuedEnum.getLabel()));
		}
		return list;
	}
	
	public static List<SelectItem> convertCatalogItems2SelectItemList(Collection<CatalogItem> catalogItems, String catalog) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (CatalogItem item : catalogItems) {
			if (item.getCatalog().equals(catalog)) {
				list.add(new SelectItem(item.getItemKey(), item.getItemValue()));
			}
		}
		
		return list;
	}
	
	public static String selectItemValueAs(List<SelectItem> list) {
		StringBuilder sb = new StringBuilder();
		for (SelectItem item: list) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(item.getValue());
		}
		
		return sb.toString();
	}
	public static String selectItemLabelAs(List<SelectItem> list) {
		StringBuilder sb = new StringBuilder();
		for (SelectItem item: list) {
			if (sb.length() > 0) {
				sb.append(", ");
			}
			sb.append(item.getLabel());
		}
		
		return sb.toString();
	}
	public static List<SelectItem> getMonthNameList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		for (Integer i=1; i <=12; i++) {
			list.add(new SelectItem(i.toString(), i.toString()));
		}
		return list;
	}		
	public static List<SelectItem> getMonthDateList() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		for (Integer i=1; i <=31; i++) {
			list.add(new SelectItem(i.toString(), i.toString()));
		}
		return list;
	}		
}
