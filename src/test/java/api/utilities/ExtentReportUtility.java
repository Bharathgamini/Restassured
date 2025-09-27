package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportUtility implements ITestListener{
		public ExtentSparkReporter sparkReporter; //UI of the report
		public ExtentReports extent; // populate common info from the report
		public ExtentTest test; //creating test case entries in the report and update statusof the test methods
		
		String repName;
		public void onStart(ITestContext testContext) {
		    // not implemented
			// it will execute only once for the whole test suite
			
//			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS");
//			Date dt = new Date();
//			String currentdatetimestamp= df.format(dt);
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date()); // current time stam
			repName = "Test-Report-"+timeStamp + ".html";
			System.out.println("on start method");
			sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName); // specify the location of the report withn name of the report
			sparkReporter.config().setDocumentTitle("Automation Report"); // Title of the Report
			sparkReporter.config().setReportName("Functional Testing"); // Name of the report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			
			
			extent.setSystemInfo("Application", "opencart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			
			String os = testContext.getCurrentXmlTest().getParameter("OS");
			extent.setSystemInfo("Operating System", os);
			// passing the browser name from the current xml file which we are executing
			String browser = testContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);
			// getting the groups that we are used in executing the script
			List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
					if(!includedGroups.isEmpty())
					{
						extent.setSystemInfo("Groups", includedGroups.toString());
		  }
		}
					
		public void onTestSuccess(ITestResult result) {
		    // not implemented
			test= extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups()); // todisplay groups in the report
			test.log(Status.PASS, "Test case passed is" +result.getName());
		  }
		  
//		public void onTestStart(ITestResult result) {
//		    // not implemented
//			// this will execude when ever test method is getting started
//			System.out.println("ON Test Start method");
//		  }
		
		
		  
		public void onTestFailure(ITestResult result) {
			    // not implemented
			test= extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL, result.getName() +"Test case Failed");
			test.log(Status.INFO, "Test case Failed cause is" +result.getThrowable().getMessage());
			
		}
		  
		public void onTestSkipped(ITestResult result) {
			    // not implemented
			test= extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName()+"got Skipped");		
			test.log(Status.INFO, result.getThrowable().getMessage());

			  }
		
		  
		public void onFinish(ITestContext context) {
			    // not implemented
			extent.flush();
			String pathofExtentReport = (System.getProperty("user.dir")+"/reports/"+repName);
			File extentReport = new File(pathofExtentReport);
			try
			{
				Desktop.getDesktop().browse(extentReport.toURI());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			// triggering email
//			try {
//				URL url = new URL("file:///"+System.getProperty("user.dir")+"/reports/"+repName);
//			
//				// create the email message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.googleemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("bharathgamini2@gmail.com", "password"));
//			email.setSSLOnConnect(true); 
//			email.setFrom("bharathgamini2@gmail.com");// sender
//			email.setSubject("Test Results");
//			email.setMsg("Please find Attached Report");
//			email.addTo("bharathgamini@gmail.com");
//			email.attach(url, "extent Report", "Please check report");
//			email.send();
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
			
			
	
			//System.out.println("on test execution finished");

			  }

	}
		
