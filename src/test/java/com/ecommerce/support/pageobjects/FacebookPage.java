package com.ecommerce.support.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public FacebookPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	public boolean isEmailFucused() {
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		return emailInput.equals(driver.switchTo().activeElement());
	}
	
	public void writeCredentials(String email, String password) {
		
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
	}
}
