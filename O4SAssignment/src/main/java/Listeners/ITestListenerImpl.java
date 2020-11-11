package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerImpl extends ExtentReportTestListener implements ITestListener {

    private static ExtentReports extent;
    ExtentTest test;

    public void onTestStart(ITestResult result) { System.out.println("Test started!"); }

    public void onTestSuccess(ITestResult result) { System.out.println("Test Passed!"); }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed!");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println(" Test Skipped!");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }


    public void onStart(ITestContext context) {

        System.out.println("Tests Started!");
        extent = extentReportSetup("API Suite");
        test = extent.createTest(context.getName());

    }


    public void onFinish(ITestContext context) {

        System.out.println("Tests Finished!");
        extent.flush();
        System.out.println("Generated Report!");

    }
}
