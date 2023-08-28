package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listener extends Base implements ITestListener {
	WebDriver driver = null;
	ExtentTest extentTest;
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	static ThreadLocal<ExtentTest> extentThreadTest = new ThreadLocal<ExtentTest>();


	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName+" executed started");
		extentThreadTest.set(extentTest);
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		
		extentThreadTest.get().log(Status.PASS,testName+" got passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentThreadTest.get().fail(result.getThrowable());

		String testMethodName = result.getName();

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String screenshotFilePath = takeScreenshot(testMethodName, driver);
			extentThreadTest.get().addScreenCaptureFromPath(screenshotFilePath,testMethodName);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
     
		extentReport.flush();
	}

}
