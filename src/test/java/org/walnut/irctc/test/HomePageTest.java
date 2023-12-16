package org.walnut.irctc.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import org.walnut.core.WebdriverFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class HomePageTest {
	private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);
//	private static ExtentSparkReporter htmlReporter;
//	private static ExtentReports extent;

/*	@BeforeSuite
	public static void bs() {
		htmlReporter = new ExtentSparkReporter("src/test/resources/reports/extent.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Desflow Test Report");
		htmlReporter.config().setTimeStampFormat("MMM dd,yyyy hh:mm:ss");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}*/
	
	@BeforeTest
	public void bt(XmlTest test) {
		logger.info("suite file: "+test.getName());
	}

	
	@BeforeGroups(groups="group3")
	public void bg1(ITestContext context) {
		logger.info("will run only once before "+context.getName());
	}
	
	@Test(groups = "group3")
	public void testconfiguration() {
		logger.info("testconfig");
	}
	@Test(groups = "group1")
	public void testDriver() throws InterruptedException, IOException {
	//	ExtentTest test = extent.createTest("test1");
try {
        logger.info("from test1");
		RemoteWebDriver driver = WebdriverFactory.fetchDriver();
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		Thread.sleep(5000l);
		RemoteWebDriver driver2 = WebdriverFactory.fetchDriver();
		logger.info(driver2.toString());
		Assert.assertTrue(false);
		/*test.log(Status.INFO, "end of test test1");
		test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(capturescreen(),"failed method1").build());
		test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath("sc.png","failed method2").build());
		test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath("sc.png","failed method3").build());
		test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath("sc.png","failed method4").build());*/
}
catch(Exception e) {
	Assert.fail();
	
}
	}

	private String capturescreen() throws IOException {
		// TODO Auto-generated method stub
		TakesScreenshot t=(TakesScreenshot)WebdriverFactory.fetchDriver();
		File f = t.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("src/test/resources/reports/sc.png"));
		return "sc.png";
	}

	/*@AfterMethod
	public static void as() {
		extent.flush(); // Generate and
	}*/

	@Test(groups = "group2")
	public void testDriver2() throws InterruptedException {
	//	ExtentTest test = extent.createTest("test2");
		logger.info("from test2");
		RemoteWebDriver driver = WebdriverFactory.fetchDriver();
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		Thread.sleep(5000l);
		//RemoteWebDriver driver2 = WebdriverFactory.getDriver();
		logger.info(driver.toString());
		//test.log(Status.INFO, test + "passed");
		//test.pass(" test2 passed");
		//WebdriverFactory.closeDriver();
		
	}
}
