package BrowserManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLauncher {

	private static BrowserLauncher instance = null;
	static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public static BrowserLauncher getInstance() {
		if (instance == null) {
			instance = new BrowserLauncher();
		}
		return instance;
	}

	public static WebDriver startBrowser(String browserName, String url) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//Resources//chromedriver.exe");
			webDriver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".//Resources//geckodriver.exe");
			webDriver.set(new FirefoxDriver());
		}
		webDriver.get().manage().window().maximize();
		webDriver.get().get(url);
		return webDriver.get();
	}

	public WebDriver getDriver() {
		return webDriver.get();
	}
}
