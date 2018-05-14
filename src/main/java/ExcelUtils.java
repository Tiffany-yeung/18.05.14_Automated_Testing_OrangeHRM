

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	// declaring variable names
	public static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	// sets the excel file
	// passes the path and sheet index as arguments
	public static void setExcelFile(String Path, int sheetIndex) {

		try {

			// initiates an excel file to open
			FileInputStream ExcelFile = new FileInputStream(Path);

			// specifying the excel file and sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheetAt(sheetIndex);

		} catch (Exception e) {

			// if exception occurs, show red errors in the console
			e.printStackTrace();

		}

	}

	// getting the cell data, reads the test data from the excel cell
	// pass the row and column indexes as arguments
	public static String getCellData(int RowNum, int ColNum) {

		try {

			// specify the row and column index
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			// gets the string value from the cell
			String CellData = Cell.getStringCellValue();

			// returns the string
			return CellData;

		} catch (Exception e) {

			// if exception occurs, show red errors in the console
			e.printStackTrace();
			return "";

		}

	}

	// setting data (writing into) the cell
	// pass row and column indexes, and the string into cell as arguments
	public static void setCellData(String Result, int RowNum, int ColNum) {

		try {

			// gets the row index
			Row = ExcelWSheet.getRow(RowNum);

			// gets the cell index and if there is no data then return null
			Cell = Row.getCell(ColNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);

			// if the cell is null
			if (Cell == null) {

				// create a new cell
				Cell = Row.createCell(ColNum);

				// put a value in the cell
				Cell.setCellValue(Result);

			} else {

				// if there is data in the cell, overwrite it with new value
				Cell.setCellValue(Result);

			}

			// retrieves data from the excel file and writes it in the XSSF workbook
			// input = excel, output = XSSF workbook
			// initiates the path and file of test data
			FileOutputStream fileOut = new FileOutputStream(Constants.pathTestData + Constants.fileTestData);

			// writes the data into XSSFWorkbook (ExcelWBook is the variable name for it)
			ExcelWBook.write(fileOut);

			// closes the stream
			fileOut.flush();

			// closes the file
			fileOut.close();

		} catch (Exception e) {

			// if exception occurs, show red errors in the console
			e.printStackTrace();

		}

	}

}