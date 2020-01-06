package amz_Methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.ProductPageLocators;
import utilities.Verify;

public class ProductPageMethods implements ProductPageLocators {

	WebDriver driver;
	int time;

	public ProductPageMethods(int time, WebDriver driver) {
		this.driver = driver;
		this.time=time;
	}

	public boolean dealProduct() {
		if (Verify.getNumElements(itemDealPrice, driver)> 0) {
			return true;
		} else
			return false;
	}

	public boolean multiSeller() {
		if ((Verify.getNumElements(itemDealPrice, driver)< 1) && (Verify.getNumElements(itemPrice, driver) < 1)
				&& (Verify.getNumElements(itemSalePrice, driver) < 1)) {
			return true;
		} else
			return false;
	}

	public String getProductName() {
		String prodName = Verify.getElement(productName, time, driver).getText();
		return prodName;
	}

	public double getProductPrice() {

		double prodPrice;
		if (Verify.getNumElements(itemSalePrice, driver) > 0) {
			prodPrice = Double
					.parseDouble((Verify.getElement(itemSalePrice, time, driver).getText()).replaceAll("\u20B9|,", "").trim());
		} else {
			prodPrice = Double.parseDouble((Verify.getElement(itemPrice, time, driver).getText()).replaceAll("\u20B9|,", "").trim());
		}
		return prodPrice;

	}

	public void addToCart() {
		WebElement addCartBtn = Verify.getElement(addToCart, time, driver);
		addCartBtn.click();
	}
}
