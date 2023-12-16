package org.walnut.config;

public class TestConfig {
 private String browserName;
 public String getRemoteURL() {
	return remoteURL;
}
public void setRemoteURL(String remoteURL) {
	this.remoteURL = remoteURL;
}
private String remoteURL;
 public synchronized String getBrowserName() {
	return browserName;
}
public synchronized void setBrowserName(String browserName) {
	this.browserName = browserName;
}
public String getPlatformName() {
	return platformName;
}
public void setPlatformName(String platformName) {
	this.platformName = platformName;
}
public String getAppURL() {
	return appURL;
}
public void setAppURL(String appURL) {
	this.appURL = appURL;
}
public boolean isExecutionRemote() {
	return isExecutionRemote;
}
public void setExecutionRemote(boolean isExecutionRemote) {
	this.isExecutionRemote = isExecutionRemote;
}
private String platformName;
 private String appURL;
 private boolean isExecutionRemote=false;
 
}
