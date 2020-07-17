package com.w2a.pages;

import org.openqa.selenium.By;
import com.w2a.base.ZohoBasePage;
import com.w2a.pages.crm.ZohoCRMHomePage;

public class ZohoAppPage extends ZohoBasePage{
	
		
	public void goToCliq() {
		click("cliq_CSS");
		//driver.findElement(By.cssSelector("._logo-chat")).click();		
				
		
	}
	
	public ZohoCRMHomePage goToCRM() {
		//driver.findElement(By.cssSelector("._logo-crm")).click();
		click("CRM_CSS");
		return new ZohoCRMHomePage();
		
		
	}
	public void goToMail() {
		//driver.findElement(By.cssSelector("._logo-mail")).click();
		click("mail_CSS");
		
		
	}

}
