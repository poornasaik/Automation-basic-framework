package utilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Verify {
	private static Logger log = LogManager.getLogger(Verify.class.getName());
	public static WebElement getElement(By locatorValue, int time, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, time);

		try {
			wait.until((ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(locatorValue),
					ExpectedConditions.numberOfElementsToBe(locatorValue, 1))));
			WebElement srchElement = driver.findElement(locatorValue);
			log.info("Found element with locator:"+locatorValue+" and returned");
			return srchElement;
		} catch (Exception e) {
			log.error("unable to locate the element using locator: "+locatorValue);
			Assert.fail("Element not found");
		}
		return null;
	}

	public static List<WebElement> getElements(By locatorValue, int time, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorValue));
			List<WebElement> srchElement = driver.findElements(locatorValue);
			log.info("Found element with locator:"+locatorValue+" and returned");
			return srchElement;
		} catch (Exception e) {
			log.error("unable to locate elements using locator: "+locatorValue);
			Assert.fail("Element not found using locator " + locatorValue + " due to :" + e);
		}
		return null;
	}

	public static int getNumElements(By locatorValue, WebDriver driver) {
		int number = 0;
		try {
			number = driver.findElements(locatorValue).size();
			log.info("Found "+number+" elements using locator:"+locatorValue+" and returned");
		} catch (Exception e) {
			log.error("Unknown exception using: "+locatorValue);
			Assert.fail("Unknown exception " + locatorValue + " due to :" + e);
		}
		return number;
	}

	public static void click(WebElement interactElement, int time, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(interactElement));
			interactElement.click();
			log.info("Element clicked using: "+ interactElement);
		} catch (StaleElementReferenceException sere) {
			log.error("unable to locate elements using locator: "+interactElement);
			Assert.fail("Element is stale");
		} catch (ElementNotInteractableException enie) {
			try {
				log.info("Element not interactable, trying actions");
				Actions actions = new Actions(driver);
				actions.moveToElement(interactElement).click().build().perform();
			} catch (Exception e) {
				log.error("Unable to interact with element because of exception " + e +" using: "+interactElement);
				System.out.println("Unable to interact with element because of exception:" + e);
				Assert.fail("Element is not interactable");
			}

		}

	}

}
