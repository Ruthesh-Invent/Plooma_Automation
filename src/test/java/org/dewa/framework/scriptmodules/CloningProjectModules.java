package org.dewa.framework.scriptmodules;

import static org.dewa.framework.utils.BaseClass.assertContains;
import static org.dewa.framework.utils.BaseClass.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dewa.framework.utils.BmxFileUtility;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CloningProjectModules extends ParentModule {

	public CloningProjectModules(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public void downloadBMXFile(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnConfigUnderProjectOverview();
		getStudioPage().clickOnCommit("Automation Testing Commit");
		final String[] additionalPath = new String[1];
		String path = System.getProperty("user.dir") + "/DownloadedBmx";
		downloadPdfSetup(path, additionalPath, getPage());
		additionalPath[0] = projectName + ".bmx";
		getStudioPage().downloadBmxFile();
		String bmxPath = path + "/" + projectName + ".bmx";
		BmxFileUtility.renameBMXtoZIP(path);
		String jsonFolderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded";
		BmxFileUtility.unzip(bmxPath, jsonFolderPath);
	}

	// To Capture Toggle result using Toggle Name
	public void captureToggleResultUsingUiNames(String subsName, String projectName, String toggleName_ToggleResultPath,
			String apiName_ToggleResultPath) {
//		getHomePage().clickSubscription(subsName);
//		getHomePage().searchProjectAndClick(projectName);
//		getPage().waitForTimeout(1000);
//		getVoicesListPage().clickOnStudio();

		getPage().waitForTimeout(3000);
		getStudioPage().clickOnModules("Project");
		Set<String> project_Overview_ToggleTexts = getStudioPage().getAllToggleText();
		System.out.println("Total toggle Text count Project Overview " + project_Overview_ToggleTexts.size());
		Map<String, String> toggleCheck = new HashMap<String, String>();
		for (String eachText : project_Overview_ToggleTexts) {
			toggleCheck.put(eachText, getStudioPage().checkToggleSelection(eachText));
		}
		System.out.println("Total Toggle Info added till Project Overview " + toggleCheck.size());

		getStudioPage().clickOnSubModules("Project", "Appearance");
		List<String> allApperrenceMenus = getStudioPage().getAllNavigationMenusUnderAppearence();
		for (String eachMenu : allApperrenceMenus) {
			getStudioPage().clickOnNavigationMenuUnderModules(eachMenu);
		}
		Set<String> project_Appearence_ToggleTexts = getStudioPage().getAllToggleText();
		System.out.println("Total toggle Text count Project Appearence " + project_Overview_ToggleTexts.size());
		for (String eachText : project_Appearence_ToggleTexts) {
			toggleCheck.put(eachText, getStudioPage().checkToggleSelection(eachText));
		}
		System.out.println("Total Toggle Info added till Project Appearence " + toggleCheck.size());

		getStudioPage().clickOnModules("Search");
		getStudioPage().clickOnSeacrh_General_NavigationMenus("Similarity");
		Set<String> search_General_ToggleTexts = getStudioPage().getAllToggleText();
		System.out.println("Total toggle Text count Search General " + search_General_ToggleTexts.size());
		for (String eachText : search_General_ToggleTexts) {
			toggleCheck.put(eachText, getStudioPage().checkToggleSelection(eachText));
		}
		System.out.println("Total Toggle Info added till Search General " + toggleCheck.size());

		getStudioPage().clickOnModules("Chat");
		getStudioPage().clickOnSeacrh_General_NavigationMenus("Copilot");
		Set<String> chat_General_ToggleTexts = getStudioPage().getAllToggleText();
		System.out.println("Total toggle Text count Chat General " + chat_General_ToggleTexts.size());
		for (String eachText : chat_General_ToggleTexts) {
			toggleCheck.put(eachText, getStudioPage().checkToggleSelection(eachText));
		}
		System.out.println("Total Toggle Info added till Chat General " + toggleCheck.size());

		writeToJson(toggleName_ToggleResultPath, toggleCheck);
		extentTest.get().log(Status.PASS, "Toggle Name - Toggle Result Json File Created Successfully");

		mergeJsonApiNameToToggleResult(toggleName_ToggleResultPath, apiName_ToggleResultPath);
	}

	// To Generate the apiName_ToggleResult json
	public static void mergeJsonApiNameToToggleResult(String toggleName_ToggleResultPath,
			String apiName_ToggleResultPath) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			// Load both JSON files
			Map<String, String> labelToKey = mapper.readValue(
					new File(System.getProperty("user.dir") + "/toggle_jsons_download/toggleName_apiName.json"),
					Map.class);
			Map<String, String> labelToValue = mapper.readValue(new File(toggleName_ToggleResultPath), Map.class);

			// Create final output map
			Map<String, String> keyToValue = new HashMap<>();

			for (Map.Entry<String, String> entry : labelToKey.entrySet()) {
				String label = entry.getKey();
				String key = entry.getValue();
				String value = labelToValue.get(label); // lookup from 2nd JSON
				keyToValue.put(key, value);
			}

			// Write output to JSON file
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(apiName_ToggleResultPath), keyToValue);
			System.out.println("✅ Merged JSON created successfully.");

		} catch (IOException e) {
			System.err.println("❌ Error while processing JSON files: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("❌ Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
		extentTest.get().log(Status.PASS, "API Name - Toggle Result Json File Created Successfully");
	}

	public void compareExpectedJsonToActualJson() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			// Load flat UI config JSON
			JsonNode flatJson = mapper
					.readTree(new File(System.getProperty("user.dir") + "/bmx_jsons/apiName_toggleResult.json"));

			// Load full nested project config JSON
			JsonNode rootJson = mapper
					.readTree(new File(System.getProperty("user.dir") + "/jsonOutput/3_Project.json"));

			// Navigate to ProjectOverview node
			JsonNode projectOverview = rootJson.path("Project").path("Overview").path("ProjectOverview");

			boolean allMatched = true;

			Iterator<Map.Entry<String, JsonNode>> fields = flatJson.fields();
			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> entry = fields.next();
				String key = entry.getKey();
				String expectedValue = entry.getValue().asText();

				JsonNode actualNode = projectOverview.get(key);
				String actualValue = actualNode != null ? actualNode.asText() : "null";

				if (!expectedValue.equalsIgnoreCase(actualValue)) {
					allMatched = false;
					System.out.println(
							"❌ Mismatch: [" + key + "] Expected: " + expectedValue + ", Found: " + actualValue);
				} else {
					System.out.println("✅ Match: [" + key + "] = " + expectedValue);
				}
			}

			if (allMatched) {
				System.out.println("\n🎉 All settings matched!");
			} else {
				System.out.println("\n❗ Some settings did not match.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To capture all the Toggle Names and Api names
	public void captureToggleNames_ApiNames(String subsName, String projectName) {
//		getHomePage().clickSubscription(subsName);
//		getHomePage().searchProjectAndClick(projectName);
//		getPage().waitForTimeout(1000);
//		getVoicesListPage().clickOnStudio();

		getPage().waitForTimeout(1000);
		Map<String, String> toggle_JsonValues = new HashMap<String, String>();
		Map<String, String> overView = getStudioPage().getAllToggleTextAndJsonValue();
		toggle_JsonValues.putAll(overView);
		getStudioPage().clickOnSubModules("Project", "Appearance");
		List<String> allApperrenceMenus = getStudioPage().getAllNavigationMenusUnderAppearence();
		for (String eachMenu : allApperrenceMenus) {
			getStudioPage().clickOnNavigationMenuUnderModules(eachMenu);
		}
		Map<String, String> appearence = getStudioPage().getAllToggleTextAndJsonValue();
		toggle_JsonValues.putAll(appearence);

		getStudioPage().clickOnModules("Search");
		getStudioPage().clickOnSeacrh_General_NavigationMenus("Similarity");
		Map<String, String> search_General = getStudioPage().getAllToggleTextAndJsonValue();
		toggle_JsonValues.putAll(search_General);

		getStudioPage().clickOnModules("Chat");
		getStudioPage().clickOnSeacrh_General_NavigationMenus("Copilot");
		Map<String, String> chat_General = getStudioPage().getAllToggleTextAndJsonValue();
		toggle_JsonValues.putAll(chat_General);

		String jsonPath = System.getProperty("user.dir") + "/toggle_jsons_download/toggleName_apiName.json";
		
		writeToJson(jsonPath, toggle_JsonValues);
		extentTest.get().log(Status.PASS, "Toggle Name - API Name Json File Created Successfully");
	}

	public void writeToJson(String path, Map<String, String> data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print

		try {
			mapper.writeValue(new File(path), data);
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Final Json Size " + data.size());
	}

	public void createProjectUsingBmx(String subsName, String projectName, String descriptionName, String bmxPath) {
		getHomePage().createNewProject(subsName, projectName, descriptionName);
		assertEquals("<b>" + projectName + " Project " + "</b>" + "Created Successfully", "Project not Created", true,
				getHomePage().checkProjectNameAvailability(projectName));
		uploadBmxFile(subsName, projectName, bmxPath);
		getStudioPage().clickOnModules("Project");
		// deleteProject(subsName, projectName, descriptionName);
	}

//	public void deleteProject(String subsName, String projectName, String descriptionName) {
//		getHomePage().clickSubscription(subsName);
//		getHomePage().searchProjectAndClick(projectName);
//		getVoicesListPage().clickOnStudio();
//		getStudioPage().clickDeleteProject(descriptionName);
//		assertEquals("<b>"+projectName+" Project "+"</b>"+"Deleted Successfully", "Project not Deleted", false, getHomePage().checkProjectNameAvailability(projectName));
//	}

	public void uploadBmxFile(String subsName, String projectName, String bmxPath) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getVoicesListPage().clickOnStudio();
		getPage().waitForTimeout(2000);
		getStudioPage().clickOnConfigUnderProjectOverview();
		Locator inputLocator = getPage().locator("//div[@role='menu']//input[@type='file']");
		inputLocator.setInputFiles(Paths.get(bmxPath));
		getPage().waitForTimeout(2000);
		getPage().locator("//div[contains(@class,'mdc-circular-progress__circle-clipper mdc-circular-progress__circle-right')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(120000));
		getPage().waitForLoadState();
	}

	public void verifyProjectModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		Set<String> allProjectOverviewToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allProjectOverviewToggleText.size()>0);
		
		Set<String> allProjectOverviewApiNames = new HashSet<String>();
		System.out.println("Total UI Toggle Text " + allProjectOverviewToggleText.size());

		int toggleTxtCount = 0;

		String toggleName_apiNamePath = System.getProperty("user.dir")
				+ "/toggle_jsons_download/toggleName_apiName.json";

		for (String eachToggleText : allProjectOverviewToggleText) {
			String apiName = BmxFileUtility.getApiNameUsingUiName(eachToggleText, toggleName_apiNamePath);
			toggleTxtCount++;
			System.out.println(toggleTxtCount + ". " + eachToggleText + " : " + apiName);
			allProjectOverviewApiNames.add(apiName);
		}

		String apiName_toggleResultPath = System.getProperty("user.dir")
				+ "/toggle_jsons_download/apiName_toggleResult.json";

		int count = 0;
		for (String eachApiName : allProjectOverviewApiNames) {
			String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName, apiName_toggleResultPath);
			if (eachApiName.contains("DisableDiagnostics")
					|| eachApiName.contains("DisableTimeRangeFilterInDashboard")) {
				if (expectedResult.contains("false")) {
					expectedResult = "true";
				} else if (expectedResult.contains("true")) {
					expectedResult = "false";
				}
			}

			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project", eachApiName);
			count++;
			boolean result = false;
			for (Object eachActualResult : actualResult) {
				if (expectedResult.equals("false")) {
					result = expectedResult.equals(eachActualResult.toString())
							|| eachActualResult.toString().equals("null");
					if (eachActualResult.toString().equals("null")) {
						eachActualResult = "false";
					}
				} else {
					result = expectedResult.equals(eachActualResult.toString());
				}

				System.out.println(count + " : Comparision Result for " + eachApiName + " -> " + result);
				assertContains("UI Toggle Button Result for <b>"+eachApiName+"</b> is equal to Json Toggle Button Result",
						"UI Toggle Button Result for <b>"+eachApiName+"</b> not equal to Json Toggle Button Result", eachActualResult.toString(),
						expectedResult);

			}
		}

		System.out.println("-----------------------------------------------");

		List<String> projectOverviewTextboxTexts = getStudioPage().getProjectOverViewTextBox_Texts();
		for (String eachText : projectOverviewTextboxTexts) {
			String[] apiNameAndValues = getStudioPage().getProjectOverviewPageTextBoxApiNamesAndValues(eachText);
			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project",
					apiNameAndValues[0]);
			for (Object eachActualResult : actualResult) {
				String expectedResult = apiNameAndValues[1];
				count++;
				System.out.println(count + " : Comarision Result for " + eachText + " Textbox "
						+ expectedResult.equals(eachActualResult.toString()));
				assertContains("UI Textbox Value is equal to Json Textbox Value",
						"UI Textbox Value is not equal to Json Textbox Value", expectedResult,
						eachActualResult.toString());
			}
		}

		System.out.println("✔️ Project Overview Comparision is Completed");

		// In Landing Page only dropdowns are there which are mapped using ids

