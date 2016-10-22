package com.punjuprogrammers.memberbook.dataImport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;

import com.punjuprogrammers.memberbook.bl.spring.SpringUtil;

public abstract class BaseImporter {


	private ApplicationContext ctx;

	public BaseImporter() {
		super();
	}

	public void importData(String file, int sheetIndex) {
		ctx = SpringUtil.startApplication();
		importFile(file, sheetIndex);
	}
	public XSSFWorkbook openXslxFile(String file) {
		File myFile = new File(file);
		InputStream fis;
		try {
			fis = new FileInputStream(myFile);
		} catch (FileNotFoundException e) {
			//try to read from class path
			fis = this.getClass().getClassLoader().getResourceAsStream(file);
			if (fis == null) {
				throw new IllegalStateException(e);
			}
		}
		XSSFWorkbook myWorkBook;
		try {
			myWorkBook = new XSSFWorkbook(fis);
			return myWorkBook;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	protected Date getDate(Cell cell) {
	
		try {
			return cell.getDateCellValue();
		} catch (Exception e) {
			return null;
		}
	}

	protected Integer getInt(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			return new Double(cell.getNumericCellValue()).intValue();
		default:
			return null;
		}
	}

	protected Long getLong(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			return new Double(cell.getNumericCellValue()).longValue();
		default:
			return null;
		}
	}

	protected Connection getMySQLConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bv?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET&zeroDateTimeBehavior=convertToNull",
					"root", "");
			Statement st = conn.createStatement();
			// st.executeQuery("SET NAMES utf8");
			return conn;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	protected String getString(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_NUMERIC:
			return new Double(cell.getNumericCellValue()).toString();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		default:
			return null;
		}
	}

	protected Writer getUtf8Writer() throws Exception {
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/tmp/utf8.txt"), "UTF-8"));
		return out;
	}

	protected boolean isCellBlank(Cell cell) {
		return cell.getCellType() == Cell.CELL_TYPE_BLANK || (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().isEmpty());
	}

	protected void importFile(String file, int sheetIndex) {
	
		XSSFWorkbook myWorkBook = openXslxFile(file);
		if (sheetIndex == -1) {
			// All sheets
			for (int i = 0; i < myWorkBook.getNumberOfSheets(); i++) {
				XSSFSheet mySheet = myWorkBook.getSheetAt(i);
				importSheet(mySheet);
			}
	
		} else {
			XSSFSheet mySheet = myWorkBook.getSheetAt(sheetIndex);
			importSheet(mySheet);
		}
	
		try {
			myWorkBook.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public ApplicationContext getApplicationContext() {
		return ctx;
	}

	abstract public void importSheet(XSSFSheet sheet);
}