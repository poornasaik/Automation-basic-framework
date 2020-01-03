package test;

import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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
import utilities.WinHandler;

public class AmzCartVerification {
	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void driverInit(String browser, String url) {
		if (browser.equalsIgnoreCase("chrome")) {
			SystemSetProperties.chrome();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			SystemSetProperties.Firefox();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	
	@Test(dataProvider="cartVerification", dataProviderClass = TestDataProvider.class)
	public void verifyCartItems(String item, String num, String sortType) throws InterruptedException {
		HomePageMethods home = new HomePageMethods(driver);
		home.searchItems(item);
		SearchPageMethods search = new SearchPageMethods(driver);
		search.sort(sortType);
		List<WebElement> productLinks=search.getSearchResults();
		int noProds=(int) (Float.parseFloat(num));
		String mainWindow = driver.getWindowHandle();
		String[] productName= new String[noProds];
		double[] productPrice= new double[noProds];
		double prodSum=0;
		ProductPageMethods product = new ProductPageMethods(driver);
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i=0;i<noProds;i++) {
			Actions actions = new Actions(driver);
			actions.moveToElement(productLinks.get(i)).click().build().perform();
			WinHandler window = new WinHandler(driver,mainWindow);
			window.childWindow();
			if(!(product.dealProduct()||product.multiSeller())) {
			productName[i] = product.getProductName();
			productPrice[i] = product.getProductPrice();
			prodSum+=productPrice[i];
			product.addToCart();
			window.closeChildWindow();
			}
			else {
				window.closeChildWindow();
				productLinks.remove(i);
				i--;
			}
			Thread.sleep(5000);
			window.mainWindow();
		}
		home.navigateToCart();
		CartPageMethods cart= new CartPageMethods(driver);
		
		String[] cartItems= cart.getCartItems();
		double[] cartItemPrice=cart.getItemPrice();
		Assert.assertEquals(cartItems.length, productName.length);
		for (int i=0;i<cartItems.length;i++) {
		Assert.assertEquals(cartItems[i], productName[i]);
		Assert.assertEquals(df.format(cartItemPrice[i]), df.format(productPrice[i]));
		}
		Assert.assertEquals(df.format(cart.getSubTotal()), df.format(prodSum));
	}
	@AfterMethod
	public void terminateDriver() {
		driver.quit();
	}
}
