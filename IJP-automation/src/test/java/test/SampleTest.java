package test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SampleTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("pens");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).submit();
		Select sort= new Select(driver.findElement(By.xpath("//select[@name='s']")));
		sort.selectByVisibleText("Price: Low to High");
		List<WebElement> productsList = driver.findElements(By.xpath("//span[@data-component-type='s-search-results'] //h2 //a"));
		String[][] productDetails= new String[3][2];
		String mainWindow = driver.getWindowHandle();
		for(int i=0;i<3;i++) {
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
				}
				else if((driver.findElements(By.xpath("//span[@id='priceblock_dealprice']")).size())>0) {
					productDetails[i][1]=driver.findElement(By.xpath("//span[@id='priceblock_dealprice']")).getText();
					}
				else {
					
					System.out.println("unable to add product");
				}

			driver.close();
			Thread.sleep(2000);
			driver.switchTo().window(mainWindow);
			
			
			
		}
		for (int i=0; i<3; i++) {
			System.out.println(productDetails[i][1]+" ");	
		}
		
		//List<WebElement> productsPrice = driver.findElements(By.xpath(xpathExpression));
		//driver.quit();
		
	}

}
