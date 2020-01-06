package amz_Locators;

import org.openqa.selenium.By;

public interface SearchPageLocators {
	By sortDropdown = By.xpath("//select[@name='s']");
	By sortedBy= By.xpath("//span[@class='a-dropdown-prompt']");
	By sortOptions = By.xpath("//div[@class='a-popover-inner']//li");
	By searchResult = By.xpath("//span[@data-component-type='s-search-results'] //h2 //a");
}
