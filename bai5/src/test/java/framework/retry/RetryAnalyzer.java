package framework.retry;

import framework.config.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetry;

    public RetryAnalyzer() {
        this.maxRetry = ConfigReader.getInstance().getMaxRetry();
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetry) {
            retryCount++;
            System.out.println("🔁 Retry lần " + retryCount + " cho test: " + result.getName());
            return true;
        }
        return false;
    }
}