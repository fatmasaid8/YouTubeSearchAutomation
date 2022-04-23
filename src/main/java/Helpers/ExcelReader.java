package Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	XSSFWorkbook wb;
	XSSFSheet sheet;

	public ExcelReader(String filePath) {
		try {
			File src = new File(filePath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			ExtentReportManager.fail("Exception while reading excel file: " + e.getMessage());
		}
	}

	public String getCellData(int sheetNum, int row, int column) {
		sheet = wb.getSheetAt(sheetNum - 1);
		DataFormatter formatter = new DataFormatter();
		Cell cell = sheet.getRow(row).getCell(column);
		String data = formatter.formatCellValue(cell);
		return data;
	}

	public int getRowCount(int sheetIndex) {
		int row = wb.getSheetAt(sheetIndex - 1).getLastRowNum() + 1;
		return row;
	}

	public Object[][] getDataObject(int sheetIndex, int colNum) {
		int rowsNum = getRowCount(sheetIndex);
		Object[][] data = new Object[rowsNum][colNum];

		for (int i = 0; i < rowsNum; i++) {
			for (int j = 0; j < colNum; j++) {
				data[i][j] = getCellData(1, i, j);
			}
		}
		return data;
	}
}