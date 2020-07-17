package com.w2a.pages;

import com.w2a.base.ZohoBasePage;

public class ZohoLoginPage extends ZohoBasePage {	
	
	public ZohoAppPage doLogin(String username, String password) throws InterruptedException {
		type("email_CSS", username);
		click("nextbtn_CSS");
		type("password_CSS", password);
		click("signinbtn_CSS");
		return new ZohoAppPage();
	}

}