//		getStudioPage().clickOnNavigationMenuUnderModules("Landing Page");
//
//		List<String> allDropdownNames = getStudioPage().getAllDropdownNames();
//		for (String eachDropdownName : allDropdownNames) {
//			String[] apiNameAndValue = getStudioPage().getDropdownApiNameAndSelectedOption(eachDropdownName);
//			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project", apiNameAndValue[0]);
//			for (Object eachActualResult : actualResult) {
//				String expectedResult = apiNameAndValue[1];
//				count++;
//				System.out.println("Comparision Result for " + eachDropdownName + " -> "
//						+ expectedResult.equals(eachActualResult.toString()));
//				assertContains("UI Dropdown Name is equal to Json Dropdown Name",
//						"UI Dropdown Name is not equal to Json Dropdown Name", expectedResult,
//						eachActualResult.toString());
//			}
//
//		}

//		System.out.println("✔️ Project Landing Page Comparision is Completed");

		// Universal Template is project specific, Display Name and Descriptions
		// getStudioPage().clickOnNavigationMenuUnderModules("Universal Template");

		System.out.println("Project Overiew is Completed and Appearence Started");
		getStudioPage().clickOnSubModules("Project", "Appearance");
		List<String> allApperrenceMenus = getStudioPage().getAllNavigationMenusUnderAppearence();
		for (String eachMenu : allApperrenceMenus) {
			getStudioPage().clickOnNavigationMenuUnderModules(eachMenu);
		}

		Set<String> allProjectAppearenceDocumentPageToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allProjectAppearenceDocumentPageToggleText.size()>0);
		Set<String> allProjectApearenceDocumentPageApiNames = new HashSet<String>();
		System.out.println("Total Toggle Text Elemenets " + allProjectAppearenceDocumentPageToggleText.size());
		System.out.println("**************************************");
		for (String eachToggleText : allProjectAppearenceDocumentPageToggleText) {
			allProjectApearenceDocumentPageApiNames
					.add(BmxFileUtility.getApiNameUsingUiName(eachToggleText, toggleName_apiNamePath));
		}
		System.out.println("Total Toggle Api Names " + allProjectApearenceDocumentPageApiNames.size());

		for (String eachApiName : allProjectApearenceDocumentPageApiNames) {

			String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName, apiName_toggleResultPath);
