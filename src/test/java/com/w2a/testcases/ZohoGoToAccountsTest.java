package com.w2a.testcases;

import org.testng.annotations.Test;

import com.w2a.base.ZohoBasePage;
import com.w2a.pages.ZohoAppPage;

public class ZohoGoToAccountsTest {
	
	@Test
	public void GoToAccountsTest() {
		
		ZohoAppPage app = new ZohoAppPage();
		app.goToCRM();
		ZohoBasePage.menu.gotoAccounts();
		
	}

}
