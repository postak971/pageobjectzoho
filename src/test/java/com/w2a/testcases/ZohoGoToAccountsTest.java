package com.w2a.testcases;

import org.testng.annotations.Test;

import com.w2a.base.ZohoBasePage;
import com.w2a.pages.ZohoAppPage;

public class ZohoGoToAccountsTest {
	
	@Test
	public void GoToAccountsTest() {
		
		ZohoAppPage app = new ZohoAppPage();
		//clicks on the CRM button.
		app.goToCRM();
		//Clicks on the Accounts tab on the main menu bar.
		ZohoBasePage.menu.gotoAccounts();
		
	}

}
