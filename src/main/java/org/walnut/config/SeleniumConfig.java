package org.walnut.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SeleniumConfig {

    public static void main(String[] args) {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("src/test/resources/config/seleniumconfig.properties")) {
            properties.load(input);

            // Reading properties from the file
            String browser = properties.getProperty("browser");
            String driverPath = properties.getProperty(browser + ".driver.path");
            String appUrl = properties.getProperty("app.url");
            int implicitWait = Integer.parseInt(properties.getProperty("implicit.wait"));
            int pageLoadTimeout = Integer.parseInt(properties.getProperty("page.load.timeout"));

            System.out.println("Browser: " + browser);
            System.out.println("Driver Path: " + driverPath);
            System.out.println("Application URL: " + appUrl);
            System.out.println("Implicit Wait: " + implicitWait + " seconds");
            System.out.println("Page Load Timeout: " + pageLoadTimeout + " seconds");

            // Use these configurations in your Selenium tests
            // For example:
            // WebDriver driver = getDriver(browser, driverPath);
            // driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            // driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            // driver.get(appUrl);
            // ...

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
