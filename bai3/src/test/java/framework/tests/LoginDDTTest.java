package framework.tests;

import framework.pages.LoginPage;
import framework.utils.ExcelReader;
import framework.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDDTTest extends BaseTest {

    private static final String EXCEL_FILE = "login_data.xlsx";

    @DataProvider(name = "smokeData")
    public Object[][] smokeData() {
        return ExcelReader.getSmokeCases(EXCEL_FILE);
    }

    @DataProvider(name = "regressionData")
    public Object[][] regressionData() {
        return ExcelReader.getAllRegressionCases(EXCEL_FILE);
    }

    @Test(dataProvider = "smokeData", groups = "smoke")
    public void testLoginFromSmokeExcel(TestData data) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(data.getUsername(), data.getPassword());

        Assert.assertTrue(
                loginPage.getCurrentUrl().contains(data.getExpectedUrl()),
                "URL thực tế không chứa expected_url. Description: " + data.getDescription()
        );
    }

    @Test(dataProvider = "regressionData", groups = "regression")
    public void testLoginFromRegressionExcel(TestData data) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(data.getUsername(), data.getPassword());

        if ("SmokeCases".equalsIgnoreCase(data.getSourceSheet())) {
            Assert.assertTrue(
                    loginPage.getCurrentUrl().contains(data.getExpectedUrl()),
                    "URL thực tế không chứa expected_url. Description: " + data.getDescription()
            );
        } else {
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "Không hiển thị thông báo lỗi. Description: " + data.getDescription()
            );

            Assert.assertTrue(
                    loginPage.getErrorMessage().toLowerCase().contains(data.getExpectedError().toLowerCase()),
                    "Thông báo lỗi không đúng. Description: " + data.getDescription()
            );
        }
    }
}