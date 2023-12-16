package org.walnut.core.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.walnut.core.WebdriverFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener implements ITestListener{

	 private static final Logger LOG = LoggerFactory.getLogger(ExtentReportListener.class);

	private ExtentSparkReporter htmlReporter;
	private ExtentReports extent;
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.pass(result.getName() + " has passed");
		if (WebdriverFactory.getDriver() != null)
		WebdriverFactory.closeDriver();
	}

	public void onTestFailure(ITestResult result) {
		 Reporter.setCurrentTestResult(result);
		// TODO Auto-generated method stub
		String filename="";
		String path="";
		try {
			filename= capturescreen();
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(filename,"failed method1").build());
			path = System.getProperty("user.dir")+"/src/test/resources/reports/"+filename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("failed image path "+path);
		Reporter.log("Attaching the screenshot of failed screen");
		Reporter.log("<br><a href='file://"+path+"'>image path</a></br>");
		Reporter.log("<br><img src='"+path+"' height='400' width='800'></br>");
		if (WebdriverFactory.getDriver() != null)
		WebdriverFactory.closeDriver();
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		htmlReporter = new ExtentSparkReporter("src/test/resources/reports/extent.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Desflow Test Report");
		htmlReporter.config().setTimeStampFormat("MMM dd,yyyy hh:mm:ss");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
	
	private String capturescreen() throws IOException {
		// TODO Auto-generated method stub
		TakesScreenshot t=(TakesScreenshot)WebdriverFactory.fetchDriver();
		File f = t.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("src/test/resources/reports/sc.png"));
		return "sc.png";
	}

}
