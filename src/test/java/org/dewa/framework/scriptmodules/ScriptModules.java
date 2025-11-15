package org.dewa.framework.scriptmodules;

import static org.dewa.framework.utils.BaseClass.assertContains;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dewa.framework.utils.ExcelUtility;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

public class ScriptModules extends ParentModule {

	public ScriptModules(Page page) {
		super(page);
	}

	public void accuracyCheck(String type, String sentiments, String mainService, String subService, String sql,
			String source, String comments, String voiceId, String status, String remark) {
		extentTest.get().log(Status.INFO, "Extepected Type : " + type);
		extentTest.get().log(Status.INFO, "Extepected Sentiments : " + sentiments);
		extentTest.get().log(Status.INFO, "Extepected Main Service : " + mainService);
		extentTest.get().log(Status.INFO, "Extepected Sub Service : " + subService);
		extentTest.get().log(Status.INFO, "Extepected SQL : " + sql);

		getHomePage().clickSubscription("VOC");
		getHomePage().searchProjectAndClick("Voice of Customer");
		getVoicesListPage().filterDocumentByTitle(voiceId);
		getVoicesListPage().clickOnDocument(voiceId);
		String typeValue = getVoicePage().getVoiceSummaryAttributes("Type");
		if (typeValue.isBlank()) {
			typeValue = "-";
		}
		String sentimentsValue = getVoicePage().getVoiceSummaryAttributes("Sentiment");
		if (sentimentsValue.isBlank()) {
			sentimentsValue = "-";
		}
		String mainServiceValue = getVoicePage().getVoiceSummaryAttributes("Main Service");
		if (mainServiceValue.isBlank()) {
			mainServiceValue = "-";
		}
		String subServiceValue = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		if (subServiceValue.isBlank()) {
			subServiceValue = "-";
		}
		String sqlValue = getVoicePage().getVoiceSummaryAttributes("SQL");
		if (sqlValue.isBlank()) {
			sqlValue = "-";
		}

		extentTest.get().log(Status.INFO, "Actual Type : " + typeValue);
		extentTest.get().log(Status.INFO, "Actual Sentiments : " + sentimentsValue);
		extentTest.get().log(Status.INFO, "Actual Main Service : " + mainServiceValue);
		extentTest.get().log(Status.INFO, "Actual Sub Service : " + subServiceValue);
		extentTest.get().log(Status.INFO, "Actual SQL : " + sqlValue);

		getVoicePage().closeDocumentPage();
		getHomePage().clickOnHomeButton();
		assertContains("Actual Type is equal to Expected Type", "Actual Type is not equal to Expected Type",
				typeValue.trim(), type);
		assertContains("Actual Sentiments is equal to Expected Sentiments",
				"Actual Sentiments is not equal to Expected Sentiments", sentimentsValue.trim(), sentiments);
		assertContains("Actual Main Service is equal to Expected Main Service",
				"Actual Main Service is not equal to Expected Main Service", mainServiceValue.trim(), mainService);
		assertContains("Actual Sub Service is equal to Expected Sub Service",
				"Actual Sub Service is not equal to Expected Sub Service", subServiceValue.trim(), subService);
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sqlValue.trim(), sql);

		ExcelUtility.writeBackStringToExcel(
				System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy", 0,
				2, "Actual");

