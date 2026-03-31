package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".inventory_item button")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".shopping_cart_badge")
    private List<WebElement> cartBadge;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return isElementDisplayed(inventoryList);
    }

    public InventoryPage addFirstItemToCart() {
        if (!addToCartButtons.isEmpty()) {
            clickElement(addToCartButtons.get(0));
        }
        return this;
    }

    public int getCartCount() {
        try {
            if (cartBadge.isEmpty()) {
                return 0;
            }
            return Integer.parseInt(cartBadge.get(0).getText().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public CartPage goToCart() {
        clickElement(cartLink);
        return new CartPage(driver);
    }
}