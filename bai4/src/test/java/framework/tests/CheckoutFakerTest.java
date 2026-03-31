package framework.tests;

import java.util.Map;

import framework.pages.CartPage;
import framework.pages.CheckoutPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutFakerTest extends BaseTest {

    @Test
    public void testCheckoutWithRandomData() {
        Map<String, String> checkoutData = TestDataFactory.randomCheckoutData();

        System.out.println("Run 1 - First Name: " + checkoutData.get("firstName"));
        System.out.println("Run 1 - Last Name: " + checkoutData.get("lastName"));
        System.out.println("Run 1 - Postal Code: " + checkoutData.get("postalCode"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isLoaded(), "Không vào được InventoryPage");

        CartPage cartPage = inventoryPage
                .addFirstItemToCart()
                .goToCart();

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.fillCheckoutForm(
                checkoutData.get("firstName"),
                checkoutData.get("lastName"),
                checkoutData.get("postalCode")
        ).clickContinue();

        String currentUrl = checkoutPage.getCurrentUrl();
        System.out.println("Run 1 - Current URL: " + currentUrl);

        if (!checkoutPage.isStepTwoLoaded()) {
            System.out.println("Run 1 - Checkout error: " + checkoutPage.getErrorMessage());
        }

        Assert.assertTrue(
                checkoutPage.isStepTwoLoaded(),
                "Không chuyển được sang bước 2 của checkout. URL = "
                        + currentUrl
                        + " | Error = "
                        + checkoutPage.getErrorMessage()
        );
    }

    @Test
    public void testCheckoutRandomDataRunAgain() {
        Map<String, String> checkoutData = TestDataFactory.randomCheckoutData();

        System.out.println("Run 2 - First Name: " + checkoutData.get("firstName"));
        System.out.println("Run 2 - Last Name: " + checkoutData.get("lastName"));
        System.out.println("Run 2 - Postal Code: " + checkoutData.get("postalCode"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isLoaded(), "Không vào được InventoryPage");

        CartPage cartPage = inventoryPage
                .addFirstItemToCart()
                .goToCart();

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.fillCheckoutForm(
                checkoutData.get("firstName"),
                checkoutData.get("lastName"),
                checkoutData.get("postalCode")
        ).clickContinue();

        String currentUrl = checkoutPage.getCurrentUrl();
        System.out.println("Run 2 - Current URL: " + currentUrl);

        if (!checkoutPage.isStepTwoLoaded()) {
            System.out.println("Run 2 - Checkout error: " + checkoutPage.getErrorMessage());
        }

        Assert.assertTrue(
                checkoutPage.isStepTwoLoaded(),
                "Không chuyển được sang bước 2 của checkout. URL = "
                        + currentUrl
                        + " | Error = "
                        + checkoutPage.getErrorMessage()
        );
    }
}