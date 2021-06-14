package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.support.pageobjects.CreateAccountForm;
import com.ecommerce.support.pageobjects.HomePage;
import com.ecommerce.support.pageobjects.SignInPage;
import com.ecommerce.support.pageobjects.dtos.CreateAccountInformationDto;
import com.ecommerce.support.pageobjects.dtos.CreateAccountInformationGenerator;

public class AccountTests extends TestBase {
	
	@Test
	public void CreateAccount_useValidInformation_shouldSucceed() throws Throwable{
		CreateAccountInformationDto newAccountInformation = new CreateAccountInformationGenerator().getFakeAccountInformation();
		
		System.out.println(newAccountInformation);
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		CreateAccountForm createAccountForm  = signInPage.createAccount(newAccountInformation.email);
		
		createAccountForm.fillForm(newAccountInformation);
		
		createAccountForm.submitForm();
		
		Assert.assertFalse(createAccountForm.hasErrorAlert(), "error alert shouldn't be present");
		
		System.out.printf("Created account for:\nemail: %s\npassword: %s\n", newAccountInformation.email, newAccountInformation.password);
		
	}
	
	@Test
	public void CreateAccount_leaveCountryUnselected_shouldFail() throws Throwable{
		CreateAccountInformationDto newAccountInformation = new CreateAccountInformationGenerator().getFakeAccountInformation();
		
		newAccountInformation.country = "-";
		
		System.out.println(newAccountInformation);
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		CreateAccountForm createAccountForm  = signInPage.createAccount(newAccountInformation.email);
		
		createAccountForm.fillForm(newAccountInformation);
		
		createAccountForm.submitForm();
		
		Assert.assertTrue(createAccountForm.hasErrorAlert(), "error alert should be present");
		
	}
	
	@Test
	public void CreateAccount_useInvalidPostalCode_shouldFail() throws Throwable{
		CreateAccountInformationDto newAccountInformation = new CreateAccountInformationGenerator().getFakeAccountInformation();
		
		newAccountInformation.postalCode = "123";
		
		System.out.println(newAccountInformation);
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		CreateAccountForm createAccountForm  = signInPage.createAccount(newAccountInformation.email);
		
		createAccountForm.fillForm(newAccountInformation);
		
		createAccountForm.submitForm();
		
		Assert.assertTrue(createAccountForm.hasErrorAlert(), "error alert should be present");
		
	}
}
