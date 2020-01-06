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

public class WinHandler {
	String mainWindow;
	WebDriver driver;
	private static Logger log = LogManager.getLogger(WinHandler.class.getName());

	public WinHandler(WebDriver driver, String mainWindow) {
		this.driver = driver;
		this.mainWindow = mainWindow;
	}
	

	public void childWindow() {

		Set<String> setWindows = driver.getWindowHandles();
		Iterator<String> iterateWindows = setWindows.iterator();

		// Using iterator of sets iterate through all the opened Windows and if they
		// don't match main window navigate to it
		while (iterateWindows.hasNext()) {
			String childWindow = iterateWindows.next();

			// if the window is not main window then switch to that window
			if (!mainWindow.equalsIgnoreCase(childWindow)) {
				log.info("Changing to Child window");
				// Switching to Product window
				driver.switchTo().window(childWindow);

				// stop switching the window once moved to next window
				break;
			}
		}
	}

	/*
	 * Change focus to main page using window handles, Note: Window Handle won't
	 * work as the focused window is closed and not available to retrieve its data
	 */
	public void mainWindow() {
		driver.switchTo().window(mainWindow);
		log.info("Changed to main window");
	}

	public boolean NewWindows(int windowCount, WebDriver driver, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		try{
			wait.until(ExpectedConditions.numberOfWindowsToBe(windowCount));
			return true;
		}
		catch(Exception e) {
			return false;
			}
		
	}
	
	public static void waitForPageLoad(WebDriver driver, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try{
			wait.until(new Function<WebDriver, Boolean>() {
		        public Boolean apply(WebDriver driver) {
		            System.out.println("Current Window State       : "
		                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
		            return String
		                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
		                .equals("complete");
		        }
		    });
			
		}
		catch(Exception e) {
			
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
		if (!mainWindow.equalsIgnoreCase(window))
		log.info("Closing Main window");
		driver.close();
	}
}
