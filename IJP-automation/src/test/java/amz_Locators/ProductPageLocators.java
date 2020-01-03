package amz_Locators;

import org.openqa.selenium.By;

public interface ProductPageLocators {
 By productName = By.xpath("//span[@id='productTitle']");
 By itemPrice = By.xpath("//span[@id='priceblock_ourprice']");
 By itemDealPrice = By.xpath("//span[@id='priceblock_dealprice']");
 By addToCart = By.xpath("//input[@id='add-to-cart-button']");
}
