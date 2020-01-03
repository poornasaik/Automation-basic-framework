package amz_Methods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.ProductPageLocators;

public class ProductPageMethods implements ProductPageLocators{
	
	WebDriver driver;
	
	public ProductPageMethods(WebDriver driver) {
		this.driver=driver;		
	}
	
	public boolean dealProduct() {
		if((driver.findElements(itemDealPrice).size())>0) {
			return true;
		}
		else
			return false;
	}
	
	public boolean multiSeller() {
		if(((driver.findElements(itemDealPrice).size())<1)&&((driver.findElements(itemPrice).size())<1)) {
			return true;
		}
		else
			return false;
	}
	
	public String getProductName() {
		String prodName = driver.findElement(productName).getText();
		return prodName;
	}
	
	public double getProductPrice() {

		double prodPrice= Double.parseDouble((driver.findElement(itemPrice).getText()).replaceAll("\u20B9|,", "").trim());
		return prodPrice;
	}
	public void addToCart() {
		WebElement addCartBtn = driver.findElement(addToCart);
		addCartBtn.click();
	}
}
