/**
 * @Title: ExcelUtils.java
 * @Package org.platform.snail.utils
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年1月7日 上午9:34:08
 * @version V1.0
 */

package com.huacainfo.ace.common.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @ClassName: ExcelUtils
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年1月7日 上午9:34:08
 *
 */

public class ExcelUtils {
	Logger logger = Logger.getLogger(this.getClass());

	public List<Map<String, Object>> readExcelByJXL(InputStream is, int startRow)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 WorkbookSettings workbookSettings = new WorkbookSettings();
		   workbookSettings.setEncoding("GBK"); //解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is,workbookSettings);

		
		Sheet sheet = workbook.getSheet(0);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, 0);
			header.put(i, c.getContents());
		}
		for (int r = startRow; r < rows; r++) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int c = 0; c < cols; c++) {
				Cell cell = sheet.getCell(c, r);
				row.put(header.get(c), cell.getContents());
			}
			list.add(row);
		}
		for (Map<String, Object> row : list) {
			logger.info(row);
		}
		return list;
	}

	public List<Map<String, Object>> readExcelByJXL(InputStream is, int startRow,String sheetName)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("GBK"); //解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is,workbookSettings);

		Sheet sheet = workbook.getSheet(sheetName);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, 0);
			header.put(i, c.getContents());
		}
		for (int r = startRow; r < rows; r++) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int c = 0; c < cols; c++) {
				Cell cell = sheet.getCell(c, r);
				row.put(header.get(c), cell.getContents());
			}
			list.add(row);
		}
		for (Map<String, Object> row : list) {
			logger.info(row);
		}
		return list;
	}

	public List<Map<String, Object>> readExcelByPOI(InputStream is, int startRow)
			throws Exception {
		Map<Object, Object> header = new HashMap<Object, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (Exception e) {
			workbook = new HSSFWorkbook(is);
		}
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Row row0 = null;
		while (rows.hasNext()) {
			row0 = rows.next();
			break;
		}
		Iterator<org.apache.poi.ss.usermodel.Cell> cells = row0.cellIterator();
		Object content = null;
		int i = 0;
		while (cells.hasNext()) {
			org.apache.poi.ss.usermodel.Cell cell = cells.next();
			switch (cell.getCellType()) { // 根据cell中的类型来输出数据
			case HSSFCell.CELL_TYPE_NUMERIC:
				content = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				content = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				content = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				content = String.valueOf(cell.getCellFormula());
				break;
			default:
				content = String.valueOf("");
				break;
			}
			header.put(i, content);
			i++;
		}
		this.logger.info(header);
		while (rows.hasNext()) {
			row0 = rows.next();
			cells = row0.cellIterator();
			Map<String, Object> row = new HashMap<String, Object>();
			int k = 0;
			while (cells.hasNext()) {
				org.apache.poi.ss.usermodel.Cell cell = cells.next();
				switch (cell.getCellType()) { // 根据cell中的类型来输出数据
				case HSSFCell.CELL_TYPE_NUMERIC:
					content = cell.getNumericCellValue();
					break;
				case HSSFCell.CELL_TYPE_STRING:
					content = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					content = cell.getBooleanCellValue();
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					content = cell.getCellFormula();
					break;
				default:
					content = null;
					break;
				}
				row.put(String.valueOf(header.get(k)), content);
				k++;
			}
			list.add(row);
		}
		if (workbook != null) {
			workbook.close();
		}
		for (Map<String, Object> row : list) {
			logger.info(row);
		}
		return list;
	}

	public List<Map<String, Object>> readExcelByPOI(InputStream is, int startRow,String sheetName)
			throws Exception {
		Map<Object, Object> header = new HashMap<Object, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			//workbook = new XSSFWorkbook(is);
			workbook = new HSSFWorkbook(is);
		} catch (Exception e) {
			//workbook = new HSSFWorkbook(is);
		}
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(sheetName);
		Iterator<Row> rows = sheet.rowIterator();
		Row row0 = null;
		while (rows.hasNext()) {
			row0 = rows.next();
			break;
		}
		Iterator<org.apache.poi.ss.usermodel.Cell> cells = row0.cellIterator();
		Object content = null;
		int i = 0;
		while (cells.hasNext()) {
			org.apache.poi.ss.usermodel.Cell cell = cells.next();
			switch (cell.getCellType()) { // 根据cell中的类型来输出数据
				case HSSFCell.CELL_TYPE_NUMERIC:
					content = String.valueOf(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					content = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					content = String.valueOf(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					content = String.valueOf(cell.getCellFormula());
					break;
				default:
					content = String.valueOf("");
					break;
			}
			header.put(i, content);
			i++;
		}
		this.logger.info(header);
		while (rows.hasNext()) {
			row0 = rows.next();
			cells = row0.cellIterator();
			Map<String, Object> row = new HashMap<String, Object>();
			int k = 0;
			while (cells.hasNext()) {
				org.apache.poi.ss.usermodel.Cell cell = cells.next();
				switch (cell.getCellType()) { // 根据cell中的类型来输出数据
					case HSSFCell.CELL_TYPE_NUMERIC:
						content = cell.getNumericCellValue();
						break;
					case HSSFCell.CELL_TYPE_STRING:
						content = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						content = cell.getBooleanCellValue();
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						content = cell.getCellFormula();
						break;
					default:
						content = null;
						break;
				}
				row.put(String.valueOf(header.get(k)), content);
				k++;
			}
			list.add(row);
		}
		if (workbook != null) {
			workbook.close();
		}
		for (Map<String, Object> row : list) {
			logger.info(row);
		}
		return list;
	}
	public List<Map<String, String>> readExcelByJXLMH(InputStream is,int sheetStart, int startRow,int header1,int header2,int header3)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		 WorkbookSettings workbookSettings = new WorkbookSettings();
		   workbookSettings.setEncoding("GBK"); //解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is,workbookSettings);
		
		Sheet sheet = workbook.getSheet(sheetStart);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, header1);
			header.put(i, c.getContents().replaceAll("\n", ""));
		}
		if(header2>0){
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header2);
				if(CommonUtils.isNotEmpty(c.getContents())){
					header.put(i, c.getContents().replaceAll("\n", ""));
				}
				
			}
		}
		if(header3>0){
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header3);
				if(CommonUtils.isNotEmpty(c.getContents())){
					header.put(i, c.getContents().replaceAll("\n", ""));
					
				}
				
			}
		}
		/*for(int i=0;i<36;i++){
			System.out.println("`"+header.get(i)+"` DECIMAL NOT NULL DEFAULT 0,");
		}*/
		logger.info(header);
		for (int r = startRow; r < rows; r++) {
			Map<String, String> row = new HashMap<String, String>();
			for (int c = 0; c < cols; c++) {
				Cell cell = sheet.getCell(c, r);
				row.put(header.get(c), cell.getContents());
			}
			list.add(row);
		}
		for (Map<String, String> row : list) {
			logger.info(row);
		}
		
		return list;
	}
	public Map<Object, String> readExcelHeadByJXLMH(InputStream is,int sheetStart,int header1,int header2,int header3)
			throws BiffException, IOException {
		Map<Object, String> header = new HashMap<Object, String>();
		
		 WorkbookSettings workbookSettings = new WorkbookSettings();
		   workbookSettings.setEncoding("GBK"); //解决中文乱码，或GBK
		Workbook workbook = Workbook.getWorkbook(is,workbookSettings);
		
		Sheet sheet = workbook.getSheet(sheetStart);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		this.logger.info(cols);
		this.logger.info(rows);
		for (int i = 0; i < cols; i++) {
			Cell c = sheet.getCell(i, header1);
			header.put(i, c.getContents().replaceAll("\n", ""));
		}
		if(header2>0){
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header2);
				if(CommonUtils.isNotEmpty(c.getContents())){
					header.put(i, c.getContents().replaceAll("\n", ""));
					
				}
				
			}
		}
		if(header3>0){
			for (int i = 0; i < cols; i++) {
				Cell c = sheet.getCell(i, header3);
				if(CommonUtils.isNotEmpty(c.getContents())){
					header.put(i, c.getContents().replaceAll("\n", ""));
					
				}
				
			}
		}
		logger.info(header);
		return header;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filepath = "/Users/chenxiaoke/Documents/student.xls";
		ExcelUtils eu = new ExcelUtils();
		FileInputStream is = null;
		try {
			is = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		eu.readExcelByJXLMH(is, 0, 2, 1, 0, 0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(is!=null){
					is.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
