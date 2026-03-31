package framework.tests;

import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginRefactorTest extends BaseTest {

    private static final String EXCEL_FILE = "login_data.xlsx";

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return ExcelReader.readLoginData(EXCEL_FILE, "LoginCases");
    }

    @Test(dataProvider = "loginData")
    public void testLoginFromExcel(String username, String password, String expected, String description) {
        LoginPage loginPage = new LoginPage(getDriver()).open(baseUrl);

        if ("success".equalsIgnoreCase(expected)) {
            InventoryPage inventoryPage = loginPage.loginSuccess(username, password);
            Assert.assertTrue(inventoryPage.isLoaded(), "Case fail: " + description);
        } else {
            loginPage.loginFailure(username, password);
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Case fail: " + description);
        }
    }
}