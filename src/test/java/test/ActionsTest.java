package test;

import org.testng.annotations.Test;
import page.FormyProjectPage;
import spec.TestSpec;

public class ActionsTest extends TestSpec {

    FormyProjectPage formyProjectPage;

    @Test(description = "Perform different actions for ActionsTest", groups = "main")
    public void performActionsSimple() {
        formyProjectPage = new FormyProjectPage(driver)
                .openDragAndDropPage()
                .dragAndDropAction()
                .returnToMainPage()
                .openFileUploadPage()
                .uploadNewFile()
                .returnToMainPage()
                .highlightText()
                .openModalPage()
                .triggerModal();
    }
}
