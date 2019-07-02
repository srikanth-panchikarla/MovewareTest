package com.moveware.objectRepository;
//import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
//commented as not able to build
import static org.testng.Assert.assertSame;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.moveware.genericfunctions.GenericFunctions;
import com.moveware.utilities.UserReport;
import com.moveware.utilities.WaitHandler;
import com.moveware.objectRepository.HomePageObjects;

import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.RefreshPage;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;

public class SystemCodesNonUIObjects extends GenericFunctions {
	private Map<String, Map> data;
	private Map<String, String> rowdata;
	private WebDriver driver;
	private int timeout = 15;
	private static SystemCodesNonUIObjects target;
	private final String pageLoadedTextSumMenu = "Codes";
	private final String pageLoadedTextSumMenu1 = " Values";
	private final String pageLoadedTextSumMenu2 = " Details";
	private int elementIndex;
	private String value;
	private ExtentTest test;
	public HomePageObjects home;
	
	  //Codes for Non-UIC menu
	  @FindBy(how = How.XPATH, using = "//a[@ng-reflect-router-link='menu,System,Codes']")
	  @CacheLookup
	  private WebElement selectAddCodeForNonUICMenu;
	  
	  @FindBy(how = How.XPATH, using = "//mat-icon[@class='mat-icon material-icons' and  contains(text(),'more_vert')]")
	  @CacheLookup
	  private WebElement selectAddMore;
	  
	  @FindBy(how = How.XPATH, using = "//mat-icon[@class='mat-icon material-icons' and  contains(text(),'add')]")
	  @CacheLookup
	  private WebElement selectAddCode;
	
	  
	//Objects related to Add and Delete
	  
	// ---------------------Objects related to Add and Delete of Type Number --------------------------------------------------------- 
	  
	  //Code
	  @FindBy(how=How.XPATH,using = "//app-input[1]/section/span[2]/input")
	  @CacheLookup
	  private WebElement code;
	  
	  //Description
	  @FindBy(how=How.XPATH,using = "//app-input[2]/section/span[2]/input")
	  @CacheLookup
	  private WebElement description;
	  
	  //Sort
	  @FindBy(how=How.XPATH,using = "//app-input[3]/section/span[2]/input")
	  @CacheLookup
	  private WebElement sort;

	  //Delimiter
	  @FindBy(how=How.XPATH,using = "//app-input[4]/section/span[2]/input")
	  @CacheLookup
	  private WebElement delimiter;
	  

	  //Format
	  @FindBy(how=How.XPATH,using = "//app-input[5]/section/span[2]/input")
	  @CacheLookup
	  private WebElement format;
	  

	  //Digits
	  @FindBy(how=How.XPATH,using = "//app-input[4]/section/span[2]/input")
	  @CacheLookup
	  private WebElement digits;
	  

	  //Start Number
	  @FindBy(how=How.XPATH,using = "//app-input[3]/section/span[2]/input")
	  @CacheLookup
	  private WebElement startNumber;
	  

	  //Notes
	  @FindBy(how=How.XPATH,using = "//textarea[@class='demo-full-width ng-untouched ng-pristine ng-valid']")
	  @CacheLookup
	  private WebElement notes;
	  
	  //Visible checkbox
	  @FindBy(how=How.XPATH,using="//span[@class='ui-chkbox-icon ui-clickable']")
	  @CacheLookup
	  private WebElement chkboxVisible;
	 
	  //Save Button
	  @FindBy(how=How.XPATH,using = "//button[@ng-reflect-message='Save']")
	  @CacheLookup
	  private WebElement saveButton;


	  //Delete Button
	  @FindBy(how=How.XPATH,using = "//button[@ng-reflect-message='Delete']")
	  @CacheLookup
	  private WebElement deleteButton;

		  //3dots in form
	  @FindBy(how=How.XPATH,using = "//app-action-handler/section/button/span/mat-icon")
	  @CacheLookup
	  private WebElement dottedIconOnForm;
	  
	  //Cancel Button
	  @FindBy(how=How.XPATH,using = "//button[@ng-reflect-message='Cancel']")
	  @CacheLookup
	  private WebElement cancelButton;
	  
	  @FindBy(how=How.XPATH,using = "//div/span[2]/ui-button/div/button")
	  @CacheLookup
	  private WebElement updateButton;
	
	    
	  // -------------Objects related to Search criteria -------------------------------------------------  
	  //@FindBy(how = How.XPATH, using = "//td[@class='search-cell font-discription ng-star-inserted'][1]/span/input")
	  @FindBy(how = How.XPATH, using = "//td[@class='search-cell font-discription ng-star-inserted'][1]/div/p-dropdown/div")
	  @CacheLookup
	  private WebElement searchFieldType;
	
