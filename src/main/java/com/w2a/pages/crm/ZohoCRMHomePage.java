package com.w2a.pages.crm;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.w2a.base.ZohoBasePage;

public class ZohoCRMHomePage extends ZohoBasePage {
	
	public void verifyWelcomeText() {
		
	}
	public void goToViewSelector() {
		driver.findElement(By.xpath("//*[@id=\"HomePage_Selector\"]")).click();
		
	}
	public void goToClassicView() {
		List<WebElement> viewOptions = driver.findElements(By.xpath("//*[@id=\"HomeSelectorDD\"]/ul/li"));
		for(int i = 0; i < viewOptions.size(); i++) {
			if(viewOptions.get(i).getText().contains("Classic View")) {
				viewOptions.get(i).click();
				break;
			}
		}
	}
	public void goToHomePageActions() {
		driver.findElement(By.cssSelector("#homePageActions")).click();;
		
	}
	
	public void goToAddComponent() {
		List<WebElement> actions = driver.findElements(By.xpath("//div[@id='dashContainer']/ul/li"));
		for(int j = 0; j < actions.size(); j++) {
			if(actions.get(j).getText().contains("Add Component")) {
				actions.get(j).click();
				break;
			}
		}
	}
	
	public void goToReorder() {
		driver.findElement(By.cssSelector("div.widget_moreDD:nth-child(4) > ul:nth-child(1) > li:nth-child(2)")).click();
	}
	public void goToViewFullScreen() {
		driver.findElement(By.cssSelector("div.widget_moreDD:nth-child(4) > ul:nth-child(1) > li:nth-child(3)")).click();
	}

}
