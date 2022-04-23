package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentReports;

import Helpers.ExtentReportManager;

public class YoutubeSearchResultsPage extends YoutubeCommons {
	ExtentReports extentReport;

	@FindBy(xpath = "//div[@id='filter-menu']//*[@id='button']")
	WebElement filterBtn;

	public YoutubeSearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public void filterBy(String type, String option) {
		waitForLoader();
		wait.until(ExpectedConditions.visibilityOf(filterBtn));
		wait.until(ExpectedConditions.elementToBeClickable(filterBtn));
		filterBtn.click();
		WebElement filterOption = driver.findElement(By.xpath("//h4[@id='filter-group-name']//" + "*[text()='" + type
				+ "']/../..//*[text()='" + option + "']/../.."));
		wait.until(ExpectedConditions.elementToBeClickable(filterOption));
		filterOption.click();
		ExtentReportManager.info("Filtering search results by (" + type + ": " + option + ") is applied");
	}

	public String getVideoTitleByNumber(String number) {
		String videoTitle = driver.findElement(By.xpath(
				"//ytd-video-renderer[" + number + "]//div[@id='meta']" + "//a[@id='video-title']/yt-formatted-string"))
				.getText();
		ExtentReportManager.info("Video #" + number + " title from search results is (" + videoTitle + ")");
		return videoTitle;
	}

	public void clickOnVideoNumber(String number) {
		WebElement video = driver.findElement(By.xpath("//ytd-video-renderer[" + number + "]//a[@id='thumbnail']"));
		wait.until(ExpectedConditions.elementToBeClickable(video));
		video.click();
		ExtentReportManager.info("Clicking on video #" + number + " from search results");
	}
}
