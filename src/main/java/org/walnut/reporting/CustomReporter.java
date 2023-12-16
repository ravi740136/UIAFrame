package org.walnut.reporting;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CustomReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
       
    	// Iterate through all suites
    	System.out.println(outputDirectory);
    	File f = new File(outputDirectory+"\\customreports");
    	if (f.exists()) {
    		f.delete();	
    	}
    	f.mkdir();
    	File output = new File(f, "customreport.html");
    	PrintWriter pw=null;;
    	try {
			pw=  new PrintWriter(new BufferedWriter(new FileWriter(output)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 pw.println("<html><body>");
        for (ISuite suite : suites) {
        	 pw.println("<h1><table align='center' border='1'><th>"+suite.getName()+"</th></table></h1>");
        	// Get a map of results of a single suite
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (String testName : suiteResults.keySet()) {
                ISuiteResult suiteResult = suiteResults.get(testName);
                ITestContext testContext = suiteResult.getTestContext();
                
                // Retrieve test results and generate custom report
               
                pw.println("<table><th>Test Name: " + testContext.getName()+"</th></table>");
                pw.println("<table border='2'><tr><td>Passed tests: </td><td>" + testContext.getPassedTests().getAllResults().size()+"</td></tr>");
                pw.println("<tr><td>Failed tests: </td><td>" + testContext.getFailedTests().getAllResults().size()+"</td></tr>");
                pw.println("<tr><td>Skipped tests: </td><td>" + testContext.getSkippedTests().getAllResults().size()+"</td></tr><table>");
                // Generate your custom report here using the retrieved information
            pw.println("</body></html>");
            }
            
        }
        pw.flush();
        pw.close();
    }
}
