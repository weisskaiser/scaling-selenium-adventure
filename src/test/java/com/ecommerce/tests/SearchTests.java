package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecommerce.support.pageobjects.HomePage;
import com.ecommerce.support.pageobjects.SearchResultPage;

class StaticProvider{
	@DataProvider(name = "ItemCountProvider")
	public static Object[][] createData1() {
	 return new Object[][] {
	   { "dress", 7 },
	   { "silk", 0 },
	 };
	}
}

public class SearchTests extends TestBase {
	
	@Test(dataProvider = "ItemCountProvider", dataProviderClass = StaticProvider.class)
	public void SearchItem_FindExpectedCount(String searchText, int expectedCount) throws Throwable{
		
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResult = homePage.search(searchText);
		
		Assert.assertEquals(searchResult.foundItemsCount(), expectedCount);
	}
	
	@Test
	public void SearchItem_SearchThenFilterByManufacturer() throws Throwable{
		String searchText = "dress";
		String manufacturer = "Fashion Manufacturer";
		
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResult = homePage.search(searchText);
		
		searchResult.selectManufacturer(manufacturer);
		
		Assert.assertEquals(searchResult.header().strip().toUpperCase(), ("List of products by manufacturer " + manufacturer).toUpperCase());
	}
}
