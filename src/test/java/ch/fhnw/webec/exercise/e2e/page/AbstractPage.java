package ch.fhnw.webec.exercise.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

abstract public class AbstractPage {

    protected final WebDriver webDriver;
    protected final int port;

    public AbstractPage(WebDriver webDriver, int port) {
        this.webDriver = webDriver;
        this.port = port;

        PageFactory.initElements(webDriver, this);
    }

    public void doSearch(String search) {
        this.webDriver.navigate().to(this.getUriBuilder()
            .queryParam("search", search).build().toString());
    }

    public AddOrEditDevicePage goToAddDevicePage() {
        this.webDriver.navigate().to(this.getUriBuilder()
            .path("/devices/add").build().toString());

        return new AddOrEditDevicePage(this.webDriver, this.port);
    }
//
//    public AddOrEditDevicePage goToEditDevicePage(int deviceId) {
//        this.webDriver.navigate().to(this.getUriBuilder()
//            .path("/devices/" + deviceId + "/edit").build().toString());
//
//        return new AddOrEditDevicePage(this.webDriver, this.port);
//    }

    public IndexPage goToIndexPage() {
        this.webDriver.navigate().to(this.getUriBuilder()
            .path("/").build().toString());

        return new IndexPage(this.webDriver, this.port);
    }

    public ShowDevicePage goToShowDevicePage(int deviceId) {
        this.webDriver.navigate().to(this.getUriBuilder()
            .path("/devices/" + deviceId).build().toString());

        return new ShowDevicePage(this.webDriver, this.port);
    }

    public UriBuilder getUriBuilder() {
        return UriComponentsBuilder.fromUriString("http://localhost:%d/".formatted(this.port));
    }
}
