package main.java.org.magento.base;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class Utilites
{
		
	
	public HashMap<String, String> SplitNStoreParamsInHashMap(String strParameters)
	{
		HashMap<String, String> objHashMap = new HashMap<String, String>();
		if(strParameters != "" && strParameters != null)
		{
			String[] arrKeysNValues = strParameters.split("--");

			for(int intArrElt = 0; intArrElt < arrKeysNValues.length; intArrElt++)
			{
				if(arrKeysNValues[intArrElt] != "" && arrKeysNValues[intArrElt] != null)
				{
					String[]  arrCurrKeyNValue = arrKeysNValues[intArrElt].split("=>");
					objHashMap.put(arrCurrKeyNValue[0], arrCurrKeyNValue[1]);
				}
			}
		}
		return objHashMap;
	}

	public String readTextFileAndGetAsString(String strFilePath) throws IOException
	{
        try
        {
               BufferedReader bufferedReader = new BufferedReader(new FileReader(strFilePath));
              
               String txtFileLines = "";
               String strCurrentLine = "";
               while((strCurrentLine = bufferedReader.readLine()) != null)
               {
                     txtFileLines = txtFileLines + strCurrentLine;
               }
              
               System.out.println(txtFileLines);
               return txtFileLines;
        }
        catch(Exception e)
        {
               System.out.println("Exception while reading the file " + strFilePath + "\n" + e.toString());
               return null;
        }
	}

	public String GetExceptionNDisplay(Exception objException, boolean blnIsDisplay) throws Exception
	{
		String strException = objException.getMessage();
		if(strException != null)
		{
			String[] arrException = strException.split("\n");
			if(blnIsDisplay == true)
			{
				System.out.println("Exception occurred " + arrException[0]);
			}
			return "<font color='blue'>" + arrException[0] + "</font>";
		}
		else
		{
			return "<font color='blue'>No specific error message thrown from driver for the current step. Check error message in previous steps</font>";
		}
	}

	public void moveFilesToAnotherDirectory(String sourcePath, String destinationPath) throws Exception {

		File sourceDir = new File(sourcePath);

		File destinationDir = new File(destinationPath);

		try {
			FileUtils.copyDirectory(sourceDir, destinationDir);
				FileUtils.deleteDirectory(sourceDir);

		} catch (IOException e) {

			GetExceptionNDisplay(e,true);
		}


	}
   

	
}
