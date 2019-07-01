/*
 * This class will define generic functions such as 
 * Login -- with parameters Browser Name and test environment (like Dev/Test)
 * Logout -- Logout from Application
 */

package com.moveware.genericfunctions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.moveware.utilities.ExcelReadWrite;
import com.moveware.utilities.Login;
import com.moveware.utilities.Logout;
import com.moveware.utilities.UserReport;
import com.moveware.utilities.WaitHandler;


public class GenericFunctions {
	private static WebDriver driver;
	private static ExcelReadWrite excel;
	static ExtentTest test;
	public static WebDriver login(String browser,ExtentTest test, String environment) throws IOException {
		//Login into Application
		test= test;
		Login login = new Login();
		driver = login.loginIntoApplication(browser,test,environment );
		return driver;
	}
	//Logout
	public static void logout(WebDriver driver) throws IOException {
		//Login into Application
		Logout.logOutofApplicationAndClose(driver);
	}
	
	public static void selectValueInDropDown(String xPathDropDownValues, String value) {
		  List<WebElement> listOfValues = driver.findElements(By.xpath(xPathDropDownValues));
		  if(listOfValues.size()>0) {
			  for(int i =1; i<=listOfValues.size(); i++) {
				  String tempXPath = xPathDropDownValues+"[" + i + "]/span";
				  WebElement tElement = driver.findElement(By.xpath(tempXPath));
				  if(value.equalsIgnoreCase(tElement.getText())) {
					  tElement.click();
					  WaitHandler.fluentWait(driver);
					  break;
				  }
			  }
			 
		  } else {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Not able to select value in DropDown. XPath of element is: " +xPathDropDownValues);
		  }
	}
	
	public static Map<String,Map> readExcel(String testDataPath, String sheetName){
		//System.out.println("calling GenericFunctions->readExcel() "+testDataPath);
		return excel.readExcel(testDataPath,sheetName);
		
	}
	
	//HoverAndClick
	//Author Srikanth
	public void hoverAndClick(WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).click().build().perform();
				
	}


}
