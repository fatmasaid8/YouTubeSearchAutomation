package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Helpers.ExtentReportManager;

public class YoutubeWatchPage extends YoutubeCommons {

	@FindBy(xpath = "//h1[contains(@class,'info-renderer')][contains(@class,'title')]/yt-formatted-string")
	WebElement videoTitle;

	public YoutubeWatchPage(WebDriver driver) {
		super(driver);
	}

	public String getVideoTitle() {
		waitForLoader();
		wait.until(ExpectedConditions.visibilityOf(videoTitle));
		String title = videoTitle.getText();
		ExtentReportManager.info("Video title in watch page is (" + title + ")");
		return title;
	}
}