package spec;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class TestSpec {
    protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void browserSetup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.WIN10);
        desiredCapabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        driver.manage().window().maximize();
    }

    @AfterTest(alwaysRun = true)
    public void browserExit() {
        driver.quit();
        driver = null;
    }
}