	  @FindBy(how = How.XPATH, using = "//td[@class='search-cell font-discription ng-star-inserted'][2]/span/input")
	  @CacheLookup
	  private WebElement searchFieldCode;      
	
	  @FindBy(how = How.XPATH, using = "//td[@class='search-cell font-discription ng-star-inserted'][3]/span/input")
	  @CacheLookup
	 
	  private WebElement searchFieldDescription;         
      
	  
	  @FindBy(how = How.XPATH, using = "//button[@class='mat-raised-button mat-primary mat-button _mat-animation-noopable']")
	  @CacheLookup
	  private WebElement okAlert;      

	  
	  //-------------------fields available for type Address Type------------------
	
	  
	  @FindBy(how = How.XPATH, using = "//mw-picklist[1]/div/div/section/section[1]/div[2]/input")
	  @CacheLookup
	  private WebElement txtTypeToSearchFields;   
	  
	
	  
	  @FindBy(how = How.XPATH, using = "//mw-picklist[1]/div/div/section/section[1]/section/div/div/ul/div/div/li")
	  @CacheLookup
	  private WebElement listTypeFieldsValues;
	  	  //Add arrow
	  @FindBy(how = How.XPATH, using = "//mw-picklist[1]/div/div/section/section[2]/div[1]/button")
	  @CacheLookup
	  private WebElement addTypeFieldsValues;
	  
	  
	//Checkbox Required
	  @FindBy(how = How.XPATH, using = "//section/div/p-checkbox/div/div[2]/span")
	  @CacheLookup
	  private WebElement chkboxRequired;
	  
	  
	  //Pagination
	  
	  @FindBy(how = How.XPATH, using = "//pagination/div/div/span")
	  @CacheLookup
	  private WebElement totalRecords;
	
	  @FindBy(how = How.XPATH, using = "//pagination/div/p-dropdown/div/div[2]/span")
	  @CacheLookup
	  private WebElement ddlRecordsPerPage;
	  
	  @FindBy(how = How.XPATH, using = "//p-table/div/div[1]/div/div[2]/table/tbody")
	  @CacheLookup
	  private WebElement table;
	
	  @FindBy(how = How.XPATH, using = "//pagination/div/p-dropdown/div")
	  @CacheLookup
	  private WebElement noInddlPagination;
	
	  String total;
	
	  
    // HomePage constructors
    
    public SystemCodesNonUIObjects() {
    }

    public SystemCodesNonUIObjects(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public SystemCodesNonUIObjects(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.rowdata = data;
    }

    public SystemCodesNonUIObjects(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }
   
    //Sequence of steps need to be executed in the current test case
    public void executeFunctionalitySequence(WebDriver driver, ExtentTest test, Map<String,Map> data, String TestCaseID, int timeout)  {
     	//selectAddCodeForNonUICMenu();
    	this.test = test;
    	waitPageLoadedForSubMenu();
    	try {
    		Thread.sleep(1000);
         	selectAddMore();
         	Thread.sleep(1000);
        	selectAddCode();
        	waitPageLoadedForSubMenuCodeforNonUI();
        	this.data = data;
        	} catch (Exception e) {
			// TODO: handle exception
		}
    	Map<String, String> otherData = data.get(TestCaseID);
		//String type = otherData.split(";")[1];
		
    	try {
    		String type = otherData.get("Type").toString();
     		selectDropDownValue(1, type);
     		
    		if (type.equalsIgnoreCase("Number")) {
    			enterCodesDatainOfCodeTypeNumber(otherData);
     		} else if(type.equalsIgnoreCase("Data Type")|| type.equalsIgnoreCase("Data Grid")|| type.equalsIgnoreCase("Activity Type")) {
    			enterCodesDatainOfCodeTypeNumber(otherData);
    			//enterCodesDatainOfCodeTypeDataType(otherData);
    		}
     		
    	} catch(Exception ie) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
			 //closing all file connection
			  Assert.fail();
			  System.gc();
    	}
    }
 
