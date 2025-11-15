package org.dewa.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static FileInputStream fis;
	public static Workbook wb;

	public ExcelUtility(String path) {
		try {
			ExcelUtility.fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ExcelUtility.wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getSingleData(String sheetName, int rowNum, int cellNum) {
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			return "";
		}
		Cell cell = row.getCell(cellNum);

		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case BLANK:
			return "";
		default:
			return cell.toString();
		}
	}
	
	public int getIntSingleValue(String sheetName, int rowNum, int cellNum) {
		Sheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			return 0;
		}
		Cell cell = row.getCell(cellNum);

		if (cell == null) {
			return 0;
		}
		
		return (int)cell.getNumericCellValue();
	}

	public Object[][] getMultipleData(String sheetName) {
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data = new Object[rowCount - 1][cellCount];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				Cell cell = sheet.getRow(i).getCell(j);
				if (cell == null) {
					data[i - 1][j] = "";
				} else {
					switch (cell.getCellType()) {
					case STRING:
						data[i - 1][j] = cell.getStringCellValue();
						break;
					case NUMERIC:
						data[i - 1][j] = (int) cell.getNumericCellValue();
						break;
					case BOOLEAN:
						data[i - 1][j] = cell.getBooleanCellValue();
						break;
					case BLANK:
						data[i - 1][j] = "";
						break;
					default:
						data[i - 1][j] = cell.toString();
						break;
					}
				}
			}
		}
		return data;
	}

	public static int getColumnIndex(String sheetName, String columnName) {
		Sheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			throw new IllegalArgumentException("Sheet with name '" + sheetName + "' does not exist.");
		}

		Row headerRow = sheet.getRow(0);
		if (headerRow == null) {
			throw new IllegalStateException("The sheet '" + sheetName + "' does not have a header row.");
		}

		int cellCount = headerRow.getPhysicalNumberOfCells();

		for (int i = 0; i < cellCount; i++) {
			Cell cell = headerRow.getCell(i);
			if (cell == null)
				continue;

			String headerName = cell.getStringCellValue().trim();
			if (headerName.equalsIgnoreCase(columnName)) {
				return i;
			}
		}
		throw new IllegalArgumentException(
				"Column with name '" + columnName + "' not found in sheet '" + sheetName + "'.");
	}

	public List<Object> getColumnDataByColoumnName(String sheetName, String columnName) {
		Sheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			throw new IllegalArgumentException("Sheet with name '" + sheetName + "' does not exist.");
		}

		int rowCount = sheet.getPhysicalNumberOfRows();
		if (rowCount <= 1) {
			throw new IllegalStateException("The sheet '" + sheetName + "' does not have data rows.");
		}

		int reqIndex = getColumnIndex(sheetName, columnName);
		List<Object> columnData = new ArrayList<>();

		for (int i = 1; i < rowCount; i++) { 
			Row row = sheet.getRow(i);
			if (row == null) {
				columnData.add("");
				continue;
			}

			Cell cell = row.getCell(reqIndex);
			if (cell == null) {
				columnData.add("");
			} else {
				switch (cell.getCellType()) {
				case STRING:
					columnData.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					columnData.add(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					columnData.add(cell.getBooleanCellValue());
					break;
				case BLANK:
					columnData.add("");
					break;
				default:
					columnData.add(cell.toString());
					break;
				}
			}
		}
		return columnData;
	}

	
	public static int findRowNumber(String filePath, String sheetName, String searchData) {
        FileInputStream fis = null;
        Workbook workbook = null;

        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return -1; 
            }

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(searchData)) {
                        return row.getRowNum(); 
                    } else if (cell.getCellType() == CellType.NUMERIC && String.valueOf(cell.getNumericCellValue()).equals(searchData)) {
                        return row.getRowNum(); 
                    }
                }
            }

            System.out.println("Data not found in the sheet: " + searchData);
            return -1;

        } catch (IOException e) {
            e.printStackTrace();
            return -1; 
        } finally {
            try {
                if (fis != null) fis.close();
                if (workbook != null) workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void writeBackIntegerToExcel(String filePath, String sheetName, int rowNum, int colNum, int valueToAdd) {
		FileInputStream fis = null;
        FileOutputStream fos = null;
        Workbook workbook = null;

        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName); 
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(0); 
            }

            if (cell.getCellType() == CellType.NUMERIC) {
                double currentValue = cell.getNumericCellValue();
                cell.setCellValue(currentValue + valueToAdd);
            } else {
                cell.setCellValue(valueToAdd);
            }

            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            System.out.println("Written value '" + valueToAdd + "' to row " + rowNum + ", column " + colNum);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
                if (workbook != null) workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void writeBackStringToExcel(String filePath, String sheetName, int rowNum, int colNum, String valueToAdd) {
		FileInputStream fis = null;
        FileOutputStream fos = null;
        Workbook workbook = null;

        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName); 
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(valueToAdd);

            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            System.out.println("Written value '" + valueToAdd + "' to row " + rowNum + ", column " + colNum);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
                if (workbook != null) workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void clearColumns(String filePath, String sheetName, int[] columnsToClear) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        Workbook workbook = null;

        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                for (Row row : sheet) {
                    for (int colNum : columnsToClear) {
                        Cell cell = row.getCell(colNum);
                        if (cell != null) {
                            row.removeCell(cell);
                            
                        }
                    }
                }
            }

            fos = new FileOutputStream(filePath);
            workbook.write(fos);
            System.out.println("Cleared columns: " + java.util.Arrays.toString(columnsToClear) + " in sheet: " + sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
                if (workbook != null) workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	public String getVoiceIdWithScenarioDescriptionFilter(String sheetName, String scenarioDescription) {
		Sheet sheet = wb.getSheet(sheetName);
		int reqIndex = 0;
		List<String> reqColumnAllData = new ArrayList<String>();
		String reqData = null;
		int rowCount = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowCount; i++) {
			Cell cell = sheet.getRow(i).getCell(reqIndex);
			String requiredColoumnData=null;
			switch (cell.getCellType()) {
			case STRING:
				requiredColoumnData = cell.getStringCellValue(); // For String cells
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					requiredColoumnData = cell.getDateCellValue().toString(); // For Date cells
				} else {
					requiredColoumnData = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
					// reqData= String.valueOf(cell.getNumericCellValue()); // Convert numeric to
					// string
				}
				break;
			case BOOLEAN:
				requiredColoumnData = String.valueOf(cell.getBooleanCellValue()); // Boolean cells
				break;
			case FORMULA:
				requiredColoumnData = cell.getCellFormula(); // Return formula
				break;
			case BLANK:
				requiredColoumnData = ""; // Blank cells
				break;
			default:
				requiredColoumnData = "Unsupported Cell Type";
			}
			reqColumnAllData.add(requiredColoumnData);
		}

		
		for (int i = 0; i < reqColumnAllData.size(); i++) {
			String excelColumnData = reqColumnAllData.get(i).trim().replace("-", "").replace(" ", "").replaceAll("[^a-zA-Z0-9\\s]", "").toUpperCase();
			String sci = scenarioDescription.trim().toUpperCase();
			if (excelColumnData.equals(sci)) {
				reqData = sheet.getRow(i + 1).getCell(reqIndex +1 ).getStringCellValue();
			}
		}

		return reqData;
	}
	
	public String getVoiceIDFromTestCaseID(String sheetName, String testCaseID) {
		Sheet sheet = wb.getSheet(sheetName);
		int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
		String[] colNames = new String[cellCount];
		for (int i = 0; i < cellCount; i++) {
			colNames[i] = sheet.getRow(0).getCell(i).getStringCellValue();
		}

		int reqIndex = 0;
		for (int i = 0; i < colNames.length; i++) {
			if (colNames[i].toString().contains("TCID")) {
				reqIndex = i;
				break;
			}
		}

		List<String> allTestCaseIDs = new ArrayList<String>();
		String requiredVoiceID=null;
		int rowCount = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowCount; i++) {
			allTestCaseIDs.add(sheet.getRow(i).getCell(reqIndex).getStringCellValue());
		}

		for (int i = 0; i < allTestCaseIDs.size(); i++) {
			if (allTestCaseIDs.get(i).contains(testCaseID)) {
				Cell cell = sheet.getRow(i + 1).getCell(reqIndex - 6);
				
				switch (cell.getCellType()) {
				case STRING:
					requiredVoiceID = cell.getStringCellValue(); // For String cells
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						requiredVoiceID = cell.getDateCellValue().toString(); // For Date cells
					} else {
						requiredVoiceID = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
						// reqData= String.valueOf(cell.getNumericCellValue()); // Convert numeric to
						// string
					}
					break;
				case BOOLEAN:
					requiredVoiceID = String.valueOf(cell.getBooleanCellValue()); // Boolean cells
					break;
				case FORMULA:
					requiredVoiceID = cell.getCellFormula(); // Return formula
					break;
				case BLANK:
					requiredVoiceID = ""; // Blank cells
					break;
				default:
					requiredVoiceID = "Unsupported Cell Type";
				}
			}
		}

		
		return requiredVoiceID;
	}
	
	public void findNumberOfRows() {
		
	}
	
//	public static void main(String[] args) {
//		ExcelUtility excel=new ExcelUtility("./src/main/resources/NewFormat_Automation_VoiceID.xlsx");
//		String voiceId = excel.getVoiceIDFromTestCaseID("NewFormat_Automation_VoiceID's", "VoC_Sentiment_005");
//		System.out.println(voiceId);
//		
//	}

}
