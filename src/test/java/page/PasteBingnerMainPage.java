package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spec.BaseSpec;

public class PasteBingnerMainPage extends BaseSpec {

    private final String BASE_URL = "https://paste.bingner.com";

    @FindBy(id = "code-editor")
    WebElement textAreaNewPaste;

    @FindBy(id = "expirationButton")
    WebElement expirationOptions;

    @FindBy(xpath = "//button[text()='Ten Minutes']")
    WebElement expirationTime;

    @FindBy(id = "editable-paste-title")
    WebElement pasteTitle;

    @FindBy(xpath = "//button[@data-original-title='Save']")
    WebElement buttonNewPaste;

    public PasteBingnerMainPage(WebDriver driver) {
        super(driver);
    }

    public PasteBingnerMainPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public PasteBingnerMainPage enterNewPaste(String newPasteText) {
        waitForWebElementVisible(textAreaNewPaste).sendKeys(newPasteText);
        return this;
    }

    public PasteBingnerMainPage openExpirationOptions() {
        expirationOptions.click();
        return this;
    }

    public PasteBingnerMainPage setExpirationTime() {
        waitForWebElementVisible(expirationTime).click();
        return this;
    }

    public PasteBingnerMainPage enterNewTitle(String newTitleText) {
        pasteTitle.sendKeys(newTitleText);
        return this;
    }

    public PasteBingnerMainPage submitPaste() {
        buttonNewPaste.click();
        return this;
    }
}
