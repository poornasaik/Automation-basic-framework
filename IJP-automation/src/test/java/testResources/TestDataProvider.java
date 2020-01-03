package testResources;

import org.testng.annotations.DataProvider;

import utilities.ExcelDataGetter;

public class TestDataProvider {

	// Data Provider for Valid Login
	@DataProvider(name = "cartVerification")
	public static Object[][] cartVerification() throws Exception {
		String excelDataFile  ="./src/test/resources/testdata/Data.xlsx";
		String sheetName="ProductDetails";
		return ExcelDataGetter.getTableArray(excelDataFile,sheetName);
	}


}
