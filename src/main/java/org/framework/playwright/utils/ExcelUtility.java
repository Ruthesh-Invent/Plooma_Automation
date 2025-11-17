
package org.framework.playwright.utils;
 
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;
 
import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.framework.playwright.constants.FrameWorkConstants;
 
public class ExcelUtility {
 
	FileInputStream fis;
	Workbook wb;

	public String getSingleData(String path, String sheetName, int rowNum, int cellNum) {
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		String data = cell.getStringCellValue();

		return data;

	}

	public Object[][] getMultipleData(String fileName, String sheetName) {
		try {
			fis = new FileInputStream(getExcelFilePath(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data = new Object[rowCount - 1][cellCount];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	

	 public String getExcelFilePath(String fileName) {
	        String Filepath = FrameWorkConstants.EXCELBASE_PATH + fileName;
	        return Filepath;
	    }

	public int getRowCount(String path, String sheetName) {
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}

	
//	public static void main(String[] args) {
//		ExcelUtility excel=new ExcelUtility();
//		String data = excel.getLastFilingDateWithCompanyNameFilter("./src/main/resources/Luxemburg - BM Monitoring.xlsx", "Sheet2", "Gemplus International SA", "MM-yyyy");
//		System.out.println(data);
//		
//	}
}