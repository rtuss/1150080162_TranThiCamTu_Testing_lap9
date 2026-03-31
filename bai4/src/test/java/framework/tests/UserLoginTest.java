package framework.tests;

import framework.model.UserData;
import framework.pages.LoginPage;
import framework.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest {

    private static final String JSON_FILE = "users.json";

    @DataProvider(name = "userJsonData")
    public Object[][] userJsonData() {
        return JsonReader.getUsersAsDataProvider(JSON_FILE);
    }

    @Test(dataProvider = "userJsonData")
    public void testUserLoginFromJson(UserData user) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getUsername(), user.getPassword());

        if (user.isExpectSuccess()) {
            Assert.assertTrue(
                    loginPage.getCurrentUrl().contains("inventory"),
                    "Expected login success nhưng không vào được trang inventory. Case: " + user.getDescription()
            );
        } else {
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "Expected login failure nhưng không thấy thông báo lỗi. Case: " + user.getDescription()
            );
        }
    }
}