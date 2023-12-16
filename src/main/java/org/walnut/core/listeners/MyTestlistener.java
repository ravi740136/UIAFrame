package org.walnut.core.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.IResultListener;
import org.walnut.config.SeleniumConfigReader;
import org.walnut.config.SeleniumConfiguration;
import org.walnut.core.WebdriverFactory;

public class MyTestlistener extends TestListenerAdapter {

	private Logger logger = LoggerFactory.getLogger(org.walnut.core.listeners.MyTestlistener.class);

	@Override
	public void onStart(ITestContext context) {
		logger.info("curet testng xml: "+System.getProperty("suiteXmlFile"));
		// TODO Auto-generated method stub
		String browser = context.getCurrentXmlTest().getParameter("browser"); 
		logger.info("browser: "+browser);
		//SeleniumConfiguration config = SeleniumConfigReader.initConfig(browser);
		//logger.info("configuration: "+config);
		SeleniumConfigReader.fetchConfig().setBrowserName(browser);
		SeleniumConfigReader.initPropertiesConfig();
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		if (WebdriverFactory.getDriver() != null)
			WebdriverFactory.closeDriver();
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		if (WebdriverFactory.getDriver() != null)
			WebdriverFactory.closeDriver();
	}
	
	@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		if (WebdriverFactory.getDriver() != null)
			WebdriverFactory.closeDriver();
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

}
