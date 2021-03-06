package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.w2a.pages.crm.accounts.ZohoCreateAccountPage;
import com.w2a.utilities.ZohoUtilities;

public class ZohoCreateAccountTest {
	
	@BeforeTest
	public void isSkip() {

	if(!ZohoUtilities.isTestRunnable("ZohoCreateAccountTest")) {

	throw new
	 SkipException("Skipping the test as the run mode in testdata is set to No");
	}

	}
	
	
	@Test(dataProviderClass = ZohoUtilities.class, dataProvider = "dp")
	public void CreateAccountTest(Hashtable<String, String>data) {
		
				
ZohoCreateAccountPage cap = new ZohoCreateAccountPage();
		
/*
 * if (!data.get("runmode").equalsIgnoreCase("Y")) { throw new
 * SkipException("Skipping the test as the run mode in testdata is set to No");
 * }
 */
//clicks on the create Account + button.
		cap.createAccount(data.get("accountname"));
		
	}

}
