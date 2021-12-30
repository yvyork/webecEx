package ch.fhnw.webec.exercise.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddOrEditDevicePage  extends AbstractPage {

    @FindBy(id = "device-serialNumber")
    private WebElement serialNumberInputElement;

    @FindBy(id = "device-model")
    private WebElement modelInputElement;

    @FindBy(id = "device-memory")
    private WebElement memoryInputElement;

    @FindBy(id = "device-manufacturer")
    private WebElement manufacturerInputElement;

    @FindBy(id = "device-displaySize")
    private WebElement displaySizeInputElement;

    @FindBy(id = "device-processor")
    private WebElement processorInputElement;

    @FindBy(id = "device-purchaseDate")
    private WebElement purchaseDateInputElement;

    @FindBy(id = "device-location")
    private WebElement locationsInputElement;

    @FindBy(id = "device-status")
    private WebElement statusInputElement;

    @FindBy(css = ".form [type=\"submit\"]")
    private WebElement submitButtonElement;

    public AddOrEditDevicePage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public AbstractPage addDevice(String serialNumber, String model, String memory, String manufacturer,
                                String displaySize, String processor, String purchaseDate,
                                List<String> locations, List<String> status) {
        this.setSerialNumber(serialNumber);
        this.setModel(model);
        this.setMemory(memory);
        this.setManufacturer(manufacturer);
        this.setDisplaySize(displaySize);
        this.setProcessor(processor);
        this.setPurchaseDate(purchaseDate);
        this.setLocations(locations);
        this.setStatus(status);

        return this.submitForm();
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumberInputElement.clear();
        this.serialNumberInputElement.sendKeys(serialNumber);
    }

    public void setModel(String model) {
        this.modelInputElement.clear();
        this.modelInputElement.sendKeys(model);
    }

    public void setMemory(String memory) {
        this.memoryInputElement.clear();
        this.memoryInputElement.sendKeys(memory);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturerInputElement.clear();
        this.manufacturerInputElement.sendKeys(manufacturer);
    }

    public void setDisplaySize(String displaySize) {
        this.displaySizeInputElement.clear();
        this.displaySizeInputElement.sendKeys(displaySize);
    }

    public void setProcessor(String processor) {
        this.processorInputElement.clear();
        this.processorInputElement.sendKeys(processor);
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDateInputElement.clear();
        this.purchaseDateInputElement.sendKeys(purchaseDate);
    }

    public void setLocations(List<String> locations) {
        var locationsSelect = new Select(this.locationsInputElement);

        locationsSelect.deselectAll();

        for (var location : locations) {
            locationsSelect.selectByVisibleText(location);
        }
    }

    public void setStatus(List<String> statuses) {
        var statusSelect = new Select(this.statusInputElement);

        statusSelect.deselectAll();

        for (var status : statuses) {
            statusSelect.selectByVisibleText(status);
        }
    }

    public AbstractPage submitForm() {
        this.submitButtonElement.click();

        if (this.webDriver.getCurrentUrl().contains("/devices/add")) {
            return this;
        } else {
            return new ShowDevicePage(this.webDriver, this.port);
        }
    }

    public String getSerialNumber() {
        return this.serialNumberInputElement.getText();
    }

    public String getModel() {
        return this.modelInputElement.getText();
    }

    public String getMemory() {
        return this.memoryInputElement.getText();
    }

    public String getManufacturer() {
        return this.manufacturerInputElement.getText();
    }

    public String getDisplaySize() {
        return this.displaySizeInputElement.getText();
    }

    public String getProcessor() {
        return this.processorInputElement.getText();
    }

    public String getPurchaseDate() {
        return this.purchaseDateInputElement.getText();
    }

    public List<String> getFieldErrors(String fieldName) {
        return this.webDriver.findElements(
                By.cssSelector(".form__field-" + fieldName + " .form__error")
        ).stream().map(WebElement::getText).toList();
    }
}
