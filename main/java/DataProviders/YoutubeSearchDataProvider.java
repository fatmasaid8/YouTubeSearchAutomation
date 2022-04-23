package DataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Helpers.ExcelReader;

public class YoutubeSearchDataProvider {

	ExcelReader excelReader;

	@DataProvider(name = "youtubeSearchData", parallel = true)
	public Object[][] youtubeSearchDataProvider() throws IOException {
		String filePath = ".\\Resources\\youtubeSearchData.xlsx";
		excelReader = new ExcelReader(filePath);
		Object[][] data = excelReader.getDataObject(1, 4);
		return data;
	}
}