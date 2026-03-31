package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage fillCheckoutForm(String firstName, String lastName, String postalCode) {
        typeText(firstNameField, safeValue(firstName));
        typeText(lastNameField, safeValue(lastName));
        typeText(postalCodeField, safeValue(postalCode));

        System.out.println("Checkout data: "
                + safeValue(firstName) + " | "
                + safeValue(lastName) + " | "
                + safeValue(postalCode));

        return this;
    }

    public CheckoutPage clickContinue() {
        clickElement(continueButton);
        sleep(800);
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

    public boolean isStepTwoLoaded() {
        try {
            return driver.getCurrentUrl().contains("checkout-step-two");
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            return "";
        }
    }

    private String safeValue(String value) {
        return value == null ? "" : value.trim();
    }
}