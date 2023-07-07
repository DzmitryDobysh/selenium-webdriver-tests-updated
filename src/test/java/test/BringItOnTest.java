package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.PasteBingnerMainPage;
import page.PasteBingnerSubmittedPage;
import spec.TestSpec;

import java.time.Duration;

public class BringItOnTest extends TestSpec {

    private static final String PASTE_TEXT = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String PASTE_TITLE = "how to gain dominance among developers";

    private static final String SUBMITTED_PASTE_TITLE_LOCATOR = "//body/div[2]/span/strong";

    protected static final String EXPECTED_PASTE_SYNTAX = "Bash";

    PasteBingnerMainPage pasteBingnerMainPage;
    PasteBingnerSubmittedPage pasteBingnerSubmittedPage;

    @Test(description = "Enter all info for BringItOnTest", groups = "main")
    public void enterAllRequiredInfoExtended() {
        pasteBingnerMainPage = new PasteBingnerMainPage(driver)
                .openPage()
                .enterNewPaste(PASTE_TEXT)
                .openSyntaxDropdown()
                .searchSyntax(EXPECTED_PASTE_SYNTAX)
                .selectBashSyntax()
                .openExpirationOptions()
                .setExpirationTime()
                .enterNewTitle(PASTE_TITLE)
                .submitPaste();
        pasteBingnerSubmittedPage = new PasteBingnerSubmittedPage(driver);
    }

    @Test(description = "Check if submitted paste title matches initial paste title", dependsOnGroups = "main")
    public void checkSubmittedPastePageTitle() {
        WebElement titleSubmittedPaste = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUBMITTED_PASTE_TITLE_LOCATOR)));
        String actualPageTitle = titleSubmittedPaste.getText();

        Assert.assertEquals(actualPageTitle, PASTE_TITLE, "Submitted paste title doesn't match initial paste title");
    }

    @Test(description = "Check if syntax highlighting is Bash", dependsOnGroups = "main")
    public void checkSubmittedPasteSyntaxHighlighting() {
        String actualSyntax = pasteBingnerSubmittedPage.getSyntaxSubmittedPaste();

        Assert.assertEquals(actualSyntax, EXPECTED_PASTE_SYNTAX, "Submitted paste syntax highlighting is not Bash");
    }

    @Test(description = "Check if submitted paste text matches initial text", dependsOnGroups = "main")
    public void checkSubmittedPasteText() {
        String actualText = pasteBingnerSubmittedPage.getSubmittedPasteText();

        Assert.assertEquals(actualText, PASTE_TEXT, "Submitted paste text doesn't match initial paste text");
    }
}
