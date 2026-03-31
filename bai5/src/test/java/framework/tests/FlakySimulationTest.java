package framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FlakySimulationTest extends BaseTest {

    private static int callCount = 0;

    @Test(description = "Flaky test – fail 2 lần, pass lần 3")
    public void testFlakyScenario() {

        callCount++;

        System.out.println("👉 Lần chạy thứ: " + callCount);

        if (callCount <= 2) {
            Assert.fail("Mô phỏng lỗi tạm thời – lần " + callCount);
        }

        Assert.assertTrue(true, "Pass ở lần " + callCount);
    }
}