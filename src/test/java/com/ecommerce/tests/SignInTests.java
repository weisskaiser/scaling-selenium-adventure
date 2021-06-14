package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.support.pageobjects.HomePage;
import com.ecommerce.support.pageobjects.MyAccountPage;
import com.ecommerce.support.pageobjects.SignInPage;
import com.ecommerce.support.pageobjects.SignInResultPage;

public class SignInTests extends TestBase {
	@Test
	public void LogIn_useValidCredentials_shouldSucceed() throws Throwable{
		
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		System.out.println("Using username as " + username);
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		SignInResultPage signInResultPage = signInPage.signIn(username, password);
		
		Assert.assertFalse(signInResultPage.hasFailed(), "error alert shouldn't be present");
	}
	
	@Test
	public void LogIn_logInThenLogOff() throws Throwable{
		
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		System.out.println("Using username as " + username);
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		SignInResultPage signInResultPage = signInPage.signIn(username, password);
		
		Assert.assertFalse(signInResultPage.hasFailed(), "error alert shouldn't be present");
		
		MyAccountPage myAccountPage = signInResultPage.myAccountPage();
		
		myAccountPage.LogOff();
		
	}
	
	@Test
	public void LogIn_useInvalidCredentials_shouldFail() throws Throwable{
		
		String username = properties.getProperty("username");
		String password = "pizza";
		System.out.println("Using username as " + username);
		System.out.println("Using password as " + password);
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		SignInResultPage signInResultPage = signInPage.signIn(username, password);
		
		Assert.assertTrue(signInResultPage.hasFailed(), "error alert should be present");
		Assert.assertEquals(signInResultPage.errorMessageText(), "Authentication failed.");
		Assert.assertEquals(signInResultPage.errorMessageColor(), "rgb(243, 81, 92)");
	}
}
