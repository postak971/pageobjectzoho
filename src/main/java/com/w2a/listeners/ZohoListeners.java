package com.w2a.listeners;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.w2a.base.ZohoBasePage;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.ZohoUtilities;

public class ZohoListeners extends ZohoBasePage implements ITestListener,ISuiteListener {

	static Date d = new Date();
	//static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	static String fileName = "extent_report.html";
	String messageBody;
	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();



	public void onTestStart(ITestResult result) {

		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {		
		String methodName=result.getMethod().getMethodName(); //Gets the method name of the test
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {
		//ExtentReport
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
		try {

			ZohoUtilities.captureScreenshot(); //accessing the screenshot from TestUtil class.
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ZohoUtilities.screenshotName)
							.build());	//attaching the screenshot		
		} catch (IOException e) {

		}
		
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED); //creates a lable with red background for the text.
		testReport.get().log(Status.FAIL, m);
		
		//ReportNG
	System.setProperty("org.uncommons.reportng.escape-output", "false");
	try {
		ZohoUtilities.captureScreenshot();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Reporter.log("Capturing screenshot");
	Reporter.log("<a target =\" _blank\" href = "+ZohoUtilities.screenshotName+">Screenshot</a>");
	Reporter.log("<br />");
	Reporter.log("<a target =\" _blank\" href = "+ZohoUtilities.screenshotName+"><img src ="+ZohoUtilities.screenshotName+" height =\"100\" width = \"150\"></img></a>");

	}

	public void onTestSkipped(ITestResult result) {
		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub		
	}

	public void onFinish(ISuite suite) {
		MonitoringMail mail = new MonitoringMail();		
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/PageObjectModelZoho/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(messageBody);
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from,TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
