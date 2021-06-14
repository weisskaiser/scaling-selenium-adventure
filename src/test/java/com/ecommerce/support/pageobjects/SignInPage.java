package com.ecommerce.support.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	
	@FindBy(id = "email_create")
	private WebElement createAccountEmailAddressInput;

	@FindBy(id = "SubmitCreate")
	private WebElement createAccountButton;
	
	@FindBy(id = "email")
	private WebElement emailAddressInput;
	
	@FindBy(id = "passwd")
	private WebElement passwordInput;
	
	@FindBy(id = "SubmitLogin")
	private WebElement submitLoginButton;

	private WebDriver driver;
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void waitVisibility() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(emailAddressInput));
	}
	
	public CreateAccountForm createAccount(String email) {
		waitVisibility();
		createAccountEmailAddressInput.sendKeys(email);
		createAccountButton.click();
		return new CreateAccountForm(driver);
	}
	
	public SignInResultPage signIn(String email, String password) {
		waitVisibility();
		emailAddressInput.sendKeys(email);
		passwordInput.sendKeys(password);
		submitLoginButton.click();
		return new SignInResultPage(driver);
	}
}
