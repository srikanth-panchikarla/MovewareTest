package com.moveware.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CreateWebDriver {
	private WebDriver driver;
	private ReadMovewareTestExecutionProperties prop;
	private String environment = "test";
	private String browserName = "chrome";

	//Create a WebDriver
	public WebDriver createDriver(String browserName, String environment) throws IOException {
		//System.out.println("In createDriver()" +browserName + "  " +environment);
		prop = ReadMovewareTestExecutionProperties.getInstance();
		if (browserName != null ) {
			this.browserName = browserName;
		}
		if (environment != null) {
			this.environment = environment;
		}
		switch (this.browserName.toLowerCase()) {
			case "internetExplorer":
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+prop.getProperty("ieDriverPath"));
				driver = new InternetExplorerDriver();
				break;
			case "chrome" :
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+prop.getProperty("chromeDriverPath"));
				driver = new ChromeDriver(); 
				break;
			case "firefox": 
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+prop.getProperty("fireFoxDriverPath"));
				driver = new FirefoxDriver();
				break;
		}
		driver=navigateToURL(this.environment);
		driver.manage().window().maximize();
		return driver;
	}
	
	/// Need to select between Development or Test Enviroment
	public WebDriver navigateToURL(String environment)  throws IOException {

		prop = ReadMovewareTestExecutionProperties.getInstance();
		switch (this.environment.toLowerCase()) {
			case "dev":
				driver.get(prop.getProperty("devURL"));
				break;
			case "test": 
				driver.get(prop.getProperty("testURL"));
				break;
		}
		return driver;
	}
}
