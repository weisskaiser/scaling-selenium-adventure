package com.ecommerce.support.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public String completeOrder() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p/a[@title='Proceed to checkout']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='processAddress']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='uniform-cgv']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='processCarrier']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Pay by bank wire']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[contains(text(),'I confirm my order')]/.."))).click();
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='box']//strong"))).getText();
	}
}
