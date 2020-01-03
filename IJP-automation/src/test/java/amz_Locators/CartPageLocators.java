package amz_Locators;

import org.openqa.selenium.By;

public interface CartPageLocators {
	By cartItems= By.xpath("//form[@id='activeCartViewForm'] //li //a[@class='a-link-normal sc-product-link']");
	By itemPrice= By.xpath("//div[@class='a-column a-span2 a-text-right a-span-last'] //p //span[@class='currencyINR']/..");
	By subTotal = By.xpath("//span[@id='sc-subtotal-amount-activecart']");
}
