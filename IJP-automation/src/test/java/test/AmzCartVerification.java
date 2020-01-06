package test;

import java.text.DecimalFormat;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import amz_Methods.CartPageMethods;
import amz_Methods.HomePageMethods;
import amz_Methods.ProductPageMethods;
import amz_Methods.SearchPageMethods;
import testResources.TestDataProvider;
import utilities.SystemSetProperties;
import utilities.Verify;
import utilities.WinHandler;

public class AmzCartVerification {
	WebDriver driver;
	int ewaitTime;

	@Parameters({ "browser", "url", "ewaitTime" })
	@BeforeMethod
	public void driverInit(String browser, String url, int ewaitTime) {

		// set the driver based on browser
		if (browser.equalsIgnoreCase("chrome")) {
			SystemSetProperties.chrome();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			SystemSetProperties.Firefox();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Enter 'chrome' for 'browser' parameter in TestNG xml file properly");
		}
		this.ewaitTime = ewaitTime;
		driver.manage().window().maximize();
		driver.get(url);

	}

	/* Test to verify cart items */
	@Test(dataProvider = "cartVerification", dataProviderClass = TestDataProvider.class)
	public void verifyCartItems(String item, String num, String sortType) throws InterruptedException {
		HomePageMethods home = new HomePageMethods(ewaitTime, driver); // create object to access home page methods
		home.searchItems(item); // search for the product
		SearchPageMethods search = new SearchPageMethods(ewaitTime, driver); // create object to access search page
																				// methods
		search.sort(sortType); // sort the search results

		List<WebElement> productLinks = search.getSearchResults(); // get all the product links
		int noProds = (int) (Float.parseFloat(num)); // parsing num as it will be returned in double type from excel

		// initialize product name array and price array
		String[] productName = new String[noProds];
		double[] productPrice = new double[noProds];
		double prodSum = 0;
		ProductPageMethods product = new ProductPageMethods(ewaitTime, driver);// create object to access product page
																				// methods
		DecimalFormat df = new DecimalFormat("0.00"); // New decimal format for currency
		/*
		 * Check whether a product is a deal product or multi seller product and ignore
		 * if it is Else the product will be added to cart
		 */
		String mainWindow = driver.getWindowHandle(); // get main window handle
		WinHandler window = new WinHandler(driver, mainWindow,ewaitTime); // create object to access winhander methods

		for (int i = 0; i < noProds; i++) {
			while (!window.NewWindows(2, driver, ewaitTime)) {
				Verify.click(productLinks.get(i), ewaitTime, driver);
			}
			window.childWindow();
			if (!(product.dealProduct() || product.multiSeller())) {
				productName[i] = product.getProductName();
				productPrice[i] = product.getProductPrice();
				prodSum += productPrice[i];
				product.addToCart();
				window.closeChildWindow();
			} else {
				window.closeChildWindow();
				productLinks.remove(i);// if product is a deal or multi seller product, remove from list and reiterate
				i--;
			}
			window.mainWindow();
		}

		home.navigateToCart();
		CartPageMethods cart = new CartPageMethods(ewaitTime, driver); // create object to access Cart page methods
		/* get cart products and price */
		String[] cartItems = cart.getCartItems();
		double[] cartItemPrice = cart.getItemPrice();

		/* Verify the product details */
		Assert.assertEquals(cartItems.length, productName.length);
		for (int i = 0; i < cartItems.length; i++) {
			Assert.assertEquals(cartItems[i], productName[i]);
			Assert.assertEquals(df.format(cartItemPrice[i]), df.format(productPrice[i]));
		}
		/* Verify the subtotal */
		Assert.assertEquals(df.format(cart.getSubTotal()), df.format(prodSum));
	}

	/* Close the session */
	@AfterMethod
	public void terminateDriver() {
		driver.quit();
	}
}