//			boolean res=eachApiName.contains("DisableChat");
//			
//			boolean partialRes1 = eachApiName.contains("Disable");
//			
//			boolean partialRes2 = !(eachApiName.contains("DisableSortOption")
//					|| eachApiName.contains("DisableStudio") || eachApiName.contains("IsPinnedViewDisabled") || eachApiName.contains("DisableSearch") || eachApiName.contains("DisableChat"));
//			boolean fullRes = eachApiName.contains("Disable") && !(eachApiName.contains("DisableSortOption")
//					|| eachApiName.contains("DisableStudio") || eachApiName.contains("IsPinnedViewDisabled") || eachApiName.contains("DisableSearch") || eachApiName.contains("DisableChat"));
			
			if (eachApiName.contains("Disable") && !(eachApiName.contains("DisableSortOption")
					|| eachApiName.contains("DisableStudio") || eachApiName.contains("IsPinnedViewDisabled") || eachApiName.contains("DisableSearch") || eachApiName.contains("DisableChat"))) {
				if (expectedResult.contains("false")) {
					expectedResult = "true";
				} else if (expectedResult.contains("true")) {
					expectedResult = "false";
				}
			}

			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project", eachApiName);
			for (Object eachAcualResult : actualResult) {
				System.out.println("Comparision Result for " + eachApiName + " -> "
						+ expectedResult.equals(eachAcualResult.toString()));
				assertContains("UI Toggle Button Result for <b>"+eachApiName+"</b> is equal to Json Toggle Button Result",
						"UI Toggle Button Result for <b>"+eachApiName+"</b> not equal to Json Toggle Button Result", expectedResult,
						eachAcualResult.toString());

			}
		}
		System.out.println("**************************************");

		List<String> allDocumentIcons = getStudioPage().getDropdownMultipleSelectedOptions("Document Icons");

		List<Object> expectedDocumentIcons = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project",
				"DocumentActions");
		String[] splittedActual = null;
		for (Object eachExpectedDocumentsIcons : expectedDocumentIcons) {
			splittedActual = eachExpectedDocumentsIcons.toString().replace("[", "").replace("]", "").split(",");
		}

		List<String> actualDocumentIcons = Arrays.asList(splittedActual);

		int minSize = Math.min(allDocumentIcons.size(), actualDocumentIcons.size());

		for (int i = 0; i < minSize; i++) {
			String expectedResult = allDocumentIcons.get(i);
			String actualResult = actualDocumentIcons.get(i);
			System.out.println("Dropdown Comparion Result for Document Icons " + actualResult.contains(expectedResult));
			assertContains("UI Dropdown Selected Options are equal to Json Dropdown Selected Options",
					"UI Dropdown Selected Options are not equal to Json Dropdown Selected Options", expectedResult,
					actualResult);
		}

		System.out.println("Project-> Appearence-> Documents Page is Completed");

		getStudioPage().clickOnNavigationMenuUnderModules("Navigation Menu");

		List<String> allExpectedNamgiationMenus = getStudioPage().getAllSelectedNavigationMenus();

		int expectedManuCount = 0;
		for (String eachExpectedName : allExpectedNamgiationMenus) {
			expectedManuCount++;
			System.out.println(expectedManuCount + " . " + eachExpectedName);
		}

		List<String> allActualNavigationMenuNames = BmxFileUtility.getBothNavigationMenuAndSubMenu();

		int actualManuCount = 0;
		for (String eachActualName : allActualNavigationMenuNames) {
			actualManuCount++;
			System.out.println(actualManuCount + " . " + eachActualName);
		}

		for (int i = 0; i < Math.min(allExpectedNamgiationMenus.size(), allActualNavigationMenuNames.size()); i++) {
			String expectedResult = allExpectedNamgiationMenus.get(i);
			String actualResult = allActualNavigationMenuNames.get(i);
			boolean res = actualResult.contains(expectedResult);
			System.out.println("Navigation Menu Name comparision result " + res);
			assertContains("UI Navigation Menu Name is equal to Json Navigation Menu Name",
					"UI Navigation Menu Name is not equal to Json Navigation Menu Name", expectedResult, actualResult);
		}

		getStudioPage().clickOnNavigationMenuUnderModules("Document List");

		List<String> allUiSelectedDocListIcons = getStudioPage().getAllDropdownSelectedOptions();
		System.out.println("All UI Doc List Icon Count " + allUiSelectedDocListIcons.size());
		for (String eachUiDocListIcon : allUiSelectedDocListIcons) {
			System.out.println("Each UI Doc List Icon Name : " + eachUiDocListIcon);
		}

		List<Object> jsonDocListIconsArray = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project",
				"DocumentListingActions");

		String[] splittedJsonDocListIcons = null;
		for (Object eachJsonDocListIcons : jsonDocListIconsArray) {
			splittedJsonDocListIcons = eachJsonDocListIcons.toString().replace("[", "").replace("]", "").split(",");
		}

		List<String> jsonDocListIcons = Arrays.asList(splittedJsonDocListIcons);
		System.out.println("All Json Doc List Icon Count " + jsonDocListIcons.size());
		for (String eachJsonDocListIcon : jsonDocListIcons) {
			System.out.println("Each Json Doc List Icon Name : " + eachJsonDocListIcon);
		}

		for (Object eachJsonDocListIcon : jsonDocListIcons) {
			String expectedResult = eachJsonDocListIcon.toString();
			if(expectedResult.length()>16) {
				expectedResult = expectedResult.substring(0, 16)+"...";
			}
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDocListIcon : allUiSelectedDocListIcons) {
				actualResult = eachUiDocListIcon.toString();
				
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Doc List Icon Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Doc List Icon Name is equal to Json Doc List Icon Name",
					"UI Doc List Icon Name is not equal to Json Doc List Icon Name", actualResult, expectedResult);
		}

	}

	public void verifyDataModelModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Data Model");
		List<String> allUiEntityNames = getStudioPage().getAllEntityNamesInDataMdel();
		System.out.println("UI Entities Count : "+allUiEntityNames.size());
		for (String eachUiEntity : allUiEntityNames) {
			System.out.println("UI Entity Name : " + eachUiEntity);
		}
		
		List<Object> allJsonEntity = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Data Model", "EntityName");
		System.out.println("JSON Entities Count : "+allJsonEntity.size());
		for (Object eachJsonEntity : allJsonEntity) {
			System.out.println("Actual Entity " + eachJsonEntity);
		}
		
		for (Object eachJsonEntity : allJsonEntity) {
			String expectedResult = eachJsonEntity.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiEntity : allUiEntityNames) {
				actualResult = eachUiEntity.toString().replace(" ", "");
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Entity Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Entity Name is equal to Json Entity Name",
					"UI Entity Name is not equal to Json Entity Name", actualResult, expectedResult);
		}


		System.out.println("Entities are done");

		getStudioPage().clickOnSubModules("Data Model", "Taxonomy");

		List<String> allUiTaxonomyNames = getStudioPage().getAllTaxonomyNames();
		System.out.println("Total UI Taxonomy Count : "+allUiTaxonomyNames.size());
		for (String eachUiTaxonomyName : allUiTaxonomyNames) {
			System.out.println("UI Taxonomy Name : " + eachUiTaxonomyName);
		}

		List<String> allJsonTaxonomyNames = BmxFileUtility.extractTaxonomyNames();
		System.out.println("Total JSON Taxonomy Count : "+allJsonTaxonomyNames.size());
		for (String eachJsonTaxonomyName : allJsonTaxonomyNames) {
			System.out.println("Json Taxonomy Name : " + eachJsonTaxonomyName);
		}
		
		for (Object eachJsonTaxonomyName : allJsonTaxonomyNames) {
			String expectedResult = eachJsonTaxonomyName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiTaxonomyName : allUiTaxonomyNames) {
				actualResult = eachUiTaxonomyName.toString().replace(" ", "");
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Taxonomy Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Taxonomy Name is equal to Json Taxonomy Name",
					"UI Taxonomy Name is not equal to Json Taxonomy Name", actualResult, expectedResult);
		}
		
		System.out.println("Taxonomy is done");

		List<String> allUILabels = new ArrayList<String>();
		for (String eachTaxonomy : allUiTaxonomyNames) {
			getStudioPage().clickOnTaxonomy(eachTaxonomy);
			List<String> labels = getStudioPage().getAllLabelNames();
			System.out.println("Label count for " + eachTaxonomy + " : " + labels.size());
			allUILabels.addAll(labels);
		}

		System.out.println("All UI Total Labels : " + allUILabels.size());

		List<String> allJsonLabels = BmxFileUtility.extractAllLabelNames();
		System.out.println("All JSON Labels : "+allJsonLabels.size());
		for(String eachJsonLabel:allJsonLabels) {
			System.out.println("Each Json Label : "+eachJsonLabel);
		}
		
		for (Object eachJsonLabel : allJsonLabels) {
			String expectedResult = eachJsonLabel.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiLabel : allUILabels) {
				actualResult = eachUiLabel.toString().replace(" ", "");
				if(actualResult.length()>25) {
					actualResult=actualResult.substring(0, 25);
				}
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Label Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Label Name is equal to Json Label Name",
					"UI Label Name is not equal to Json Label Name", actualResult, expectedResult);
		}
		
		System.out.println("Insights Taxonomies are done");

		getStudioPage().clickOnSubModules("Data Model", "Workflows");
		List<String> allUiWorkflows = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Workflows Count : "+allUiWorkflows.size());
		for (String eachUiWorkflowName : allUiWorkflows) {
			System.out.println("UI Workflow Name : "+eachUiWorkflowName);
		}
		
		List<String> allJsonWorkflows = BmxFileUtility.extractWorkflowNames();
		System.out.println("All Json Workflow Count : "+allJsonWorkflows.size());
		for (String eachJsonWorkflowName : allJsonWorkflows) {
			System.out.println("Json Workflow Name : "+eachJsonWorkflowName);
		}
		
		for (Object eachJsonWorkflowName : allJsonWorkflows) {
			String expectedResult = eachJsonWorkflowName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiWorkflowName : allUiWorkflows) {
				actualResult = eachUiWorkflowName.toString().replace(" ", "");
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Workflow Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Workflow Name is equal to Json Workflow Name",
					"UI Workflow Name is not equal to Json Workflow Name", actualResult, expectedResult);
		}
	}

	public void verifyAutomation(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Automation");
		
		List<String> allUiXflows = getStudioPage().getAllTaxonomyNames();
		System.out.println("UI X-Flow Count : " + allUiXflows.size());
		for (String eachUiXflow : allUiXflows) {
			System.out.println("UI X-Flow Name : " + eachUiXflow);
		}

		List<String> allJsonXflows = BmxFileUtility.extractXflows();
		System.out.println("JSON X-Flow Count : " + allJsonXflows.size());
		for (String eachJsonXflow : allJsonXflows) {
			System.out.println("Json X-Flow Name : " + eachJsonXflow);
		}
		
		for (Object eachJsonXflow : allJsonXflows) {
			String expectedResult = eachJsonXflow.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiXflow : allUiXflows) {
				actualResult = eachUiXflow.toString().replace(" ", "");
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("XFlow Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI XFlow Name is equal to Json XFlow Name",
					"UI XFlow Name is not equal to Json XFlow Name", actualResult, expectedResult);
		}

		System.out.println("X-Flows are done");

		getStudioPage().clickOnSubModules("Automation", "Events");
		List<String> allUIEvenets = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Event Count : " + allUIEvenets.size());
		for (String eachUiEvent : allUIEvenets) {
			System.out.println("UI Event Name : " + eachUiEvent);
		}

		List<String> allJsonEvents = BmxFileUtility.extractDatafeedNames();
		System.out.println("All Json Event Count : " + allJsonEvents.size());
		for (String eachJsonEvent : allJsonEvents) {
			System.out.println("Json Event Name : " + eachJsonEvent);
		}

		for (Object eachJsonEvent : allJsonEvents) {
			String expectedResult = eachJsonEvent.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiEvent : allUIEvenets) {
				actualResult = eachUiEvent.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Event Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Event Name is equal to Json Event Name", "UI Event Name is not equal to Json Event Name",
					actualResult, expectedResult);
		}

		System.out.println("Events Are Done");
		getStudioPage().clickOnSubModules("Automation", "AI Pipeline");

		List<String> allUiModelPipelines = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Model Pipeline Count : " + allUiModelPipelines.size());
		for (String eachModelPipeline : allUiModelPipelines) {
			System.out.println("UI Model Pipeline : " + eachModelPipeline);
		}

		List<String> allJsonModelPipeline = BmxFileUtility.extractAiPipelineNames();
		System.out.println("All Json Model Pipeline Count : " + allJsonModelPipeline.size());
		for (String eachJsonModelPipeline : allJsonModelPipeline) {
			System.out.println("Json Model Pipeline Name : " + eachJsonModelPipeline);
		}

		for (Object eachJsonModelPipeline : allJsonModelPipeline) {
			String expectedResult = eachJsonModelPipeline.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiModelPipeline : allUiModelPipelines) {
				actualResult = eachUiModelPipeline.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Model Pipeline Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Model Pipeline Name is equal to Json Model Pipeline Name",
					"UI Model Pipeline Name is not equal to Json Model Pipeline Name", actualResult, expectedResult);
		}
	}

	public void verifySearchModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Search");

		String toggleName_apiNamePath = System.getProperty("user.dir") + "/toggle_jsons_download/toggleName_apiName.json";

		Set<String> allSearchGeneralToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allSearchGeneralToggleText.size()>0);
		System.out.println("Total UI Toggle Text " + allSearchGeneralToggleText.size());

		int toggleTxtCount = 0;
		Set<String> allSearchGeneralApiNames = new HashSet<String>();
		for (String eachToggleText : allSearchGeneralToggleText) {
			toggleTxtCount++;
			String apiName = BmxFileUtility.getApiNameUsingUiName(eachToggleText, toggleName_apiNamePath);
			System.out.println(toggleTxtCount + ". " + eachToggleText + " : " + apiName);
			allSearchGeneralApiNames.add(apiName);
		}

		String apiName_toggleResultPath = System.getProperty("user.dir") + "/toggle_jsons_download/apiName_toggleResult.json";

		System.out.println("-----------------------------------------------------------");
		int count = 0;
		for (String eachApiName : allSearchGeneralApiNames) {
			if (!(eachApiName.equals("SearchBoostingFields"))) {
				String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName,
						apiName_toggleResultPath);
				List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Search", eachApiName);
				count++;
				boolean result = false;
				for (Object eachActualResult : actualResult) {
					if (expectedResult.equals("false")) {
						result = expectedResult.equals(eachActualResult.toString())
								|| eachActualResult.toString().equals("null") || eachActualResult.toString().isBlank();
						if (eachActualResult.toString().isBlank()) {
							eachActualResult = "false";
						}
					} else {
						result = expectedResult.equals(eachActualResult.toString());
					}
					System.out.println(count + " : Comparision Result for " + eachApiName + " -> " + result);
					assertContains("UI Toggle Button Result is equal to Json Toggle Button Result",
							"UI Toggle Button Result not equal to Json Toggle Button Result", expectedResult,
							eachActualResult.toString());
				}
			}

		}

		System.out.println("Search General is Done");

		getStudioPage().clickOnSubModules("Search", "Views");

		List<String> allUiViewNames = getStudioPage().getAllViewNamesUnderSearchViews();
		System.out.println("All UI View Count : "+allUiViewNames.size());
		for(String eachUiViewName:allUiViewNames) {
			System.out.println("Each UI View Name : "+eachUiViewName);
		}
		
		List<String> allJsonViewNames = BmxFileUtility.extractNamesFromViewSearchModule("ColumnViewName");
		System.out.println("All JSON View Count : "+allUiViewNames.size());
		for(String eachJsonViewName:allJsonViewNames) {
			System.out.println("Each JSON View Name : "+eachJsonViewName);
		}
		
		for (Object eachJsonViewName : allJsonViewNames) {
			String expectedResult = eachJsonViewName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiViewName : allUiViewNames) {
				actualResult = eachUiViewName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("View Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI View Name is equal to Json View Name",
					"UI View Name is not equal to Json View Name", actualResult, expectedResult);
		}
		
		List<String> allUiDescriptionNames = getStudioPage().getAllDescriptionNamesUnderSearchViews();
		System.out.println("All UI Description Count : "+allUiDescriptionNames.size());
		for(String eachUiDescriptionName:allUiDescriptionNames) {
			System.out.println("Each UI Description Name : "+eachUiDescriptionName);
		}
		
		List<String> allJsonDescriptionNames = BmxFileUtility.extractNamesFromViewSearchModule("HelpMessage");
		System.out.println("All JSON Description Count : "+allJsonDescriptionNames.size());
		for(String eachJsonDescriptionName:allJsonDescriptionNames) {
			System.out.println("Each Json Description Name : "+eachJsonDescriptionName);
		}
		
		for (Object eachJsonDescriptionName : allJsonDescriptionNames) {
			String expectedResult = eachJsonDescriptionName.toString();
			if(expectedResult.isBlank()||expectedResult.isEmpty()) {
				expectedResult="null";
			}
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDescriptionName : allUiDescriptionNames) {
				actualResult = eachUiDescriptionName.toString();
				if(actualResult.isBlank()||actualResult.isEmpty()) {
					actualResult="null";
				}
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Description Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Description Name is equal to Json Description Name",
					"UI Description Name is not equal to Json Description Name", actualResult, expectedResult);
		}

		System.out.println("Search Module -> View Sub Module Completed");
		System.out.println("Search Module Completed");
	}

	public void verifyChatModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Chat");

		List<String> allNavigationNamesUnderChat = getStudioPage().getAllNavigationMenusUnderAppearence();

		for (String eachNavigationName : allNavigationNamesUnderChat) {
			getStudioPage().clickOnNavigationMenuUnderModules(eachNavigationName);
		}

		Set<String> allChatGeneralToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allChatGeneralToggleText.size()>0);
		Set<String> allChatGeneralViewApiNames = new HashSet<String>();
		System.out.println("Total UI Toggle Text " + allChatGeneralToggleText.size());

		int toggleTxtCount = 0;
		String jsonPath = System.getProperty("user.dir") + "/toggle_jsons_download/toggleName_apiName.json";

		for (String eachToggleText : allChatGeneralToggleText) {
			String apiName = BmxFileUtility.getApiNameUsingUiName(eachToggleText, jsonPath);
			toggleTxtCount++;
			System.out.println(toggleTxtCount + ". " + eachToggleText + " : " + apiName);
			allChatGeneralViewApiNames.add(apiName);
		}

		String apiName_toggleResultPath = System.getProperty("user.dir") + "/toggle_jsons_download/apiName_toggleResult.json";

		int count = 0;
		for (String eachApiName : allChatGeneralViewApiNames) {
			String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName, apiName_toggleResultPath);

			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Chat", eachApiName);
			count++;
			boolean result = false;
			for (Object eachActualResult : actualResult) {
				result = expectedResult.equals(eachActualResult.toString());

				System.out.println(count + " : Comparision Result for " + eachApiName + " -> " + result);
				assertContains("UI Toggle Button Result is equal to Json Toggle Button Result",
						"UI Toggle Button Result not equal to Json Toggle Button Result", eachActualResult.toString(),
						expectedResult);

			}
		}

		// Dropdowns are mapped using ids not by UI Text
		
		List<String> allExpectedQuestions = getStudioPage().getAllQuestionsFromChatDefaultQuestions();
		List<Object> allActualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Chat", "Question");

		for (int i = 0; i < Math.min(allExpectedQuestions.size(), allActualResult.size()); i++) {
			String expectedResult = allExpectedQuestions.get(i);
			String actualResult = allActualResult.get(i).toString();
			boolean res = actualResult.contains(expectedResult);
			System.out.println("Description Names comparision result " + res);
			assertContains("UI Question Name is equal to Json Question Name",
					"UI Question Name is not equal to Json Question Name", expectedResult, actualResult);
		}

		System.out.println("Chat Module Completed");
	}

	public void verifyIntelligenceModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Intelligence");

		List<String> allUiDashboardNames = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Dashboard Names Count : "+allUiDashboardNames.size());
		for(String eachUiDashboardName:allUiDashboardNames) {
			System.out.println("Each UI Dashboard Name "+eachUiDashboardName);
		}
		
		List<String> allJsonDashboardNames = BmxFileUtility.extractDashboardNames();
		System.out.println("All JSON Dashboard Names Count : "+allJsonDashboardNames.size());
		for(String eachJsonDashboardName:allJsonDashboardNames) {
			System.out.println("Each JSON Dashboard Name "+eachJsonDashboardName);
		}
		
		for (Object eachJsonDashboardName : allJsonDashboardNames) {
			String expectedResult = eachJsonDashboardName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDashboardName : allUiDashboardNames) {
				actualResult = eachUiDashboardName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Dashboard Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Dashboard Name is equal to Json Dashboard Name",
					"UI Dashboard Name is not equal to Json Dashboard Name", actualResult, expectedResult);
		}

		System.out.println("------------------------------------------");

		List<String> allUiWidgetNames = new ArrayList<String>();

		for (String eachDashboardName : allUiDashboardNames) {
			getStudioPage().clickOnTaxonomy(eachDashboardName);
			List<String> expectedWidgetNames = getStudioPage().getAllWidgetNamesUnderIntellegenceDashbaord();
			allUiWidgetNames.addAll(expectedWidgetNames);
		}

		System.out.println("All UI Widgets Size " + allUiWidgetNames.size());

		List<String> allJsonWidgetNames = BmxFileUtility.extractAllWidgetNamesUnderIntellegence();
		System.out.println("Actual Widgets Size " + allJsonWidgetNames.size());
		for(String eachJsonWidgetName:allJsonWidgetNames) {
			System.out.println("Each JSON Widget Name "+eachJsonWidgetName);
		}
		
		for (Object eachJsonWidgetName : allJsonWidgetNames) {
			String expectedResult = eachJsonWidgetName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiWidgetName : allUiWidgetNames) {
				actualResult = eachUiWidgetName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Widget Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Widget Name is equal to Json Widget Name",
					"UI Widget Name is not equal to Json Widget Name", actualResult, expectedResult);
		}
		

