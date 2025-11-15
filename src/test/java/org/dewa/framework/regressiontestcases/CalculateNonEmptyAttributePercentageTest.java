package org.dewa.framework.regressiontestcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CalculateNonEmptyAttributePercentageTest {

	
	public static void main(String[] args) {
		String filePath=System.getProperty("user.dir") +"/src/main/resources/DataExtraction_12-03.xlsx";
        String sheetName = "Voice Summary Extraction";
        String percentageSheetName = "Accuracy";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Sheet not found!");
                return;
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                System.out.println("Header row not found!");
                return;
            }

            Map<String, Double> columnPercentage = new HashMap<>();
            int totalRows = sheet.getPhysicalNumberOfRows() - 1; // Exclude header row
            System.out.println("Total Rows : "+totalRows);

            for (Cell headerCell : headerRow) {
                int columnIndex = headerCell.getColumnIndex();
                int nonEmptyCount = 0;

                for (int i = 1; i <= totalRows; i++) { // Skip header row
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cell = row.getCell(columnIndex);
                        if (cell != null && cell.getCellType() != CellType.BLANK) {
                            nonEmptyCount++;
                        }
                    }
                }

                double percentage = (double) nonEmptyCount / totalRows * 100;
                columnPercentage.put(headerCell.getStringCellValue(), percentage);
            }
            
            // Print the percentage for each column
            columnPercentage.forEach((header, percent) ->
                System.out.println("Column: " + header + " -> " + String.format("%.2f", percent) + "%"));

            // Create or overwrite the 'Percentage' sheet
            Sheet percentageSheet = workbook.getSheet(percentageSheetName);
            if (percentageSheet != null) {
                workbook.removeSheetAt(workbook.getSheetIndex(percentageSheet));
            }
            percentageSheet = workbook.createSheet(percentageSheetName);

            // Write headers
            Row header = percentageSheet.createRow(0);
            header.createCell(0).setCellValue("Attribute Name");
            header.createCell(1).setCellValue("Percentage");

            // Write data
            int rowIndex = 1;
            for (Map.Entry<String, Double> entry : columnPercentage.entrySet()) {
                Row row = percentageSheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(String.format("%.2f", entry.getValue()));
            }

            // Save the updated workbook
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            System.out.println("Percentage data written successfully to the '" + percentageSheetName + "' sheet.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
