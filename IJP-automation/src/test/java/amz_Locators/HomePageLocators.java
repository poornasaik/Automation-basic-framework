package amz_Locators;

import org.openqa.selenium.By;

public interface HomePageLocators {
	By searchField =By.xpath("//input[@id='twotabsearchtextbox']");
	By accountList = By.xpath("//a[@id='nav-link-accountList']");
	By selectLanguage = By.xpath("//a[@id='icp-nav-flyout']");
	By orders = By.xpath("//a[@id='nav-orders']");
	By cart = By.xpath("//a[@id='nav-cart']");

}
