package ch.fhnw.webec.exercise.e2e;

import ch.fhnw.webec.exercise.e2e.page.AddOrEditDevicePage;
import ch.fhnw.webec.exercise.e2e.page.IndexPage;
import ch.fhnw.webec.exercise.e2e.page.ShowDevicePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        this.indexPage.doSearch("MacBook Air");

        assertEquals(3, this.indexPage.getDeviceTitles().size());
//        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air"));
    }

    @Test
    public void testSearchDeviceLocation() {
        this.indexPage.goToIndexPage();

        assertEquals(5, this.indexPage.getDeviceTitles().size());

        this.indexPage.doSearch("Nord");

        assertEquals(2, this.indexPage.getDeviceTitles().size());
//        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air"));
    }

    @Test
    public void testSearchDeviceStatus() {
        this.indexPage.goToIndexPage();

        assertEquals(5, this.indexPage.getDeviceTitles().size());

        this.indexPage.doSearch("new");
        assertEquals(2, this.indexPage.getDeviceTitles().size());
        //        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air"));
    }

    @Test
    public void testShowDevice() {
        var showDevicePage = this.indexPage.goToShowDevicePage(1);

        assertEquals("Model: MacBook Air", showDevicePage.getHeading());
        assertEquals("Manufacturer: Apple", showDevicePage.getManufacturer());
        assertEquals("Serial Number: ABC", showDevicePage.getSerialNumber());
        //assertEquals("Display Size: 13\"", showDevicePage.getDisplaySize());
        //assertEquals("Processor: Intel Chip", showDevicePage.getProcessor());
        //assertEquals("Memory: 16GB", showDevicePage.getMemory());
        //assertEquals("Purchase Date: 2008-01-01", showDevicePage.getPurchasedDate());
       //assertTrue(showDevicePage.getLocationNames().contains("Nordflügel"));
        //assertTrue(showDevicePage.getStatusNames().contains("new"));
    }

    @Test
    @DirtiesContext
    public void testAddDevice() {
        var addDevicePage = this.indexPage.goToAddDevicePage();
        var abstractPage = addDevicePage.addDevice("XYZ", "MacBook Air next generation", "16GB", "Apple",
                "13\"", "M1", "2022-01-01", List.of("Suedfluegel EG01"), List.of("new"));

        this.indexPage.doSearch("MacBook Air next generation");

        if (abstractPage instanceof ShowDevicePage showDevicePage) {
            assertEquals("Serialnumber: XYZ, Display size: 13\", Processor: M1, Memory: 16GB, Purchase date: 2022-01-01", showDevicePage.getDeviceInformationElement());
        } else {
            fail();
        }
    }

    @Test
    public void testAddDeviceInvalid() {
        var addDevicePage = this.indexPage.goToAddDevicePage();
        var abstractPage = addDevicePage.addDevice("XYZ", "", "", "",
                "", "", "", List.of("Suedfluegel EG01"), List.of("new"));

        if (abstractPage instanceof AddOrEditDevicePage addOrEditDevicePage) {
            assertEquals(1, addDevicePage.getFieldErrors("device-model").size());
            assertTrue(addDevicePage.getFieldErrors("device-model").contains("must not be empty"));
        } else {
            fail();
        }
    }
}
