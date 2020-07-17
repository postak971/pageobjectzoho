package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.w2a.listeners.ZohoListeners;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ZohoUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZohoBasePage {

	public static WebDriver driver; // static will stop opening a new browser after each test)
	public WebDriverWait wait;
	public static ZohoTopMenu menu;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	// public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Logger log = Logger.getLogger(ZohoBasePage.class.getName());
	public static ExcelReader excel = new ExcelReader("./src/test/resources/com/w2a/excel/testdata.xlsx");
	public static SoftAssert softAssert;
	String browser;

	public ZohoBasePage() {
		if (driver == null) {// new browser will open only if it's null

			// use the below 3 lines if you want to generate the new log for each execution.
			// Date d = new Date();
			// System.out.println(d.toString().replace(":", "_").replace(" ", "_"));
			// System.setProperty("current.date", d.toString().replace(":", "_").replace("
			// ", "_"));

			PropertyConfigurator.configure("./src/test/resources/com/w2a/properties/log4j.properties");
			// PropertyConfigurator.configure(System.getProperty("user.dir")+
			// "\\src\\test\\java\\log4j.properties");

			// load configuration file.
			log.info("Logger loaded");

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file loaded!!!");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Configuration file did not load");

			}

			// load OR properties file.
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR file loaded!!!");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			// selecting the browser supplied from the Jenkins parameterization.
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser"); // If no browser parameterized in jenkins, then default to the
															// browser

			}
			// from the config.properties file.
			config.setProperty("browser", browser);

			// load browser from the config.properties file.
			if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox launched!!!");

			} else if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				//driver = new ChromeDriver();
				
				// stop push notifications.
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("start-maximized");
				options.addArguments("disable-infobars");
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-notifications");
				// browser set up
				driver = new ChromeDriver(options);
				log.info("Chrome launched!!!");
			}

			else if (config.getProperty("browser").equals("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("IE launched!!!");

			}
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to: " + config.getProperty("testsiteurl"));

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			// implicit wait takes only numeric values where as the config file lists it as
			// string. So, we need it to parse it to the
			// integer. Integer.parseInt(config.getProperty("implicit.wait")
			wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicit.wait")));
			menu = new ZohoTopMenu(driver);
		}
	}

	public boolean isElementPresent(String locator) {
		try {
			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator)));
			} else if (locator.endsWith("_NAME")) {
				driver.findElement(By.name(OR.getProperty(locator)));
			}
			ZohoListeners.testReport.get().log(Status.INFO,
					"Checked the presence of and found the element : " + locator);
			log.info("Checked the presence of and found the element: " + locator);
			return true;

		} catch (NoSuchElementException e) {
			ZohoListeners.testReport.get().log(Status.ERROR,
					"Checked the presence of element  " + locator + " and did not find it");
			log.error("Checked the presence of element  " + locator + " and did not find it");
			return false;
		}
	}

	public static void click(String locator) {
		if (locator.endsWith("_CSS")) {
			//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(locator)))).click();
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).click();
		}
		log.info("Clicked on the element: " + locator);
		ZohoListeners.testReport.get().log(Status.INFO, "Clicking on : " + locator);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			;
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
			;
		}
		log.info("Typed in: " + locator + " the value as " + value);
		ZohoListeners.testReport.get().log(Status.INFO, "Typing in : " + locator + " entered value as " + value);
	}

	public static void quit() {
		driver.quit();
	}
	
	

	static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("_NAME")) {
			dropdown = driver.findElement(By.name(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.info("Selected from dropdown: " + locator + "the value as " + value);
		ZohoListeners.testReport.get().log(Status.INFO, "Selecting from Dropdown: " + locator + " value as " + value);
	}

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			ZohoUtilities.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + ZohoUtilities.screenshotName + "><img src="
					+ ZohoUtilities.screenshotName + " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			ZohoListeners.testReport.get().log(Status.FAIL,
					" Verification failed with exception : " + t.getMessage());
			// CustomListeners.testReport.get().log(Status.FAIL,
			// CustomListeners.testReport.get().addScreenCaptureFromPath(TestUtil.screenshotName));

		}

	}

}
