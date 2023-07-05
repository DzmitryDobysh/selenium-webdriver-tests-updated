package test;

import org.testng.annotations.Test;
import page.PasteBingnerMainPage;
import spec.TestSpec;

public class ICanWinTest extends TestSpec {

    private static final String PASTE_TEXT = "Hello from WebDriver";
    private static final String PASTE_TITLE = "helloweb";
    PasteBingnerMainPage pasteBingnerMainPage;

    @Test(description = "Enter all info for ICanWinTest")
    public void enterAllRequiredInfoSimple() throws InterruptedException {
        pasteBingnerMainPage = new PasteBingnerMainPage(driver)
                .openPage()
                .enterNewPaste(PASTE_TEXT)
                .openExpirationOptions()
                .setExpirationTime()
                .enterNewTitle(PASTE_TITLE)
                .submitPaste();
    }

}
