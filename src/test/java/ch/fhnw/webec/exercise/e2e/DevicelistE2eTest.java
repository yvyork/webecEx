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

        this.indexPage.doSearch("Apple MacBook Air");

        assertEquals(3, this.indexPage.getDeviceTitles().size());
        assertTrue(this.indexPage.getDeviceTitles().contains("Apple MacBook Air"));
    }

    @Test
    public void testSearchDeviceLocation() {
        this.indexPage.goToIndexPage();

        assertEquals(5, this.indexPage.getDeviceTitles().size());

        this.indexPage.doSearch("Nordflügel");

        assertEquals(1, this.indexPage.getDeviceTitles().size());
        assertTrue(this.indexPage.getDeviceTitles().contains("Apple MacBook Air"));
    }

    @Test
    public void testShowDevice() {
        var showDevicePage = this.indexPage.goToShowDevicePage(1);

        assertEquals("Test book 1", showDevicePage.getHeading());
        assertEquals("Test book 1 description", showBookPage.getDescription());
        assertTrue(showBookPage.getTopicNames().contains("test topic 1"));
        assertTrue(showBookPage.getTopicNames().contains("test topic 2"));
    }

}
