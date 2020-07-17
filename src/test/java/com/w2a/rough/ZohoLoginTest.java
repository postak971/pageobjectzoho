package com.w2a.rough;

import com.w2a.pages.ZohoHomePage;
import com.w2a.pages.ZohoLoginPage;
import com.w2a.pages.crm.ZohoCRMHomePage;
import com.w2a.pages.crm.accounts.ZohoAccountsPage;
import com.w2a.pages.crm.accounts.ZohoCreateAccountPage;
import com.w2a.base.ZohoBasePage;
import com.w2a.pages.ZohoAppPage;

public class ZohoLoginTest {

	public static void main(String[] args) throws InterruptedException {

		ZohoHomePage home = new ZohoHomePage();
		//home.goToLogin();
		
		ZohoLoginPage lp = home.goToLogin();

		//ZohoLoginPage login = new ZohoLoginPage();
		//login.doLogin("travelfarandhigh@gmail.com", "Americandreams@2021");
		
			ZohoAppPage app = lp.doLogin("travelfarandhigh@gmail.com", "Americandreams@2021");
		//ZohoAppPage app = new ZohoAppPage();
		ZohoCRMHomePage crm = app.goToCRM();

		//ZohoCRMHomePage crm = new ZohoCRMHomePage();
		//crm.goToHomePageActions();
		//crm.goToAddComponent();
		//Thread.sleep(3000);
		//crm.goToViewSelector();
		//crm.goToClassicView();
		
		ZohoAccountsPage account = ZohoBasePage.menu.gotoAccounts();
		
		//ZohoAccountsPage account = new ZohoAccountsPage();
		ZohoCreateAccountPage cap = account.gotoCreateAccounts();
		
		//ZohoCreateAccountPage cap = new ZohoCreateAccountPage();
		cap.createAccount("Ram");
		
		ZohoBasePage.menu.signOut();
		
		
	
		


	}

}
