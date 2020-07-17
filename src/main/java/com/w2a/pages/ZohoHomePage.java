package com.w2a.pages;

import com.w2a.base.ZohoBasePage;

public class ZohoHomePage extends ZohoBasePage{
	
	public ZohoLoginPage goToLogin() {
		
		click("loginlink_CSS");
		
		return new ZohoLoginPage();
	}

}
