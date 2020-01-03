package amz_Methods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.CartPageLocators;

public class CartPageMethods implements CartPageLocators {

	WebDriver driver;

	public CartPageMethods(WebDriver driver) {
		this.driver = driver;
	}
	public String[] getCartItems() {
		List<WebElement> cartItemElement = driver.findElements(cartItems);
		int size = cartItemElement.size();
		String[] name = new String[size];
		for (int i = 0; i < size; i++) {
			name[i] = cartItemElement.get((size - 1) - i).getText();
		}
		return name;
	}

	public double[] getItemPrice() {
		List<WebElement> cartItemElement = driver.findElements(itemPrice);
		int size = cartItemElement.size();
		double[] price = new double[size];
		for (int i = 0; i < size; i++) {
			price[i] = Double.parseDouble(cartItemElement.get((size - 1) - i).getText().replaceAll("\u20B9|,", "").trim());
		}
		return price;
	}
	public double getSubTotal() {
		WebElement subTotEle = driver.findElement(subTotal);
		double subTot = Double.parseDouble(subTotEle.getText().replaceAll("\u20B9|,", "").trim());
		return subTot;
	}
	
}