    //Remove
	public void executeFunctionalitySequenceRemove(WebDriver driver, ExtentTest test, Map<String, Map> data,
			String TestCaseID, int timeout) {
		// selectAddCodeForNonUICMenu();
		waitPageLoadedForSubMenu();
		// waitPageLoadedForSubMenuCodeforNonUI();
		this.data = data;
		Map<String, String> otherData = data.get(TestCaseID);
		// String type = otherData.split(";")[1];

		try {
			Thread.sleep(1000);
			// String type = otherData.get("Type").toString();
			String code = otherData.get("Code").toString();
			String description = otherData.get("Description").toString();
			// selectDropDownValue(1, type);
			// selectCodeTypeSearchDropDownValue(3,description);
			// clearSearchCriteriaForCodeSelection();
			searchCriteriaForCodeSelection(code, description);
			dottedIconOnForm.click();
			// delete();

		} catch (Exception ie) {
			ie.printStackTrace();
			Assert.fail();
		}
	}
    
    //Update
	public void executeFunctionalitySequenceUpdate(WebDriver driver, ExtentTest test, Map<String, Map> data,
			String TestCaseID, int timeout) {
		// selectAddCodeForNonUICMenu();
		waitPageLoadedForSubMenu();
		// waitPageLoadedForSubMenuCodeforNonUI();
		this.data = data;
		Map<String, String> otherData = data.get(TestCaseID);
		// String type = otherData.split(";")[1];

		try {
			Thread.sleep(3000);
			// String type = otherData.get("Type").toString();
			String codedata = otherData.get("Code").toString();
			String descriptiondata = otherData.get("Description").toString();
			searchCriteriaForCodeSelection(codedata, descriptiondata);
			Thread.sleep(2000);
			String codeupdate = otherData.get("CodeUpdate").toString();
			String descriptionupdate = otherData.get("DescriptionUpdate").toString();
			enterTextinField(code, codeupdate);
			enterTextinField(description, descriptionupdate);
			hoverAndClick(updateButton);
			// Verify correct data is updated

			String rowValue = driver
					.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + descriptionupdate + "')]"))
					.getText();
			System.out.println("Value in the row::::" + rowValue);
			Assert.assertEquals(rowValue, descriptionupdate);

