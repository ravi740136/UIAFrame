package org.walnut.config;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SeleniumConfigReader {
	
	private static Document doc;
	private static ThreadLocal<TestConfig> configpool = new ThreadLocal<>();
	private static  TestConfig config; 
	public synchronized static void setConfig() {
		config = new TestConfig();
        configpool.set(config);
	}
	
	private static TestConfig getConfig() { 
		return configpool.get();
	}
	
	public synchronized static TestConfig fetchConfig() {
		if (getConfig() == null) {
			setConfig();
			}
		return getConfig();
	}

	static {
		File inputFile = new File("src/test/resources/config/seleniumConfig.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
        }
        catch(Exception e) {
        	
        }
		
	}

    public static void main(String[] args) {
        try {
            File inputFile = new File("src/test/resources/config/seleniumConfig.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Read browser details
            NodeList browserList = doc.getElementsByTagName("browser");
           System.out.println("nodelist length: "+browserList.getLength());
            for (int i = 0; i < browserList.getLength(); i++) {
                Node browserNode = browserList.item(i);
                if (browserNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element browserElement = (Element) browserNode;
                    String browserName = browserElement.getAttribute("name");
                    System.out.println("Browser Name: " + browserName);
                    String driverPath = browserElement.getElementsByTagName("driverPath").item(0).getTextContent();
                    String isHeadless = browserElement.getElementsByTagName("headless").item(0).getTextContent();

                    
                    System.out.println("Driver Path: " + driverPath);
                    System.out.println("Headless Mode: " + isHeadless);
                    System.out.println();
                }
            }

            // Read environment settings
            NodeList environmentList = doc.getElementsByTagName("environment");
            for (int i = 0; i < environmentList.getLength(); i++) {
                Node envNode = environmentList.item(i);
                if (envNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element envElement = (Element) envNode;
                    String envType = envElement.getNodeName();
                   // String browser = envElement.getElementsByTagName("browser").item(0).getTextContent();
                    String url = envElement.getElementsByTagName("url").item(0).getTextContent();

                    System.out.println("Environment Type: " + envType);
                  //  System.out.println("Browser: " + browser);
                    System.out.println("URL: " + url);
                    System.out.println();
                }
            }

            // Read test data
            NodeList userList = doc.getElementsByTagName("user");
            for (int i = 0; i < userList.getLength(); i++) {
                Node userNode = userList.item(i);
                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;
                    String userId = userElement.getAttribute("id");
                    String username = userElement.getElementsByTagName("username").item(0).getTextContent();
                    String password = userElement.getElementsByTagName("password").item(0).getTextContent();

                    System.out.println("User ID: " + userId);
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        public static SeleniumConfiguration initConfig(String browserName) {
        NodeList nl =doc.getElementsByTagName("browser");
        String path="";
        for (int i=0; i< nl.getLength();i++) {
        	Element e = (Element) nl.item(i);
        	
        	if (e.getAttribute("name").equalsIgnoreCase(browserName)){
        		path = e.getElementsByTagName("driverPath").item(0).getTextContent();
        	}
        }
        int imptime=Integer.parseInt(doc.getElementsByTagName("implicit").item(0).getTextContent());
        int pltime=Integer.parseInt(doc.getElementsByTagName("pageload").item(0).getTextContent());
        String url = doc.getElementsByTagName("url").item(0).getTextContent();
        return new SeleniumConfiguration(browserName, path, url, imptime, pltime);
        }
        
        public synchronized static void initPropertiesConfig() {
        	Properties properties = new Properties();
        	
        		FileInputStream input=null;
        		
				try {
					input = new FileInputStream("src/test/resources/config/testconfig.properties");                
					properties.load(input);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
				if (properties.getProperty("execution.type").equalsIgnoreCase("remote")) {
					configpool.get().setExecutionRemote(true);
					configpool.get().setRemoteURL(properties.getProperty("remote.url"));
				}
				
				
        
        
        }
}

