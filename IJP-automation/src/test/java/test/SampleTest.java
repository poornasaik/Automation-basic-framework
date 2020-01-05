package test;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class SampleTest {

	public static void main(String[] args) throws InterruptedException, ParseException {

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("mobiles");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).submit();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='a-dropdown-label']")).click();
		List<WebElement> sortList = driver.findElements(By.xpath("//div[@class='a-popover-inner']//li"));
		for (WebElement option : sortList) {
			System.out.println(option.getText());
			if (option.getText().equalsIgnoreCase("Price: Low To High")) {
				option.click();
				break;
			} else {
				System.out.println("err");
			}
		}
		List<WebElement> productsList = driver
				.findElements(By.xpath("//span[@data-component-type='s-search-results'] //h2 //a"));
		String[][] productDetails = new String[3][2];
		String mainWindow = driver.getWindowHandle();
		for (int i = 0; i < 3; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(productsList.get(i)));
			Actions actions = new Actions(driver);
			actions.moveToElement(productsList.get(i)).click().build().perform();
			// Get all the window handles opened in the browser and assign them
			Set<String> setWindows = driver.getWindowHandles();
			Iterator<String> iterateWindows = setWindows.iterator();
			while (iterateWindows.hasNext()) {
				String childWindow = iterateWindows.next();
				System.out.println("checking windows");

				// if the window is not main window then switch to that window
				if (!mainWindow.equalsIgnoreCase(childWindow)) {

					// Switching to Product window
					driver.switchTo().window(childWindow);
					System.out.println("changed window");
					// stop switching the window once moved to next window
					break;
				}
			}

			if ((driver.findElements(By.xpath("//span[@id='priceblock_ourprice']")).size()) > 0) {
				productDetails[i][1] = driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
				productDetails[i][0] = driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
				driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
			} else {

				System.out.println("unable to add product\n replacing with another");
				productsList.remove(i);
				i--;

			}

			Iterator<String> iterateWindows1 = setWindows.iterator();
			while (iterateWindows1.hasNext()) {
				String childWindow = iterateWindows1.next();

				// if the window is not main window then switch to that window
				if (!mainWindow.equalsIgnoreCase(childWindow)) {

					driver.close();
					break;
				}
			}
			driver.switchTo().window(mainWindow);

		}
		float sum = 0;
		float[] price = new float[3];
		for (int i = 0; i < 3; i++) {
			System.out.println(productDetails[i][1].replaceAll("\u20B9|,", "").trim());
			price[i] = Float.parseFloat(productDetails[i][1].replaceAll("\u20B9|,", "").trim());
			sum += price[i];
		}
		System.out.println(sum);

		driver.findElement(By.xpath("//a[@id='nav-cart']")).click();

		// List<WebElement> productsPrice =
		// driver.findElements(By.xpath(xpathExpression));
		// driver.quit();

	}

}