			// dottedIconOnForm.click();
			// delete();

		} catch (Exception ie) {
			ie.printStackTrace();
			Assert.fail();
		}
	}

    

    public static SystemCodesNonUIObjects initiateAddCodePageFactory(WebDriver driver, Map<String,String> data, int timeout) {
    	target = new SystemCodesNonUIObjects(driver, data, timeout);
        PageFactory.initElements(driver, target);
        return target;
    }
    
    private void selectAddMore() {
    	try {
    		selectAddMore.click();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
     }

    private void selectAddCode() {
    	try {
    		selectAddCode.click();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
     }

    //Save New code
    private void save() {
    	saveButton.click();
    	
    }
    
    //Delete a code
    private void delete() {
    	deleteButton.click();
     	//WaitHandler.waitForAlert(driver);
    	WaitHandler.elementToBeClickable(driver, okAlert);
    	//driver.switchTo().alert().accept();
    	okAlert.click();
    }
    
    //Delete a code
    private void cancel() {
    	cancelButton.click();
    }
 
    
    public void enterTextinField(WebElement element, String value) {
    	element.clear();
		element.sendKeys(value);
	}    
	private void selectDropDownValue(int elementIndex, String value) {
		 try {
		  String xPathDroDown = "//app-select["+elementIndex+"]/section/p-dropdown/div/label";
		  WebElement dropDownButton = driver.findElement(By.xpath(xPathDroDown));
		  WaitHandler.elementToBeClickable(driver, dropDownButton);
		  clickDropDownIcon(dropDownButton);
		  //Creating and Filling search criteria
		  String xPathSearch= "//app-select["+ elementIndex+ "]/section/p-dropdown/div/div[4]/div[1]/input";
		  WebElement searchElement = driver.findElement(By.xpath(xPathSearch));
		  searchCriteria(searchElement,value);
		  // Selecting a dropdown value
		  String xPathDropDownValues = "//app-select["+elementIndex+"]/section/p-dropdown/div/div[4]/div[2]/ul/li";
		  selectValueInDropDown(xPathDropDownValues, value);
		 } catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
		 }
  
	 }
	
	//Srikanth
	public void selectCodeTypeSearchDropDownValue(int elementIndex, String value) {
		 try {
		  String xPathDroDown = "//table/thead/tr[2]/td["+elementIndex+"]/div/p-dropdown/div/div[3]"; 
		  //app-select["+elementIndex+"]/section/p-dropdown/div/label";
		  WebElement dropDownButton = driver.findElement(By.xpath(xPathDroDown));
		  WaitHandler.elementToBeClickable(driver, dropDownButton);
		  Thread.sleep(1000);
		  clickDropDownIcon(dropDownButton);
		  //Creating and Filling search criteria
		  String xPathSearch="//table/thead/tr[2]/td["+elementIndex+"]/div/p-dropdown/div/div[4]/div[1]/input";
				  //app-select["+ elementIndex+ "]/section/p-dropdown/div/div[4]/div[1]/input";
		  WebElement searchElement = driver.findElement(By.xpath(xPathSearch));
		  Thread.sleep(1000);
		  searchCriteria(searchElement,value);
		  // Selecting a dropdown value
		  String xPathDropDownValues = "//table/thead/tr[2]/td["+elementIndex+"]/div/p-dropdown/div/div[4]/div[2]/ul/li";
				  //app-select["+elementIndex+"]/section/p-dropdown/div/div[4]/div[2]/ul/li";
		  Thread.sleep(1000);
		  selectValueInDropDown(xPathDropDownValues, value);
		 } catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
		 }
 
	 }
	
	
	public void codesSearch(WebDriver driver, ExtentTest test, Map<String,Map> data, String TestCaseID, int timeout)
	{
		try {
			Map<String,String> searchtestcaseno = data.get(TestCaseID);
			String searchValue= searchtestcaseno.get("SearchType").toString();
			Thread.sleep(1000);
			//String searchValue = search.;
			selectCodeTypeSearchDropDownValue(1,searchValue);
			//This is for header status
			////table/thead/tr[1]/th/span[contains(text(),'Status ')]
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//table/thead/tr[2]/td[1]/div/p-dropdown/div/div[3]
	 
	 private void clickDropDownIcon(WebElement element) {
		  element.click();
	 }
	 private void searchCriteria(WebElement element,String value) {
		 element.clear(); 
		 element.sendKeys(value);
	 }


    
     
    //Type and Screen selection
    private void enterCodesDatainOfCodeTypeNumber(Map data) {
    	
    	   total = totalRecords.getText();
    	System.out.println("Total in codes page--------" +total);
       String[] totalCodesbeforSplit=total.split("of");
       String totalCodes=(String) Array.get(totalCodesbeforSplit, 1);
       System.out.println("Total in codes page--------" +totalCodes);
    	//"Add;Number;SivaTest1;SivaTest1;Entities;1;Active;Yes;No;Yes;No;.;Float;2;1;Test from Automation code"
    	//String[] tdata = data.split(";");
    	try {
	   		waitPageLoadedForSubMenuCodeforValue();
	   		
	 		//enterTextinField(code, data.get("Code").toString()+"_Automation");
	  		//enterTextinField(description, data.get("Description").toString()+"_Automation");
	  		
	  		enterTextinField(code, data.get("Code").toString());
	  		enterTextinField(description, data.get("Description").toString());
	  		String description = data.get("Description").toString();
	  		
	  		//Module
	  		if((data.get("Module").toString().equalsIgnoreCase("Field Not Present"))!= true) {
	  			waitPageLoadedForSubMenuCodeforValue("Module");
			selectDropDownValue(2,  data.get("Module").toString());
	  		}
			WaitHandler.implicitWait(driver);
			//Sort
			if((data.get("Sort").toString().equalsIgnoreCase("Field Not Present"))!= true) {
	  			waitPageLoadedForSubMenuCodeforValue("Sort");
			enterTextinField(sort, data.get("Sort").toString());
			}
			WaitHandler.implicitWait(driver);
		//	Status
			if((data.get("Status").toString().equalsIgnoreCase("Field Not Present"))!= true) {
	  			waitPageLoadedForSubMenuCodeforValue("Status");
	  		selectDropDownValue(3, data.get("Status").toString());
			}
	  		WaitHandler.implicitWait(driver);
	  			
	  		//OrganizationPrefix
	  		
	  		if((data.get("OrganizationPrefix").toString().equalsIgnoreCase("Field Not Present"))!= true) {
	  			waitPageLoadedForSubMenuCodeforValue("Organization");
	  			selectDropDownValue(2, data.get("OrganizationPrefix").toString());
	  		}
			WaitHandler.implicitWait(driver);
			//CompanyPrefix
			if((data.get("CompanyPrefix").toString().equalsIgnoreCase("Field Not Present"))!= true) {
				waitPageLoadedForSubMenuCodeforValue("Company");
				selectDropDownValue(3, data.get("CompanyPrefix").toString());
			}
			
			//BranchPrefix
			if((data.get("BranchPrefix").toString().equalsIgnoreCase("Field Not Present"))!= true) {
				waitPageLoadedForSubMenuCodeforValue("Branch");
				selectDropDownValue(4, data.get("BranchPrefix").toString());
			}
	
			//TypePrefix
			if((data.get("TypePrefix").toString().equalsIgnoreCase("Field Not Present"))!= true) {
				waitPageLoadedForSubMenuCodeforValue("Type");
				selectDropDownValue(5,data.get("TypePrefix").toString());
			}
	
			
			//Delimiter
			if((data.get("Delimiter").toString().equalsIgnoreCase("Field Not Present"))!= true) {
				
				enterTextinField(delimiter, data.get("Delimiter").toString());
			}
	
			
			
			//Format
			if((data.get("Format").toString().equalsIgnoreCase("Field Not Present"))!= true) {
				enterTextinField(format,  data.get("Format").toString());
			}
	
		
			//Digits
			if((data.get("Digits").toString().equalsIgnoreCase("Field Not Present"))!= true) {
				enterTextinField(digits, data.get("Digits").toString());
			}
			
			
			//StartNumber
			if((data.get("StartNumber").toString().equalsIgnoreCase("Field Not Present"))!= true) {
				enterTextinField(startNumber, data.get("StartNumber").toString());
			}
			
			//Notes
			enterTextinField(notes,data.get("Notes").toString());
			WaitHandler.implicitWait(driver);
			
			
			
			
			
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
			  Assert.fail();

    	}
    	
    	//Commenting this method for time being
    	save();
    	driver.navigate().refresh();
    	codesTotalRecords(totalCodes);
    	/*try {
    			Thread.sleep(1000);
    			WebElement newtotal = driver.findElement(By.xpath("//pagination/div/div/span"));
    		String newtotalrecords = newtotal.getText();
    		String[] totalCodesafterSave=newtotalrecords.split("of");
            String totalCodesIncremented=(String) Array.get(totalCodesafterSave, 1);
            System.out.println("After save total codes: "+ totalCodesIncremented);
            int compare = totalCodesIncremented.compareTo(totalCodes);
            
            if(compare==1)
            {
            	System.out.println("Record is successfully saved");
            }
            else if(compare<=0)
            {
            	
            	System.out.println("Record is not saved");
            	Assert.fail("Record Not Saved");
            }
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Record Not Saved");
		}*/
    	
      

    
    }
    
    //validating count
    public void codesTotalRecords(String totalCodes)
    {
    	
    	try {
		Thread.sleep(1000);
		WebElement newtotal = driver.findElement(By.xpath("//pagination/div/div/span"));
		String newtotalrecords = newtotal.getText();
		String[] totalCodesafterSave=newtotalrecords.split("of");
        String totalCodesIncremented=(String) Array.get(totalCodesafterSave, 1);
        System.out.println("After save total codes: "+ totalCodesIncremented);
        int compare = totalCodesIncremented.compareTo(totalCodes);
        
        if(compare==1)
        {
        	System.out.println("Record is successfully saved");
        }
        else if(compare<=0)
        {
        	
        	System.out.println("Record is not saved");
        	Assert.fail("Record Not Saved");
        }
	} catch (Exception e) {
		e.printStackTrace();
		Assert.fail("Record Not Saved");
	}
    	
    }
    
    private void enterCodesDatainOfCodeTypeDataType(Map data) {
    	
    	try {
	   		waitPageLoadedForSubMenuCodeforValue();
	  		enterTextinField(code, data.get("Code").toString()+"_Automation");
	  		enterTextinField(description, data.get("Description").toString()+"_Automation");
	  		//Module
			selectDropDownValue(2,  data.get("Module").toString());
			//Sort
			enterTextinField(sort, data.get("Sort").toString());
			//Status
	  		selectDropDownValue(3, data.get("Status").toString());
	  		WaitHandler.implicitWait(driver);
	  		
			enterTextinField(notes, "Test from Automation code");
			WaitHandler.implicitWait(driver);
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
    	}
		//save();
    }
    
	private void searchCriteriaForCodeSelection (String valueforType, String valueforCode, String valueforDescription ) {
		try {
		searchFieldType.sendKeys(valueforType);
		searchFieldCode.sendKeys(valueforCode);
		searchFieldDescription.sendKeys(valueforDescription);
		searchFieldDescription.sendKeys(Keys.ENTER);
		waitPageLoadedForSubMenu();
		WaitHandler.elementToBeClickable(driver, deleteButton);
		} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
		}
	}    
	
	private void searchCriteriaForCodeSelection( String valueforCode, String valueforDescription ) {
		try {
		//searchFieldType.sendKeys(valueforType);
		searchFieldCode.sendKeys(valueforCode);
		searchFieldDescription.sendKeys(valueforDescription);
		searchFieldDescription.sendKeys(Keys.ENTER);
		waitPageLoadedForSubMenu();
		//WaitHandler.elementToBeClickable(driver, deleteButton);
		} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
		}
	}    
	
	public void searchCriteriaForCodeSelection(String valueforType) {
		try {
			searchFieldType.sendKeys(valueforType);
			searchFieldCode.sendKeys("*_Automation");
			searchFieldDescription.sendKeys("*_Automation");
			searchFieldDescription.sendKeys(Keys.ENTER);
			//WaitHandler.elementToBeClickable(driver, deleteButton);
			WaitHandler.pageLoadTimeout(driver);
		}catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");

		}
	} 
	
	private void clearSearchCriteriaForCodeSelection() {
		try {
			searchFieldType.clear();
			searchFieldCode.clear();
			searchFieldDescription.clear();
		//WaitHandler.elementToBeClickable(driver, deleteButton);
			WaitHandler.pageLoadTimeout(driver);
		} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");

		}
	}
	
	
	public void enterTypeToSearchInFieldsSection(String value) throws InterruptedException {
		String[] fieldSearch = value.split(",");
		for (int i = 0; i < value.length(); i++) {
			
			if (!(listTypeFieldsValues.getAttribute("class").toString().contains("active"))) {
				
				WebElement txtField= driver.findElement(
						By.xpath("//mw-picklist[1]/div/div/section/section[1]/div[2]/input"));
				//txtTypeToSearchFields.clear();
				//txtTypeToSearchFields.sendKeys(Keys.ENTER);
				//txtTypeToSearchFields.sendKeys(fieldSearch[i]);//
				
				//txtField.clear();
				txtField.sendKeys(Keys.ENTER);
				txtField.sendKeys(fieldSearch[i]);
				
				Thread.sleep(2000);
				
					// WaitHandler.textToBePresentInUsingStaleElement(driver,listTypeFieldsValues,fieldSearch[i]);
					WebElement ele = driver.findElement(By.xpath("//mw-picklist[1]/div/div/section/section[1]/section/div/div/ul/div/div/li"));
					ele.click();
					// hoverAndClick(listTypeFieldsValues);
					// System.out.println(listTypeFieldsValues.getAttribute("title"));
					//listTypeFieldsValues.click();
				
				Thread.sleep(1000);
				//addTypeFieldsValues.click();  
				
				driver.findElement(By.xpath("//mw-picklist[1]/div/div/section/section[2]/div[1]/button")).click();
				Thread.sleep(1000);

			}
		}

	}
	
