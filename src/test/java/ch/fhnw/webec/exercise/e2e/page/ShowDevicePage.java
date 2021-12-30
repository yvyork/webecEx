package ch.fhnw.webec.exercise.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShowDevicePage extends AbstractPage {

    @FindBy(css = "h2")
    private WebElement headingElement;

    @FindBy(css = "heading heading--size-2")
    private WebElement modelElement;

    @FindBy(css = ".device-detail_manufacturer")
    private WebElement manufacturerElement;

    @FindBy(css = ".device-detail_serialNumber")
    private WebElement serialNumberElement;

    @FindBy(css = ".device-detail_displaySize")
    private WebElement displaySizeElement;

    @FindBy(css = ".device-detail_processor")
    private WebElement processorElement;

    @FindBy(css = ".device-detail_memory")
    private WebElement memoryElement;

    @FindBy(css = ".device-detail_purchaseDate")
    private WebElement purchaseDateElement;

    @FindBy(css = ".device-detail_location")
    private WebElement locationElement;

    @FindBy(css = ".device-detail_status")
    private WebElement statusElement;

    @FindBy(css = "[value=\"Edit\"]")
    private WebElement editButtonElement;

    @FindBy(css = "[value=\"Delete\"]")
    private WebElement deleteButtonElement;

    @FindBy(css = "[value=\"Back\"]")
    private WebElement backButtonElement;

    public ShowDevicePage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public String getHeading() {
        return this.headingElement.getText();
    }

    public String getManufacturer() {
        return this.manufacturerElement.getText();
    }

    public String getModel() {
        return this.modelElement.getText();
    }

    public String getSerialNumber() {
        return this.serialNumberElement.getText();
    }

    public String getDisplaySize() {
        return this.displaySizeElement.getText();
    }

    public String getProcessor() {
        return this.processorElement.getText();
    }

    public String getMemory() {
        return this.memoryElement.getText();
    }

    public String getPurchasedDate() {
        return this.purchaseDateElement.getText();
    }

    public String getLocation() {
        return this.locationElement.getText();
    }

    public String getStatus() {
        return this.statusElement.getText();
    }

    public AbstractPage deleteDevice() {
        this.deleteButtonElement.click();
        //this.webDriver.switchTo().alert().accept();

        if (this.webDriver.getCurrentUrl().contains("/devices")) {
            return this;
        } else {
            return new IndexPage(this.webDriver, this.port);
        }
    }

    public AbstractPage backDevice() {
        this.backButtonElement.click();
        this.webDriver.switchTo().alert().accept();

        if (this.webDriver.getCurrentUrl().contains("/devices")) {
            return this;
        } else {
            return new IndexPage(this.webDriver, this.port);
        }
    }

    public void setModel(String model) {
        this.modelElement.clear();
        this.modelElement.sendKeys(model);
    }
}