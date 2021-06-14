package com.ecommerce.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecommerce.support.pageobjects.CheckoutPage;
import com.ecommerce.support.pageobjects.HomePage;
import com.ecommerce.support.pageobjects.SearchResultPage;
import com.ecommerce.support.pageobjects.SignInPage;
import com.ecommerce.support.utilities.SeleniumUtilities;

public class OrderTests extends TestBase {
	@Test
	public void Order_Add3differentItems_ShouldSeeSuccessMessage() throws Throwable{
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		List<String> items = List.of("t-shirt", "dress", "blouse");
		
		HomePage homePage = new HomePage(driver);
		CheckoutPage checkoutPage = null;
		SignInPage signInPage = homePage.signIn();
		signInPage.signIn(username, password);
		
		for(String item : items) {

			homePage = new HomePage(driver);
			SearchResultPage search = homePage.search(item);
			
			checkoutPage = search.addItemToCart(0, item != items.get(items.size()-1));
		}
		
		Assert.assertNotNull(checkoutPage);
		
		String message = checkoutPage.completeOrder();
		
		Assert.assertEquals(message, "Your order on My Store is complete.");
		
		SeleniumUtilities.takeScreenshot(driver, "order-complete");
	}

}
