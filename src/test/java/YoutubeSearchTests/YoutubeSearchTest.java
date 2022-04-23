package YoutubeSearchTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BrowserManager.BrowserLauncher;
import DataProviders.YoutubeSearchDataProvider;
import Helpers.ExtentReportManager;
import PageObjects.YoutubeSearchResultsPage;
import PageObjects.YoutubeWatchPage;

/**
 *
 * @author FatmaSaid
 *
 **/


public class YoutubeSearchTest {
	@Test (enabled = true, dataProvider = "youtubeSearchData", dataProviderClass = YoutubeSearchDataProvider.class)
	public void youtubeTitleAssertion(String searchText, String type, String option, String videoNumber) throws InterruptedException, IOException {
		SoftAssert sa = new SoftAssert();
		WebDriver driver = BrowserLauncher.startBrowser("chrome", "http://www.youtube.com");
		YoutubeSearchResultsPage searchResult = PageFactory.initElements(driver, YoutubeSearchResultsPage.class);
		YoutubeWatchPage watchPage = PageFactory.initElements(driver, YoutubeWatchPage.class);
		ExtentReportManager.createTest("YouTube Video Title assertion - Searching for ("+ searchText + ") and getting result #" + videoNumber);
		searchResult.searchForText(searchText);
		searchResult.filterBy(type, option);
		String title = searchResult.getVideoTitleByNumber(videoNumber);
		searchResult.clickOnVideoNumber(videoNumber);
		sa.assertEquals(watchPage.getVideoTitle(), title);
		sa.assertTrue(driver.getTitle().contains(title));
		driver.quit();
		sa.assertAll();
		ExtentReportManager.pass("Page title in video watch page is same as in search results");
	}

}
