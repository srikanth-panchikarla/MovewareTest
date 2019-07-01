package com.moveware.objectRepository;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Map;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.moveware.genericfunctions.GenericFunctions;
import com.moveware.utilities.UserReport;
import com.moveware.utilities.WaitHandler;
import com.moveware.system.codes.ManageCodesNonUI;

public class QuickMenuObjects extends GenericFunctions {
	  
	private Map<String, Map> data;
	private Map<String, String> rowdata;
	private WebDriver driver;
	private int timeout = 15;
	private static QuickMenuObjects target;
	private final String pageLoadedTextSumMenu = "Codes";
	private final String pageLoadedTextSumMenu1 = " Values";
	private final String pageLoadedTextSumMenu2 = " Details";
	private int elementIndex;
	private String value;
	private ExtentTest test;
	private String primaryColourGreen="#008000";
	private String primaryColourRed = "#C00000";
	private String primaryColourGrey="darkslategrey";
	private String primaryColourBlue="#1171b9";
	private String menubgcolor="#253544";
	
	//Profile
	      @FindBy(how = How.XPATH, using = "//div/header/ul/li[8]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement selectMyProfile;
	      
	      @FindBy(how = How.XPATH, using = "//p-dropdown[@ng-reflect-placeholder='Select a City']")
	      @CacheLookup
	      private static WebElement selectThemeColour;
	      
	      
	  //Search
	      @FindBy(how = How.XPATH, using = "//ul/li[1]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement quickSearch;
	  //QuickAdd
	      @FindBy(how = How.XPATH, using = "//ul/li[2]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement quickAdd;
	      
	      //print
	      @FindBy(how = How.XPATH, using = "//ul/li[3]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement quickPrint;
	      
	      //EventViewer
	      @FindBy(how = How.XPATH, using = "//ul/li[4]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement quickEventViewer;
	     
	      //Files
	      @FindBy(how = How.XPATH, using = "//ul/li[5]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement quickFiles;
	      
	      //Notification
	      @FindBy(how = How.XPATH, using = "//ul/li[6]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement quickNotification;
	      
	      //HelpandSupport
	      @FindBy(how = How.XPATH, using = "//ul/li[7]/quick-view-loader/button")
	      @CacheLookup
	      private static WebElement quickHelpSupport;
	    
	      
	      @FindBy(how = How.XPATH, using = "//span/strong[contains(text(),'Quick Add')]")
	      @CacheLookup
	      private static WebElement quickAddPageHeading;
	   
	      
	      
	    
	      
	      
	   //Constructors
	      public QuickMenuObjects() {
	      }

	      public QuickMenuObjects(WebDriver driver) {
	          this();
	          this.driver = driver;
	      }

	      public QuickMenuObjects(WebDriver driver, Map<String, String> data) {
	          this(driver);
	          this.rowdata = data;
	      }

	      public QuickMenuObjects(WebDriver driver, Map<String, String> data, int timeout) {
	          this(driver, data);
	          this.timeout = timeout;
	      }
	      
	      //Initializing the Page objects
	      public static QuickMenuObjects initiateMenuPageFactory(WebDriver driver, Map<String,String> data, int timeout) {
	      	target = new QuickMenuObjects(driver, data, timeout);
	          PageFactory.initElements(driver, target);
	          return target;
	      }
	      
	      public static void clickOnMyProfile() {
	      	
	      	selectMyProfile.click();
	      }
	      
	      public static void selectTheme()
	      {
	      	selectThemeColour.click();
	      	
	      }
	      
	      public static void clickQuickSearch()
	      {
	    	  quickSearch.click();
	    	  	  
	      }
	      
	      
	      
	public void validateThemeColorChanged(WebDriver driver, ExtentTest test, Map<String, Map> data, String TestCaseID,
			int timeout) {
		try {
			clickOnMyProfile();
			Map<String, String> rowdata = data.get("TC_01");
			System.out.println("Column data is:" + rowdata.get("SelectTheme").toString());
			// String color = data.get("SelectTheme").toString();
			System.out.println("*******************inside validateThemeColorChanged *************************");
			String xpath = "//mw-theme/section/section/p-dropdown/div/div[3]"; // "//mw-theme/section/section/p-dropdown/div";
																				// //"//p-dropdown[@ng-reflect-placeholder='Select
																				// a City']";
			// selectDropDownValue(xpath, data.get("SelectTheme").toString());
			Thread.sleep(1000);
			WebElement colorValue = driver.findElement(By.xpath(xpath));
			colorValue.click();
			// String color = data.get("SelectTheme").toString();

			String colorSelect = "//p-dropdown/div/div[4]/div/ul/li/span[text()='" + rowdata.get("SelectTheme") + "']";
			// String colorSelect = "//p-dropdown/div/div[4]/div/ul/li/span[text()='Red']";
			WebElement select = driver.findElement(By.xpath(colorSelect));
			select.click();

			System.out.println("*******************colorSelect *************************" + select);
			Thread.sleep(1000);
			// selectTheme();
			// selectDropDownValue(2,colour);
			System.out.println("*******************Data displayed *************************"
					+ rowdata.get("SelectTheme").toString());

			WaitHandler.implicitWait(driver);
			
			verifyColour();
			
		} catch (Exception e) {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			// UserReport.testFail(driver, test, methodName, "Test Step Failed:");
		}

	}
	      
	public void verifyColour() {

		String[] xpathPagePrimaryColour = driver.findElement(By.xpath("/html")).getAttribute("style").split(";");
		String colorCode = (String) Array.get(xpathPagePrimaryColour, 0);
		String[] primarycolor = colorCode.split(":");
		String primarybghashcode = (String) Array.get(primarycolor, 1);
		System.out.println("++++++++++++++++++++++Colour background:+++++++++++++++++++++++++" + primarybghashcode);
//		if(primarybghashcode.equals(primaryColourGreen))
//		{
//			Assert.assertEquals(primarybghashcode, primaryColourGreen);
//			//Assert.assertEquals(xpathPrimaryColourPage, primaryColourGreen);
//		}

		if (primarybghashcode.equals(primaryColourGreen) || primarybghashcode.equals(primaryColourRed)
				|| primarybghashcode.equals(primaryColourGrey) || primarybghashcode.equals(primaryColourBlue)) {
			System.out.println(
					"*******************Background color changed and working as expected *************************");
			Assert.assertTrue(true);
		} else {
			Assert.fail("BG does not change");
			System.out.println(
					"*******************Background color changed and working as expected *************************");
		}

	}
	
	public void verifyQuickSearch() {
		
		
		
		
	}
	
	public void clickQuickAdd()
	{
	
		quickAdd.click();
		System.out.println("Quick Add page heading"+ quickAddPageHeading.getText() );
		Assert.assertEquals(quickAddPageHeading.getText(), "Quick Add");
		//mat-list-item[@class='quick-list-item mat-list-item ng-star-inserted']/div[contains(text(),'Animal')]
		
		
	}
	      
	     
}
