package com.moveware.utilities;


import java.io.File;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public final class TakeScreenshot {
	public static String getScreenshot(WebDriver driver, String screenshotOutputFolder) throws IOException {
		String screenshotOutputFile = screenshotOutputFolder+ "\\screen"+System.currentTimeMillis()+".jpg";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File SrcFile= ts.getScreenshotAs(OutputType.FILE);
		File outFile = new File(screenshotOutputFile);
		FileUtils.copyFile(SrcFile, outFile );
		//FileHandler.copy(SrcFile, outFile);
		return screenshotOutputFile;
	}
}