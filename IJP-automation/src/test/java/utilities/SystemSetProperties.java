package utilities;

//System property setter
public class SystemSetProperties {
	//sets system property for chrome
	public static void chrome() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

	}
	
	//sets system property for firefox
	public static void Firefox() {
		
		//DesiredCapabilities capability = DesiredCapabilities.firefox();
		//capability.setCapability("marionette", false);
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");

	}

}
