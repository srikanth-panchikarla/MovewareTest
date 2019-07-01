package com.moveware.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.moveware.objectRepository.LoginPageObjects;

/**
 * @author Siva Ram Khandrika
 *
 */
public class Login {
	private ReadMovewareTestExecutionProperties prop;
	private Map<String, String> loginCredetials = new HashMap<String, String>();
	private LoginPageObjects lpo;
	WebDriver driver;
	// Get Property file details
	public Map<String, String> getLoginCredentials() throws IOException{
		prop = ReadMovewareTestExecutionProperties.getInstance();
		loginCredetials.put("userName",prop.getProperty("userName"));
		loginCredetials.put("password",prop.getProperty("password"));
		return  loginCredetials;
	}
	
	//Login into Application
	public WebDriver loginIntoApplication(String browser, ExtentTest test,String envronment) throws IOException {
		getLoginCredentials();
		//Navigate to URL and Maximize window
		driver = new CreateWebDriver().createDriver(browser,envronment);
		//lpo = new LoginPageObjects(driver, loginCredetials, 10);
		lpo = PageFactory.initElements(driver, LoginPageObjects.class);
		
		lpo = lpo.fillLoginForm(driver, loginCredetials, 10);
		
		return driver;
	}
	
}
