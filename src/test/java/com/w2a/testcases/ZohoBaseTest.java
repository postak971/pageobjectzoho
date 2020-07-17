package com.w2a.testcases;

import org.testng.annotations.AfterSuite;

import com.w2a.base.ZohoBasePage;

public class ZohoBaseTest {
	
	@AfterSuite
	public void tearDown() throws InterruptedException {
		
		ZohoBasePage.menu.signOut();		
		ZohoBasePage.quit();
		
	}

}
