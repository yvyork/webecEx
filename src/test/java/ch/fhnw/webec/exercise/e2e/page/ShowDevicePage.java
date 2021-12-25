package ch.fhnw.webec.exercise.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ShowDevicePage extends AbstractPage {

    @FindBy(css = "h1")
    private WebElement headingElement;

    @FindBy(css = ".device-detail__description")
    private WebElement descriptionElement;

    @FindBy(css = "[value=\"Delete\"]")
    private WebElement deleteButtonElement;

    @FindBy(css = ".location-list__item")
    private List<WebElement> locationItemElements;

    @FindBy(css = ".status-list__item")
    private List<WebElement> statusItemElement;

    @FindBy(id = "review-rating")
    private WebElement ratingInputElement;

    @FindBy(id = "review-comment")
    private WebElement commentInputElement;

    @FindBy(css = ".reviews__add-form [type=\"submit\"]")
    private WebElement submitButtonElement;

    @FindBy(className = "review")
    private List<WebElement> reviewElements;

    public ShowDevicePage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public String getHeading() {
        return this.headingElement.getText();
    }

    public String getDescription() {
        return this.descriptionElement.getText();
    }

    public List<String> getLocationNames() {
        return this.locationItemElements.stream().map(WebElement::getText).toList();
    }

    public AbstractPage deleteDevice() {
        this.deleteButtonElement.click();
        this.webDriver.switchTo().alert().accept();

        if (this.webDriver.getCurrentUrl().contains("/devices")) {
            return this;
        } else {
            return new IndexPage(this.webDriver, this.port);
        }
    }

    public ShowDevicePage addLocation(String location) {
        this.setLocation(location);

        return this.submitForm();
    }

//    public void setStatus(String Status) {
//        var ratingSelect = new Select(this.ratingInputElement);
//
//        ratingSelect.selectByValue("" + rating);
//    }

    public void setLocation(String comment) {
        this.commentInputElement.clear();
        this.commentInputElement.sendKeys(comment);
    }

    public ShowDevicePage submitForm() {
        this.submitButtonElement.click();

        return this;
    }

    public List<String> getReviewComments() {
        return this.reviewElements.stream().map(
            reviewElement -> reviewElement.findElement(By.cssSelector(".review__comment")).getText()
        ).toList();
    }
}
