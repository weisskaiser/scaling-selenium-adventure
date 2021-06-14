package com.ecommerce.support.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public ItemPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
	}
	
	@FindBy(className = "open-comment-form")
	private WebElement openReviewButton;
	
	@FindBy(xpath = "//button[contains(@class, 'btn-pinterest')]")
	private WebElement openPinterestButton;
	
	@FindBy(xpath = "//button[contains(@class, 'btn-google-plus')]")
	private WebElement openGoogleButton;
	
	@FindBy(xpath = "//button[contains(@class, 'btn-facebook')]")
	private WebElement openFacebookButton;
	
	private void waitPage() {
		wait.until(ExpectedConditions.elementToBeClickable(openPinterestButton));
	}
	
	public WriteReviewResult writeReview(String title, String comment) {
		
		waitPage();

		openReviewButton.click();
		
		WebElement titleInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("comment_title")));
		WebElement contentInput = driver.findElement(By.id("content"));
		WebElement submitReview = driver.findElement(By.id("submitNewMessage"));
		
		titleInput.sendKeys(title);
		contentInput.sendKeys(comment);
		
		submitReview.click();
		
		return new WriteReviewResult(driver);
	}

	public PinterestPage goToPinterest() throws InterruptedException {
		
		waitPage();
		
		openPinterestButton.click();
		
		return new PinterestPage(driver);
	}

	public FacebookPage goToFacebook() throws InterruptedException {
		
		waitPage();
		
		openFacebookButton.click();
		
		return new FacebookPage(driver);
	}
}
