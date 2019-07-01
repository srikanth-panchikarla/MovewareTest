package com.moveware.utilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitHandler {
	
	static WebDriverWait driverWait ;
	public static void elementToBeClickable(WebDriver driver, WebElement element) {
		driverWait= new WebDriverWait(driver, 10);
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static boolean elementToBeSelected(WebDriver driver, WebElement element) {
		driverWait= new WebDriverWait(driver, 10);
		return driverWait.until(ExpectedConditions.elementToBeSelected(element));
	}
	public static Alert waitForAlert(WebDriver driver) {
		driverWait= new WebDriverWait(driver, 20);
		return driverWait.until(ExpectedConditions.alertIsPresent());
	}
	public static void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	public static void pageLoadTimeout(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
	}
	public static void setScriptTimeout(WebDriver driver, WebElement element) {
		driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
	}	
	public static boolean textToBePresentInElement(WebDriver driver, WebElement element, String toBeCompared) {
		driverWait= new WebDriverWait(driver, 10);
		return driverWait.until(ExpectedConditions.textToBePresentInElement(element, toBeCompared));
	}
	
	@SuppressWarnings("unused")
	public static void fluentWait(WebDriver driver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				 .pollingEvery(Duration.ofSeconds(10))
		        .ignoring(Exception.class);
	}
	
	public static void textToBePresentInUsingStaleElement(WebDriver driver, WebElement element, String toBeCompared) {
		driverWait= new WebDriverWait(driver, 10);
		driverWait.ignoring(StaleElementReferenceException.class);
		driverWait.until(ExpectedConditions.stalenessOf(element));
	}
	

}
