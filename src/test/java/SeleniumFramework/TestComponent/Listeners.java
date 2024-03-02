package SeleniumFramework.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumFramework.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports reports = ExtentReporterNG.reportConfig();
	ThreadLocal<ExtentTest> reportsTest = new ThreadLocal<ExtentTest>(); // to have its own thread for particular execution
	
	//ITestListener is the interface provided by TestNg
	@Override
	public void onTestStart(ITestResult result) {
	    // not implemented
		test = reports.createTest(result.getMethod().getMethodName());
		reportsTest.set(test);//assigns unique thread id
	  }
	
	@Override
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		//test.log(Status.PASS, "success");
		reportsTest.get().log(Status.PASS, "success");
	  }
	
	@Override
	public void onTestFailure(ITestResult result) {
	    // not implemented
		//test.fail(result.getThrowable());
		reportsTest.get().fail(result.getThrowable());
		//screenshot
		//gettomg drover field part of class
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destFileLocation = null;
		try {
			destFileLocation = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();// if screenshot doesnot exist this is what gets printed
		}
		reportsTest.get().addScreenCaptureFromPath(destFileLocation, result.getMethod().getMethodName());
	  }
	
	@Override
	public void onFinish(ITestContext context) {
	    // not implemented
		reports.flush();
	  }

}