//		for (String eachActualWidgetName : allActualWidgetNames) {
//			System.out.println("Comparision Result " + allExpectedWidgetNames.contains(eachActualWidgetName));
//			assertEquals("UI Widget Names is equal to Json Widget Name",
//					"UI Widget Names is not equal to Json Widget Name", true,
//					allExpectedWidgetNames.contains(eachActualWidgetName));
//		}

		System.out.println("Intelligence Module is completed");
	}

	public void verifyAiBuilderModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("AI Builder");

		List<String> allModelNames = getStudioPage().getAllAiModelNames();
		System.out.println("Total Model Names " + allModelNames.size());
		for (String eachModelName : allModelNames) {
			System.out.println("Each Model Name " + eachModelName);
		}

		List<Object> allActualModelNames = BmxFileUtility.getJsonValueFromBmxDownloadedFile("AI Builder",
				"TrainingName");
		System.out.println("Total Actual Model Names " + allActualModelNames.size());
		for (Object eachModelName : allActualModelNames) {
			System.out.println("Each Model Name " + eachModelName);
		}

		for (int i = 0; i < Math.min(allModelNames.size(), allActualModelNames.size()); i++) {
			String expectedResult = allModelNames.get(i);
			String actualResult = allActualModelNames.get(i).toString();
			boolean res = actualResult.contains(expectedResult);
			System.out.println("Model Names comparision result " + res);
			assertContains("UI Model Name is equal to Json Model Name", "UI Model Name is not equal to Json Model Name",
					expectedResult, actualResult);
		}
	}

	public void verifyAgentBuilderModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Agent Builder");

		List<String> allExpectedLLMS = getStudioPage().getAllTaxonomyNames();

		List<String> allActualLLMs = BmxFileUtility.extractLLM_Names("LLMs", "ModelName");

		for (int i = 0; i < Math.min(allExpectedLLMS.size(), allActualLLMs.size()); i++) {
			String expectedResult = allExpectedLLMS.get(i);
			String actualResult = allActualLLMs.get(i);
			boolean res = actualResult.contains(expectedResult);
			System.out.println("LLM Names comparision result " + res);
			assertContains("UI LLM Name is equal to Json LLM Name", "UI LLM Name is not equal to Json LLM Name",
					expectedResult, actualResult);
		}

		List<String> allExpectedDescription = new ArrayList<String>();
		for (String eachLLM : allExpectedLLMS) {
			getStudioPage().clickOnTaxonomy(eachLLM);
			allExpectedDescription.add(getStudioPage().getAllModelDescriptionUnderLLMs());
		}

		List<String> allActualDescription = BmxFileUtility.extractLLM_Names("LLMs", "ModelDescription");

		for (int i = 0; i < Math.min(allExpectedDescription.size(), allActualDescription.size()); i++) {
			String expectedResult = allExpectedDescription.get(i);
			String actualResult = allActualDescription.get(i);
			boolean res = actualResult.contains(expectedResult);
			System.out.println("LLM Description comparision result " + res);
			assertContains("UI LLM Description is equal to Json LLM Description",
					"UI LLM Description is not equal to Json LLM Description", expectedResult, actualResult);
		}

		getStudioPage().clickOnSubModules("Agent Builder", "Prompts");

		List<String> allExpectedPromtNames = getStudioPage().getAllAiModelNames();

		List<String> allActualPrompts = BmxFileUtility.extractLLM_Names("Prompts", "PromptName");

		for (int i = 0; i < Math.min(allExpectedPromtNames.size(), allActualPrompts.size()); i++) {
			String expectedResult = allExpectedPromtNames.get(i);
			String actualResult = allActualPrompts.get(i);
			boolean res = actualResult.contains(expectedResult);
			System.out.println("Prompt Name comparision result " + res);
			assertContains("UI Prompt Name is equal to Json Prompt Name",
					"UI Prompt Name is not equal to Json Prompt Name", expectedResult, actualResult);
		}

		List<String> allActualPromptTexts = BmxFileUtility.extractLLM_Names("Prompts", "PromptText");
		System.out.println("Total Actual Prompt Text : " + allActualPromptTexts.size());

		int actualCount = 0;
		for (String eachActualPrompt : allActualPromptTexts) {
			actualCount++;
			System.out.println(actualCount + " . " + eachActualPrompt);
			assertEquals("UI Prompt Text is equal to Json Prompt Text",
					"UI Prompt Text is not equal to Json Prompt Text", false, eachActualPrompt.isEmpty());
		}

		getStudioPage().clickOnSubModules("Agent Builder", "Tools");

		List<String> allUiToolsName = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Tools Count : " + allUiToolsName.size());
		for (String eachUiToolsName : allUiToolsName) {
			System.out.println("UI Tool Name : " + eachUiToolsName);
		}

		List<String> allJsonTools = BmxFileUtility.extractLLM_Names("Tools", "SkillName");
		System.out.println("All Json Tools Count : " + allJsonTools.size());
		for (String eachJsonToolsName : allJsonTools) {
			System.out.println("Json Tool Name : " + eachJsonToolsName);
		}

		for (Object eachJsonTool : allJsonTools) {
			String expectedResult = eachJsonTool.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiTool : allUiToolsName) {
				actualResult = eachUiTool.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Tool Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Tool Name is equal to Json Tool Name", "UI Tool Name is not equal to Json Tool Name",
					actualResult, expectedResult);
		}
		
		getStudioPage().clickOnSubModules("Agent Builder", "Agents");
		
		List<String> allUiAgentsName = getStudioPage().getAllAgentNames();
		System.out.println("Total UI Agents Count : "+allUiAgentsName.size());
		for(String eachUiAgentName:allUiAgentsName) {
			System.out.println("Each Ui Name : "+eachUiAgentName);
		}
		
		List<String> allJsonAgentNames = BmxFileUtility.extractLLM_Names("Agents", "AgentName");
		System.out.println("All Json Agent Name Count : "+allJsonAgentNames.size());
		for(String eachJsonAgent:allJsonAgentNames) {
			System.out.println("Each Json Agent name : "+eachJsonAgent);
		}
		
		for (Object eachJsonAgent : allJsonAgentNames) {
			String expectedResult = eachJsonAgent.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiAgentName : allUiAgentsName) {
				actualResult = eachUiAgentName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Agent Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Agent Name is equal to Json Agent Name", "UI Agent Name is not equal to Json Agent Name",
					actualResult, expectedResult);
		}

		System.out.println("Agent Builder is completed");
	}

	public void verifyAssetBuilderModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Asset Builder");

		List<String> allExpectedlBotNames = getStudioPage().getAllTaxonomyNames();

		List<String> allActualBotNames = BmxFileUtility.extractBotNamesUnderLibraryBuilder();

		for (int i = 0; i < Math.min(allExpectedlBotNames.size(), allActualBotNames.size()); i++) {
			String expectedResult = allExpectedlBotNames.get(i);
			String actualResult = allActualBotNames.get(i);
			boolean res = actualResult.contains(expectedResult);
			System.out.println("Bot Name comparision result " + res);
			assertContains("UI Bot Name is equal to Json Bot Name", "UI Bot Name is not equal to Json Bot Name",
					expectedResult, actualResult);
		}
	}

	public void verifyDataLakeModule(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Data Lake");
		getStudioPage().clickOnSubModules("Data Lake", "Datasheet");

		List<String> allUiDataSheetNames = getStudioPage().getAllDataSheetNames();
		System.out.println("UI Data sheet count " + allUiDataSheetNames.size());
		for (String eachUiDataSheetName : allUiDataSheetNames) {
			System.out.println("UI Data Sheet Name : " + eachUiDataSheetName);
		}

		List<String> allJsonDataSheetNames = BmxFileUtility.extractAllDataSheetName();
		System.out.println("Json Data sheet count " + allJsonDataSheetNames.size());
		for (String eachJsonDataSheetName : allJsonDataSheetNames) {
			System.out.println("Json Data Sheet Name : " + eachJsonDataSheetName);
		}

		for (Object eachJsonDataSheetName : allJsonDataSheetNames) {
			String expectedResult = eachJsonDataSheetName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDataSheetName : allUiDataSheetNames) {
				actualResult = eachUiDataSheetName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Data Sheet Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Data Sheet Name is equal to Json Data Sheet Name",
					"UI Data Sheet Name is not equal to Json Data Sheet Name", actualResult, expectedResult);
		}
	}

	public void verifyProjectModuleUpload(String subsName, String projectName) {
//		String jsonPath = System.getProperty("user.dir") + "/upload_bmx_jsons/toggleName_toggleResult.json";
//		String apiName_toggleResultPath = System.getProperty("user.dir") + "/upload_bmx_jsons/apiName_toggleResult.json";
//		
//		readToggleUI_Status_UsingUiNames(subsName, projectName, jsonPath, apiName_toggleResultPath);
		// mergeJsonApiNameToToggleResult(jsonPath, apiName_toggleResultPath);

		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getPage().waitForTimeout(3000);

		Set<String> allProjectOverviewToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allProjectOverviewToggleText.size()>0);
		Set<String> allProjectOverviewApiNames = new HashSet<String>();
		System.out.println("Total UI Toggle Text " + allProjectOverviewToggleText.size());

		int toggleTxtCount = 0;

		String jsonPath = System.getProperty("user.dir") + "/toggle_jsons_download/toggleName_apiName.json";

		for (String eachToggleText : allProjectOverviewToggleText) {
			String apiName = BmxFileUtility.getApiNameUsingUiName(eachToggleText, jsonPath);
			toggleTxtCount++;
			System.out.println(toggleTxtCount + ". " + eachToggleText + " : " + apiName);
			allProjectOverviewApiNames.add(apiName);
		}

		String apiName_toggleResultPath = System.getProperty("user.dir")
				+ "/upload_bmx_jsons/apiName_toggleResult.json";

		int toggleComparisionCount = 0;
		for (String eachApiName : allProjectOverviewApiNames) {
			String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName, apiName_toggleResultPath);
			if (eachApiName.contains("DisableDiagnostics")
					|| eachApiName.contains("DisableTimeRangeFilterInDashboard")) {
				if (expectedResult.contains("false")) {
					expectedResult = "true";
				} else if (expectedResult.contains("true")) {
					expectedResult = "false";
				}
			}

			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project", eachApiName);
			toggleComparisionCount++;
			boolean result = false;
			for (Object eachActualResult : actualResult) {
				if (expectedResult.equals("false")) {
					result = expectedResult.equals(eachActualResult.toString())
							|| eachActualResult.toString().equals("null");
					if (eachActualResult.toString().equals("null")) {
						eachActualResult = "false";
					}
				} else {
					result = expectedResult.equals(eachActualResult.toString());
				}

				System.out
						.println(toggleComparisionCount + " : Comparision Result for " + eachApiName + " -> " + result);
				assertContains(
						"UI Toggle Button Result for <b>" + eachApiName + "</b> is equal to Json Toggle Button Result",
						"UI Toggle Button Result for <b>" + eachApiName
								+ "</b> is not equal to Json Toggle Button Result",
						eachActualResult.toString(), expectedResult);

			}
		}

		System.out.println("-----------------------------------------------");

		int textboxCount = 0;
		List<String> projectOverviewTextboxTexts = getStudioPage().getProjectOverViewTextBox_Texts();
		for (String eachText : projectOverviewTextboxTexts) {
			String[] apiNameAndValues = getStudioPage().getProjectOverviewPageTextBoxApiNamesAndValues(eachText);
			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project",
					apiNameAndValues[0]);
			for (Object eachActualResult : actualResult) {
				String expectedResult = apiNameAndValues[1];
				textboxCount++;
				System.out.println(textboxCount + ". Comarision Result for " + eachText + " Textbox "
						+ expectedResult.equals(eachActualResult.toString()));
				assertContains("UI Textbox Value for <b>" + eachText + "</b> is equal to Json Textbox Value",
						"UI Textbox Value for <b>" + eachText + "</b> is not equal to Json Textbox Value",
						expectedResult, eachActualResult.toString());
			}
		}

		System.out.println("✔️ Project Overview Comparision is Completed");

