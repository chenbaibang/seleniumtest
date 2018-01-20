package seleniumtest.seleniumtest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabsTest {
	static WebDriver driver;
	public static final String USERNAME = "Chenbaibang";
	public static final String ACCESS_KEY = "1f8b2a21-9b67-4f13-9486-e5a526f97ecf";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

	@BeforeClass
	public static void setupTest() throws MalformedURLException {
		// Desired Capabilities
		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("platform", Platform.ANDROID);
		//caps.setCapability("version", "20");
		driver = new RemoteWebDriver(new URL(URL), caps);
	}

	@Test
	public void SauceLabsTest() {
		// Go to www.swtestacademy.com
		driver.get("http://www.swtestacademy.com/");

		// Check title is correct
		assertEquals("Title is not correct!", "Software Test Academy", driver.getTitle());
	}

	@AfterClass
	public static void quitDriver() {
		driver.quit();
	}
}
