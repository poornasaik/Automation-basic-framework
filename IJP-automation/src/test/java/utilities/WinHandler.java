package utilities;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WinHandler {
	String mainWindow;
	WebDriver driver;
	
	public WinHandler(WebDriver driver, String mainWindow) {
		this.driver=driver;
		this.mainWindow=mainWindow;
	}


	public void childWindow(){

		Set<String> setWindows = driver.getWindowHandles();
		Iterator<String> iterateWindows = setWindows.iterator();

		// Using iterator of sets iterate through all the opened Windows and if they
		// don't match main window navigate to it
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
	}

	/*
	 * Change focus to main page using window handles, Note: Window Handle won't
	 * work as the focused window is closed and not available to retrieve its data
	 */
	public void mainWindow() {
		driver.switchTo().window(mainWindow);
	}
	public void closeChildWindow() {
		String window = driver.getWindowHandle();
		if (!mainWindow.equalsIgnoreCase(window))
		driver.close();
	}
}
