package com.w2a.testcases;

import org.testng.annotations.Test;

import com.w2a.pages.crm.accounts.ZohoAccountsPage;

public class ZohoGoToCreateAccountTest {

	@Test
	public void goToCreateAccountTest() {
	ZohoAccountsPage account = new ZohoAccountsPage();
	account.gotoCreateAccounts();

	}

}
