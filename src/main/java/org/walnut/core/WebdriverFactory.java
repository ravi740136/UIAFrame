package org.walnut.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.walnut.config.SeleniumConfigReader;

public class WebdriverFactory {
	private static final Logger logger = LoggerFactory.getLogger(WebdriverFactory.class);
	private static ThreadLocal<RemoteWebDriver> driverpool = new ThreadLocal();
	private static RemoteWebDriver driver;
	private static DesiredCapabilities capab = new DesiredCapabilities();

	public static RemoteWebDriver getDriver() {
		return driverpool.get();
	}

	public synchronized static RemoteWebDriver fetchDriver() {
		if (getDriver() == null) {
			logger.info("initializing config..");
			/*
			 * Properties p = new Properties(); String servertype=""; try { p.load(new
			 * FileInputStream("src\\test\\resources\\config\\seleniumconfig.properties"));
			 * servertype= p.getProperty("server.type"); } catch (FileNotFoundException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); } catch (IOException
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 */
			logger.info("initializing driver");
			// Capabilities options;

			capab.setAcceptInsecureCerts(true);
			switch (SeleniumConfigReader.fetchConfig().getBrowserName()) {
			case "chrome": {

				ChromeOptions options = new ChromeOptions();
				// options.setImplicitWaitTimeout(Duration.ofSeconds(10));

				// following option is causing delay
				// options.setEnableDownloads(true);
				// options.addArguments("--disable-popup-blocking");
				options.addArguments("--start-maximized");
				// following has really worked
				options.addArguments("--disable-notifications");
				// options.setExperimentalOption("download.default_directory", "d:\\downloads");
				logger.info("setting chrome capabilities");
				if (SeleniumConfigReader.fetchConfig().isExecutionRemote()) {
					capab.setCapability(ChromeOptions.CAPABILITY, options);
					capab.setBrowserName("chrome");
					startRemoteWebDriver();
				} else {
					logger.info("setting up local web driver");
					System.setProperty("webdriver.chrome.driver",
							"D:\\apps\\selenium\\chromedriver\\chromedriver-win64\\chromedriver.exe");
					driver = new ChromeDriver(options);
				}
			}
				break;
			case "firefox": {
				logger.info("setting firefox capabilities");
				FirefoxOptions options = new FirefoxOptions();
				if (SeleniumConfigReader.fetchConfig().isExecutionRemote()) {
					capab.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
					capab.setBrowserName("firefox");
					startRemoteWebDriver();
				} else {
					logger.info("setting up local web driver");
					System.setProperty("webdriver.gecko.driver", "D:\\apps\\selenium\\geckodriver.exe");
					driver = new FirefoxDriver(options);
				}
			}
				break;
			default:
				logger.info("browser not supported" + SeleniumConfigReader.fetchConfig().getBrowserName());
				System.exit(0);

			}
			driverpool.set(driver);
		} else {
			logger.info("returning driver");
		}
		return getDriver();
	}

	private static void startRemoteWebDriver() {
		logger.info("setting up remote web driver");
		logger.info("starting "+capab.getBrowserName()+" browser");
		try {
			driver = new RemoteWebDriver(new URL(SeleniumConfigReader.fetchConfig().getRemoteURL() + "/wd/hub"), capab);
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * if (SeleniumConfigReader.getConfig().isExecutionRemote()) {
	 * logger.info("starting remote webdriver"); //DesiredCapabilities capabf = new
	 * DesiredCapabilities(); DesiredCapabilities capab = new DesiredCapabilities();
	 * ChromeOptions options = new ChromeOptions();
	 * //options.setImplicitWaitTimeout(Duration.ofSeconds(10));
	 * 
	 * //following option is causing delay //options.setEnableDownloads(true);
	 * options.addArguments("--disable-popup-blocking");
	 * options.addArguments("--start-maximized"); //following has really worked
	 * options.addArguments("--disable-notifications");
	 * options.setExperimentalOption("download.default_directory", "d:\\downloads");
	 * capab.setCapability(ChromeOptions.CAPABILITY, options);
	 * capab.setBrowserName("chrome"); capabf.setBrowserName("firefox"); JSONObject
	 * jsonObject = new JSONObject(); jsonObject.put("implicit", "20000");
	 * jsonObject.put("pageLoad", "30000"); jsonObject.put("script", "20000"); //
	 * capab.setCapability(CapabilityType.TIMEOUTS, jsonObject);
	 * 
	 * //capab.setCapability(CapabilityType.BROWSER_NAME, "chrome");
	 * capab.setCapability(CapabilityType.TIMEOUTS, "{" +
	 * "			    \"implicit\": 0," + "			    \"pageLoad\": 300000," +
	 * "			    \"script\": 30000" + "			  }");
	 * capab.setAcceptInsecureCerts(true);
	 */

	public static synchronized void closeDriver() {
		try {
			Thread.sleep(5000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("ending browser session");
		getDriver().quit();
		driverpool.set(null);
	}

}
