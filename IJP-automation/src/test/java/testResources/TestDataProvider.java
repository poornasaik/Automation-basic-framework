package testResources;

import org.testng.annotations.DataProvider;

import utilities.ExcelDataGetter;

public class TestDataProvider {
	/*
	 * Provide data for other test methods as shown below
	 **/
	
	/* Data Provider for cart verification test*/
	@DataProvider(name = "cartVerification")
	public static Object[][] cartVerification() throws Exception {
		String excelDataFile  ="./src/test/resources/testdata/Data.xlsx"; // specify excel file path 
		String sheetName="ProductDetails"; // specify sheet name
		return ExcelDataGetter.getTableArray(excelDataFile,sheetName); // calling ExcelDataGetter class to retrieve data
	}
	
	
	
}