//	public void hoverAndClick(WebElement element)
//	{
//		Actions action=new Actions(driver);
//		action.moveToElement(element).click().build().perform();
//				
//	}
	
	//Srikanth: Method to Add AcivityType
	//Sequence of steps need to be executed in the current test case
    public void executeFunctionalitySequenceAddTypeActivityType(WebDriver driver, ExtentTest test, Map<String,Map> data, String TestCaseID, int timeout) {
     	//selectAddCodeForNonUICMenu();
    	this.test = test;
    	waitPageLoadedForSubMenu();
     	selectAddMore();
    	selectAddCode();
    	waitPageLoadedForSubMenuCodeforNonUI();
    	this.data = data;
    	Map<String, String> otherData = data.get(TestCaseID);
		//String type = otherData.split(";")[1];
		
    	try {
    		String type = otherData.get("Type").toString();
     		selectDropDownValue(1, type);
     		
//    		if (type.equalsIgnoreCase("Number")) {
//    			enterCodesDatainOfCodeTypeNumber(otherData);
//     		} else if(type.equalsIgnoreCase("Data Type")|| type.equalsIgnoreCase("Data Grid")|| type.equalsIgnoreCase("Activity Type")) {
//    			enterCodesDatainOfCodeTypeNumber(otherData);
//    			//enterCodesDatainOfCodeTypeDataType(otherData);
//    		}
     		enterCodesDatainOfCodeTypeActivityType(otherData);
     		
    	} catch(Exception ie) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
    	}
    }
    
    //Srikanth: Entered Activity Type
    private void enterCodesDatainOfCodeTypeActivityType(Map data) {
    	//String[] tdata = data.split(";");
    	//"Add;Data Type;SivaTest1;SivaTest1;Accounts;1;Active;Test from Automation code"
    	try {
	   		waitPageLoadedForSubMenuCodeforValue();
	  		enterTextinField(code, data.get("Code").toString()+"_Automation");
	  		enterTextinField(description, data.get("Description").toString()+"_Automation");
	  		//Module
			selectDropDownValue(2,  data.get("Module").toString());
			//Sort
			enterTextinField(sort, data.get("Sort").toString());
			
			//Visibility
//			if (data.get("Visible").toString().equalsIgnoreCase("Yes")) {
//				selectCheckBox();
//			}
						
			//Enabled
			//selectDropDownValue(3, data.get("Enabled").toString());
	  		
		
	  		//Status
			WaitHandler.implicitWait(driver);
	  		selectDropDownValue(3, data.get("Status").toString());
	  		WaitHandler.implicitWait(driver);
	  		//Notes
			enterTextinField(notes, "Test from Automation code");
			WaitHandler.implicitWait(driver);
    	} catch(Exception e) {
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
    	}
		//save();
    }
    
    
  //Srikanth: Method to Add AddressType
  	//Sequence of steps need to be executed in the current test case
      public void executeFunctionalitySequenceAddTypeAddressType(WebDriver driver, ExtentTest test, Map<String,Map> data, String TestCaseID, int timeout) {
       	//selectAddCodeForNonUICMenu();
      	this.test = test;
      	waitPageLoadedForSubMenu();
       	selectAddMore();
      	selectAddCode();
      	waitPageLoadedForSubMenuCodeforNonUI();
      	this.data = data;
      	Map<String, String> otherData = data.get(TestCaseID);
  		//String type = otherData.split(";")[1];
  		
      	try {
      		String type = otherData.get("Type").toString();
       		selectDropDownValue(1, type);
       		enterCodesDatainOfCodeTypeAddressType(otherData);
       		
      	} catch(Exception ie) {
  			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
  			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
      	}
      }
      
      //Srikanth: Entered Address Type
      private void enterCodesDatainOfCodeTypeAddressType(Map data) {
      	//String[] tdata = data.split(";");
      	//"Add;Data Type;SivaTest1;SivaTest1;Accounts;1;Active;Test from Automation code"
      	try {
  	   		waitPageLoadedForSubMenuCodeforValue();
  	  		enterTextinField(code, data.get("Code").toString()+"_Automation");
  	  		enterTextinField(description, data.get("Description").toString()+"_Automation");
  	  		//Module
  		//	selectDropDownValue(2,  data.get("Module").toString());
  			//Sort
  			enterTextinField(sort, data.get("Sort").toString());
  			
  			//Required
  			if (data.get("Required").toString().equalsIgnoreCase("Yes")) {
  				selectCheckBox();
  			}
  		//Status
  			WaitHandler.implicitWait(driver);
  	  		selectDropDownValue(2, data.get("Status").toString());
  	  		WaitHandler.implicitWait(driver);		
  			//Enabled
  	  	
  			selectDropDownValue(3, data.get("VisibleFor").toString());
  			WaitHandler.implicitWait(driver);
  			//Collection
  			selectDropDownValue(4, data.get("Collection").toString());
  			WaitHandler.implicitWait(driver);
  		//Enabled
  			selectDropDownValue(5, data.get("Enabled").toString());
  			WaitHandler.implicitWait(driver);
  	  		
  			//UILocation
  			selectDropDownValue(6, data.get("UILocation").toString());
  			WaitHandler.implicitWait(driver);
  			//Fields
  			enterTypeToSearchInFieldsSection(data.get("Fields").toString());
  			WaitHandler.implicitWait(driver);
  			
  	  		//Notes
  			enterTextinField(notes, "Test from Automation code");
  			WaitHandler.implicitWait(driver);
      	} catch(Exception e) {
  			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName().toString();
  			  UserReport.testFail(driver, test, methodName, "Test Step Failed:");
      	}
  		//save();
      }
    
    //Srikanth
    //Selecting Checkbox in Activity Type
    private void selectCheckBox() {
    	
    	chkboxVisible.click();
    }
    
    //Validating the Codes total count matches with the mongodb codes count
    public void validateCodesCount()  
    {
    	try {
    		home = PageFactory.initElements(driver,HomePageObjects.class);
        	home.clickOnSystemMenu(test);
        	home.clickOnCodesSubSystemMenu(test);
        	Thread.sleep(1000);
        	String total = totalRecords.getText();
        	System.out.println("Total in codes page--------" +total);
        String[] totalCodes=total.split("of");
        String totalCodes1=(String) Array.get(totalCodes, 1);
        System.out.println("Total in codes page--------" +totalCodes1);
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  	
    	
    }
    
    //Srikanth
    //This is to check mongoDb connection and retrieve count of the codes
    public void mongoDbconnection()
    {
    	try {
    		System.out.println("++++++++++++++++ inside mongoDBconnection method ++++++++++++++++++++++++");
        	int port =27017;
        	String host="inhysmoveware-dev.comakeit.com";
        	String username="mwadmin";
        	String authpassword = "mwadmin";
        	String Dbname="moveware";
        	
        	 // Mongodb connection string.
           // String client_url = "mongodb://" + username + ":" + authpassword + "@" + host + ":" + port + "/" + Dbname;
            String client_url = "mongodb://mwadmin:mwadmin@inhysmoveware-dev.comakeit.com:27017/moveware";
          //  MongoClient uri = new MongoClientURI(client_url);
            java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
         // Connecting to the mongodb server using the given client uri.
            MongoClient mongo_client = new MongoClient("172.16.25.17",27017);
            
           
         // Fetching the database from the mongodb.
            MongoDatabase db = mongo_client.getDatabase(Dbname);
         // Fetching the collection from the mongodb.
            MongoCollection<Document> coll = db.getCollection("codes");
            System.out.println("Collections:"+coll.count());
            
            // Getting the iterable object 
            FindIterable<Document> iterDoc = coll.find(); 
            System.out.println("iterDoc++++++" +iterDoc);  
            int i = 1; 

            // Getting the iterator 
            Iterator it = iterDoc.iterator(); 
          
            while (it.hasNext()) {  
               System.out.println(it.next());  
            i++; 
            }
            
            //log.info("Fetching all documents from the collection");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void recordsPerPage(WebDriver driver, ExtentTest test, Map<String, Map> data, String TestCaseID,
			int timeout)
    {
    	try {
    		
    		Map<String, String> rowdata = data.get(TestCaseID);
    		System.out.println("+++++data displayed PageNumber++++++:"+rowdata.get("PageNumber"));
    		String rowsInt= rowdata.get("PageNumber").toString()    				;
    		    		System.out.println("integer value :::::"+ rowsInt);
    		    		String pageNumber= rowsInt.replaceAll("[0]+$", "").replaceAll("(\\.)", "");
    		home = PageFactory.initElements(driver,HomePageObjects.class);
        	home.clickOnSystemMenu(test);
        	home.clickOnCodesSubSystemMenu(test);
        	Thread.sleep(1000);
        	String defaultRecordsPage= noInddlPagination.getText();
        	System.out.println("recordsPage in ddl : " +defaultRecordsPage);
        	Thread.sleep(1000);
        	noInddlPagination.click();
        	//select value 20
        	//driver.findElement(By.xpath("//div[3]/div/ul/li/span[text()='"+rowdata.get("PageNumber")+"']")).click();
        	driver.findElement(By.xpath("//div[3]/div/ul/li/span[text()='"+pageNumber+"']")).click();
        	Thread.sleep(1000);
        	//selectValueInDropDown("//pagination/div/p-dropdown/div", "20");
          	
        	String updatedRecordsPage= noInddlPagination.getText();
        	System.out.println("recordsPage after selection in ddl : " +updatedRecordsPage);
        	WebElement Codestable = driver.findElement(By.xpath("//p-table/div/div[1]/div/div[2]/table/tbody"));
        	List<WebElement> tableRow= Codestable.findElements(By.xpath("//p-table/div/div[1]/div/div[2]/table/tbody/tr")); 
        	int rowCount = tableRow.size();
        	String rowCountString = Integer.toString(rowCount);
        	System.out.println("Codes Rows count : " +rowCount);
        	if (updatedRecordsPage.equals(rowCountString))
        	{
        	Assert.assertTrue(true);
        
        	}
        	else
        	{
        		Assert.fail("Does not match");
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    
    
	//Page Waits
    
    private SystemCodesNonUIObjects waitPageLoadedForSubMenu() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
               return d.getPageSource().contains(pageLoadedTextSumMenu);
   	
            }
        });
        return this;
    }
    
    private SystemCodesNonUIObjects waitPageLoadedForSubMenuCodeforNonUI() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
               return d.getPageSource().contains(pageLoadedTextSumMenu2);
            }
        });
        return this;
    }
    private SystemCodesNonUIObjects waitPageLoadedForSubMenuCodeforValue() {
    	WaitHandler.implicitWait(driver);
    	//WaitHandler.fluentWait(driver);
        driver.getPageSource().contains(pageLoadedTextSumMenu1);
        return this;
    }
    private SystemCodesNonUIObjects waitPageLoadedForSubMenuCodeforValue(String element) {
    	WaitHandler.implicitWait(driver);
    	//WaitHandler.fluentWait(driver);
        driver.getPageSource().contains(element);
        return this;
    }


}
