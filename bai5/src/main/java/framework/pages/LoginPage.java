package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".login_logo")
    private WebElement loginLogo;

    public LoginPage(WebDriver driver, int explicitWait) {
        super(driver, explicitWait);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return isElementDisplayed(loginLogo)
                && isElementDisplayed(usernameField)
                && isElementDisplayed(passwordField)
                && isElementDisplayed(loginButton);
    }

    public LoginPage login(String username, String password) {
        typeText(usernameField, username);
        typeText(passwordField, password);
        clickElement(loginButton);
        return this;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}