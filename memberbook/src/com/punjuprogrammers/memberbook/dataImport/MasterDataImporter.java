package com.punjuprogrammers.memberbook.dataImport;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.punjuprogrammers.memberbook.bl.model.CatalogItem;
import com.punjuprogrammers.memberbook.bl.service.CatalogService;

public class MasterDataImporter extends BaseImporter {
	public static void main(String[] args) {
		MasterDataImporter importer = new MasterDataImporter();
		importer.importData("MasterData.xlsx",0);
		System.exit(0);
	}


	public void importSheet(XSSFSheet sheet) {
		final CatalogService service = getApplicationContext().getBean(CatalogService.class);
		Iterator<Row> rowIterator = sheet.iterator();
		// skip header row
		Row row = rowIterator.next();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			if (row.getCell(0) ==null) break;
			
			String catalog = getString(row.getCell(0));
			String itemKey = getString(row.getCell(1));
			String itemValue = getString(row.getCell(2));
			int sortOrder = getInt(row.getCell(3));

			CatalogItem item = service.findById(itemKey);
			boolean newItem = false;
			if (item == null) {
				newItem = true;
				item = new CatalogItem();
			}
			item.setCatalog(catalog);
			item.setItemKey(itemKey);
			item.setItemValue(itemValue);
			item.setSortOrder(sortOrder);
			if (newItem) {
				service.add(item);
			}else{
				service.save(item);
			}
		}
	}

}
