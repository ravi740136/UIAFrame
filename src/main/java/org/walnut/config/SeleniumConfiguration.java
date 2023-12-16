package org.walnut.config;
public class SeleniumConfiguration {
    private String browser;
    private String driverPath;
    private String appUrl;
    private int implicitWait;
    private int pageLoadTimeout;

    // Constructor
    public SeleniumConfiguration(String browser, String driverPath, String appUrl, int implicitWait, int pageLoadTimeout) {
        this.browser = browser;
        this.driverPath = driverPath;
        this.appUrl = appUrl;
        this.implicitWait = implicitWait;
        this.pageLoadTimeout = pageLoadTimeout;
    }
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "browser="+browser+",driverPath="+driverPath+",url="+appUrl+
    			",implicitWait="+implicitWait+",pageLoadTimeout="+pageLoadTimeout;
    }

    // Getters and setters for configuration properties
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public int getImplicitWait() {
        return implicitWait;
    }

    public void setImplicitWait(int implicitWait) {
        this.implicitWait = implicitWait;
    }

    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }
}
