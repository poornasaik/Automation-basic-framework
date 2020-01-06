package amz_Methods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.CartPageLocators;
import utilities.Verify;

public class CartPageMethods implements CartPageLocators {

	WebDriver driver;
	int time;

	public CartPageMethods(int time, WebDriver driver) {
		this.driver = driver;
		this.time=time;
	}
	public String[] getCartItems() {
		List<WebElement> cartItemElement = Verify.getElements(cartItems, time, driver);
		int size = cartItemElement.size();
		String[] name = new String[size];
		for (int i = 0; i < size; i++) {
			name[i] = cartItemElement.get((size - 1) - i).getText();
		}
		return name;
	}

	public double[] getItemPrice() {
		List<WebElement> cartItemElement = Verify.getElements(itemPrice, time, driver);
		int size = cartItemElement.size();
		double[] price = new double[size];
		for (int i = 0; i < size; i++) {
			price[i] = Double.parseDouble(cartItemElement.get((size - 1) - i).getText().replaceAll("\u20B9|,", "").trim());
		}
		return price;
	}
	public double getSubTotal() {
		WebElement subTotEle = Verify.getElement(subTotal, time, driver);
		double subTot = Double.parseDouble(subTotEle.getText().replaceAll("\u20B9|,", "").trim());
		return subTot;
	}
	
}
