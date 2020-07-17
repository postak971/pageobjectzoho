package com.w2a.pages.crm.accounts;

import com.w2a.base.ZohoBasePage;

public class ZohoCreateAccountPage extends ZohoBasePage{
	
	public void createAccount(String accountName) {
	//driver.findElement(By.id("Crm_Accounts_ACCOUNTNAME")).sendKeys(accountName);
	type("accountname_ID",accountName);
	}

}
