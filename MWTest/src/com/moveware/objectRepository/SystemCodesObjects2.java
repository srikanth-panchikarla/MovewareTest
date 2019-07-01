package com.moveware.objectRepository;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.moveware.genericfunctions.GenericFunctions;
import com.moveware.utilities.WaitHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SystemCodesObjects2 extends GenericFunctions {
	private Map<String, String> data;
	private WebDriver driver;
	private int timeout = 15;
	private static SystemCodesObjects2 target;
	private final String pageLoadedTextSumMenu = "Codes";
	private final String pageLoadedTextSumMenu1 = " Values";
	private int elementIndex;
	private String value;
	
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
	  //Type Dropdown
	  @FindBy(how=How.XPATH,using="//app-select[1]/section/p-dropdown/div/div[3]/span")
	  @CacheLookup
	  private WebElement typeDropDown;
	  
	    
	  //Search in Type
	  @FindBy(how=How.XPATH,using = "//app-select[1]/section/p-dropdown/div/div[4]/div[1]/input")
	  @CacheLookup
	  private WebElement typeSearchinAdd;
	 
	  //List of Types
	  @FindBy(how=How.XPATH,using = "//app-select[1]/section/p-dropdown/div/div[4]/div[2]/ul/li")
	  @CacheLookup
	  private WebElement typeList;	  
	  
	  //Code
	  @FindBy(how=How.XPATH,using = "//app-input[1]/section/span[2]/input")
	  @CacheLookup
	  private WebElement code;
	  
	  //Description
	  @FindBy(how=How.XPATH,using = "//app-input[2]/section/span[2]/input")
	  @CacheLookup
	  private WebElement description;
	  
	  //Module dropdown
	  
	  @FindBy(how = How.XPATH, using = "//app-select[2]/section/p-dropdown/div/div[3]/span")
	  @CacheLookup
	  private WebElement moduleDropDown;

	  //Search in Module
	  @FindBy(how=How.XPATH,using = "//app-select[2]/section/p-dropdown/div/div[4]/div[1]/input")
	  @CacheLookup
	  private WebElement moduleSearchinAdd;
	 
	  //List of Module
	  @FindBy(how=How.XPATH,using = "//app-select[2]/section/p-dropdown/div/div[4]/div[2]/ul/li")
	  @CacheLookup
	  private WebElement moduleList;	  

	  //Sort
	  @FindBy(how=How.XPATH,using = "//app-input[3]/section/span[2]/input")
	  @CacheLookup
	  private WebElement sort;
	  
	  //Status dropdown
	  
	  @FindBy(how = How.XPATH, using = "//app-select[3]/section/p-dropdown/div/div[3]/span")
	  @CacheLookup
	  private WebElement statusDropDown;

	  //Search in Status
	  @FindBy(how=How.XPATH,using = "//app-select[3]/section/p-dropdown/div/div[4]/div[1]/input")
	  @CacheLookup
	  private WebElement statusSearchinAdd;
	 
	  //List of Status
	  @FindBy(how=How.XPATH,using = "//app-select[3]/section/p-dropdown/div/div[4]/div[2]/ul/li")
	  @CacheLookup
	  private WebElement statusList;	  

	  //Organisation dropdown
	  
	  @FindBy(how = How.XPATH, using = "//app-select[4]/section/p-dropdown/div/div[3]/span")
	  @CacheLookup
	  private WebElement organisationDropDown;

	  //Search in Organisation
	  @FindBy(how=How.XPATH,using = "//app-select[4]/section/p-dropdown/div/div[4]/div[1]/input")
	  @CacheLookup
	  private WebElement organisationSearchinAdd;
	 
	  //List of Organisation
	  @FindBy(how=How.XPATH,using = "//app-select[4]/section/p-dropdown/div/div[4]/div[2]/ul/li")
	  @CacheLookup
	  private WebElement organisationList;	  

	  //Company dropdown
	  
	  @FindBy(how = How.XPATH, using = "//app-select[5]/section/p-dropdown/div/div[3]/span")
	  @CacheLookup
	  private WebElement companyDropDown;

	  //Search in Company
	  @FindBy(how=How.XPATH,using = "//app-select[5]/section/p-dropdown/div/div[4]/div[1]/input")
	  @CacheLookup
	  private WebElement companySearchinAdd;
	 
	  //List of Company
	  @FindBy(how=How.XPATH,using = "//app-select[5]/section/p-dropdown/div/div[4]/div[2]/ul/li")
	  @CacheLookup
	  private WebElement companyList;	  

	  //Branch dropdown
	  
	  @FindBy(how = How.XPATH, using = "//app-select[6]/section/p-dropdown/div/div[3]/span")
	  @CacheLookup
	  private WebElement branchDropDown;

	  //Search in Branch
	  @FindBy(how=How.XPATH,using = "//app-select[6]/section/p-dropdown/div/div[4]/div[1]/input")
	  @CacheLookup
	  private WebElement branchSearchinAdd;
	 
	  //List of Branch
	  @FindBy(how=How.XPATH,using = "//app-select[6]/section/p-dropdown/div/div[4]/div[2]/ul/li")
	  @CacheLookup
	  private WebElement branchList;	  

	  //TypePrefix dropdown
	  
	  @FindBy(how = How.XPATH, using = "//app-select[7]/section/p-dropdown/div/div[3]/span")
	  @CacheLookup
	  private WebElement typePrefixDropDown;

	  //Search in TypePrefix
	  @FindBy(how=How.XPATH,using = "//app-select[7]/section/p-dropdown/div/div[4]/div[1]/input")
	  @CacheLookup
	  private WebElement typePrefixSearchinAdd;
	 
	  //List of TypePrefix
	  @FindBy(how=How.XPATH,using = "//app-select[7]/section/p-dropdown/div/div[4]/div[2]/ul/li")
	  @CacheLookup
	  private WebElement typePrefixList;	  


	  //Delimiter
	  @FindBy(how=How.XPATH,using = "//app-input[4]/section/span[2]/input")
	  @CacheLookup
	  private WebElement delimiter;
	  

	  //Format
	  @FindBy(how=How.XPATH,using = "//app-input[5]/section/span[2]/input")
	  @CacheLookup
	  private WebElement format;
	  

	  //Digits
	  @FindBy(how=How.XPATH,using = "//app-input[6]/section/span[2]/input")
	  @CacheLookup
	  private WebElement digits;
	  

	  //Start Number
	  @FindBy(how=How.XPATH,using = "//app-input[7]/section/span[2]/input")
	  @CacheLookup
	  private WebElement startNumber;
	  

	  //Notes
	  @FindBy(how=How.XPATH,using = "//textarea[@class='demo-full-width ng-untouched ng-pristine ng-valid']")
	  @CacheLookup
	  private WebElement notes;
	  

	  //Save Button
	  @FindBy(how=How.XPATH,using = "//button[@ng-reflect-message='Save']")
	  @CacheLookup
	  private WebElement saveButton;


	  //Delete Button
	  @FindBy(how=How.XPATH,using = "//button[@ng-reflect-message='Delete']")
	  @CacheLookup
	  private WebElement deleteButton;

	  //Cancel Button
	  @FindBy(how=How.XPATH,using = "//button[@ng-reflect-message='Delete']")
	  @CacheLookup
	  private WebElement cancelButton;
	  
	    
	  // -------------Objects related to Search criteria -------------------------------------------------  
	  @FindBy(how = How.XPATH, using = "//td[@class='search-cell font-discription ng-star-inserted'][1]/span/input")
	  @CacheLookup
	  private WebElement searchFieldType;
	
	  @FindBy(how = How.XPATH, using = "//td[@class='search-cell font-discription ng-star-inserted'][2]/span/input")
	  @CacheLookup
	  private WebElement searchFieldCode;      
	
	  @FindBy(how = How.XPATH, using = "//td[@class='search-cell font-discription ng-star-inserted'][3]/span/input")
	  @CacheLookup
	 
	  private WebElement searchFieldDescription;         
      
    // HomePage constructors
    
    public SystemCodesObjects2() {
    }

    public SystemCodesObjects2(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public SystemCodesObjects2(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public SystemCodesObjects2(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }
   
    //Sequence of steps need to be executed in the current test case
    public void executeFunctionalitySequence(WebDriver driver, Map<String,String> data, String TestCaseID, int timeout) {
     	//selectAddCodeForNonUICMenu();
     	selectAddMore();
    	selectAddCode();
    	waitPageLoadedForSubMenu();
    	//"Add;Number;SivaTest1;SivaTest1;Entities;1;Active;Yes;No;Yes;No;.;Float;2;1;Test from Automation code"
    	//"Data Type;SivaTest1;SivaTest1;Accounts;1;Active;Test from Automation code"
    	TestCaseID = "TC";
    	data.put("TC","Add;Number;SivaTest1;SivaTest1;Entities;1;Active;Yes;No;Yes;No;.;Float;2;1;Test from Automation code");
    	String otherData = data.get(TestCaseID);
		String type = otherData.split(";")[1];
		
    	try {
     		selectDropDownValue(1, type);
    		if (type.equalsIgnoreCase("Number")) {
    			enterCodesDatainOfCodeTypeNumber(otherData);
    		} else if(type.equalsIgnoreCase("Data Type")|| type.equalsIgnoreCase("Data Grid")) {
    			enterCodesDatainOfCodeTypeDataType(otherData);
    		}
     		
    	} catch(Exception ie) {
    		ie.printStackTrace();
    	}
    }
    
    public static SystemCodesObjects2 initiateAddCodePageFactory(WebDriver driver, Map<String,String> data, int timeout) {
    	target = new SystemCodesObjects2(driver, data, timeout);
        PageFactory.initElements(driver, target);
        return target;
    }
    
  /*  private void selectAddCodeForNonUICMenu() {
    	try {
    		selectAddCodeForNonUICMenu.click();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }*/
    
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

    private void save() {
    	
    }
    private void enterTextinField(WebElement element, String value) {
		  
		  element.sendKeys(value);
	}    
	 private void selectDropDownValue(int elementIndex, String value) {
		 // this.elementIndex = elementIndex;
		  //this.value = value;
		  
		  String xPathDroDown = "//app-select["+elementIndex+"]/section/p-dropdown/div/div[3]/span";
		  
		  WebElement dropDownButton = driver.findElement(By.xpath(xPathDroDown));
		  
		  clickDropDownIcon(dropDownButton);
		  //Creating and Filling search criteria
		  String xPathSearch= "//app-select["+ elementIndex+ "]/section/p-dropdown/div/div[4]/div[1]/input";
		  WebElement searchElement = driver.findElement(By.xpath(xPathSearch));
		  searchCriteria(searchElement,value);
		  // Selecting a dropdown value
		  String xPathDropDownValues = "//app-select["+elementIndex+"]/section/p-dropdown/div/div[4]/div[2]/ul/li";
		  selectValueInDropDown(xPathDropDownValues, value);
  
	  }
	 
	  private void clickDropDownIcon(WebElement element) {
		  element.click();
	  }
	  private void searchCriteria(WebElement element,String value) {
		  element.sendKeys(value);
	  }


    
    //Page Waits
    
    private SystemCodesObjects2 waitPageLoadedForSubMenu() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
               return d.getPageSource().contains(pageLoadedTextSumMenu);
   	
            }
        });
        return this;
    }
    
    private SystemCodesObjects2 waitPageLoadedForSubMenuCodeforNonUI() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
               return d.getPageSource().contains(pageLoadedTextSumMenu1);
            }
        });
        return this;
    }
    private SystemCodesObjects2 waitPageLoadedForSubMenuCodeforValue() {
    	WaitHandler.implicitWait(driver);
    	//WaitHandler.fluentWait(driver);
        driver.getPageSource().contains(pageLoadedTextSumMenu1);
        return this;
    }
    
    //Type and Screen selection
    private void enterCodesDatainOfCodeTypeNumber(String data) {
    	
    	//"Add;Number;SivaTest1;SivaTest1;Entities;1;Active;Yes;No;Yes;No;.;Float;2;1;Test from Automation code"
    	String[] tdata = data.split(";");

   		waitPageLoadedForSubMenuCodeforValue();
   		
 		enterTextinField(code, tdata[2]);
  		enterTextinField(description, tdata[3]);
  		//Module
		selectDropDownValue(2, tdata[4]);
		//Sort
		enterTextinField(sort, tdata[5]);
		//Status
  		selectDropDownValue(3, tdata[6]);
  		WaitHandler.implicitWait(driver);
		selectDropDownValue(4, tdata[7]);
		WaitHandler.implicitWait(driver);
		selectDropDownValue(5, tdata[8]);
		WaitHandler.implicitWait(driver);
		selectDropDownValue(6, tdata[9]);
		WaitHandler.implicitWait(driver);
		selectDropDownValue(7, tdata[10]);
		WaitHandler.implicitWait(driver);
		enterTextinField(delimiter, tdata[11]);
		WaitHandler.implicitWait(driver);
		enterTextinField(format, tdata[12]);
		WaitHandler.implicitWait(driver);
		enterTextinField(digits, tdata[13]);
		WaitHandler.implicitWait(driver);
		enterTextinField(startNumber, tdata[14]);
		WaitHandler.implicitWait(driver);
		enterTextinField(notes, tdata[15]);
    }
    private void enterCodesDatainOfCodeTypeDataType(String data) {
    	String[] tdata = data.split(";");
    	//"Add;Data Type;SivaTest1;SivaTest1;Accounts;1;Active;Test from Automation code"
   		waitPageLoadedForSubMenuCodeforValue();
 		enterTextinField(code, tdata[2]);
  		enterTextinField(description, tdata[3]);
  		//Module
		selectDropDownValue(2, tdata[4]);
		//Sort
		enterTextinField(sort, tdata[5]);
		//Status
  		selectDropDownValue(3, tdata[6]);
  		WaitHandler.implicitWait(driver);
		enterTextinField(notes, "Test from Automation code");
    }

}
