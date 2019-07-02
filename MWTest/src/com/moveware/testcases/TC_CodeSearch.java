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
import com.moveware.utilities.ReadMovewareTestExecutionProperties;
import com.moveware.utilities.UserReport;

public class TC_CodeSearch extends GenericFunctions{

	SystemCodesNonUIObjects codes;
	private WebDriver driver;
	//private String browser = "firefox";
	//private String environment = "dev";
	private Map<String, String> data = new HashMap<String, String>();
	private Map<String, Map> data1=new HashMap<String, Map>();
	private int timeout = 15;
	public QuickMenuObjects qm;
	public HomePageObjects home;
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
	
	@Parameters({"searchInCodes","TestName"})
	@Test
	public void searchInCodes(String testCaseID, String testName)
	{
		
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
		UserReport.testInforation(test, "Currently executing addCodesNumber()");
		//UserReport.testPassWithoutScreenshot( test, methodName ,"Test Step is passed:  ");
		String[] testCaseIDs = testCaseID.split(",");
		ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
		
		String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
		
		data1 = GenericFunctions.readExcel(testDataPath,sheetName);
		//Initiate and Access elements in Home Page
		//After that, Select (in left side) Menu(Functionality) and SubMenu(Sub Functionality) .. Example System->Codes Non UI
		codes=PageFactory.initElements(driver, SystemCodesNonUIObjects.class);
		//qm.clickOnMyProfile();
		home = PageFactory.initElements(driver,HomePageObjects.class);
    	home.clickOnSystemMenu(test);
		//Comment
    	home.clickOnCodesSubSystemMenu(test);
				
		for(int i=0; i <testCaseIDs.length; i++) {
			try {
				codes=PageFactory.initElements(driver, SystemCodesNonUIObjects.class);
				codes.codesSearch(driver, test, data1, testCaseIDs[i], i);
				//codes.enterTypeToSearchInFieldsSection("Action Type"); 
				//codes.selectCodeTypeSearchDropDownValue(1,"Action Type");
				//codes.recordsPerPage(driver, test, data1,testCaseIDs[i], timeout);
				//qm.validateThemeColorChanged(driver, test, data1,testCaseIDs[i], timeout);
				//sc.executeFunctionalitySequence(driver, test, data1,testCaseIDs[i], timeout);
				UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
	}
}
