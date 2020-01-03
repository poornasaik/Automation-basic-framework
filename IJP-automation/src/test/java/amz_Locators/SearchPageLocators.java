package amz_Locators;

import org.openqa.selenium.By;

public interface SearchPageLocators {
	By sort = By.xpath("//select[@name='s']");
	By searchResult = By.xpath("//span[@data-component-type='s-search-results'] //h2 //a");
}
