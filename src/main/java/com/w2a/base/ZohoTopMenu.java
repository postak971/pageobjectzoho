package com.w2a.base;

import org.openqa.selenium.WebDriver;
import com.w2a.pages.crm.accounts.ZohoAccountsPage;

public class ZohoTopMenu {
	
	WebDriver driver;
	public ZohoTopMenu(WebDriver driver) {
		this.driver = driver;
	}
	public ZohoAccountsPage gotoAccounts() {
		ZohoBasePage.click("accounts_CSS");
		
		//driver.findElement(By.cssSelector("div.lyteItem:nth-child(4) > a:nth-child(1)")).click();
		return new ZohoAccountsPage();
	}
	public void gotoHome() {
		
	}
	public void gotoLeads() {
				
	}
	public void gotoContacts() {
		
	}
	public void signOut() throws InterruptedException {
		ZohoBasePage.click("icon_ID");
		//driver.findElement(By.id("dropclk")).click();
		Thread.sleep(2000);
		ZohoBasePage.click("signout_XPATH");
		//driver.findElement(By.xpath("//lyte-button[2]//button[1]")).click();	
		
	}

}
