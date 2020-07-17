package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.ZohoHomePage;
import com.w2a.pages.ZohoLoginPage;
import com.w2a.utilities.ZohoUtilities;

public class ZohoLoginTest extends ZohoBaseTest{

	@Test(dataProviderClass=ZohoUtilities.class, dataProvider = "dp")

	public void LoginTest(Hashtable<String,String> data) {
				//method name must match with the sheetname in the excelsheet.
		

		ZohoHomePage home = new ZohoHomePage();

		ZohoLoginPage lp = home.goToLogin();
		/*
		 * if (!data.get("runmode").equalsIgnoreCase("Y")) { throw new
		 * SkipException("Skipping the test as the run mode in testdata is set to No");
		 * }
		 */
		try {
			
			ZohoAppPage app = lp.doLogin(data.get("username"), data.get("password"));//reading data from excel sheet
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
