package amz_Methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.HomePageLocators;
import utilities.Verify;

public class HomePageMethods implements HomePageLocators{
	
	WebDriver driver;
	int time;
	public HomePageMethods(int time, WebDriver driver){
		this.driver=driver;
		this.time=time;
	}
	
	public void searchItems(String item) {
		WebElement searchBox = Verify.getElement(searchField, time, driver);
		searchBox.clear();
		searchBox.sendKeys(item);
		searchBox.submit();
	}
	public void navigateToCart() {
		WebElement cartBtn = Verify.getElement(cart, time, driver);
		cartBtn.click();
		
	}

}
