package com.ecommerce.support.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInResultPage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//div[contains(@class, 'alert')]")
	private WebElement alert;
	
	public SignInResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean hasFailed() {
		try {			
			return alert.isDisplayed();

		}catch(NoSuchElementException e) {
			
		}

		return false;
	}
	
	public String errorMessageText() {
		if(!hasFailed()) return null;
		
		return alert.findElement(By.xpath("./ol/li")).getText();
	}
	
	public String errorMessageColor() {
		if(!hasFailed()) return null;
		
		return alert.getCssValue("background-color");
	}
	
	public MyAccountPage myAccountPage() {
		return new MyAccountPage(driver); 
	}
	
}
