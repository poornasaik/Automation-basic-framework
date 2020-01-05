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

public class SearchPageMethods implements SearchPageLocators, HomePageLocators {
	private static Logger log = LogManager.getLogger(SearchPageMethods.class.getName());
	WebDriver driver;
	int time;

	public SearchPageMethods(int time, WebDriver driver) {
		this.driver = driver;
		this.time = time;
	}

	public void sort(String sortType) throws InterruptedException {
		log.info("trying to sort");
		Verify.click(Verify.getElement(sortDropdown, time, driver), time, driver);
		
		List<WebElement> sortOptionElements = Verify.getElements(sortOptions, time, driver);
		for (int i=0;i<sortOptionElements.size();i++) {
			System.out.println(sortOptionElements.get(i).getText());
			if (sortOptionElements.get(i).getText().equalsIgnoreCase(sortType)) {
				Verify.click(sortOptionElements.get(i), time, driver);
				log.info("Successfully clicked");
				break;

			} else if(i==sortOptionElements.size()){
				log.error("Unable to sort based on: "+sortType);
				Assert.fail("Unable to sort");
			}
		}

		/*
		 * Initial code not working in Firefox because of element obscure {Select
		 * sortDropdown= new Select(Verify.getElement(sort, time, driver)); if
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

	public List<WebElement> getSearchResults() {
		List<WebElement> result = Verify.getElements(searchResult, time, driver);
		return result;

	}

	public void navigateToProducts(int noProds, List<WebElement> prodList) {
		for (int i = 0; i < noProds; i++) {
			prodList.get(i).click();

		}

	}

	public void navigateToProducts(WebElement prodList) {

	}
}
