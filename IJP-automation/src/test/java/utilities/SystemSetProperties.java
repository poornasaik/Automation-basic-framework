package utilities;
//System property setter
public class SystemSetProperties {
	//sets system property for chrome
	public static void chrome() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

	}
	
	//sets system property for firefox
	public static void Firefox() {
		System.setProperty("webdriver.geckodriver", "./src/test/resources/drivers/geckodriver.exe");

	}

}
