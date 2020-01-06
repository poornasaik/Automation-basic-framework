package amz_Methods;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import amz_Locators.HomePageLocators;
import amz_Locators.SearchPageLocators;
import utilities.Verify;
import utilities.WinHandler;

/*Contains methods for actions that can be performed in search results page after searching for an item*/
public class SearchPageMethods implements SearchPageLocators, HomePageLocators {
	private static Logger log = LogManager.getLogger(SearchPageMethods.class.getName());
	WebDriver driver;
	int time;

	public SearchPageMethods(int time, WebDriver driver) {
		this.driver = driver;
		this.time = time;
	}

	/* Method to sort search results */
	public void sort(String sortType) throws InterruptedException {
		log.info("Sorting Search results");
		WinHandler.waitForPageLoad(driver, time);
		WebElement sortdropDown = Verify.getElement(sortedBy, time, driver);
		while((driver.findElements(sortOptions)).size()<1) {
		Verify.click(sortdropDown, time, driver);
		}
		log.info("Clicked on Sort dropdown");
		List<WebElement> sortOptionElements = Verify.getElements(sortOptions, time, driver);
		
		for (int i = 0; i < sortOptionElements.size(); i++) {
			if (sortOptionElements.get(i).getText().equalsIgnoreCase(sortType)) {
				Verify.click(sortOptionElements.get(i), time, driver);
				log.info("Successfully clicked");
				break;

			} else if (i == sortOptionElements.size()) {
				log.error("Unable to sort based on: " + sortType);
				Assert.fail("Unable to sort");
			}
		}
		WinHandler.waitForPageLoad(driver,time);
		// Initial code 
		/*
		 * {Select sortDropdown= new Select(Verify.getElement(sort, time, driver)); if
		 * (sortType.equalsIgnoreCase("Price: Low to High")) {
		 * sortDropdown.selectByVisibleText("Price: Low to High"); } else if
		 * (sortType.equalsIgnoreCase("Price: High to Low")) {
		 * sortDropdown.selectByVisibleText("Price: High to Low"); } else if
		 * (sortType.equalsIgnoreCase("Price: High to Low")) {
		 * sortDropdown.selectByVisibleText("Price: High to Low"); } else if
		 * (sortType.equalsIgnoreCase("Customer Review")) {
		 * sortDropdown.selectByVisibleText("Avg. Customer Review"); } else if
		 * (sortType.equalsIgnoreCase("Newest Arrivals")) {
		 * sortDropdown.selectByVisibleText("Newest Arrivals"); } else {
		 * sortDropdown.selectByVisibleText("Featured"); }}
		 */
	}

	/* Method to return search results */
	public List<WebElement> getSearchResults() {
		List<WebElement> result = Verify.getElements(searchResult, time, driver);
		log.info("Returned product list");
		return result;

	}

	/* Method to navigate to each search result based on entered number */
	public void navigateToProducts(int noProds, List<WebElement> prodList) {
		for (int i = 0; i < noProds; i++) {
			prodList.get(i).click();
			log.info("Clicked on product link");
		}

	}
	/*Method to return search results using each element*/
	public void navigateToProducts(WebElement prodList) {

	}
}
