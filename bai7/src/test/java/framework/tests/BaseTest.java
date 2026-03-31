package framework.tests;

import framework.base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    protected final String baseUrl = "https://www.saucedemo.com/";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    protected WebDriver getDriver() {
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Lỗi khi đóng browser: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}