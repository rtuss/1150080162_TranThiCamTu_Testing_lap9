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

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage login(String username, String password) {
        typeText(usernameField, username);
        typeText(passwordField, password);
        clickElement(loginButton);
        return this;
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        try {
            return errorMessage.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}