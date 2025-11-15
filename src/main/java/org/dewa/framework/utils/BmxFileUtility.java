package org.dewa.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BmxFileUtility {

	public static void renameBMXtoZIP(String filePath) {
		File bmxFile = new File(filePath);
		String zipFilePath = filePath.replace(".bmx", ".zip");
		File zipFile = new File(zipFilePath);

		if (bmxFile.renameTo(zipFile)) {
			System.out.println("Renamed to: " + zipFilePath);
		} else {
			System.out.println("Rename failed.");
		}
	}

	public static void unzip(String zipFilePath, String destDir) {
		File dir = new File(destDir);
		if (!dir.exists())
			dir.mkdirs();

		try (FileInputStream fis = new FileInputStream(zipFilePath); ZipInputStream zis = new ZipInputStream(fis)) {

			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				File newFile = new File(destDir, entry.getName());

				if (entry.isDirectory()) {
					newFile.mkdirs();
				} else {
					// Create parent directories if they don't exist
					new File(newFile.getParent()).mkdirs();

					try (FileOutputStream fos = new FileOutputStream(newFile)) {
						byte[] buffer = new byte[1024];
						int len;
						while ((len = zis.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
					}
				}
				zis.closeEntry();
			}
			System.out.println("Extraction complete.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readAllJsonFiles(String directoryPath) throws IOException {
		File dir = new File(directoryPath);
		ObjectMapper mapper = new ObjectMapper();

		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(".json")) {
				JsonNode root = mapper.readTree(file);
				System.out.println("Data from " + file.getName() + ":");
				System.out.println(root.toPrettyString());
			}
		}
	}

	public static String getApiNameUsingUiName(String uiName, String jsonPath) {
		//String jsonPath = System.getProperty("user.dir") + "/bmx_jsons/toggleName_apiName.json";
		try {
			// Read the JSON file content as a String
			String jsonContent = new String(Files.readAllBytes(Paths.get(jsonPath)));

			// Convert to JSONObject
			JSONObject jsonObject = new JSONObject(jsonContent);

			// Return value for key, or a message if not found
			return jsonObject.optString(uiName, "Key Not Found");

		} catch (IOException e) {
			return "Error reading file: " + e.getMessage();
		} catch (Exception e) {
			return "Error parsing JSON: " + e.getMessage();
		}
	}

	public static String getUiToggleResultUsingApiName(String apiName, String apiName_toggleResultPath) {
		//String jsonPath = System.getProperty("user.dir") + "/bmx_jsons/apiName_toggleResult.json";
		try {
			// Read the JSON file content as a String
			String jsonContent = new String(Files.readAllBytes(Paths.get(apiName_toggleResultPath)));

			// Convert to JSONObject
			JSONObject jsonObject = new JSONObject(jsonContent);

			// Return value for key, or a message if not found
			return jsonObject.optString(apiName, "Key Not Found");

		} catch (IOException e) {
			return "Error reading file: " + e.getMessage();
		} catch (Exception e) {
			return "Error parsing JSON: " + e.getMessage();
		}
	}

//	private static Object searchKeyRecursive(Object obj, String searchKey) {
//        if (obj instanceof JSONObject) {
//            JSONObject json = (JSONObject) obj;
//            Iterator<String> keys = json.keys();
//
//            while (keys.hasNext()) {
//                String key = keys.next();
//                Object value = json.get(key);
//
//                if (key.equals(searchKey)) {
//                    return value;
//                }
//
//                Object nestedResult = searchKeyRecursive(value, searchKey);
//                if (nestedResult != null) {
//                    return nestedResult;
//                }
//            }
//        } else if (obj instanceof JSONArray) {
//            JSONArray arr = (JSONArray) obj;
//            for (int i = 0; i < arr.length(); i++) {
//                Object nestedResult = searchKeyRecursive(arr.get(i), searchKey);
//                if (nestedResult != null) {
//                    return nestedResult;
//                }
//            }
//        }
//        return null;
//    }
//	
//	public static String getJsonValueFromBmxDownloadedFile(String moduleName, String searchKey) {
//		String path=getJsonFilePath(moduleName);
//		
//        try {
//            String jsonContent = new String(Files.readAllBytes(Paths.get(path)));
//            JSONObject jsonObject = new JSONObject(jsonContent);
//            return searchKeyRecursive(jsonObject, searchKey).toString();
//        } catch (IOException e) {
//            return "Error reading file: " + e.getMessage();
//        } catch (Exception e) {
//            return "Error parsing JSON: " + e.getMessage();
//        }
//    }

	private static void searchAllKeysRecursive(Object obj, String searchKey, List<Object> results) {
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			Iterator<String> keys = json.keys();

			while (keys.hasNext()) {
				String key = keys.next();
				Object value = json.get(key);

				if (key.equals(searchKey)) {
					results.add(value); // Add the match to the list
				}

				// Recurse into nested objects/arrays
				searchAllKeysRecursive(value, searchKey, results);
			}
		} else if (obj instanceof JSONArray) {
			JSONArray arr = (JSONArray) obj;
			for (int i = 0; i < arr.length(); i++) {
				searchAllKeysRecursive(arr.get(i), searchKey, results);
			}
		}
	}

	public static List<Object> getJsonValueFromBmxDownloadedFile(String moduleName, String searchKey) {
		String path = getJsonFilePath(moduleName);
		List<Object> resultList = new ArrayList<>();

		try {
			String jsonContent = new String(Files.readAllBytes(Paths.get(path)));
			JSONObject jsonObject = new JSONObject(jsonContent);
			searchAllKeysRecursive(jsonObject, searchKey, resultList);
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error parsing JSON: " + e.getMessage());
		}

		return resultList;
	}

	public static String getJsonFilePath(String keyword) {
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded";
		File folder = new File(folderPath);

		// Ensure the directory exists
		if (!folder.exists() || !folder.isDirectory()) {
			System.out.println("Folder not found: " + folderPath);
			return null;
		}

		// Search for the file with the keyword
		for (File file : folder.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".json") && file.getName().contains("_" + keyword)) {
				return file.getAbsolutePath();
			}
		}

		System.out.println("No file found with keyword: " + keyword);
		return null;
	}

	public static List<String> extractTaxonomyNames() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/2_Data Model.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("DataModel").path("LearnerList");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("Name");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ LearnerList is missing or not an array.");
		}
		return allTaxonomyName;
	}
	
	public static List<String> extractWorkflowNames() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/2_Data Model.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("DataModel").path("Stateflows");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("Name");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ LearnerList is missing or not an array.");
		}
		return allTaxonomyName;
	}

	public static List<String> extractAllLabelNames() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/2_Data Model.json";
		List<String> allTaxonomyNames = new ArrayList<>();

		try {
			JsonNode root = mapper.readTree(new File(folderPath));
			JsonNode learnerList = root.path("DataModel").path("LearnerList");

			if (learnerList.isArray()) {
				for (JsonNode learner : learnerList) {
					JsonNode labelList = learner.path("LabelList");

					if (labelList.isArray()) {
						for (JsonNode label : labelList) {
							JsonNode nameNode = label.path("Name");
							if (!nameNode.isMissingNode() && !nameNode.isNull()) {
								allTaxonomyNames.add(nameNode.asText());
							}
						}
					}
				}
			} else {
				System.out.println("❌ LearnerList is missing or not an array.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allTaxonomyNames;
	}

	public static List<String> extractEventNames() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/9_Automation.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("Automation").path("DataFeed");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("Name");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ DataFeed is missing or not an array.");
		}
		return allTaxonomyName;
	}
	
	public static List<String> extractXflows() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/9_Automation.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("Automation").path("WorkflowMetaInfo");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("WorkflowName");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ DataFeed is missing or not an array.");
		}
		return allTaxonomyName;
	}

	public static List<String> extractAiPipelineNames() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/9_Automation.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("Automation").path("AiPipelines");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("PipelineName");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ PipelineName is missing or not an array.");
		}
		return allTaxonomyName;
	}
	
	public static List<String> extractDatafeedNames() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/9_Automation.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("Automation").path("DataFeed");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("Name");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ PipelineName is missing or not an array.");
		}
		return allTaxonomyName;
	}

	public static List<String> extractNamesFromViewSearchModule(String labelName) {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/10_Search.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("Search").path("ColumnViewTeamplates");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path(labelName);

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ " + labelName + " is missing or not an array.");
		}
		return allTaxonomyName;
	}

	public static List<String> extractDashboardNames() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/12_Intelligence.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("Intelligence").path("Dashboards");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("Name");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ PipelineName is missing or not an array.");
		}
		return allTaxonomyName;
	}

	public static List<String> extractAllWidgetNamesUnderIntellegence() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/12_Intelligence.json";
		List<String> allTaxonomyNames = new ArrayList<>();

		try {
			JsonNode root = mapper.readTree(new File(folderPath));
			JsonNode learnerList = root.path("Intelligence").path("Dashboards");

			if (learnerList.isArray()) {
				for (JsonNode learner : learnerList) {
					JsonNode labelList = learner.path("Widgets");

					if (labelList.isArray()) {
						for (JsonNode label : labelList) {
							JsonNode nameNode = label.path("Name");
							if (!nameNode.isMissingNode() && !nameNode.isNull()) {
								allTaxonomyNames.add(nameNode.asText());
							}
						}
					}
				}
			} else {
				System.out.println("❌ Dashboard is missing or not an array.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allTaxonomyNames;
	}

	public static List<String> extractLLM_Names(String arrayName, String keyName) {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/5_Agent Builder.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("AgentBuilder").path(arrayName);

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path(keyName);

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ PipelineName is missing or not an array.");
		}
		return allTaxonomyName;
	}

	public static List<String> extractBotNamesUnderLibraryBuilder() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/4_Asset Builder.json";
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(folderPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode learnerList = root.path("AssetBuilder").path("Bots").path("Process");

		List<String> allTaxonomyName = new ArrayList<String>();

		if (learnerList.isArray()) {
			for (int i = 0; i < learnerList.size(); i++) {
				JsonNode learner = learnerList.get(i);
				JsonNode nameNode = learner.path("ProcessName");

				if (!nameNode.isMissingNode()) {
					allTaxonomyName.add(nameNode.asText());
					// System.out.println("✅ DataModel.LearnerList[" + i + "].Name = " +
					// nameNode.asText());
				}
			}
		} else {
			System.out.println("❌ PipelineName is missing or not an array.");
		}
		return allTaxonomyName;
	}

	public static List<String> extractAllDataSheetName() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/7_Data Lake.json";
		List<String> allDataSheetNames = new ArrayList<>();

		try {
			JsonNode root = mapper.readTree(new File(folderPath));
			JsonNode dataSheets = root.path("DataSheet").path("DataSheets");

			if (dataSheets.isArray()) {
				for (JsonNode sheet : dataSheets) {
					JsonNode nameNode = sheet.path("DataSheetName");
					if (!nameNode.isMissingNode() && !nameNode.isNull()) {
						allDataSheetNames.add(nameNode.asText());
					}
				}
			} else {
				System.out.println("❌ DataSheets is missing or not an array.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allDataSheetNames;
	}
	

	public static List<String> extractNavigationMenuNamesUnderApperence() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/3_Project.json";
		List<String> allDataSheetNames = new ArrayList<>();

		try {
			JsonNode root = mapper.readTree(new File(folderPath));
			JsonNode dataSheets = root.path("Project").path("Appearance").path("NavigationMenu").path("NavigationMenu");

			if (dataSheets.isArray()) {
				for (JsonNode sheet : dataSheets) {
					JsonNode nameNode = sheet.path("Name");
					if (!nameNode.isMissingNode() && !nameNode.isNull()) {
						allDataSheetNames.add(nameNode.asText());
					}
				}
			} else {
				System.out.println("❌ DataSheets is missing or not an array.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allDataSheetNames;
	}
	
	public static List<String> extractNavigationSubMenuNamesUnderApperence() {
		ObjectMapper mapper = new ObjectMapper();
		String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/3_Project.json";
		List<String> allDataSheetNames = new ArrayList<>();

		try {
			JsonNode root = mapper.readTree(new File(folderPath));
			JsonNode dataSheets = root.path("Project").path("Appearance").path("NavigationMenu").path("NavigationMenu");

			if (dataSheets.isArray()) {
				for (JsonNode learner : dataSheets) {
					JsonNode labelList = learner.path("SubMenu");

					if (labelList.isArray()) {
						for (JsonNode label : labelList) {
							JsonNode nameNode = label.path("Name");
							if (!nameNode.isMissingNode() && !nameNode.isNull()) {
								allDataSheetNames.add(nameNode.asText());
							}
						}
					}
				}
			} else {
				System.out.println("❌ DataSheets is missing or not an array.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allDataSheetNames;
	}
	
	public static List<String> getBothNavigationMenuAndSubMenu() {
//		List<String> allDataSheetNames = new ArrayList<>();
//		List<String> mainMenus = extractNavigationMenuNamesUnderApperence();
//		List<String> subMenus = extractNavigationSubMenuNamesUnderApperence();
//		allDataSheetNames.addAll(mainMenus);
//		allDataSheetNames.addAll(subMenus);
//		return allDataSheetNames;
		
		ObjectMapper mapper = new ObjectMapper();
	    String folderPath = System.getProperty("user.dir") + "/bmx_to_jsons_downloaded/3_Project.json";
	    List<String> orderedMenuNames = new ArrayList<>();

	    try {
	        JsonNode root = mapper.readTree(new File(folderPath));
	        JsonNode menuArray = root.path("Project")
	                                  .path("Appearance")
	                                  .path("NavigationMenu")
	                                  .path("NavigationMenu");

	        if (menuArray.isArray()) {
	            for (JsonNode menuItem : menuArray) {
	                // Add main menu name
	                JsonNode mainNameNode = menuItem.path("Name");
	                if (!mainNameNode.isMissingNode() && !mainNameNode.isNull()) {
	                    orderedMenuNames.add(mainNameNode.asText());
	                }

	                // Add sub-menu names if present
	                JsonNode subMenus = menuItem.path("SubMenu");
	                if (subMenus.isArray()) {
	                    for (JsonNode subItem : subMenus) {
	                        JsonNode subNameNode = subItem.path("Name");
	                        if (!subNameNode.isMissingNode() && !subNameNode.isNull()) {
	                            orderedMenuNames.add(subNameNode.asText());
	                        }
	                    }
	                }
	            }
	        } else {
	            System.out.println("❌ NavigationMenu is missing or not an array.");
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return orderedMenuNames;
	}

	public static void main(String[] args) {
		List<String> name = extractLLM_Names("Agents", "AgentName");
		for (String eachName : name) {
			System.out.println(eachName);
		}

		System.out.println("Size : " + name.size());
	}

}
