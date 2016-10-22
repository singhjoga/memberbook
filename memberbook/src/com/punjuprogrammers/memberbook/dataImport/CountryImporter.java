package com.punjuprogrammers.memberbook.dataImport;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.punjuprogrammers.memberbook.bl.model.Country;
import com.punjuprogrammers.memberbook.bl.service.CatalogService;

public class CountryImporter extends BaseImporter {
	public static void main(String[] args) {
		CountryImporter importer = new CountryImporter();
		importer.importData("CountryList.xlsx",0);
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
			
			String name = getString(row.getCell(0));
			String iso2Code = getString(row.getCell(1));
			String iso3Code = getString(row.getCell(2));
			Integer isoNum = getInt(row.getCell(3));
			String dialCode = getString(row.getCell(4));
			String currCode = getString(row.getCell(5));
			Integer currMinorUnit = getInt(row.getCell(6));
			String currName = getString(row.getCell(7));
			Integer currNumCode = getInt(row.getCell(8));

			Country item = service.findCountryById(iso3Code);
			boolean newItem = false;
			if (item == null) {
				newItem = true;
				item = new Country();
			}
			item.setCode(iso3Code);
			item.setName(name);
			item.setIso2Code(iso2Code);
			item.setIsoNumCode(isoNum);
			item.setDialCode(dialCode);
			item.setCurrencyCode(currCode);
			item.setCurrencyMinorUnit(currMinorUnit);
			item.setCurrencyName(currName);
			item.setCurrencyNumCode(currNumCode);
			if (newItem) {
				service.addCountry(item);
			}else{
				service.saveCountry(item);
			}
		}
	}

}
