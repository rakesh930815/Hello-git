package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext context) {
		
		String timeStamp=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";

		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/" + repName);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Server Name", "localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Tester Name","Rakesh");
		extent.setSystemInfo("OS","Windows11");
		extent.setSystemInfo("Browser Name","Firfox");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is:" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
	}

	public void onTestSkippped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPED is:" + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
