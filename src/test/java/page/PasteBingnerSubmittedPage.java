package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import spec.BaseSpec;

public class PasteBingnerSubmittedPage extends BaseSpec {
    @FindBy(xpath = "//body/div[2]/span/span")
    WebElement syntaxSubmittedPaste;

    @FindBy(xpath = "//*[@id=\"code\"]")
    WebElement textSubmittedPaste;

    public PasteBingnerSubmittedPage(WebDriver driver) {
        super(driver);
    }

    public String getSyntaxSubmittedPaste() {
        return syntaxSubmittedPaste.getText();
    }

    public String getSubmittedPasteText() {
        return textSubmittedPaste.getText();
    }
}
