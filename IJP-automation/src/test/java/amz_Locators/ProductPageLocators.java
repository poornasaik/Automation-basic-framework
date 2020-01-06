package amz_Locators;

import org.openqa.selenium.By;
/* Contains Locators of Amazon Product page.
 * Add/modify any locators of Amazon Product page here.
 * */

public interface ProductPageLocators {
 By productName = By.xpath("//span[@id='productTitle']");
 By itemPrice = By.xpath("//span[@id='priceblock_ourprice']");
 By itemDealPrice = By.xpath("//span[@id='priceblock_dealprice']");
 By itemSalePrice = By.xpath("//span[@id='priceblock_saleprice']");
 By addToCart = By.xpath("//input[@id='add-to-cart-button']");
}
