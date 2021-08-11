package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pages.BasePage;

public class Listeners implements ITestListener {
    private static Logger logger = LogManager.getLogger(BasePage.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info(iTestResult.getName()+" - STARTED");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(iTestResult.getName()+" - COMPLETED SUCCESSFULLY");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.fatal(iTestResult.getName()+" - FAILED");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.warn(iTestResult.getName()+" - SKIPPED");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
