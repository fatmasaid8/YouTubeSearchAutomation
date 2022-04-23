package PageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helpers.ExtentReportManager;

public class YoutubeCommons {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//div[@id='search-input']/input")
	@CacheLookup
	WebElement searchTextbox;

	@FindBy(xpath = "//button[@id='search-icon-legacy']")
	@CacheLookup
	WebElement searchButton;

	@FindBy(xpath = "//title[contains(text(),'YouTube')]")
	WebElement pageTitle;

	@FindBy(xpath = "//yt-page-navigation-progress")
	WebElement loader;

	public YoutubeCommons(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(20), Duration.ofMillis(200));
	}

	public void waitForLoader() {
		wait.until(ExpectedConditions.invisibilityOf(loader));
	}

	public void searchForText(String searchText) {
		waitForLoader();
		wait.until(ExpectedConditions.visibilityOf(searchButton));
		wait.until(ExpectedConditions.visibilityOf(searchTextbox));
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchTextbox.click();
		searchTextbox.clear();
		searchTextbox.sendKeys(searchText);
		if (searchTextbox.getText() != searchText) {
			searchTextbox.clear();
			searchTextbox.sendKeys(searchText);
		}
		searchTextbox.sendKeys(Keys.ENTER);
		if (!pageTitle.getText().contains(searchText)) {
			searchTextbox.sendKeys(Keys.ENTER);
		}
		waitForLoader();
		ExtentReportManager.info("Searching for (" + searchText + ") is applied");
	}
}