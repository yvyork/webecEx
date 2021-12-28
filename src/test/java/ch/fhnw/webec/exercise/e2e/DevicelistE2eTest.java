package ch.fhnw.webec.exercise.e2e;

import ch.fhnw.webec.exercise.e2e.page.IndexPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(WebDriverConfiguration.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DevicelistE2eTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebDriver webDriver;
    private IndexPage indexPage;

    @BeforeEach
    public void setUp() {
        this.indexPage = new IndexPage(this.webDriver, this.port);
    }

    @Test
    public void testSearchDeviceTitles() {
        this.indexPage.goToIndexPage();

        assertEquals(5, this.indexPage.getDeviceTitles().size());

        this.indexPage.doSearch("MacBook Air Apple");

        assertEquals(0, this.indexPage.getDeviceTitles().size());
        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air Apple"));
    }

    @Test
    public void testSearchDeviceLocation() {
        this.indexPage.goToIndexPage();

        assertEquals(5, this.indexPage.getDeviceTitles().size());

        this.indexPage.doSearch("Nordflügel");

        assertEquals(0, this.indexPage.getDeviceTitles().size());
        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air Apple"));
    }

    @Test
    public void testShowDevice() {
        var showDevicePage = this.indexPage.goToShowDevicePage(1);

        assertEquals("Model: MacBook Air", showDevicePage.getHeading());
        assertEquals("Manufacturer: Apple", showDevicePage.getManufacturer());
        assertEquals("Serial Number: ABC", showDevicePage.getSerialNumber());
        assertEquals("Display Size: 13\"", showDevicePage.getDisplaySize());
        //assertEquals("Processor: Intel Chip", showDevicePage.getProcessor());
        //assertEquals("Memory: 16GB", showDevicePage.getMemory());
        //assertEquals("Purchase Date: 2008-01-01", showDevicePage.getPurchasedDate());
       //assertTrue(showDevicePage.getLocationNames().contains("Nordflügel"));
        //assertTrue(showDevicePage.getStatusNames().contains("new"));
    }
}
