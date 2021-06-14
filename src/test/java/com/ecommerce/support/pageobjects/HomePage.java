package com.ecommerce.support.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.support.utilities.SeleniumUtilities;

public class HomePage {

	@FindBy(xpath = "//a[normalize-space(text())='Sign in']")
	private WebElement signIn;
	
	@FindBy(id = "search_query_top")
	private WebElement searchInput;
	
	@FindBy(xpath = "//form[@id='searchbox']/button")
	private WebElement searchButton;
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void waitForVisibility() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(searchInput));
	}
	
	public SignInPage signIn() {
		waitForVisibility();
		signIn.click();
		return new SignInPage(driver);
	}
	
	public SearchResultPage search(String text) throws Throwable {
		waitForVisibility();
		SeleniumUtilities.tryClickWhileIntercepted(driver, searchButton);
		searchInput.click();
		searchInput.clear();
		searchInput.sendKeys(text);
		searchButton.click();
		
		return new SearchResultPage(driver);
	}
}
