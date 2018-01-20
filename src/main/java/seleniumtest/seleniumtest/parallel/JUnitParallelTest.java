package seleniumtest.seleniumtest.parallel;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;

@RunWith(Parallelized.class)
public class JUnitParallelTest {
    private String os;
    private String osVersion;
    private String browserName;
    private String browserVersion;

    //Hold all Configuration values in a LinkedList
    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"Windows","XP", "chrome", "48.0"});
        env.add(new String[]{"Windows", "7","firefox", "44.0"});
        env.add(new String[]{"Windows", "8","IE","10.0"});
        env.add(new String[]{"Windows", "10","Edge","13.0"});
        env.add(new String[]{"OS X","El Capitan", "Safari","9.0"});
        //add more browsers here
        return env;
    }

    //Constructor
    public JUnitParallelTest(String os, String osVersion, String browserName, String browserVersion) {
        this.os = os;
        this.osVersion = osVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        //Set DesiredCapabilities
        DesiredCapabilities capability = new DesiredCapabilities();
        //capability.setCapability("platform", platform);
        capability.setCapability("os", os);
        capability.setCapability("os_version", osVersion);
        capability.setCapability("browser", browserName);
        capability.setCapability("browserVersion", browserVersion);
        capability.setCapability("build", "JUnit-Parallel");
        //Connect to BrowserStack with your credentials
        driver = new RemoteWebDriver(
                new URL("http://chenbaibang1:C6rDEqEY2VF5yXVVgRqD@hub.browserstack.com/wd/hub"),
                capability
        );
    }

    @Test
    public void testSimple() throws Exception {
        //Go to swtestacademy and check title
        driver.get("http://www.swtestacademy.com");
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        assertEquals("Title is not correct!", "Software Test Academy", driver.getTitle());

        //ScreenShot Section
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("Screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
