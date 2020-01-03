package amz_Methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.HomePageLocators;

public class HomePageMethods implements HomePageLocators{
	
	WebDriver driver;
	public HomePageMethods(WebDriver driver){
		this.driver=driver;
	}
	
	public void searchItems(String item) {
		WebElement searchBox = driver.findElement(searchField);
		searchBox.clear();
		searchBox.sendKeys(item);
		searchBox.submit();
	}
	public void navigateToCart() {
		WebElement cartBtn = driver.findElement(cart);
		cartBtn.click();
		
	}

}
