package com.ecommerce.support.pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.support.pageobjects.dtos.CreateAccountInformationDto;
import com.ecommerce.support.utilities.SeleniumUtilities;

public class CreateAccountForm {

	private WebDriver driver;

	public CreateAccountForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private WebElement title(String text) {
		return driver.findElement(titleBy(text));
	}
	
	private By titleBy(String text) {
		return By.xpath(String.format("//label[text()[contains(., '%s')]]", text));
	}
	
	@FindBy(id = "customer_firstname")
	private WebElement firstName;
	
	@FindBy(id = "customer_lastname")
	private WebElement lastName;
	
	@FindBy(id = "passwd")
	private WebElement password;
	
	private Select selectDobDay() {
		WebElement days = driver.findElement(By.id("days"));
		return new Select(days);
	}
	
	private Select selectDobMonth() {
		WebElement months = driver.findElement(By.id("months"));
		return new Select(months);
	}
	
	private Select selectDobYear() {
		WebElement years = driver.findElement(By.id("years"));
		return new Select(years);
	}
	
	@FindBy(id = "firstname")
	private WebElement addressFirstName;
	
	@FindBy(id = "lastname")
	private WebElement addressLastName;
	
	@FindBy(id = "company")
	private WebElement company;

	@FindBy(id = "address1")
	private WebElement addressLine1;
	
	@FindBy(id = "address2")
	private WebElement addressLine2;	
	
	@FindBy(id = "city")
	private WebElement city;
	
	private Select selectState() {
		WebElement state = driver.findElement(By.id("id_state"));
		return new Select(state);
	}
	
	@FindBy(id = "postcode")
	private WebElement postalCode;
	
	private Select selectCountry() {
		WebElement country = driver.findElement(By.id("id_country"));
		return new Select(country);
	}
	
	@FindBy(id = "phone")
	private WebElement phoneHome;
	
	@FindBy(id = "phone_mobile")
	private WebElement phoneMobile;
	
	@FindBy(id = "alias")
	private WebElement addressAlias;
	
	@FindBy(id = "submitAccount")
	private WebElement submitAccount;
	
	private void fillDob(String dob) {
		
		String sep = "-";
		
		if(!dob.contains("-")) {
			throw new InvalidArgumentException("dob should be in the format yyyy-M-d, found "+dob);
		}
		
		String[] fields = dob.split(sep);
		
		if(fields.length != 3) {
			throw new InvalidArgumentException("dob should be in the format yyyy-M-d, found size "+ fields.length + " and value "+dob);
		}
		
		selectDobDay().selectByValue(fields[2]);
		selectDobMonth().selectByValue(fields[1]);
		selectDobYear().selectByValue(fields[0]);
	}
	
	public void fillForm(CreateAccountInformationDto createAccountInformation) {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(titleBy(createAccountInformation.title)));
		
		title(createAccountInformation.title).click();
		
		SeleniumUtilities.sendKeysIfInputNotNull(firstName, createAccountInformation.firstName);
		SeleniumUtilities.sendKeysIfInputNotNull(lastName, createAccountInformation.lastName);
		SeleniumUtilities.sendKeysIfInputNotNull(password, createAccountInformation.password);

		fillDob(createAccountInformation.dateOfBirth);
		
		SeleniumUtilities.sendKeysIfInputNotNull(addressFirstName, createAccountInformation.addressFirstName);
		SeleniumUtilities.sendKeysIfInputNotNull(addressLastName, createAccountInformation.addressLastName);
		SeleniumUtilities.sendKeysIfInputNotNull(company, createAccountInformation.company);
		SeleniumUtilities.sendKeysIfInputNotNull(addressLine1, createAccountInformation.addressLine1);
		SeleniumUtilities.sendKeysIfInputNotNull(addressLine2, createAccountInformation.addressLine2);
		SeleniumUtilities.sendKeysIfInputNotNull(city, createAccountInformation.city);
		
		selectState().selectByVisibleText(createAccountInformation.state);
		
		SeleniumUtilities.sendKeysIfInputNotNull(postalCode, createAccountInformation.postalCode);
		
		selectCountry().selectByVisibleText(createAccountInformation.country);
		
		SeleniumUtilities.sendKeysIfInputNotNull(phoneHome, createAccountInformation.phoneHome);
		SeleniumUtilities.sendKeysIfInputNotNull(phoneMobile, createAccountInformation.phoneMobile);
		SeleniumUtilities.sendKeysIfInputNotNull(addressAlias, createAccountInformation.addressAlias);
	}
	
	public MyAccountPage submitForm() throws IOException {
		
		submitAccount.click();
		
		if(hasErrorAlert()) {
			
			SeleniumUtilities.executeJsScript(driver, "window.scrollTo(0, 0);");
			SeleniumUtilities.takeScreenshot(driver, "error-alert-create-account");
			
			return null;
		}
		
		return new MyAccountPage(driver);
	}
	
	public boolean hasErrorAlert() {

		try {

			WebElement alert = driver.findElement(By.xpath("//div[contains(@class, 'alert')]"));
			
			return alert.isDisplayed();

		}catch(NoSuchElementException e) {
			
		}

		return false;
	}
}
