package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getItemCount() {
        return cartItems == null ? 0 : cartItems.size();
    }
}