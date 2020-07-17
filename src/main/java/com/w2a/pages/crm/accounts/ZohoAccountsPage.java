package com.w2a.pages.crm.accounts;

import org.openqa.selenium.By;

import com.w2a.base.ZohoBasePage;

public class ZohoAccountsPage extends ZohoBasePage {
	
	public ZohoCreateAccountPage gotoCreateAccounts() {
		
		click("createaccount_CSS");
		//driver.findElement(By.cssSelector(".customPlusBtn")).click();
		return new ZohoCreateAccountPage();
		
	}
	public void gotoImportAccounts() {
		
	}

}
