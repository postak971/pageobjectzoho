package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.base.ZohoBasePage;


public class ZohoUtilities extends ZohoBasePage{
	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";//get's dynamic screenshot name.

		FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		
		//ReportNG: use the below new File destination to copy the screenshot in the surefire-reports/html folder. 
				//new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
	}
	
	//This data provider method code block is moved from individual testcase to this centralized location. Provide the sheetname as same as the
	//test class name and that the test method has the same name in the camel case. This data provider will be used for all other tests
	//that need to fetch data from the different sheets. Make sure the sheet name matches the test class and test method name(camelcase)
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		
		String sheetName = m.getName(); //m.getName() provides the name of the method that calls this dataProvider
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		//Object[][] data = new Object[rows-1][cols];//rows-1 is because we are leaving the header row. 
		
		Object[][] data = new Object[rows-1][1]; //cols changed to 1 to use hashtable.
		Hashtable<String, String> table = null;

		for(int rowNum = 2; rowNum <= rows; rowNum++) {//rowNum = 2, because row 1 is the header row.
			table = new Hashtable<String, String>();
			
			for(int colNum = 0; colNum < cols; colNum++) {
				//data[0,0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0] = table;
			}
		}

		return data;
	}

	public static boolean isTestRunnable(String testName) {
		String sheetName="test_suite";
		int rows = excel.getRowCount(sheetName);		
		
		for(int rNum=2; rNum<=rows; rNum++){
			
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(testName)){
				
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
			
			
		}
		return false;
	}
	
	
}