		if (typeValue.toUpperCase().contains(type.trim().toUpperCase())) {
			ExcelUtility.writeBackIntegerToExcel(
					System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy",
					1, 2, 1);
		}
		if (sentimentsValue.toUpperCase().contains(sentiments.trim().toUpperCase())) {
			ExcelUtility.writeBackIntegerToExcel(
					System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy",
					2, 2, 1);
		}
		if (mainServiceValue.toUpperCase().contains(mainService.trim().toUpperCase())) {
			ExcelUtility.writeBackIntegerToExcel(
					System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy",
					3, 2, 1);
		}
		if (subServiceValue.toUpperCase().contains(subService.trim().toUpperCase())) {
			ExcelUtility.writeBackIntegerToExcel(
					System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy",
					4, 2, 1);
		}
		if (sqlValue.toUpperCase().contains(sql.trim().toUpperCase())) {
			ExcelUtility.writeBackIntegerToExcel(
					System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy",
					5, 2, 1);
		}
	}

	public static int accuracyCalci(int expected, int actual) {
		int accuracy = (int) (((double) actual / expected) * 100);
		return accuracy;
	}

	public void accuracyCalulation(String attributeName, int expected, int actual) {
		ExcelUtility.writeBackStringToExcel(
				System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy", 0,
				3, "Accuracy");
		int rowNumber = ExcelUtility.findRowNumber(
				System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy",
				attributeName);
		int accuracy = accuracyCalci(expected, actual);
		System.out.println(attributeName + " Accuracy is " + accuracy);
		ExcelUtility.writeBackIntegerToExcel(
				System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy",
				rowNumber, 3, accuracy);
		extentTest.get().log(Status.PASS, attributeName + " Accuracy is " + accuracy);
	}

	public void calculateTotalAccuracy() {
		ExcelUtility excel = new ExcelUtility(
				System.getProperty("user.dir") + "/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx");
		List<Object> expectedData = excel.getColumnDataByColoumnName("Accuracy", "Expected");
		int expectedTotal = 0;
		for (Object data : expectedData) {
			if (data instanceof Double) {
				expectedTotal += ((Double) data).intValue();
			} else if (data instanceof Integer) {
				expectedTotal += (Integer) data;
			}
		}
		System.out.println("Expected Total: " + expectedTotal);

		List<Object> actualData = excel.getColumnDataByColoumnName("Accuracy", "Actual");
		int actualTotal = 0;
		for (Object data : actualData) {
			if (data instanceof Double) {
				actualTotal += ((Double) data).intValue();
			} else if (data instanceof Integer) {
				actualTotal += (Integer) data;
			}
		}
		System.out.println("Actual Total: " + actualTotal);

		int totalAccuracy = accuracyCalci(expectedTotal, actualTotal);
		System.out.println("Total Accuracy :" + totalAccuracy);
		extentTest.get().log(Status.PASS, "Total Accuracy is " + totalAccuracy);
		report.setSystemInfo("Total Accuracy in of all the Attributes is", String.valueOf(totalAccuracy));
	}

	public void summaryExtraction(String voiceId, String filePath) {
		String[] attributes = { "Source", "Date", "User", "AI", "Aspect", "Channel", "Sub-Channel", "Main Service",
				"Sub Service", "Service Journey Stage", "Type", "SQL", "Subject", "Location", "Language",
				"Customer Category", "Comments", "AI Summary" };

		Map<String, String> extractedData = new LinkedHashMap<>();
		

		getHomePage().clickSubscription("VOCTest");
		getHomePage().searchProjectAndClick("Voice of Customer");
		//getVoicesListPage().clickOnSideNavigationBarOptions("Voice");
		getVoicesListPage().filterDocumentByTitle(voiceId);
		getPage().waitForTimeout(2000);
		if (getVoicesListPage().checkResultsForSearchedVoidId()) {
			getVoicesListPage().clickOnDocument(voiceId);
			
			String commentsText = getPage().locator("iframe[name='htmlPanel']").contentFrame()
					.locator("//div[@class='comments-parent']/following-sibling::div").textContent();
			System.out.println("Comments: " + commentsText);
			
			getPage().waitForTimeout(1000);
			String aiSummary=null;
			int aiCount = getPage().locator("//div[@id='aiSummaryContainer']/p").all().size();
			System.out.println("AI Count : "+aiCount);
			if(aiCount>0) {
				String aiSummaryTitle = getVoicePage().fetchAISummaryTitle();
				String aiSummaryContent = getVoicePage().fetchAISummaryContent();
				aiSummary = aiSummaryTitle.trim() + " :- " + aiSummaryContent.trim();
				aiSummary=aiSummary.trim();
			}else {
				String aiSummaryContent = getVoicePage().fetchAISummaryForNoData();
				aiSummary =aiSummaryContent;
			}
			

			for (int i = 0; i < attributes.length - 2; i++) {
				String data = null;
				if (i == 2) {
					data = getVoicePage().getAIorUserTitleAttribute(attributes[i]).trim();
				} else if(i==3) {
					if(getVoicePage().checkAISenimentAvailability()) {
						data = getVoicePage().getAIorUserTitleAttribute(attributes[i]).trim();
					}else {
						data="N/A";
					}
				}
				else {
					data = getVoicePage().getVoiceSummaryAttributes(attributes[i]).trim();
				}
				extractedData.put(attributes[i], data);
			}

			extractedData.put("Comments", commentsText);
			extractedData.put("AI Summary", aiSummary);

			extractedData.forEach((key, value) -> System.out.println(key + " : " + value));

			writeDataToExcel(filePath, voiceId, extractedData);

			getVoicePage().closeDocumentPage();
			getHomePage().clickOnHomeButton();
			getPage().waitForTimeout(2000);
			getPage().reload();
		} else {
			System.out.println("Data is not found skipping this execution");
			getPage().waitForTimeout(2000);
			getHomePage().clickOnHomeButton();
			getPage().waitForTimeout(2000);
			getPage().reload();
		}

	}

	public void writeDataToExcel(String filepath, String voiceId, Map<String, String> extractedData) {
		Workbook workbook = null;
		Sheet sheet = null;
		boolean headersCreated = false;

		try {
			File file = new File(filepath);
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(filepath);
				workbook = new XSSFWorkbook(fileInputStream);
				fileInputStream.close();

				sheet = workbook.getSheet("Voice Summary Extraction");
				if (sheet == null) {
					sheet = workbook.createSheet("Voice Summary Extraction");
				}
			} else {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("Voice Summary Extraction");
			}

			int rowCount = sheet.getPhysicalNumberOfRows();
			Row headerRow;
			if (rowCount == 0) {
				headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("Voice ID");
				int colIndex = 1;
				for (String attr : extractedData.keySet()) {
					headerRow.createCell(colIndex++).setCellValue(attr);
				}
			} else {
				headerRow = sheet.getRow(0);
				int existingCols = headerRow.getLastCellNum();
				int colIndex = existingCols;
				for (String attr : extractedData.keySet()) {
					boolean exists = false;
					for (int i = 0; i < existingCols; i++) {
						if (headerRow.getCell(i).getStringCellValue().equals(attr)) {
							exists = true;
							break;
						}
					}
					if (!exists) {
						headerRow.createCell(colIndex++).setCellValue(attr);
					}
				}
			}

			Row dataRow = sheet.createRow(rowCount + 1);
			dataRow.createCell(0).setCellValue(voiceId);
			int colIndex = 1;
			for (String value : extractedData.values()) {
				dataRow.createCell(colIndex++).setCellValue(value);
			}

			FileOutputStream fileOut = new FileOutputStream(filepath);
			workbook.write(fileOut);
			fileOut.close();

			System.out.println("Data successfully written to " + filepath);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Map<String, String> getPriorityValues(String filePath) {
		Map<String, Map<String, Integer>> attributeCounts = new LinkedHashMap<>();
		Map<String, String> priorityValues = new LinkedHashMap<>();

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheet("Voice Summary Extraction");

			Row headerRow = sheet.getRow(0);
			int totalColumns = headerRow.getLastCellNum();

			for (int col = 1; col < totalColumns; col++) { // Skipping "Voice ID" column
				String attribute = headerRow.getCell(col).getStringCellValue();
				attributeCounts.put(attribute, new HashMap<>());
			}

			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row == null)
					continue;

				for (int col = 1; col < totalColumns; col++) {
					String attribute = headerRow.getCell(col).getStringCellValue();
					Cell cell = row.getCell(col);
					String value = (cell != null) ? cell.getStringCellValue().trim() : "Unknown";

					attributeCounts.get(attribute).put(value,
							attributeCounts.get(attribute).getOrDefault(value, 0) + 1);
				}
			}

			for (String attribute : attributeCounts.keySet()) {
				priorityValues.put(attribute, getMostFrequentValue(attributeCounts.get(attribute)));
			}

			workbook.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return priorityValues;
	}

	private static String getMostFrequentValue(Map<String, Integer> valueCounts) {
		return Collections.max(valueCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
	}

	public String getPriorityForAttribute(String attributeName, String filePath) {
		Map<String, String> priorityValues = getPriorityValues(filePath);
		return priorityValues.getOrDefault(attributeName, "No Data Found");
	}

	public void writePriorityToSheet(String filePath, Map<String, String> priorityValues) {
		Workbook workbook = null;
		File file = new File(filePath);

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileInputStream);
			fileInputStream.close();

			Sheet prioritySheet = workbook.getSheet("Priority");
			if (prioritySheet == null) {
				prioritySheet = workbook.createSheet("Priority");
			} else {
				int rowCount = prioritySheet.getPhysicalNumberOfRows();
				for (int i = 0; i < rowCount; i++) {
					prioritySheet.removeRow(prioritySheet.getRow(i));
				}
			}

			Row headerRow = prioritySheet.createRow(0);
			headerRow.createCell(0).setCellValue("Attribute");
			headerRow.createCell(1).setCellValue("Priority Value");

			int rowNum = 1;
			for (Map.Entry<String, String> entry : priorityValues.entrySet()) {
				Row row = prioritySheet.createRow(rowNum++);
				row.createCell(0).setCellValue(entry.getKey());
				row.createCell(1).setCellValue(entry.getValue());
			}

			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			System.out.println("Priority values successfully written to the 'Priority' sheet.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void calculateAndWriteAccuracy(String filePath) {
		Workbook workbook = null;
		File file = new File(filePath);

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileInputStream);
			fileInputStream.close();

			Sheet dataSheet = workbook.getSheet("Voice Summary Extraction");
			if (dataSheet == null) {
				System.out.println("Data sheet not found!");
				return;
			}

			Row headerRow = dataSheet.getRow(0);
			int totalSamples = dataSheet.getPhysicalNumberOfRows() - 1;
			int totalColumns = headerRow.getLastCellNum();

			Map<String, Integer> nonEmptyCounts = new LinkedHashMap<>();

			for (int col = 1; col < totalColumns; col++) {
				String attribute = headerRow.getCell(col).getStringCellValue();
				nonEmptyCounts.put(attribute, 0);
			}

			for (int rowNum = 1; rowNum <= dataSheet.getLastRowNum(); rowNum++) {
				Row row = dataSheet.getRow(rowNum);
				if (row == null)
					continue;

				for (int col = 1; col < totalColumns; col++) {
					Cell cell = row.getCell(col);
					if (cell != null && !cell.getStringCellValue().trim().isEmpty()) {
						String attribute = headerRow.getCell(col).getStringCellValue();
						nonEmptyCounts.put(attribute, nonEmptyCounts.get(attribute) + 1);
					}
				}
			}

			Sheet accuracySheet = workbook.getSheet("Accuracy");
			if (accuracySheet == null) {
				accuracySheet = workbook.createSheet("Accuracy");
			} else {
				int rowCount = accuracySheet.getPhysicalNumberOfRows();
				for (int i = 0; i < rowCount; i++) {
					accuracySheet.removeRow(accuracySheet.getRow(i));
				}
			}

			Row header = accuracySheet.createRow(0);
			header.createCell(0).setCellValue("Attribute");
			header.createCell(1).setCellValue("Accuracy (%)");

			int rowNum = 1;
			for (Map.Entry<String, Integer> entry : nonEmptyCounts.entrySet()) {
				String attribute = entry.getKey();
				int nonEmptyCount = entry.getValue();
				double accuracy = ((double) nonEmptyCount / totalSamples) * 100;

				Row row = accuracySheet.createRow(rowNum++);
				row.createCell(0).setCellValue(attribute);
				row.createCell(1).setCellValue(String.format("%.2f", accuracy) + "%");
			}

			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			System.out.println("Accuracy values successfully written to the 'Accuracy' sheet.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void compareAndCalculateAccuracy() {
		ExcelUtility excel = new ExcelUtility(System.getProperty("user.dir")
				+ "/src/test/resources/RegTest_060225_Ser_Run 2_20250206075509_20250206_075529.xlsx");
		List<Object> mainServiceExpected = excel.getColumnDataByColoumnName("Expected", "Main Service");
		List<Object> subServiceExpected = excel.getColumnDataByColoumnName("Expected", "Sub Service");
		List<Object> mainServiceActual = excel.getColumnDataByColoumnName("Voice Summary Extraction", "Main Service");
		List<Object> subServiceActual = excel.getColumnDataByColoumnName("Voice Summary Extraction", "Sub Service");

		List<String> expectedMainService = mainServiceExpected.stream().map(Object::toString)
				.collect(Collectors.toList());
		List<String> expectedSubService = subServiceExpected.stream().map(Object::toString)
				.collect(Collectors.toList());
		List<String> actualMainService = mainServiceActual.stream().map(Object::toString).collect(Collectors.toList());
		List<String> actualSubService = subServiceActual.stream().map(Object::toString).collect(Collectors.toList());

		double subServiceAccuracy = calculateAccuracy(expectedSubService, actualSubService);
		double mainServiceAccuracy = calculateAccuracy(expectedMainService, actualMainService);
		System.out.println("Sub Service Accuracy is :" + subServiceAccuracy);
		System.out.println("Main Service Accuracy is :" + mainServiceAccuracy);

	}

	public static double calculateAccuracy(List<String> expectedList, List<String> actualList) {
		if (actualList.size() < expectedList.size()) {
			throw new IllegalArgumentException(
					"Actual list must have at least the same number of elements as the expected list.");
		}

		int matchCount = 0;
		int compareSize = expectedList.size();

		for (int i = 0; i < compareSize; i++) {
			if (expectedList.get(i).equalsIgnoreCase(actualList.get(i))) {
				matchCount++;
			} else {
				System.out.println(
						"Index " + i + " - Expected: " + expectedList.get(i) + ", Actual: " + actualList.get(i));
			}
		}

		return (matchCount / (double) compareSize) * 100;
	}

	public void dashboardValidation() {
		getHomePage().clickSubscription("VOC");
		getHomePage().searchProjectAndClick("Voice of Customer");
		getHomePage().clickOnNavigationMenus("Voices list");
	}

}
