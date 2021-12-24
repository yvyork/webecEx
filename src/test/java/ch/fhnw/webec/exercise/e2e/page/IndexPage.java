package ch.fhnw.webec.exercise.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IndexPage extends AbstractPage {

    @FindBy(css = ".device-list__heading")
    private List<WebElement> deviceListHeadingElements;

    public IndexPage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public List<String> getDeviceTitles() {
        return this.deviceListHeadingElements.stream().map(WebElement::getText).toList();
    }
}
