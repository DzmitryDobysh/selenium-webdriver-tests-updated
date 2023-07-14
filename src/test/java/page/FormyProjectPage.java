package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import spec.BaseSpec;

public class FormyProjectPage extends BaseSpec {

    /*
    Used direct link to 'dragdrop' due to Uncaught TypeError: $(...).draggable is not a function
    error can be present if accessed via main page
     */
    String FP_BASE_URL = "https://formy-project.herokuapp.com/dragdrop";

    @FindBy(id = "image")
    WebElement dragImage;

    @FindBy(id = "box")
    WebElement dropBox;

    @FindBy(id = "logo")
    WebElement mainPageButton;

    @FindBy(xpath = "//a[@class='btn btn-lg' and @href='/fileupload']")
    WebElement fileUploadPage;

    @FindBy(xpath = "//*[@id=\"file-upload-field\"]")
    WebElement fieldUploadField;

    @FindBy(css = "h1.display-3")
    WebElement mainPageText;

    @FindBy(xpath = "//a[@class='btn btn-lg' and @href='/modal']")
    WebElement modalPage;

    @FindBy(id = "modal-button")
    WebElement modalButton;

    @FindBy(id = "close-button")
    WebElement closeModalButton;

    public FormyProjectPage(WebDriver driver) {
        super(driver);
    }

    public FormyProjectPage openDragAndDropPage() {
        driver.get(FP_BASE_URL);
        return this;
    }

    public FormyProjectPage dragAndDropAction() {
        waitForWebElementVisible(dragImage);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragImage, dropBox).build().perform();
        return this;
    }

    public FormyProjectPage returnToMainPage() {
        mainPageButton.click();
        return this;
    }

    public FormyProjectPage openFileUploadPage() {
        waitForWebElementVisible(fileUploadPage).click();
        return this;
    }

    public FormyProjectPage uploadNewFile() {
        waitForWebElementVisible(fieldUploadField);
        fieldUploadField.sendKeys("cat-file.png");
        return this;
    }

    public FormyProjectPage highlightText() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = 'yellow';", mainPageText);
        js.executeScript("arguments[0].style.border = '2px solid red';", mainPageText);
        return this;
    }

    public FormyProjectPage openModalPage() {
        waitForWebElementVisible(modalPage).click();
        return this;
    }

    public FormyProjectPage triggerModal() {
        waitForWebElementVisible(modalButton);
        modalButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", closeModalButton);
        return this;
    }
}
