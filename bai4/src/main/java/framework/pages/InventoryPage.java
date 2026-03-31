package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".inventory_item button")
    private WebElement firstAddToCartButton;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return isElementDisplayed(inventoryList);
    }

    public InventoryPage addFirstItemToCart() {
        clickElement(firstAddToCartButton);
        return this;
    }

    public CartPage goToCart() {
        clickElement(cartLink);
        return new CartPage(driver);
    }
}