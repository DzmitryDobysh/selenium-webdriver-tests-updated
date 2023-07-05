package spec;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseSpec {

    protected WebDriver driver;

    protected final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(10);

    protected WebElement waitForWebElementVisible(WebElement element) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(element));
    }

    protected BaseSpec(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