//		getStudioPage().clickOnNavigationMenuUnderProject("Landing Page");
//		int dropDownCount = 0;
//
//		List<String> allDropdownNames = getStudioPage().getAllDropdownNames();
//		for (String eachDropdownName : allDropdownNames) {
//			String[] apiNameAndValue = getStudioPage().getDropdownApiNameAndSelectedOption(eachDropdownName);
//			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project", apiNameAndValue[0]);
//			for (Object eachActualResult : actualResult) {
//				String expectedResult = apiNameAndValue[1];
//				dropDownCount++;
//				System.out.println(dropDownCount + ". Comparision Result for " + eachDropdownName + " -> "
//						+ expectedResult.equals(eachActualResult.toString()));
//				assertContains("UI Dropdown Value for <b>" + eachDropdownName + "</b> is equal to Json Dropdown Name",
//						"UI Dropdown Value for <b>" + eachDropdownName + "</b> is not equal to Json Dropdown Name",
//						expectedResult, eachActualResult.toString());
//			}
//		}
//
//		System.out.println("✔️ Project Landing Page Comparision is Completed");

		System.out.println("Project Overiew is Completed and Appearence Started");
		getStudioPage().clickOnSubModules("Project", "Appearance");
		List<String> allApperrenceMenus = getStudioPage().getAllNavigationMenusUnderAppearence();
		for (String eachMenu : allApperrenceMenus) {
			getStudioPage().clickOnNavigationMenuUnderModules(eachMenu);
		}

		Set<String> allProjectAppearenceDocumentPageToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allProjectAppearenceDocumentPageToggleText.size()>0);
		Set<String> allProjectApearenceDocumentPageApiNames = new HashSet<String>();
		System.out.println("Total Toggle Text Elemenets " + allProjectAppearenceDocumentPageToggleText.size());
		System.out.println("**************************************");
		for (String eachToggleText : allProjectAppearenceDocumentPageToggleText) {
			allProjectApearenceDocumentPageApiNames.add(BmxFileUtility.getApiNameUsingUiName(eachToggleText, jsonPath));
		}
		System.out.println("Total Toggle Api Names " + allProjectApearenceDocumentPageApiNames.size());

		int toggleCount = 0;
		for (String eachApiName : allProjectApearenceDocumentPageApiNames) {

			String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName, apiName_toggleResultPath);

			if (eachApiName.contains("Disable") && !(eachApiName.contains("DisableSortOption")
					|| eachApiName.contains("DisableStudio") || eachApiName.contains("IsPinnedViewDisabled") || eachApiName.contains("DisableSearch") || eachApiName.contains("DisableChat"))) {
				if (expectedResult.contains("false")) {
					expectedResult = "true";
				} else if (expectedResult.contains("true")) {
					expectedResult = "false";
				}
			}

			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project", eachApiName);
			for (Object eachAcualResult : actualResult) {
				toggleCount++;
				System.out.println(toggleCount + " . Comparision Result for " + eachApiName + " -> "
						+ expectedResult.equals(eachAcualResult.toString()));
				assertContains("UI Toggle Button Result for " + eachApiName + " is equal to Json Toggle Button Result",
						"UI Toggle Button Result for " + eachApiName + " is not equal to Json Toggle Button Result",
						expectedResult, eachAcualResult.toString());
			}
		}
		System.out.println("**************************************");

		List<String> allDocumentIcons = getStudioPage().getDropdownMultipleSelectedOptions("Document Icons");

		List<Object> expectedDocumentIcons = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project",
				"DocumentActions");
		String[] splittedActual = null;
		for (Object eachExpectedDocumentsIcons : expectedDocumentIcons) {
			splittedActual = eachExpectedDocumentsIcons.toString().replace("[", "").replace("]", "").split(",");
		}

		List<String> actualDocumentIcons = Arrays.asList(splittedActual);

		int minSize = Math.min(allDocumentIcons.size(), actualDocumentIcons.size());

		for (int i = 0; i < minSize; i++) {
			String expectedResult = allDocumentIcons.get(i);
			String actualResult = actualDocumentIcons.get(i);
			System.out.println("Dropdown Comparion Result for Document Icons " + actualResult.contains(expectedResult));
			assertContains("UI Dropdown Selected Options are equal to Json Dropdown Selected Options",
					"UI Dropdown Selected Options are not equal to Json Dropdown Selected Options", expectedResult,
					actualResult);
		}

		System.out.println("Project-> Appearence-> Documents Page is Completed");

		getStudioPage().clickOnNavigationMenuUnderModules("Navigation Menu");

		List<String> allExpectedNamgiationMenus = getStudioPage().getAllSelectedNavigationMenus();

		int expectedManuCount = 0;
		for (String eachExpectedName : allExpectedNamgiationMenus) {
			expectedManuCount++;
			System.out.println(expectedManuCount + " . " + eachExpectedName);
		}

		List<String> allActualNavigationMenuNames = BmxFileUtility.getBothNavigationMenuAndSubMenu();

		int actualManuCount = 0;
		for (String eachActualName : allActualNavigationMenuNames) {
			actualManuCount++;
			System.out.println(actualManuCount + " . " + eachActualName);
		}

		for (int i = 0; i < Math.min(allExpectedNamgiationMenus.size(), allActualNavigationMenuNames.size()); i++) {
			String expectedResult = allExpectedNamgiationMenus.get(i);
			String actualResult = allActualNavigationMenuNames.get(i);
			boolean res = actualResult.contains(expectedResult);
			System.out.println("Navigation Menu Name comparision result " + res);
			assertContains("UI Navigation Menu Name is equal to Json Navigation Menu Name",
					"UI Navigation Menu Name is not equal to Json Navigation Menu Name", expectedResult, actualResult);
		}

		getStudioPage().clickOnNavigationMenuUnderModules("Document List");

		List<String> allUiSelectedDocListIcons = getStudioPage().getAllDropdownSelectedOptions();
		System.out.println("All UI Doc List Icon Count " + allUiSelectedDocListIcons.size());
		for (String eachUiDocListIcon : allUiSelectedDocListIcons) {
			System.out.println("Each UI Doc List Icon Name : " + eachUiDocListIcon);
		}

		List<Object> jsonDocListIconsArray = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Project",
				"DocumentListingActions");

		String[] splittedJsonDocListIcons = null;
		for (Object eachJsonDocListIcons : jsonDocListIconsArray) {
			splittedJsonDocListIcons = eachJsonDocListIcons.toString().replace("[", "").replace("]", "").split(",");
		}

		List<String> jsonDocListIcons = Arrays.asList(splittedJsonDocListIcons);
		System.out.println("All Json Doc List Icon Count " + jsonDocListIcons.size());
		for (String eachJsonDocListIcon : jsonDocListIcons) {
			System.out.println("Each Json Doc List Icon Name : " + eachJsonDocListIcon);
		}

		for (Object eachJsonDocListIcon : jsonDocListIcons) {
			String expectedResult = eachJsonDocListIcon.toString();
			if(expectedResult.length()>16) {
				expectedResult = expectedResult.substring(0, 16)+"...";
			}
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDocListIcon : allUiSelectedDocListIcons) {
				actualResult = eachUiDocListIcon.toString();
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Doc List Icon Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Doc List Icon Name is equal to Json Doc List Icon Name",
					"UI Doc List Icon Name is not equal to Json Doc List Icon Name", actualResult, expectedResult);
		}
	}

	public void verifyDataModelModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Data Model");
		List<String> allUiEntities = getStudioPage().getAllEntityNamesInDataMdel();
		System.out.println("Total Entites found in the UI : " + allUiEntities.size());
		for (String eachEntity : allUiEntities) {
			System.out.println("UI Entity Name : " + eachEntity);
		}
		List<Object> allJsonEntities = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Data Model", "EntityName");
		System.out.println("Total Entites found in the Json : " + allJsonEntities.size());

		for (Object eachEntity : allJsonEntities) {
			System.out.println("Json Entity Name : " + eachEntity);
		}

		for (Object eachJsonEntity : allJsonEntities) {
			String expectedResult = eachJsonEntity.toString();
			boolean found = false;
			String actualResult = null;
			for (Object eachUiEntitty : allUiEntities) {
				actualResult = (String) eachUiEntitty;
				if (actualResult.contains(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Entity Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Entity Name is equal to Json Entity Name",
					"UI Entity Name is not equal to Json Entity Name", actualResult, expectedResult);
		}

		System.out.println("Entities are done");

		getStudioPage().clickOnSubModules("Data Model", "Taxonomy");

		List<String> allUITaxonomyNames = getStudioPage().getAllTaxonomyNames();
		System.out.println("UI Taxonomies Count : " + allUITaxonomyNames.size());

		for (String eachUiTaxonomy : allUITaxonomyNames) {
			System.out.println("UI Taxonomy Name : " + eachUiTaxonomy);
		}

		List<String> allJsonTaxonomyNames = BmxFileUtility.extractTaxonomyNames();
		System.out.println("JSON Taxonomies Count : " + allJsonTaxonomyNames.size());

		for (String eachJsonTaxonomy : allJsonTaxonomyNames) {
			System.out.println("JSON Taxonomy Name : " + eachJsonTaxonomy);
		}

		for (Object eachJsonTaxonomy : allJsonTaxonomyNames) {
			String expectedResult = eachJsonTaxonomy.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiTaxonomy : allUITaxonomyNames) {
				actualResult = eachUiTaxonomy.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Taxonomy Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Taxonomy Name is equal to Json Taxonomy Name",
					"UI Taxonomy Name is not equal to Json Taxonomy Name", actualResult, expectedResult);
		}

		System.out.println("-----Taxonomy Done--------");

		List<String> allUiLabels = new ArrayList<String>();
		for (String eachTaxonomy : allUITaxonomyNames) {
			getStudioPage().clickOnTaxonomy(eachTaxonomy);
			List<String> labels = getStudioPage().getAllLabelNames();
			System.out.println("Label count for " + eachTaxonomy + " : " + labels.size());
			allUiLabels.addAll(labels);
		}

		System.out.println("Total UI Labels : " + allUiLabels.size());

		for (String eachUiLabel : allUiLabels) {
			System.out.println("UI Label Name : " + eachUiLabel);
		}

		List<String> allJsonLabels = BmxFileUtility.extractAllLabelNames();

		System.out.println("Total Json Labels : " + allJsonLabels.size());

		for (String eachJsonLabel : allJsonLabels) {
			System.out.println("Json Label Name : " + eachJsonLabel);
		}

		for (Object eachJsonLabel : allJsonLabels) {
			String expectedResult = eachJsonLabel.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiLabel : allUiLabels) {
				actualResult = eachUiLabel.toString().replace(" ", "");
				if(actualResult.length()>25) {
					actualResult=actualResult.substring(0, 25);
				}
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Label Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Label Name is equal to Json Label Name",
					"UI Label Name is not equal to Json Label Name", actualResult, expectedResult);
		}

		System.out.println("Insights Taxonomies are done");

		// Need confirmation from Raja on what happens for existing workflow
		getStudioPage().clickOnSubModules("Data Model", "Workflows");
		List<String> allexpectedWorkFlowNames = getStudioPage().getAllTaxonomyNames();
		for (String eachWorkflowName : allexpectedWorkFlowNames) {
			System.out.println(eachWorkflowName);
		}
	}

	public void verifyAutomationUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Automation");

		getStudioPage().clickOnSubModules("Automation", "XFlow");
		List<String> allUiXflows = getStudioPage().getAllTaxonomyNames();
		System.out.println("UI X-Flow Count : " + allUiXflows.size());
		for (String eachUiXflow : allUiXflows) {
			System.out.println("UI X-Flow Name : " + eachUiXflow);
		}

		List<String> allJsonXflows = BmxFileUtility.extractXflows();
		System.out.println("JSON X-Flow Count : " + allJsonXflows.size());
		for (String eachJsonXflow : allJsonXflows) {
			System.out.println("Json X-Flow Name : " + eachJsonXflow);
		}

		for (Object eachJsonXflow : allJsonXflows) {
			String expectedResult = eachJsonXflow.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiXflow : allUiXflows) {
				actualResult = eachUiXflow.toString().replace(" ", "");
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("XFlow Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI XFlow Name is equal to Json XFlow Name",
					"UI XFlow Name is not equal to Json XFlow Name", actualResult, expectedResult);
		}
		
		System.out.println("X-Flows are done");

		getStudioPage().clickOnSubModules("Automation", "Events");

		List<String> allUIEvenets = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Event Count : " + allUIEvenets.size());
		for (String eachUiEvent : allUIEvenets) {
			System.out.println("UI Event Name : " + eachUiEvent);
		}

		List<String> allJsonEvents = BmxFileUtility.extractDatafeedNames();
		System.out.println("All Json Event Count : " + allJsonEvents.size());
		for (String eachJsonEvent : allJsonEvents) {
			System.out.println("Json Event Name : " + eachJsonEvent);
		}

		for (Object eachJsonEvent : allJsonEvents) {
			String expectedResult = eachJsonEvent.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiEvent : allUIEvenets) {
				actualResult = eachUiEvent.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Event Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Event Name is equal to Json Event Name", "UI Event Name is not equal to Json Event Name",
					actualResult, expectedResult);
		}

		System.out.println("Events are done");

		getStudioPage().clickOnSubModules("Automation", "AI Pipeline");

		List<String> allUiModelPipelines = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Model Pipeline Count : " + allUiModelPipelines.size());
		for (String eachModelPipeline : allUiModelPipelines) {
			System.out.println("UI Model Pipeline : " + eachModelPipeline);
		}

		List<String> allJsonModelPipeline = BmxFileUtility.extractAiPipelineNames();
		System.out.println("All Json Model Pipeline Count : " + allJsonModelPipeline.size());
		for (String eachJsonModelPipeline : allJsonModelPipeline) {
			System.out.println("Json Model Pipeline Name : " + eachJsonModelPipeline);
		}

		for (Object eachJsonModelPipeline : allJsonModelPipeline) {
			String expectedResult = eachJsonModelPipeline.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiModelPipeline : allUiModelPipelines) {
				actualResult = eachUiModelPipeline.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Model Pipeline Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Model Pipeline Name is equal to Json Model Pipeline Name",
					"UI Model Pipeline Name is not equal to Json Model Pipeline Name", actualResult, expectedResult);
		}
	}

	public void verifySearchModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Search");

		String jsonPath = System.getProperty("user.dir") + "/bmx_jsons/toggleName_apiName.json";

		Set<String> allSearchGeneralToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allSearchGeneralToggleText.size()>0);
		System.out.println("Total UI Toggle Text " + allSearchGeneralToggleText.size());

		int toggleTxtCount = 0;
		Set<String> allSearchGeneralApiNames = new HashSet<String>();
		for (String eachToggleText : allSearchGeneralToggleText) {
			toggleTxtCount++;
			String apiName = BmxFileUtility.getApiNameUsingUiName(eachToggleText, jsonPath);
			System.out.println(toggleTxtCount + ". " + eachToggleText + " : " + apiName);
			allSearchGeneralApiNames.add(apiName);
		}

		String apiName_toggleResultPath = System.getProperty("user.dir")
				+ "/upload_bmx_jsons/apiName_toggleResult.json";

		System.out.println("-----------------------------------------------------------");
		int count = 0;
		for (String eachApiName : allSearchGeneralApiNames) {
			if (!(eachApiName.equals("SearchBoostingFields"))) {
				String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName,
						apiName_toggleResultPath);
				List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Search", eachApiName);
				count++;
				boolean result = false;
				for (Object eachActualResult : actualResult) {
					if (expectedResult.equals("false")) {
						result = expectedResult.equals(eachActualResult.toString())
								|| eachActualResult.toString().equals("null") || eachActualResult.toString().isBlank();
						if (eachActualResult.toString().isBlank()) {
							eachActualResult = "false";
						}
					} else {
						result = expectedResult.equals(eachActualResult.toString());
					}
					System.out.println(count + " : Comparision Result for " + eachApiName + " -> " + result);
					assertContains(
							"UI Toggle Button Result for <b>" + eachApiName
									+ "</b> is equal to Json Toggle Button Result",
							"UI Toggle Button Result for <b>" + eachApiName
									+ "</b> is not equal to Json Toggle Button Result",
							eachActualResult.toString(), expectedResult);
				}
			}
		}

		System.out.println("Search General is Done");

		getStudioPage().clickOnSubModules("Search", "Views");

		List<String> allUIViewNames = getStudioPage().getAllViewNamesUnderSearchViews();
		System.out.println("All UI View Names Count : " + allUIViewNames.size());

		List<String> allJsonViewNames = BmxFileUtility.extractNamesFromViewSearchModule("ColumnViewName");
		System.out.println("All JSON View names Count : " + allJsonViewNames.size());

		for (Object eachJsonViewName : allJsonViewNames) {
			String expectedResult = eachJsonViewName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiViewName : allUIViewNames) {
				actualResult = eachUiViewName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("View Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI View Name is equal to Json View Name", "UI View Name is not equal to Json View Name",
					actualResult, expectedResult);
		}

		List<String> allUiDescriptionNames = getStudioPage().getAllDescriptionNamesUnderSearchViews();
		System.out.println("All UI Description Names Count : " + allUiDescriptionNames.size());

		List<String> allJsonDescriptionNames = BmxFileUtility.extractNamesFromViewSearchModule("HelpMessage");
		System.out.println("All JSON Description Names Count : " + allJsonDescriptionNames.size());

		for (Object eachJsonDescriptionName : allJsonDescriptionNames) {
			String expectedResult = eachJsonDescriptionName.toString();
			if(expectedResult.isBlank()||expectedResult.isEmpty()) {
				expectedResult="null";
			}
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDescriptionName : allUiDescriptionNames) {
				actualResult = eachUiDescriptionName.toString();
				if(actualResult.isBlank()||actualResult.isEmpty()) {
					actualResult="null";
				}
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Description Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Description Name is equal to Json Description Name",
					"UI Description Name is not equal to Json Description Name", actualResult, expectedResult);
		}
	}

	public void verifyChatModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Chat");

		List<String> allNavigationNamesUnderChat = getStudioPage().getAllNavigationMenusUnderAppearence();

		for (String eachNavigationName : allNavigationNamesUnderChat) {
			getStudioPage().clickOnNavigationMenuUnderModules(eachNavigationName);
		}

		Set<String> allChatGeneralToggleText = getStudioPage().getAllToggleText();
		assertEquals("Toggle Text Captured", "Toggle Text not Captured", true, allChatGeneralToggleText.size()>0);
		Set<String> allChatGeneralViewApiNames = new HashSet<String>();
		System.out.println("Total UI Toggle Text " + allChatGeneralToggleText.size());

		int toggleTxtCount = 0;
		String jsonPath = System.getProperty("user.dir") + "/bmx_jsons/toggleName_apiName.json";

		for (String eachToggleText : allChatGeneralToggleText) {
			String apiName = BmxFileUtility.getApiNameUsingUiName(eachToggleText, jsonPath);
			toggleTxtCount++;
			System.out.println(toggleTxtCount + ". " + eachToggleText + " : " + apiName);
			allChatGeneralViewApiNames.add(apiName);
		}

		String apiName_toggleResultPath = System.getProperty("user.dir")
				+ "/upload_bmx_jsons/apiName_toggleResult.json";

		int count = 0;
		for (String eachApiName : allChatGeneralViewApiNames) {
			String expectedResult = BmxFileUtility.getUiToggleResultUsingApiName(eachApiName, apiName_toggleResultPath);
			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Chat", eachApiName);
			count++;
			boolean result = false;
			for (Object eachActualResult : actualResult) {
				result = expectedResult.equals(eachActualResult.toString());
				System.out.println(count + " : Comparision Result for " + eachApiName + " -> " + result);
				assertContains(
						"UI Toggle Button Result for <b>" + eachApiName + "</b> is equal to Json Toggle Button Result",
						"UI Toggle Button Result for <b>" + eachApiName
								+ "</b> is not equal to Json Toggle Button Result",
						eachActualResult.toString(), expectedResult);
			}
		}

