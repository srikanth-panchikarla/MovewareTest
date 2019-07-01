package com.moveware.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.moveware.genericfunctions.GenericFunctions;
import com.moveware.objectRepository.HomePageObjects;
import com.moveware.objectRepository.QuickMenuObjects;
import com.moveware.objectRepository.SystemCodesNonUIObjects;
import com.moveware.utilities.UserReport;

public class TC_CodesCount extends GenericFunctions {

	SystemCodesNonUIObjects codes;
	HomePageObjects home;
	private WebDriver driver;
	//private String browser = "firefox";
	//private String environment = "dev";
	private Map<String, String> data = new HashMap<String, String>();
	private Map<String, Map> data1=new HashMap<String, Map>();
	private int timeout = 15;
	public QuickMenuObjects qm;
	public SystemCodesNonUIObjects sc;
	public String sheetName = null ;
	public String methodName=null;
	public ExtentTest test  = null;
	
	@Parameters({ "browser","environment","TestName" })
	@BeforeMethod
	public void loginToApplication(String browser,String environment, String testName ) throws IOException {
		//System.out.println("loginToApplication()" +browser+ "" + environment);
		 sheetName = this.getClass().getSimpleName();
		 methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
		 test = UserReport.createInstance(sheetName +":"+testName);
		 driver = login(browser,test, environment);
		//testPassWithoutScreenshot()..first parameter is test name , second parameter is Method and Third parameter is Message
		UserReport.testPassWithScreenshot(driver,test, methodName , "Test Step Login Successfull:");
		//UserReport.report.flush();
	}
	
	@Test
	public void CodesCount() 
	{
		try {
			codes=PageFactory.initElements(driver, SystemCodesNonUIObjects.class);
			home=PageFactory.initElements(driver, HomePageObjects.class);
			home.expandMenuItems(test);
			codes.validateCodesCount();
			codes.mongoDbconnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
