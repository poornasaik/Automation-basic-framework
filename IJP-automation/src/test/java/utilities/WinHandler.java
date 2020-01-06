package utilities;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WinHandler {
	String mainWindow;
	WebDriver driver;
	int time;
	private static Logger log = LogManager.getLogger(WinHandler.class.getName());

	public WinHandler(WebDriver driver, String mainWindow, int time) {
		this.driver = driver;
		this.mainWindow = mainWindow;
		this.time= time;
	}
	

	public void childWindow() {

		Set<String> setWindows = driver.getWindowHandles();
		Iterator<String> iterateWindows = setWindows.iterator();
		while (iterateWindows.hasNext()) {
			String childWindow = iterateWindows.next();
			if (!mainWindow.equalsIgnoreCase(childWindow)) {
				log.info("Changing to Child window");
				driver.switchTo().window(childWindow);
				waitForPageLoad(driver, time);
				break;
			}
		}
	}

	/*
	 * Change focus to main page using window handles, Note: Window Handle won't
	 * work as the focused window is closed and not available to retrieve its data
	 */
	public void mainWindow() {
		log.info("Changed to main window");
		driver.switchTo().window(mainWindow);
		
		
	}

	public boolean NewWindows(int windowCount, WebDriver driver, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		try{
			wait.until(ExpectedConditions.numberOfWindowsToBe(windowCount));
			return true;

		}
		catch(Exception e) {
			log.error("Window count doesn't Match");
			return false;
			}
		
	}
	
	public static void waitForPageLoad(WebDriver driver, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try{
			wait.until(new Function<WebDriver, Boolean>() {
		        public Boolean apply(WebDriver driver) {
		            return String
		                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
		                .equals("complete");
		        }
		    });
			
		}
		catch(Exception e) {
			log.error("Page load has exceeded the maximum time i.e. "+time+" seconds");
			}
	}
	public void closeChildWindow() {

		String window = driver.getWindowHandle();
		if (!mainWindow.equalsIgnoreCase(window))
		log.info("Closing Child window");
		driver.close();

	}

	public void closeMainWindow() {
		String window = driver.getWindowHandle();
		if (mainWindow.equalsIgnoreCase(window))
		log.info("Closing Main window");
		driver.close();
	}
}
