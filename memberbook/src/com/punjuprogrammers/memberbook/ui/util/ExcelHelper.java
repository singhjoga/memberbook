package com.punjuprogrammers.memberbook.ui.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.punjuprogrammers.memberbook.bl.model.Contact;

public class ExcelHelper {
	public static final String STYLE_INTEGER_FORMAT = "0";
	public static final String STYLE_INTEGER_NAME = "INT";
	public static final String STYLE_DECIMAL_FORMAT = "#,##0.00";
	public static final String STYLE_DECIMAL_NAME = "DECI";
	public static final String STYLE_DATE_FORMAT = "m/d/yy";
	public static final String STYLE_DATE_NAME = "DT";
	public static final String STYLE_TIMESTAMP_FORMAT = "m/d/yy h:mm";
	public static final String STYLE_TIMESTAMP_NAME = "TS";
	public static final String STYLE_TIME_FORMAT = "h:mm:ss";
	public static final String STYLE_TIME_NAME = "TM";

	public static XSSFWorkbook exportContacts(List<Contact> contacts) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("contacts");
		Map<String, CellStyle> styles = createStandardStyles(workbook);
		// Create header row
		String headers[] = new String[] { "Id", "title", "firstname", "lastname", "address1", "address2", "zipcode", "city", "phone", "email", "startdate", "renewaldate",
				"expirydate", "type" };
		appendRow(styles, sheet, headers);
		for (Contact c : contacts) {
			appendRow(styles, sheet, new Object[] { c.getContactId(), c.getTitle(), c.getFirstName(), c.getLastName(), c.getAddress1(), c.getAddress2(), c.getZipCode(),
					c.getCity(), c.getPhone(), c.getEmail(), c.getStartDate(), c.getRenewDate(), c.getExpiryDate(), c.getContactType() });
		}
		return workbook;
	}

	private static void appendRow(Map<String, CellStyle> styles, XSSFSheet sheet, Object[] values) {
		XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
		int cellIndex = -1;
		for (Object value : values) {
			cellIndex++;
			Cell cell = row.createCell(cellIndex);
			setCellValue(styles, cell, value);
		}
	}

	private static void setCellValue(Map<String, CellStyle> styles, Cell cell, Object value) {
		if (value == null) {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
		} else if ((value instanceof String)) {
			cell.setCellValue((String) value);
			cell.setCellType(Cell.CELL_TYPE_STRING);
		} else if ((value instanceof Boolean)) {
			cell.setCellValue((boolean) value);
			cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
		} else if ((value instanceof Integer) || (value instanceof Short) || (value instanceof Long)) {
			cell.setCellValue(((Number)value).doubleValue());
			cell.setCellStyle(styles.get(STYLE_INTEGER_NAME));
		} else if ((value instanceof BigDecimal) || (value instanceof Double) || (value instanceof Float)) {
			cell.setCellValue((double) value);
			cell.setCellStyle(styles.get(STYLE_DECIMAL_NAME));
		} else if ((value instanceof Date)) {
			cell.setCellValue(dateToCalendar((Date) value));
			cell.setCellStyle(styles.get(STYLE_DATE_NAME));
		} else if ((value instanceof Calendar)) {
			cell.setCellValue((Calendar) value);
			cell.setCellStyle(styles.get(STYLE_TIMESTAMP_NAME));
		} else {
			throw new IllegalArgumentException("Cannot set value for cell " + cell.getColumnIndex() + ". Value type: " + value.getClass().getSimpleName());
		}
	}

	public static Map<String, CellStyle> createStandardStyles(XSSFWorkbook workbook) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat((short) BuiltinFormats.getBuiltinFormat(STYLE_DATE_FORMAT));
		styles.put(STYLE_DATE_NAME, style);

		style = workbook.createCellStyle();
		style.setDataFormat((short) BuiltinFormats.getBuiltinFormat(STYLE_DECIMAL_FORMAT));
		styles.put(STYLE_DECIMAL_NAME, style);

		style = workbook.createCellStyle();
		style.setDataFormat((short) BuiltinFormats.getBuiltinFormat(STYLE_INTEGER_FORMAT));
		styles.put(STYLE_INTEGER_NAME, style);

		style = workbook.createCellStyle();
		style.setDataFormat((short) BuiltinFormats.getBuiltinFormat(STYLE_TIME_FORMAT));
		styles.put(STYLE_TIME_NAME, style);

		style = workbook.createCellStyle();
		style.setDataFormat((short) BuiltinFormats.getBuiltinFormat(STYLE_TIMESTAMP_FORMAT));
		styles.put(STYLE_TIMESTAMP_NAME, style);

		return styles;
	}

	public static Calendar dateToCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);

		return cal;
	}
}
