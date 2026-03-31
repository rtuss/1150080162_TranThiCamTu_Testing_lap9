package framework.tests;

import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartRefactorTest extends BaseTest {

    @Test
    public void testAddFirstItemToCart() {
        LoginPage loginPage = new LoginPage(getDriver()).open(baseUrl);
        InventoryPage inventoryPage = loginPage.loginSuccess("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isLoaded(), "Không vào được InventoryPage");

        inventoryPage.addFirstItemToCart();
        Assert.assertEquals(inventoryPage.getCartCount(), 1, "Cart badge phải tăng lên 1");
    }

    @Test
    public void testGoToCartAfterAddItem() {
        LoginPage loginPage = new LoginPage(getDriver()).open(baseUrl);
        InventoryPage inventoryPage = loginPage.loginSuccess("standard_user", "secret_sauce");

        CartPage cartPage = inventoryPage
                .addFirstItemToCart()
                .goToCart();

        Assert.assertEquals(cartPage.getItemCount(), 1, "Giỏ hàng phải có 1 sản phẩm");
    }
}