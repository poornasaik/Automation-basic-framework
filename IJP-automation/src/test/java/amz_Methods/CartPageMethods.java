package amz_Methods;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.CartPageLocators;
import utilities.Verify;
/* Contains methods for actions performed in Amazon cart page*/
public class CartPageMethods implements CartPageLocators {
	private static Logger log = LogManager.getLogger(CartPageLocators.class.getName());
	WebDriver driver;
	int time;
	
	public CartPageMethods(int time, WebDriver driver) {
		this.driver = driver;
		this.time=time;
	}
	
	/*returns cart item names by first added item in last*/
	public String[] getCartItems() {
		List<WebElement> cartItemElement = Verify.getElements(cartItems, time, driver);
		int size = cartItemElement.size();
		String[] name = new String[size];
		for (int i = 0; i < size; i++) {
			name[i] = cartItemElement.get((size - 1) - i).getText();
		}
		log.info("Returned cart item name");
		return name;
	}
	
	/*returns cart item price by first added item in last*/
	public double[] getItemPrice() {
		List<WebElement> cartItemElement = Verify.getElements(itemPrice, time, driver);
		int size = cartItemElement.size();
		double[] price = new double[size];
		for (int i = 0; i < size; i++) {
			price[i] = Double.parseDouble(cartItemElement.get((size - 1) - i).getText().replaceAll("\u20B9|,", "").trim());
		}
		log.info("Returned cart item price");
		return price;
	}
	
	/*returns subtotal of the cart*/
	public double getSubTotal() {
		WebElement subTotEle = Verify.getElement(subTotal, time, driver);
		double subTot = Double.parseDouble(subTotEle.getText().replaceAll("\u20B9|,", "").trim());
		log.info("Returned sub total of the cart products");
		return subTot;
	}
	
}
