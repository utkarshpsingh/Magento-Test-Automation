package main.java.org.magento.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


public class TestScenarioData {
		
		
	public synchronized ConcurrentHashMap<String, Object> loadTestData(String testCaseName,String filePath, String sheetName, Object exrArg) throws IOException{
	
	  ConcurrentHashMap<String, Object> map = null;
		try {
			//Create an object of File class to open xlsx file
			 File file = new File(filePath);

			    //Create an object of FileInputStream class to read excel file
			    FileInputStream inputStream = new FileInputStream(file);
			    	Workbook ObjWorkbook = null;
			    		
			    	//If it is xlsx file then create object of XSSFWorkbook class
					    ObjWorkbook = new XSSFWorkbook(inputStream);
					    	
					    	//Read sheet inside the workbook by its name
						    Sheet getSheet = ObjWorkbook.getSheet(sheetName);

	    //Create a loop over all the rows of excel file to read it
	    for (int i = 0; i <= getSheet.getLastRowNum(); i++) {
	    	
	        Row row = getSheet.getRow(i);

	        String scriptName= row.getCell(0).getStringCellValue();
	       
	        if(scriptName.equalsIgnoreCase(testCaseName)) {
	        	
	        	
	         map=new ConcurrentHashMap<String, Object>();
	        		        	
	        	for(int j=1;j<=row.getLastCellNum()-1;j++) {
	        		Cell cellValue = getSheet.getRow(i).getCell(j);
	        		CellType getCellType= cellValue.getCellType();
	        			        		
	        		if(getCellType==getCellType.STRING) {
	        			
	        			map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getStringCellValue());
	        			        			
	        		}else if(getCellType==getCellType.NUMERIC) {
	        			
	        			map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getNumericCellValue());
	        			
	        		}else if(getCellType==getCellType.FORMULA) {
	        			map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getRichStringCellValue());
	        			        			
	        		}else if(getCellType==getCellType.BOOLEAN) {
	        			map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getBooleanCellValue());
	        		
	        		}else if(getCellType==getCellType.BLANK) {
	        			map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getStringCellValue());
	        		}	
	        		
	        	}
	        	 
	        }//If statement end
	        
	       	       
	    }//first for loop End
	    ObjWorkbook.close();
	    
		} catch (Exception objException) {
			
			System.err.println(objException.getMessage());
			
		}
		
	    return map; 
  }  //FUnction END

	public synchronized ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> loadTestData(String filePath, String sheetName, Object exrArg) throws IOException{

		System.out.println("Started loading Test data to HashMap....!");
		ConcurrentHashMap<String,ConcurrentHashMap<String, Object>> parentMap=
							new ConcurrentHashMap<String,ConcurrentHashMap<String, Object>>();

		ConcurrentHashMap<String, Object> map = null;

		try {
			//Create an object of File class to open xlsx file
			File file = new File(filePath);

			//Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(file);
			Workbook ObjWorkbook = null;

			//If it is xlsx file then create object of XSSFWorkbook class
			ObjWorkbook = new XSSFWorkbook(inputStream);

			//Read sheet inside the workbook by its name
			Sheet getSheet = ObjWorkbook.getSheet(sheetName);

			//Create a loop over all the rows of excel file to read it
			for (int i = 1; i <= getSheet.getLastRowNum(); i++) {

				Row row = getSheet.getRow(i);

				String scriptName= row.getCell(0).getStringCellValue();

					map=new ConcurrentHashMap<String, Object>();

					for(int j=1;j<=row.getLastCellNum()-1;j++) {

						Cell cellValue = getSheet.getRow(i).getCell(j);
						CellType getCellType= cellValue.getCellType();

						if(getCellType==getCellType.STRING) {

							map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getStringCellValue());

						}else if(getCellType==getCellType.NUMERIC) {

							map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getNumericCellValue());

						}else if(getCellType==getCellType.FORMULA) {
							map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getRichStringCellValue());

						}else if(getCellType==getCellType.BOOLEAN) {
							map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getBooleanCellValue());

						}else if(getCellType==getCellType.BLANK) {
							map.put(getSheet.getRow(0).getCell(j).getStringCellValue(), getSheet.getRow(i).getCell(j).getStringCellValue());
						}

					}

					parentMap.put(scriptName,map);
				}//If statement end

			ObjWorkbook.close();

		} catch (Exception objException) {
			System.err.println(objException.getMessage());
		}

		return parentMap;
	}  //FUnction END



}
