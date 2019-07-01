package com.moveware.objectRepository;


import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.moveware.genericfunctions.GenericFunctions;
import com.moveware.utilities.UserReport;
import com.moveware.utilities.WaitHandler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObjects extends GenericFunctions{
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;
    private static HomePageObjects target;
    private final String pageLoadedTextSumMenu = "Codes";
    //ExtentTest test;
    
    @FindBy(how=How.XPATH,using="//body")
    @CacheLookup
    private WebElement body;
    
    @FindBy(how=How.XPATH,using = "//button[@class='left-menu-toggle nav-link sidebar-toggler js-sidebar-toggler not-a-btn']")
    @CacheLookup
    private WebElement menuButton;
    
    //@FindBy(how = How.XPATH, using = "//span[@class='menudescription ng-star-inserted' and contains(text(),'System')]")
    @FindBy(how = How.XPATH, using = "//span[@class='menudescription ng-star-inserted' and contains(text(),'System')]/parent::span/preceding::span[1]")
    @CacheLookup
    private WebElement menuSystem;
    
    @FindBy(how = How.XPATH, using = "//span[@class='menudescription ng-star-inserted' and contains(text(),'System')]/parent::span/following-sibling::i")
    @CacheLookup
    private WebElement menuSystemArrow;
    
    @FindBy(how = How.XPATH, using = "//a[@ng-reflect-router-link='menu,System,ContainerCodes']")
    @CacheLookup
    private WebElement subMenuCodes;
  
    @FindBy(how = How.XPATH, using = "//button[@class='pull-left mat-icon-button _mat-animation-noopable']")
    @CacheLookup
    private WebElement selectAddCodeMenu;
    
      @FindBy(how = How.XPATH, using = "//mat-icon[@class='mat-icon material-icons' and  contains(text(),'add')]")
    @CacheLookup
    private WebElement selectAddCode;
      
      @FindBy(how = How.XPATH, using = "//div/header/ul/li[8]/quick-view-loader/button")
      @CacheLookup
      private static WebElement selectMyProfile;
      
      @FindBy(how = How.XPATH, using = "//p-dropdown[@ng-reflect-placeholder='Select a City']")
      @CacheLookup
      private static WebElement selectThemeColour;

    // HomePage constructors
    
    public HomePageObjects() {
    }

    public HomePageObjects(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public HomePageObjects(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public HomePageObjects(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }
    
    //Sequence of steps need to be executed in the current test case
    public void executeFunctionalitySequence(WebDriver driver, ExtentTest test, String menu,String subMenu, int timeout) {
    	selectMenu(menu, test,subMenu ); 
    }
    public static HomePageObjects initiateHomePageFactory(WebDriver driver, ExtentTest test, Map<String,String> data, int timeout) {
    	target = new HomePageObjects(driver, data, timeout);
        PageFactory.initElements(driver, target);
        return target;
    }
    
    //Expand Menu items ---- Currently Not using
    public void collapseMenuItems ( ExtentTest test) {
    	//System.out.println("expandMenuItems()"+body.getAttribute("class"));
    	
    	//if(! (body.getAttribute("class").equalsIgnoreCase("sidebar-mini"))) {
    	try {
	    	if(body.getAttribute("class").equals(null)||(body.getAttribute("class").isEmpty())) {
	    		//System.out.println("expandMenuItems() condition true");
	    		WaitHandler.implicitWait(driver);
	    		WaitHandler.elementToBeClickable(driver, menuButton);
	    		menuButton.click();
	    	}
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Case Failed due to Element in Application");
		}
    }
 
     //Collapse Menu items --- Currently Not using
     public void expandMenuItems( ExtentTest test)  {
    	//System.out.println("collapseMenuItems()"+body.getAttribute("class"));
    	try {
	    	WaitHandler.implicitWait(driver);
	    	//if(body.getAttribute("class").equalsIgnoreCase("sidebar-mini")) {
	    	if(!(body.getAttribute("class").equals(null))||!(body.getAttribute("class").isEmpty())) {
	    		WaitHandler.implicitWait(driver);
	    		WaitHandler.elementToBeClickable(driver, menuButton);
	    		menuButton.click();
	     	}
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Case Failed due to Element in Application");   		
    	}
    }
     
    //Select Menu and Sub Menu options
    private void selectMenu(String menuName, ExtentTest test, String subMenu) {
    	//return target;
    	try {
	    	clickOnSystemMenu( test);
	    	waitSubMenuDisplay();
	    	clickOnCodesSubSystemMenu(test);
	    	System.out.println("+++++Clicked on codes+++++");
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Case Failed due to Element in Application");   	    	
    	}
    }
    // Click on System Menu item
    public void clickOnSystemMenu( ExtentTest test) {
    	try {
    		menuSystemArrow.click();	
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Case Failed due to Element in Application");   	   	}
    }
    
    //Click on System->Codes subSystem menu item
    
    public void clickOnCodesSubSystemMenu(ExtentTest test) {
    	try {
    		Thread.sleep(1000);
    		System.out.println("++++++++Inside clickOnCodesSubSystemMenu method++++++++++");
    		subMenuCodes.click();
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Case Failed due to Element in Application");   	    	}
      }
    
    //Page Waits
     //Wait for Code to be displayed
    private HomePageObjects waitSubMenuDisplay() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
               
            	return subMenuCodes.isDisplayed();
            }
        });
        return this;
    }
    
   
    
   
}
