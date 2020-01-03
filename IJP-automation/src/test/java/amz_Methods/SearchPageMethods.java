package amz_Methods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import amz_Locators.HomePageLocators;
import amz_Locators.SearchPageLocators;

public class SearchPageMethods implements SearchPageLocators, HomePageLocators {
	WebDriver driver;

	public SearchPageMethods(WebDriver driver) {
		this.driver = driver;
	}

	public void sort(String sortType) {
		Select sortDropdown= new Select(driver.findElement(sort));
		if (sortType.equalsIgnoreCase("Price: Low to High")) {
			sortDropdown.selectByVisibleText("Price: Low to High");
		}
		else if (sortType.equalsIgnoreCase("Price: High to Low")) {
			sortDropdown.selectByVisibleText("Price: High to Low");
		}
		else if (sortType.equalsIgnoreCase("Price: High to Low")) {
			sortDropdown.selectByVisibleText("Price: High to Low");
		}
		else if (sortType.equalsIgnoreCase("Customer Review")) {
			sortDropdown.selectByVisibleText("Avg. Customer Review");
		}
		else if (sortType.equalsIgnoreCase("Newest Arrivals")) {
			sortDropdown.selectByVisibleText("Newest Arrivals");
		}
		else {
			sortDropdown.selectByVisibleText("Featured");
		}
	}
	
	public List<WebElement> getSearchResults() {
		List<WebElement> result = driver.findElements(searchResult);
		return result;
		
	}
	public void navigateToProducts(int noProds, List<WebElement> prodList) {
		for (int i=0;i<noProds;i++) {
			prodList.get(i).click();
			
		}
		
	}
	public void navigateToProducts(WebElement prodList) {		
		
	}
}
