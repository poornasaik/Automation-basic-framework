package amz_Methods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.HomePageLocators;
import utilities.Verify;
/*Contains methods for actions that can be performed on home page*/
public class HomePageMethods implements HomePageLocators{
	private static Logger log = LogManager.getLogger(HomePageLocators.class.getName());
	WebDriver driver;
	int time;
	public HomePageMethods(int time, WebDriver driver){
		this.driver=driver;
		this.time=time;
	}
	/*Method to Search for items in Amazon*/
	public void searchItems(String item) {
		WebElement searchBox = Verify.getElement(searchField, time, driver);
		searchBox.clear();
		searchBox.sendKeys(item);
		searchBox.submit();
		log.info("Searched for product: "+item);
	}
	/*Method to navigate to cart*/
	public void navigateToCart() {
		WebElement cartBtn = Verify.getElement(cart, time, driver);
		cartBtn.click();
		log.info("Clicked on cart button");
	}

}
