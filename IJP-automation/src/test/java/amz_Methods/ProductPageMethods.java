package amz_Methods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amz_Locators.ProductPageLocators;
import utilities.Verify;

/*Contains methods for actions that can be performed in Product page*/
public class ProductPageMethods implements ProductPageLocators {
	private static Logger log = LogManager.getLogger(ProductPageLocators.class.getName());
	WebDriver driver;
	int time;

	public ProductPageMethods(int time, WebDriver driver) {
		this.driver = driver;
		this.time = time;
	}

	/* Method to know whether a product is deal product */
	public boolean dealProduct() {
		if (Verify.getNumElements(itemDealPrice, driver) > 0) {
			log.info("returned True as product is  a deal item");
			return true;
		} else {
			log.info("returned False as product is not a deal item");
			return false;
		}

	}

	/*
	 * Method to know whether a product is a multiseller and price is not available
	 */
	public boolean multiSeller() {
		if ((Verify.getNumElements(itemDealPrice, driver) < 1) && (Verify.getNumElements(itemPrice, driver) < 1)
				&& (Verify.getNumElements(itemSalePrice, driver) < 1)) {
			log.info("returned True as product is  a Multiseller item");
			return true;
		} else {
			log.info("returned False as product is not a Multiseller item");
			return false;
		}
	}

	/* method to get product name */
	public String getProductName() {
		String prodName = Verify.getElement(productName, time, driver).getText();
		log.info("Returned product name");
		return prodName;
	}

	/* method to get product price */
	public double getProductPrice() {

		double prodPrice;
		if (Verify.getNumElements(itemSalePrice, driver) > 0) {
			prodPrice = Double.parseDouble(
					(Verify.getElement(itemSalePrice, time, driver).getText()).replaceAll("\u20B9|,", "").trim());
		} else {
			prodPrice = Double.parseDouble(
					(Verify.getElement(itemPrice, time, driver).getText()).replaceAll("\u20B9|,", "").trim());
		}
		log.info("Returned product Price");
		return prodPrice;

	}

	/* method to add product to cart */
	public void addToCart() {
		WebElement addCartBtn = Verify.getElement(addToCart, time, driver);
		addCartBtn.click();
		log.info("Click on Added to cart");
	}
}
