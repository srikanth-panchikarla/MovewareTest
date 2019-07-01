package com.moveware.objectRepository;


import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Siva Ram Khandrika
 *
 */
public class LoginPageObjects {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;
 
    @FindBy(how= How.ID,using = "username")
    @CacheLookup
    private WebElement usernameOrEmail;

    @FindBy(how= How.ID,using = "password")
    @CacheLookup
    private WebElement password;

 
    @FindBy(how= How.ID,using = "kc-login")
    @CacheLookup
    private WebElement logIn;

    private final String pageLoadedText = "";

    private final String pageUrl = "/auth/realms/moveware/protocol/openid-connect/auth?client_id=moveware-ui";
    /*protected final By logIn = By.id("kc-login");
    private final String pageLoadedText = "";
    private final String pageUrl = "/auth/realms/moveware/protocol/openid-connect/auth?client_id";
    protected final By password = By.id("password");
    protected final By usernameOrEmail = By.id("username");*/

    //LoginPage() constructor
    public LoginPageObjects() {
    }
    //LoginPage() constructor with Driver details
    public LoginPageObjects(WebDriver driver) {
      //  this();
        this.driver = driver;
    }
  //LoginPage() constructor with Driver, Login credential details
    public LoginPageObjects(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public LoginPageObjects(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the LoginPage class instance.
     */
    public LoginPageObjects fillLoginForm(WebDriver driver, Map<String, String> data, int timeout) {
    	
    	//System.out.println("in FillingLoginForm()");
    	this.data = data;
    	this.timeout = timeout;
    	setUsernameOrEmailTextField();
        setPasswordPasswordField();
        clickLogInButton();
        return verifyPageLoaded();
       // return verifyPageUrl();
    }
 
   /**
     * Set default value to Username Or Email Text field.
     *
     * 
     */
    private void setUsernameOrEmailTextField() {
    	try {
    		usernameOrEmail.sendKeys(data.get("userName"));
    		//driver.findElement(usernameOrEmail).sendKeys(data.get("userName"));
    	} catch(ElementNotVisibleException env) {
    		env.printStackTrace();
    	}
        
        
    }

    /**
     * Set default value to Password Password field.
     *
     *
     */
    private void setPasswordPasswordField() {
    	try {
    		password.sendKeys(data.get("password"));
    		//driver.findElement(password).sendKeys(data.get("userName"));
    	} catch (ElementNotVisibleException env) {
    		env.printStackTrace();
    	}
    	
    }


    /**
     * Click on Log In Button.
     *
     * @return the LoginPage class instance.
     */
    private LoginPageObjects clickLogInButton() {
    	try {
    		logIn.click();
    		//driver.findElement(logIn).click();
    	} catch (ElementNotVisibleException env) {
    		env.printStackTrace();
    	}
        
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the LoginPage class instance.
     */
    private LoginPageObjects verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the LoginPage class instance.
     */
    //Note: Currently not testing 
    private LoginPageObjects verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
