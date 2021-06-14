package com.ecommerce.support.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumUtilities {

	public static void sendKeysIfInputNotNull(WebElement element, String input) {
		if(element == null) {
			throw new IllegalArgumentException("element should not be null");
		}
		
		if(input != null) {
			element.sendKeys(input);
		}
	}
	
    public static void takeScreenshot(WebDriver driver, String FileName)
            throws IOException
    {
            // Creating instance of File
            File File = ((TakesScreenshot)driver)
                            .getScreenshotAs(OutputType.FILE);
      
            FileUtils.copyFile(File, new File("test-output/screenshots/" + FileName + ".jpeg"));
    }
    
    public static void executeJsScript(WebDriver driver, String script) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
    	js.executeScript(script);
    }
    
    public static void clickUsingJavascript(WebDriver driver, WebElement element) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].click();", element);
    }
    
    public static void tryClickWhileIntercepted(WebDriver driver, WebElement element) throws InterruptedException {
    	int tries = 10;
    	do {
    		try {
    			element.click();
    			return;
    		}catch(ElementClickInterceptedException e) {
    			Thread.sleep(400);
    			tries--;
    			if(tries < 0) {
    				throw e;
    			}
    		}
    	}while(true);
    }
}
