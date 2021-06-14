package com.ecommerce.tests;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.support.pageobjects.FacebookPage;
import com.ecommerce.support.pageobjects.HomePage;
import com.ecommerce.support.pageobjects.ItemPage;
import com.ecommerce.support.pageobjects.PinterestPage;
import com.ecommerce.support.pageobjects.SearchResultPage;
import com.ecommerce.support.pageobjects.SignInPage;
import com.ecommerce.support.pageobjects.WriteReviewResult;
import com.ecommerce.support.utilities.SeleniumUtilities;

public class ItemsTests extends TestBase {
	@Test
	public void Item_ReviewItemLess50chars_ShouldFail() throws Throwable{
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String product = "dress";
		String reviewTitle = "A review";
		String reviewBody = StringUtils.repeat('a', 49);
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		signInPage.signIn(username, password);

		SearchResultPage search = homePage.search(product);
		
		ItemPage itemPage = search.selectItem(0);
		
		WriteReviewResult result = itemPage.writeReview(reviewTitle, reviewBody);
		
		Assert.assertTrue(result.hasError());
	}
	@Test
	public void Item_GoToPinterest_Redirected() throws Throwable{
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String product = "dress";
		String pinterestTitleSegment = "Pinterest";
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		signInPage.signIn(username, password);

		SearchResultPage search = homePage.search(product);
		
		ItemPage itemPage = search.selectItem(0);
		
		PinterestPage pinterest = itemPage.goToPinterest();
		
		final String originalWindow = driver.getWindowHandle();
		
		String otherWindow = driver.getWindowHandles()
		.stream()
		.filter(e -> !e.equals(originalWindow))
		.findFirst()
		.get();
		
		driver.switchTo().window(otherWindow);
		
		Assert.assertTrue(pinterest.isEmailFucused(), "Email should be focused");
		
		pinterest.writeCredentials(username, password);
		
		SeleniumUtilities.takeScreenshot(driver, "pinterest");
		
		Assert.assertTrue(driver.getTitle().contains(pinterestTitleSegment), "Title should have pinterest");
		
		driver.switchTo().window(originalWindow);
		
		Assert.assertFalse(driver.getTitle().contains(pinterestTitleSegment), "Title shouldn't have pinterest");
	}
	@Test
	public void Item_GoToFacebook_Redirected() throws Throwable{
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String product = "dress";
		String facebookTitleSegment = "Facebook";
		
		HomePage homePage = new HomePage(driver);
		
		SignInPage signInPage = homePage.signIn();
		
		signInPage.signIn(username, password);

		SearchResultPage search = homePage.search(product);
		
		ItemPage itemPage = search.selectItem(0);
		
		FacebookPage facebook = itemPage.goToFacebook();
		
		String originalWindow = driver.getWindowHandle();
		
		String otherWindow = driver.getWindowHandles()
		.stream()
		.filter(e -> !e.equals(originalWindow))
		.findFirst()
		.get();
		
		driver.switchTo().window(otherWindow);
		
		Assert.assertTrue(facebook.isEmailFucused(), "Email should be focused");
		
		facebook.writeCredentials(username, password);
		
		SeleniumUtilities.takeScreenshot(driver, "facebook");
		
		Assert.assertTrue(driver.getTitle().contains(facebookTitleSegment), "Title should have facebook");
		
		driver.switchTo().window(originalWindow);
		
		Assert.assertFalse(driver.getTitle().contains(facebookTitleSegment), "Title shouldn't have facebook");
	}
}
