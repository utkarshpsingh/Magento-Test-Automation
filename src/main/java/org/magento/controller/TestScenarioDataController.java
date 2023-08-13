package main.java.org.magento.controller;



import main.java.org.magento.data.TestScenarioData;
import main.java.org.magento.objectsRepository.LoginPage;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


public class TestScenarioDataController {
	
	LoginPage login = null;
	TestScenarioData testScenarioData = null;

	public TestScenarioDataController() {
		testScenarioData = new TestScenarioData();
	}

	
	 public synchronized ConcurrentHashMap<String, Object> getDataForSheetTestData(String testCaseName) throws IOException {
		
		 String filePath =System.getProperty("user.dir")+"/Input/TestData.xlsx";
		 	String sheetName= "TestData";
	 	 
		 	ConcurrentHashMap<String,Object> data =testScenarioData.loadTestData(testCaseName, filePath, sheetName, "");
		
	 	return data; 
	 }

	public synchronized ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getDataForSheetTestData() throws IOException {

		String filePath =System.getProperty("user.dir")+"/Input/TestData.xlsx";
		String sheetName= "TestData";

		ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> data =testScenarioData.loadTestData(filePath, sheetName, "");

		return data;
	}


}
