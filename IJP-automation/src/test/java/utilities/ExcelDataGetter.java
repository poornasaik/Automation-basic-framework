package utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataGetter {
	static Workbook workbook;
	static Sheet sheet;

	public static Object[][] getTableArray(String File, String SheetName) throws Exception {
		// Create a Work book to open the excel file
		InputStream excelFile = new FileInputStream(File);
		workbook = WorkbookFactory.create(excelFile);

		// get the sheet from the excel file
		sheet = workbook.getSheet(SheetName);

		// create integers to iterate the object array
		int ci = 0, cj;

		// get the last row of the sheet
		int totalRows = sheet.getLastRowNum();

		// get number of columns of the sheet calling the method countNonEmptyColumns
		int totalCols = countNonEmptyColumns(sheet);

		// create a string array with array size equals to number of rows and columns
		String[][] tabArray = new String[totalRows][totalCols];

		// get the cell data and store it in array
		for (int i = 1; i <= totalRows; i++, ci++) {
			cj = 0;
			for (int j = 0; j < totalCols; j++, cj++) {

				// get cell data
				tabArray[ci][cj] = getCellData(i, j);
			}
		}

		return tabArray; // return the data to data provider method
	}

	// method to get cell data in string format need to create for other formats
	public static String getCellData(int RowNum, int ColNum) {

		Cell cellValue = sheet.getRow(RowNum).getCell(ColNum); // get the cell and store in Cell variable

		String CellData;

		// compare the cell and if cell is null then send null data
		if ((cellValue == null) || (cellValue.getCellType() == CellType.BLANK)) {
			CellData = "";
		}

		// else send the cell value
		else if((cellValue.getCellType() == CellType.NUMERIC)){
			CellData = Double.toString((cellValue.getNumericCellValue()));
		}
		else {
			CellData = cellValue.getStringCellValue();
		}
		return CellData;
	}

	// method to count number of columns
	private static int countNonEmptyColumns(final Sheet sheet) {

		// get the first row
		Row firstRow = sheet.getRow(0);

		// return the first row
		return firstEmptyCellPosition(firstRow); // gets the column count and returns it
	}

	// method to get first blank cell in the row and return number of cells
	private static int firstEmptyCellPosition(final Row firstRowCells) {
		int columnCount = 0; /*
								 * Columns and rows starts from 0, iterate through all the cells in the first
								 * row until the blank cell is reached
								 */
		for (Cell cell : firstRowCells) {
			if (cell.getCellType() == CellType.BLANK) {
				break; // Break the loop once the blank cell is reached
			}
			columnCount++;// Increment the column count
		}
		return columnCount; // Return the column count
	}

}