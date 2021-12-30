package ch.fhnw.webec.exercise.e2e;

import ch.fhnw.webec.exercise.e2e.page.AddOrEditDevicePage;
import ch.fhnw.webec.exercise.e2e.page.IndexPage;
import ch.fhnw.webec.exercise.e2e.page.ShowDevicePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
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
        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air"));
    }

    @Test
    public void testSearchDeviceLocation() {
        this.indexPage.goToIndexPage();

        assertEquals(5, this.indexPage.getDeviceTitles().size());

        this.indexPage.doSearch("Nord");

        assertEquals(2, this.indexPage.getDeviceTitles().size());
        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air"));
    }

    @Test
    public void testSearchDeviceStatus() {
        this.indexPage.goToIndexPage();

        assertEquals(5, this.indexPage.getDeviceTitles().size());

        this.indexPage.doSearch("new");
        assertEquals(2, this.indexPage.getDeviceTitles().size());
        assertTrue(this.indexPage.getDeviceTitles().contains("MacBook Air"));
    }

    @Test
    public void testShowDevice() {
        var showDevicePage = this.indexPage.goToShowDevicePage(1);

        assertEquals("MacBook Air", showDevicePage.getHeading());
        assertEquals("Manufacturer: Apple", showDevicePage.getManufacturer());
        assertEquals("Serial Number: ABC", showDevicePage.getSerialNumber());
        assertEquals("Display Size: 13\"", showDevicePage.getDisplaySize());
        assertEquals("Processor: Intel Chip", showDevicePage.getProcessor());
        assertEquals("Memory: 16GB", showDevicePage.getMemory());
        assertEquals("Purchase Date: 2008-01-01", showDevicePage.getPurchasedDate());
        assertEquals("Location: Nordfluegel EG01", showDevicePage.getLocation());
        assertEquals("Status: new", showDevicePage.getStatus());
    }

    @Test
    @DirtiesContext
    public void testAddDevice() {
        var addDevicePage = this.indexPage.goToAddDevicePage();
        var abstractPage = addDevicePage.addDevice("XYZ", "MacBook Air next generation", "16GB", "Apple",
                "13\"", "M1", "2022-01-01", List.of("Suedfluegel EG01"), List.of("new"));

        this.indexPage.doSearch("MacBook Air next generation");

        if (abstractPage instanceof ShowDevicePage showDevicePage) {
            assertEquals("Serial Number: XYZ", showDevicePage.getSerialNumber());
            assertEquals("Display Size: 13\"", showDevicePage.getDisplaySize());
            assertEquals("Processor: M1", showDevicePage.getProcessor());
            assertEquals("Memory: 16GB", showDevicePage.getMemory());
            assertEquals("Purchase Date: 2022-01-01", showDevicePage.getPurchasedDate());
            assertEquals("Location: Suedfluegel EG01", showDevicePage.getLocation());
            assertEquals("Status: new", showDevicePage.getStatus());
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

    @Test
    @DirtiesContext
    public void testEditDevice() {
        var showDevicePage = this.indexPage.goToShowDevicePage(1);
        assertEquals("MacBook Air", showDevicePage.getHeading());

        var editDevicePage = showDevicePage.goToEditDevicePage(1);

        editDevicePage.setModel("MacBook Air next generation");
        //var abstractPage = editDevicePage.submitForm();

        //if (abstractPage instanceof ShowDevicePage showDevicePageEdited) {
//            assertEquals("MacBook Air next generation", showDevicePageEdited.getModel());
//        } else {
//            fail();
//        }
    }

    @Test
    @DirtiesContext
    public void testDeleteDevice() {
        if (this.webDriver instanceof HtmlUnitDriver) {
            return;
        }

        var indexPage = this.indexPage.goToIndexPage();
        assertTrue(indexPage.getDeviceTitles().contains("MacBook SuperMegaGeil"));

        var showDevicePage = this.indexPage.goToShowDevicePage(2);
        assertEquals("MacBook SuperMegaGeil", showDevicePage.getHeading());

        var abstractPage = showDevicePage.deleteDevice();

        if (abstractPage instanceof IndexPage indexPageEdited) {
            assertFalse(indexPageEdited.getDeviceTitles().contains("MacBook SuperMegaGeil"));
        } else {
            fail();
        }

    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }
}
