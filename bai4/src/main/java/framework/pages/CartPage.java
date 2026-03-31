package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage goToCheckout() {
        clickElement(checkoutButton);
        return new CheckoutPage(driver);
    }
}