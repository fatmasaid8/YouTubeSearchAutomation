package Helpers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static final ThreadLocal<ExtentTest> logger = new ThreadLocal<>();
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
	static String currentTime = dtf.format(LocalDateTime.now()); 
	private static final String REPORT_NAME = "automationResults-" + currentTime + ".html";


	public static ExtentReports createInstance() throws IOException {
		ExtentSparkReporter esr = new ExtentSparkReporter(System.getProperty("user.dir") + ".\\SavedExtentReports\\" + REPORT_NAME);
        esr.config().setDocumentTitle("YouTube Automation Report");
        esr.config().setReportName("YouTube Automation Test Results - By Fatma Said");
		esr.config().setEncoding("utf-8");
		esr.config().setTimelineEnabled(true);
        esr.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST , ViewName.LOG , ViewName.CATEGORY ,ViewName.EXCEPTION}).apply();
		extent = new ExtentReports();
		extent.attachReporter(esr);
		return extent;
	}

	public static synchronized void createTest(String testName, String category) throws IOException {
		if (extent == null) {
			createInstance();
		}
		logger.set(extent.createTest(testName).assignCategory(category));
	}

	public static synchronized void createTest(String testName) throws IOException {
		if (extent == null) {
			createInstance();
		}
		logger.set(extent.createTest(testName));
	}

	public static synchronized void info(String info) {
		logger.get().info(info);
	}

	public static synchronized void pass(String info) {
		logger.get().pass(info);
	}

	public static synchronized void fail(String info) {
		logger.get().fail(info);
	}

	public static synchronized void skip(String info) {
		logger.get().skip(info);
	}

	public static synchronized void flush() {
		extent.flush();
	}
}
