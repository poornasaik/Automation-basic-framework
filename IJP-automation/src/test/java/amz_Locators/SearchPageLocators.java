package amz_Locators;

import org.openqa.selenium.By;
/* Contains Locators of Amazon Search page.
 * Add/modify any locators of Amazon Search page here.
 * */
public interface SearchPageLocators {
	By sortDropdown = By.xpath("//select[@name='s']");
	By sortedBy= By.xpath("//span[@class='a-dropdown-label']");
	By sortOptions = By.xpath("//div[@class='a-popover-inner']//li");
	By searchResult = By.xpath("//span[@data-component-type='s-search-results'] //h2 //a");
}
