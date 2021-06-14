package com.ecommerce.tests;

import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.ecommerce.support.utilities.ConfigUtilities;
import com.ecommerce.support.utilities.SeleniumUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class TestBase {
	
	protected WebDriver driver;
	protected Properties properties;
	
	@BeforeClass
	public void beforeClassSetup() throws Throwable {
		WebDriverManager.firefoxdriver().setup();
		
		String workingDir = System.getProperty("user.dir");
		String propertiesPath = Paths.get(workingDir, "resources", "config.resources").toString();
		properties = ConfigUtilities.LoadProperties(propertiesPath);
	}
	
	@BeforeMethod
	public void beforeMethodSetup() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/");
	}
	
	@AfterMethod
	public void afterMethodCleanup(ITestResult result) throws Throwable {
		if(driver == null)
			return;
		
		if(result.getStatus() == ITestResult.FAILURE) {
			SeleniumUtilities.takeScreenshot(driver, "after-test-failure-screenshot-0");
			SeleniumUtilities.executeJsScript(driver, "window.scrollTo(0, 0);");
			SeleniumUtilities.takeScreenshot(driver, "after-test-failure-screenshot-1");
		}
		
		driver.quit();
	}
}
