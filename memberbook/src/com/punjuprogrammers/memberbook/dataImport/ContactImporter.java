package com.punjuprogrammers.memberbook.dataImport;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.punjuprogrammers.memberbook.bl.model.Contact;
import com.punjuprogrammers.memberbook.bl.service.ContactService;

public class ContactImporter extends BaseImporter{
	public static void main(String[] args) {
		ContactImporter importer = new ContactImporter();
		String file = "c:/temp/contacts.xlsx";
		importer.importData(file, 0);
		System.exit(0);
	}
	public void importSheet(XSSFSheet sheet) {
		final ContactService service = getApplicationContext().getBean(ContactService.class);
		Iterator<Row> rowIterator = sheet.iterator();
		// skip header row
		Row row = rowIterator.next();
		int i=0;
		while (rowIterator.hasNext()) {
			i++;
			//if (i==10) break;
			row = rowIterator.next();
			Long id = getLong(row.getCell(0));
			if (!service.exists(id)) {
				String status = getString(row.getCell(13));
				Integer zipCode = getInt(row.getCell(6));
				final Contact c = new Contact();
				c.setContactId(id);
				c.setTitle(getString(row.getCell(1)));
				c.setFirstName(getString(row.getCell(2)));
				c.setLastName(getString(row.getCell(3)));
				c.setAddress1(getString(row.getCell(4)));
				c.setAddress2(getString(row.getCell(5)));
				c.setZipCode(zipCode==null?null:zipCode.toString());
				c.setCity(getString(row.getCell(7)));
				c.setPhone(getString(row.getCell(8)));
				c.setEmail(getString(row.getCell(9)));
				c.setStartDate(getDate(row.getCell(10)));
				c.setRenewDate(getDate(row.getCell(11)));
				c.setExpiryDate(getDate(row.getCell(12)));
				c.setStatus((status == null || status.isEmpty() ? "A" : status.equals("Archive") ? "I" : "D"));
				c.setComments(getString(row.getCell(14)));
				c.setContactType("INDV");
				c.setCountryCode("DE");
				service.add(c);
			}
		}
	}
}
