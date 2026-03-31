package framework.tests;

import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EnvironmentTest extends BaseTest {

    @Test
    public void testConfigReaderLoadsCorrectEnvironment() {
        String env = System.getProperty("env", "dev");

        System.out.println("Test đang chạy với env = " + env);
        System.out.println("URL thực tế = " + config.getBaseUrl());
        System.out.println("Explicit wait thực tế = " + config.getExplicitWait());

        Assert.assertEquals(config.getEnv(), env, "ConfigReader đọc sai môi trường");

        if ("dev".equals(env)) {
            Assert.assertEquals(config.getExplicitWait(), 15, "Dev phải có explicit.wait = 15");
        } else if ("staging".equals(env)) {
            Assert.assertEquals(config.getExplicitWait(), 20, "Staging phải có explicit.wait = 20");
        }
    }

    @Test
    public void testLoginPageLoadsFromConfiguredUrl() {
        LoginPage loginPage = new LoginPage(driver, config.getExplicitWait());

        Assert.assertTrue(loginPage.isLoaded(), "Trang login không load được từ URL trong config");
        Assert.assertTrue(
                loginPage.getCurrentUrl().contains("saucedemo"),
                "URL hiện tại không đúng theo config"
        );
    }
}