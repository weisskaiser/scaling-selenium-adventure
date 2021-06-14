package com.ecommerce.support.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WriteReviewResult {

	private WebDriver driver;

	public WriteReviewResult(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean hasError() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 4);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert')]"))).isDisplayed();

		}catch(TimeoutException e) {
			
		}

		return false;
	}

}
