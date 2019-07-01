/**
 * 
 */
package com.moveware.system.codes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.moveware.genericfunctions.GenericFunctions;
import com.moveware.objectRepository.HomePageObjects;
import com.moveware.objectRepository.SystemCodesNonUIObjects;
import com.moveware.utilities.ReadMovewareTestExecutionProperties;
import com.moveware.utilities.UserReport;
import com.moveware.utilities.WaitHandler;

/**
 * @author Siva Ram Khandrika
 *
 */

public class ManageCodesNonUI extends GenericFunctions{
	private WebDriver driver;
	//private String browser = "firefox";
	//private String environment = "dev";
	private Map<String, String> data = new HashMap<String, String>();
	private Map<String, Map> data1=new HashMap<String, Map>();
	private int timeout = 15;
	public HomePageObjects hp;
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
		test= UserReport.createInstance(sheetName +":"+testName);
		driver = login(browser,test, environment);
		//testPassWithoutScreenshot()..first parameter is test name , second parameter is Method and Third parameter is Message
		UserReport.testPassWithScreenshot(driver,test, methodName , "Test Step Login Successfull:");
		//UserReport.report.flush();
	}
	
	@Parameters({"addCodesNumberTestCaseID","TestName"})
	@Test
	public void addCodesNumber(String testCaseID, String testName) {
		//Initiate and Access elements belongs to HomePage
		//eReport.test = eReport.report.createTest(sheetName +":" + testName + ": addCodesNumber() ");
		//test.pass("You are in addCodesNumber()");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
		UserReport.testInforation(test, "Currently executing addCodesNumber()");
		//UserReport.testPassWithoutScreenshot( test, methodName ,"Test Step is passed:  ");
		String[] testCaseIDs = testCaseID.split(",");
		ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
		
		String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
		//System.out.println("File Name is "+ testDataPath);
		data1 = GenericFunctions.readExcel(testDataPath,sheetName);
		//Initiate and Access elements in Home Page
		//After that, Select (in left side) Menu(Functionality) and SubMenu(Sub Functionality) .. Example System->Codes Non UI
		hp = HomePageObjects.initiateHomePageFactory(driver,test, data, timeout);
		hp.expandMenuItems(test);
		hp.executeFunctionalitySequence(driver,test, "System","Codes", timeout);
		
		for(int i=0; i <testCaseIDs.length; i++) {
				//Initiate and Access elements belongs to System-Codes;
			//1. Add a Code
			//2. Delete the code added above
			sc = SystemCodesNonUIObjects.initiateAddCodePageFactory(driver, data, timeout);
			//sc.executeFunctionalitySequence(driver, data1,"TC_01", timeout);
			sc.executeFunctionalitySequence(driver, test, data1,testCaseIDs[i], timeout);
			UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
			
			/*if (testCaseIDs.length>1) {
				hp.collapseMenuItems(test);
				WaitHandler.implicitWait(driver);
				try {
					Thread.sleep(5000);
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
				hp.expandMenuItems(test);
			}*/
		}
		//UserReport.testFail(driver,test,methodName, "Test Step failed: " );
		
	}
	
	@Parameters({"removeCodesNumberTestCaseID","TestName"})
	@Test//(dependsOnMethods = {"addCodesNumber"} )
	public void removeCodesNumber(String testCaseID, String testName) {
		//eReport.test = eReport.report.createTest(sheetName +":" + testName + ": removeCodesNumber() ");
		//test.pass("You are in removeCodesNumber()");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		UserReport.testPassWithoutScreenshot(test, methodName,"Test Step is passed:  ");
		//Initiate and Access elements belongs to HomePage
		String[] testCaseIDs = testCaseID.split(",");
		ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
		String sheetName = this.getClass().getSimpleName() ;
		String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
		//System.out.println("File Name is "+ testDataPath);
		data1 = GenericFunctions.readExcel(testDataPath,sheetName);
		hp = HomePageObjects.initiateHomePageFactory(driver, test, data, timeout);
		hp.expandMenuItems(test);
		hp.executeFunctionalitySequence(driver, test, "System","Codes", timeout);
		for(int i=0; i <testCaseIDs.length; i++) {
			//Initiate and Access elements belongs to System-Codes;
			//1. Add a Code
			//2. Delete the code added above
			sc = SystemCodesNonUIObjects.initiateAddCodePageFactory(driver, data, timeout);
			sc.executeFunctionalitySequenceRemove(driver, test, data1,testCaseIDs[i], timeout);
			//UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
			/*if (testCaseIDs.length>1) {
				hp.collapseMenuItems(test);
				WaitHandler.implicitWait(driver);
				hp.expandMenuItems(test);
			}*/
		}
		//UserReport.report.flush();
	}
	
	//This method is to update Type
	
	@Parameters({"updateCodesNumberTestCaseID","TestName"})
	@Test//(dependsOnMethods = {"addCodesNumber"} )
	public void updateCodesNumber(String testCaseID, String testName) {
		//eReport.test = eReport.report.createTest(sheetName +":" + testName + ": removeCodesNumber() ");
		//test.pass("You are in removeCodesNumber()");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		UserReport.testPassWithoutScreenshot(test, methodName,"Test Step is passed:  ");
		//Initiate and Access elements belongs to HomePage
		String[] testCaseIDs = testCaseID.split(",");
		ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
		String sheetName = this.getClass().getSimpleName() ;
		String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
		//System.out.println("File Name is "+ testDataPath);
		data1 = GenericFunctions.readExcel(testDataPath,sheetName);
		hp = HomePageObjects.initiateHomePageFactory(driver, test, data, timeout);
		hp.expandMenuItems(test);
		hp.executeFunctionalitySequence(driver, test, "System","Codes", timeout);
		for(int i=0; i <testCaseIDs.length; i++) {
			//Initiate and Access elements belongs to System-Codes;
			//1. Add a Code
			//2. Delete the code added above
			sc = SystemCodesNonUIObjects.initiateAddCodePageFactory(driver, data, timeout);
			sc.executeFunctionalitySequenceUpdate(driver, test, data1,testCaseIDs[i], timeout);
			//UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
			/*if (testCaseIDs.length>1) {
				hp.collapseMenuItems(test);
				WaitHandler.implicitWait(driver);
				hp.expandMenuItems(test);
			}*/
		}
		//UserReport.report.flush();
	}

	/* Add Data type
	 * 
	 * 
	 * */
	
	
	@Parameters({"addCodesDataTypeTestCaseID","TestName"})
	@Test
	public void addCodesDataType(String testCaseID, String testName) {
		//eReport.test = eReport.report.createTest(sheetName +":" + testName + ": addCodesDataType() ");
		//test.pass("You are in addCodesDataType()");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		//Initiate and Access elements belongs to HomePage
		String[] testCaseIDs = testCaseID.split(",");
		ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
		String sheetName = this.getClass().getSimpleName() ;
		String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
		//System.out.println("File Name is "+ testDataPath);
		data1 = GenericFunctions.readExcel(testDataPath,sheetName);
		hp = HomePageObjects.initiateHomePageFactory(driver,test, data, timeout);
		hp.expandMenuItems(test);
		hp.executeFunctionalitySequence(driver, test, "System","Codes", timeout);

		for(int i=0; i <testCaseIDs.length; i++) {
			//Initiate and Access elements belongs to System-Codes;
			//1. Add a Code
			//2. Delete the code added above
			sc = SystemCodesNonUIObjects.initiateAddCodePageFactory(driver, data, timeout);
			sc.executeFunctionalitySequence(driver,test, data1,testCaseIDs[i], timeout);
			UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
			/*if (testCaseIDs.length>1) {
				hp.collapseMenuItems(test);
				WaitHandler.implicitWait(driver);
				hp.expandMenuItems(test);
			}*/
			
		}
		//UserReport.report.flush();
	}
	
	//Remove Data Type
	@Parameters({"addCodesDataTypeTestCaseID","TestName"})
	@Test(dependsOnMethods = {"addCodesDataType"} )
	public void removeDataType(String testCaseID, String testName) {
       System.out.println("+++++Inside remove Method +++++++");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		UserReport.testPassWithoutScreenshot(test, methodName ,"Test Step is passed:  ");
		//Initiate and Access elements belongs to HomePage
		String[] testCaseIDs = testCaseID.split(",");
		ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
		String sheetName = this.getClass().getSimpleName() ;
		String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
		//System.out.println("File Name is "+ testDataPath);
		data1 = GenericFunctions.readExcel(testDataPath,sheetName);
		hp = HomePageObjects.initiateHomePageFactory(driver, test,data, timeout);
		hp.executeFunctionalitySequence(driver, test, "System","Codes", timeout);

		for(int i=0; i <testCaseIDs.length; i++) {
			//Initiate and Access elements belongs to System-Codes;
			//1. Add a Code
			//2. Delete the code added above
			sc = SystemCodesNonUIObjects.initiateAddCodePageFactory(driver, data, timeout);
			//sc.executeFunctionalitySequence(driver, data1,"TC_01", timeout);
			sc.executeFunctionalitySequenceRemove(driver, test,data1,testCaseIDs[i], timeout);
			//UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
			/*if (testCaseIDs.length>1) {
				hp.collapseMenuItems(test);
				WaitHandler.implicitWait(driver);
				try {
					Thread.sleep(5000);
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
				hp.expandMenuItems(test);
			}*/
		}
	}
	
	
	//Srikanth
	@Parameters({"addCodesActivityType","TestName"})
	@Test
	public void addCodesActivityType(String testCaseID, String testName) {
		//Initiate and Access elements belongs to HomePage
		//eReport.test = eReport.report.createTest(sheetName +":" + testName + ": addCodesNumber() ");
		//test.pass("You are in addCodesNumber()");
		methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
		UserReport.testInforation(test, "Currently executing addCodesNumber()");
		//UserReport.testPassWithoutScreenshot( test, methodName ,"Test Step is passed:  ");
		String[] testCaseIDs = testCaseID.split(",");
		ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
		
		String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
		//System.out.println("File Name is "+ testDataPath);
		data1 = GenericFunctions.readExcel(testDataPath,sheetName);
		//Initiate and Access elements in Home Page
		//After that, Select (in left side) Menu(Functionality) and SubMenu(Sub Functionality) .. Example System->Codes Non UI
		hp = HomePageObjects.initiateHomePageFactory(driver,test, data, timeout);
		hp.executeFunctionalitySequence(driver,test, "System","Codes", timeout);
		
		for(int i=0; i <testCaseIDs.length; i++) {
				//Initiate and Access elements belongs to System-Codes;
			//1. Add a Code
			//2. Delete the code added above
			sc = SystemCodesNonUIObjects.initiateAddCodePageFactory(driver, data, timeout);
			//sc.executeFunctionalitySequence(driver, data1,"TC_01", timeout);
			sc.executeFunctionalitySequenceAddTypeActivityType(driver, test, data1,testCaseIDs[i], timeout);
			//UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
			
			/*if (testCaseIDs.length>1) {
				hp.collapseMenuItems(test);
				WaitHandler.implicitWait(driver);
				try {
					Thread.sleep(5000);
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
				hp.expandMenuItems(test);
			}*/
		}
		//UserReport.testFail(driver,test,methodName, "Test Step failed: " );
		
	}
	
	
	//Srikanth
		@Parameters({"addCodesAddressType","TestName"})
		@Test
		public void addCodesAddressType(String testCaseID, String testName) {
			//Initiate and Access elements belongs to HomePage
			//eReport.test = eReport.report.createTest(sheetName +":" + testName + ": addCodesNumber() ");
			//test.pass("You are in addCodesNumber()");
			methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			UserReport.testInforation(test, "Currently executing addCodesNumber()");
			//UserReport.testPassWithoutScreenshot( test, methodName ,"Test Step is passed:  ");
			String[] testCaseIDs = testCaseID.split(",");
			ReadMovewareTestExecutionProperties prop = ReadMovewareTestExecutionProperties.getInstance();
			
			String testDataPath = System.getProperty("user.dir") + prop.getProperty("inputDataPath")+ sheetName +".xlsx";
			//System.out.println("File Name is "+ testDataPath);
			data1 = GenericFunctions.readExcel(testDataPath,sheetName);
			//Initiate and Access elements in Home Page
			//After that, Select (in left side) Menu(Functionality) and SubMenu(Sub Functionality) .. Example System->Codes Non UI
			hp = HomePageObjects.initiateHomePageFactory(driver,test, data, timeout);
			hp.executeFunctionalitySequence(driver,test, "System","Codes", timeout);
			
			for(int i=0; i <testCaseIDs.length; i++) {
					//Initiate and Access elements belongs to System-Codes;
				//1. Add a Code
				//2. Delete the code added above
				sc = SystemCodesNonUIObjects.initiateAddCodePageFactory(driver, data, timeout);
				//sc.executeFunctionalitySequence(driver, data1,"TC_01", timeout);
				sc.executeFunctionalitySequenceAddTypeAddressType(driver, test, data1,testCaseIDs[i], timeout);
				//UserReport.testPassWithScreenshot(driver, test, methodName ,"Test Step is passed for --> testCaseIDs[i]: ");
				
				/*if (testCaseIDs.length>1) {
					hp.collapseMenuItems(test);
					WaitHandler.implicitWait(driver);
					try {
						Thread.sleep(5000);
					} catch(InterruptedException ie) {
						ie.printStackTrace();
					}
					hp.expandMenuItems(test);
				}*/
			}
			//UserReport.testFail(driver,test,methodName, "Test Step failed: " );
			
		}
	

	@AfterMethod
	public void logOutApplication() throws IOException {
		//test.pass("You are in  "+ methodName);
		//methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//UserReport.testPassWithoutScreenshot(test,methodName,"Test Step is passed:  ");
	
		UserReport.flushReport();
		logout(driver);
	}

}