//		// Dropdowns names are missing in json it is mapped based on id
//		List<String> allDropdownNames = getStudioPage().getAllDropdownNames();
//		for (String eachDropdownName : allDropdownNames) {
//			String[] apiNameAndValue = getStudioPage().getDropdownApiNameAndSelectedOption(eachDropdownName);
//			List<Object> actualResult = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Search", apiNameAndValue[0]);
//			for (Object eachActualResult : actualResult) {
//				String expectedResult = apiNameAndValue[1];
//				count++;
//				System.out.println("Comparision Result for " + eachDropdownName + " -> "
//						+ expectedResult.equals(eachActualResult.toString()));
//				assertContains("UI Dropdown Name is equal to Json Dropdown Name",
//						"UI Dropdown Name is not equal to Json Dropdown Name", expectedResult,
//						eachActualResult.toString());
//			}
//		}

		getStudioPage().clickOnNavigationMenuUnderChat("Default Questions");

		List<String> allUIQuestions = getStudioPage().getAllQuestionsFromChatDefaultQuestions();
		List<Object> allJsonQuestions = BmxFileUtility.getJsonValueFromBmxDownloadedFile("Chat", "Question");

		for (int i = 0; i < Math.min(allUIQuestions.size(), allJsonQuestions.size()); i++) {
			String expectedResult = allUIQuestions.get(i);
			String actualResult = allJsonQuestions.get(i).toString();
			boolean res = actualResult.contains(expectedResult);
			System.out.println("Question Name Names comparision result " + res);
			assertContains("UI Question Name is equal to Json Question Name",
					"UI Question Name is not equal to Json Question Name", expectedResult, actualResult);
		}

		System.out.println("Chat Module Completed");
	}

	public void verifyIntelligenceModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Intelligence");

		List<String> allUiDashboardNames = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Dashboard Count : " + allUiDashboardNames.size());

		List<String> allJsonDashboardNames = BmxFileUtility.extractDashboardNames();
		System.out.println("All Json Dashboard Count : " + allJsonDashboardNames.size());

		for (Object eachJsonDashboardName : allJsonDashboardNames) {
			String expectedResult = eachJsonDashboardName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDashboardName : allUiDashboardNames) {
				actualResult = eachUiDashboardName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Dashboard Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Dashboard Name is equal to Json Dashboard Name",
					"UI Dashboard Name is not equal to Json Dashboard Name", actualResult, expectedResult);
		}

		System.out.println("------------------------------------------");

		List<String> allUiWidgetNames = new ArrayList<String>();

		for (String eachDashboardName : allUiDashboardNames) {
			getStudioPage().clickOnTaxonomy(eachDashboardName);
			List<String> expectedWidgetNames = getStudioPage().getAllWidgetNamesUnderIntellegenceDashbaord();
			allUiWidgetNames.addAll(expectedWidgetNames);
		}

		System.out.println("All UI Widgets Size " + allUiWidgetNames.size());

		List<String> allJsonWidgetNames = BmxFileUtility.extractAllWidgetNamesUnderIntellegence();
		System.out.println("All Json Widgets Size " + allJsonWidgetNames.size());

		for (Object eachJsonWidgetName : allJsonWidgetNames) {
			String expectedResult = eachJsonWidgetName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiWidgetName : allUiWidgetNames) {
				actualResult = eachUiWidgetName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Widget Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Widget Name is equal to Json Widget Name",
					"UI Widget Name is not equal to Json Widget Name", actualResult, expectedResult);
		}

		System.out.println("Intelligence Module is completed");
	}

	public void verifyAiBuilderModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("AI Builder");

		List<String> allUiModelNames = getStudioPage().getAllAiModelNames();
		System.out.println("Total UI Model Count " + allUiModelNames.size());
		for (String eachUiModelName : allUiModelNames) {
			System.out.println("UI Model Name " + eachUiModelName);
		}

		List<Object> allJsonModelNames = BmxFileUtility.getJsonValueFromBmxDownloadedFile("AI Builder", "TrainingName");
		System.out.println("Total JSON Model Count " + allJsonModelNames.size());
		for (Object eachJsonModelName : allJsonModelNames) {
			System.out.println("Json Model Name " + eachJsonModelName);
		}

		for (Object eachJsonModelName : allJsonModelNames) {
			String expectedResult = eachJsonModelName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiModelName : allUiModelNames) {
				actualResult = eachUiModelName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("AI Model Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI AI Model Name is equal to Json AI Model Name",
					"UI AI Model Name is not equal to Json AI Model Name", actualResult, expectedResult);
		}
	}

	public void verifyAgentBuilderModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Agent Builder");

		List<String> allUiLLMS = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI LLM Count : " + allUiLLMS.size());
		for (String eachUiLLM : allUiLLMS) {
			System.out.println("UI LLM Name : " + eachUiLLM);
		}

		List<String> allJsonLLMs = BmxFileUtility.extractLLM_Names("LLMs", "ModelName");
		System.out.println("All JSON LLM Count : " + allJsonLLMs.size());
		for (String eachJsonLLM : allJsonLLMs) {
			System.out.println("Json LLM Name : " + eachJsonLLM);
		}

		for (Object eachJsonLLM : allJsonLLMs) {
			String expectedResult = eachJsonLLM.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiLLM : allUiLLMS) {
				actualResult = eachUiLLM.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("UI LLM Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI LLM Name is equal to Json LLM Name", "UI LLM Name is not equal to Json LLM Name",
					actualResult, expectedResult);
		}

		getStudioPage().clickOnSubModules("Agent Builder", "Prompts");

		List<String> allUiPromtNames = getStudioPage().getAllAiModelNames();
		System.out.println("All UI Prompt Count : " + allUiPromtNames.size());
		for (String eachPrompt : allUiPromtNames) {
			System.out.println("UI Prompt Name : " + eachPrompt);
		}

		List<String> allJsonPrompts = BmxFileUtility.extractLLM_Names("Prompts", "PromptName");
		System.out.println("All JSON Prompt Count : " + allJsonPrompts.size());
		for (String eachJsonPrompt : allJsonPrompts) {
			System.out.println("Json Prompt Name : " + eachJsonPrompt);
		}

		for (Object eachUiPrompt : allUiPromtNames) {
			String expectedResult = eachUiPrompt.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachJsonPrompt : allJsonPrompts) {
				actualResult = eachJsonPrompt.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Prompt Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Prompt Name is equal to Json Prompt Name",
					"UI Prompt Name is not equal to Json Prompt Name", actualResult, expectedResult);
		}

		getStudioPage().clickOnSubModules("Agent Builder", "Tools");

		List<String> allUiToolsName = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Tools Count : " + allUiToolsName.size());
		for (String eachUiToolsName : allUiToolsName) {
			System.out.println("UI Tool Name : " + eachUiToolsName);
		}

		List<String> allJsonTools = BmxFileUtility.extractLLM_Names("Tools", "SkillName");
		System.out.println("All Json Tools Count : " + allJsonTools.size());
		for (String eachJsonToolsName : allJsonTools) {
			System.out.println("Json Tool Name : " + eachJsonToolsName);
		}

		for (Object eachJsonTool : allJsonTools) {
			String expectedResult = eachJsonTool.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiTool : allUiToolsName) {
				actualResult = eachUiTool.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Tool Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Tool Name is equal to Json Tool Name", "UI Tool Name is not equal to Json Tool Name",
					actualResult, expectedResult);
		}

		System.out.println("Agent Builder is completed");
	}

	public void verifyAssetBuilderModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Asset Builder");

		List<String> allUiBotNames = getStudioPage().getAllTaxonomyNames();
		System.out.println("All UI Bots Count : " + allUiBotNames.size());
		for (String eachUiBotName : allUiBotNames) {
			System.out.println("UI Bot Name : " + eachUiBotName);
		}

		List<String> allJsonBotNames = BmxFileUtility.extractBotNamesUnderLibraryBuilder();
		System.out.println("All Json Bots Count : " + allJsonBotNames.size());
		for (String eachJsonBotName : allJsonBotNames) {
			System.out.println("Json Bot Name : " + eachJsonBotName);
		}

		for (Object eachJsonBotName : allJsonBotNames) {
			String expectedResult = eachJsonBotName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiBotName : allUiBotNames) {
				actualResult = eachUiBotName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Bot Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Bot Name is equal to Json Bot Name", "UI Bot Name is not equal to Json Bot Name",
					actualResult, expectedResult);
		}
	}

	public void verifyDataLakeModuleUpload(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().waitForTimeout(1000);
		getVoicesListPage().clickOnStudio();
		getStudioPage().clickOnModules("Data Lake");
		getStudioPage().clickOnSubModules("Data Lake", "Datasheet");

		List<String> allUiDataSheetNames = getStudioPage().getAllDataSheetNames();
		System.out.println("UI Data sheet count " + allUiDataSheetNames.size());
		for (String eachUiDataSheetName : allUiDataSheetNames) {
			System.out.println("UI Data Sheet Name : " + eachUiDataSheetName);
		}

		List<String> allJsonDataSheetNames = BmxFileUtility.extractAllDataSheetName();
		System.out.println("Json Data sheet count " + allJsonDataSheetNames.size());
		for (String eachJsonDataSheetName : allJsonDataSheetNames) {
			System.out.println("Json Data Sheet Name : " + eachJsonDataSheetName);
		}

		for (Object eachJsonDataSheetName : allJsonDataSheetNames) {
			String expectedResult = eachJsonDataSheetName.toString().replace(" ", "");
			boolean found = false;
			String actualResult = null;
			for (Object eachUiDataSheetName : allUiDataSheetNames) {
				actualResult = eachUiDataSheetName.toString().replace(" ", "");
				if (actualResult.equals(expectedResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Data Sheet Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("UI Data Sheet Name is equal to Json Data Sheet Name",
					"UI Data Sheet Name is not equal to Json Data Sheet Name", actualResult, expectedResult);
		}

	}

}
