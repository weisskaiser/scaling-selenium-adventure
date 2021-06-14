package com.ecommerce.support.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.support.utilities.SeleniumUtilities;

public class SearchResultPage {

	private WebDriver driver;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public ItemPage selectItem(int itemIndex) throws InterruptedException {
		List<WebElement> items = driver.findElements(By.xpath("//*[contains(@class, 'product-container')]/div/div/a/img"));
		
		SeleniumUtilities.clickUsingJavascript(driver, items.get(itemIndex));
		
		return new ItemPage(driver);
	}
	
	public CheckoutPage addItemToCart(int itemIndex, boolean continueShopping) throws InterruptedException {
		List<WebElement> items = driver.findElements(By.xpath("//*[contains(@class, 'product-container')]/div/div/a/img"));
		
		Actions actions = new Actions(driver);
		
		actions
		.moveToElement(items.get(itemIndex))
		.perform();
		
		actions
		.moveToElement(driver.findElement(By.xpath("//*[contains(@class, 'product-container')]//span[text()='Add to cart']")))
		.click()
		.perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Continue shopping']")));
		WebElement checkoutBtn = driver.findElement((By.xpath("//a[@title='Proceed to checkout']")));
		
		if(continueShopping) {
			continueBtn.click();
			return null;
		}else {
			checkoutBtn.click();
			return new CheckoutPage(driver);
		}
	}
	
	public int foundItemsCount() {
		WebElement findResultMessage = driver.findElement(By.xpath("//span[contains(text(), 'results have been found')]"));
		String message = findResultMessage.getText();
		int spaceIndex = message.indexOf(' ');
		if(spaceIndex == -1) {
			throw new RuntimeException("Error parsing found result message");
		}
		
		return Integer.parseInt(message.substring(0, spaceIndex));
	}
	
	public void selectManufacturer(String manufacturer) {
		WebElement manufacturerFilter = driver.findElement(By.xpath(String.format("//a[normalize-space(text())='%s']", manufacturer)));
		manufacturerFilter.click();
	}
	
	public String header() {
		WebElement header = driver.findElement(By.className("product-listing"));
		return header.getText();
	}
	
}
