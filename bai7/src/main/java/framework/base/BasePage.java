package framework.base;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void clickElement(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(d -> element.isDisplayed() && element.isEnabled());
                element.click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Không thể click element");
    }

    protected void typeText(WebElement element, String text) {
        wait.until(d -> element.isDisplayed());
        element.clear();
        if (text != null) {
            element.sendKeys(text);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}