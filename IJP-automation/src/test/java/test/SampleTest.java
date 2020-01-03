package test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
		Select sort= new Select(driver.findElement(By.xpath("//select[@name='s']")));
		sort.selectByVisibleText("Price: Low to High");
		List<WebElement> productsList = driver.findElements(By.xpath("//span[@data-component-type='s-search-results'] //h2 //a"));
		String[][] productDetails= new String[3][2];
		String mainWindow = driver.getWindowHandle();
		for(int i=0;i<3;i++) {
			wait.until(ExpectedConditions.elementToBeClickable(productsList.get(i)));
			productsList.get(i).click();
			// Get all the window handles opened in the browser and assign them
			Set<String> setWindows = driver.getWindowHandles();
			Iterator<String> iterateWindows = setWindows.iterator();
			while (iterateWindows.hasNext()) {
				String childWindow = iterateWindows.next();

				// if the window is not main window then switch to that window
				if (!mainWindow.equalsIgnoreCase(childWindow)) {

					// Switching to Product window
					driver.switchTo().window(childWindow);
					// stop switching the window once moved to next window
					break;
				}
			}

				productDetails[i][0]= driver.findElement(By.xpath("//span[@id='productTitle']")).getText();
				if((driver.findElements(By.xpath("//span[@id='priceblock_ourprice']")).size())>0) {
				productDetails[i][1]=driver.findElement(By.xpath("//span[@id='priceblock_ourprice']")).getText();
				driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
				}
				else if((driver.findElements(By.xpath("//span[@id='priceblock_dealprice']")).size())>0) {
					productDetails[i][1]=driver.findElement(By.xpath("//span[@id='priceblock_dealprice']")).getText();
					if (driver.findElements(By.xpath("//a[@title='Add to Shopping Cart']")).size()>0) {
						driver.findElement(By.xpath("//a[@title='Add to Shopping Cart']")).click();
					}
					else if (driver.findElements(By.xpath("//input[@id='add-to-cart-button']")).size()>0) {
						driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
					}
					}
				else {
					
					System.out.println("unable to add product\n replacing with another");
					productsList.remove(i);
					i--;

				}
				
			
			driver.close();
			
			driver.switchTo().window(mainWindow);
			
		}
		float sum =0;
		float[] price= new float[3];
		for (int i=0; i<3; i++) {
			System.out.println(productDetails[i][1].replaceAll("\u20B9|,", "").trim());
			price[i]= Float.parseFloat(productDetails[i][1].replaceAll("\u20B9|,", "").trim());
			sum+=price[i];
		}
		System.out.println(sum);
		
		driver.findElement(By.xpath("//a[@id='nav-cart']")).click();
		
		
		
		
		//List<WebElement> productsPrice = driver.findElements(By.xpath(xpathExpression));
		//driver.quit();
		
		
		
	}

}
