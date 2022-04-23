package Helpers;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListeners implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		// If test method failed
		Throwable exception = result.getThrowable();
		ExtentReportManager.fail("Test Failed, with execution time: "
				+ (result.getEndMillis() - result.getStartMillis()) / 1000 + " seconds");
		ExtentReportManager.log(Status.FAIL, exception);
		System.out.println("[Test_Status] Test_set: " + result.getInstanceName() + ", Test_case: " + result.getName()
				+ ", Case_Result: Failed, Case_Total_Time: " + (result.getEndMillis() - result.getStartMillis())
				+ "ms");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// If test method is successful
		long testTime = result.getEndMillis() - result.getStartMillis();
		ExtentReportManager.pass("Test Passed, with execution time: " + testTime + "ms");
		System.out.println("[Test_Status] Test_set: " + result.getInstanceName() + ", Test_case: " + result.getName()
				+ ", Case_Result: Passed, Case_Total_Time: " + (result.getEndMillis() - result.getStartMillis())
				+ "ms");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// If test method is skipped
		try {
			ExtentReportManager.createTest(result.getName(), "Skipped");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[Test_Status] Test_set: " + result.getInstanceName() + ", Test_case: " + result.getName()
				+ ", Case_Result: Skipped, Case_Total_Time: " + (result.getEndMillis() - result.getStartMillis())
				+ "ms");
		ExtentReportManager
				.skip("Test Skipped, with execution time: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
	}

	@Override
	public void onFinish(ITestContext context) {
		// After the test tag in XML file is closed
		System.out.println("[Test_Status] Test_Group " + context.getName() + " Execution is done in "
				+ (context.getEndDate().getTime() - context.getStartDate().getTime()) + "ms");
		ExtentReportManager.flush();
	}
}