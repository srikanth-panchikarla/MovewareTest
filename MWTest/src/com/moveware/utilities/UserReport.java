package com.moveware.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class UserReport {
	static ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
	static String fileLocation = System.getProperty("user.dir")+ prop.getProperty("extentReportPath")+"\\Results"+System.currentTimeMillis()+".html";
    ExtentHtmlReporter htmlReporter;
     
	//public static ExtentHtmlReporter reporter = new ExtentHtmlReporter();
	public static ExtentReports report;
	public static ExtentTest test;
	public static String screenShotPath =null;
		
	public static  ExtentTest createInstance(String testName) {
	    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileLocation);
	    htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	    htmlReporter.config().setChartVisibilityOnOpen(true);
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    htmlReporter.config().setDocumentTitle("Test Execution Report for " +testName);
	    htmlReporter.config().setEncoding("utf-8");
	    htmlReporter.config().setReportName(testName);
	    report = new ExtentReports();
	    report.attachReporter(htmlReporter);
	    report.setReportUsesManualConfiguration(true);
	    test = report.createTest(testName);
	    return test;
	}
	//Test case Pass without Screenshot
	public static void testPassWithoutScreenshot(ExtentTest test1,String methodName, String passMessage) {
		test = test1;
		test.pass(passMessage+ "---in method: "+methodName + "()---");
	}
	//Test case Pass with Screenshot
	public static void testPassWithScreenshot(WebDriver driver,ExtentTest test1,String methodName,String passMessage ) {
		test = test1;
		try {
			screenShotPath = TakeScreenshot.getScreenshot(driver,System.getProperty("user.dir")+ prop.getProperty("screenShotsPath"));
			test.pass(passMessage+ "---in method: "+ methodName + "()---.  Please refer Screenshot : ", 
					MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, "Pass").build());
		} catch(IOException ie) {
			try {
				test.error("Exceptionis : "+ ie.getClass()+ ":  Message is :" + ie.getMessage() + "  : StackTrace is:    "+ ie.getStackTrace()[4] 
						+ " ; Full Stack Trace is : "+ ie.getStackTrace().toString(), 
						MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, "Fail").build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			try {
				test.fatal("Exceptionis : "+ e.getClass()+ ":  Message is :" + e.getMessage() + "  : StackTrace is:    "+ e.getStackTrace()[4] 
						+ " ; Full Stack Trace is : "+ e.getStackTrace().toString(), 
						MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, "Fail").build());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		}
	}
	//Test case Fail with Screenshot
	public static void testFail(WebDriver driver, ExtentTest test1,String methodName,String failMessage) {
		test = test1;
		
		try {
			
			screenShotPath = TakeScreenshot.getScreenshot(driver,System.getProperty("user.dir")+ prop.getProperty("screenShotsPath"));
			test.fail(failMessage +"---in method: " +methodName + "()---.  Please refer Screeshot ", 
					MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, "Fail").build());
		} catch(IOException ie) {
			
			try {
				test.error("Exception is : "+ ie.getClass()+ ":  Message is :" + ie.getMessage() + "  : StackTrace is:    "+ ie.getStackTrace()[4] 
						+ " ; Full Stack Trace is : "+ ie.getStackTrace().toString(), 
						MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, "Fail").build());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			try {
				test.fatal("Exception is : "+ e.getClass()+ ":  Message is :" + e.getMessage() + "  : StackTrace is:    "+ e.getStackTrace()[4] 
						+ " ; Full Stack Trace is : "+ e.getStackTrace().toString(), 
						MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, "Fail").build());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				test.fail("Failed due to: ");
				e1.printStackTrace();
			}
		}
		
	}
	//Test case Pass without Screenshot
	public static void testInforation(ExtentTest test1,String message) {
		test = test1;
		test.info(message);
	}
	//Test case Pass without Screenshot
	public static void flushReport() {
		report.flush();
	}

}