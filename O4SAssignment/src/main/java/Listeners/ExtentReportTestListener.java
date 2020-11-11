package Listeners;

import Utilities.BaseUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportTestListener extends BaseUtility {

    public static ExtentHtmlReporter report = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;

    static ExtentReports extentReportSetup (String suiteName) {

        String reportLocation = "src/test/reports/";
        report = new ExtentHtmlReporter(reportLocation+"FinalExtentReport.html");
        report.config().setDocumentTitle("Cucumber BDD Automation");
        report.config().setReportName("Test Report");
        report.config().setTheme(Theme.STANDARD);
        report.start();

        extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Application", suiteName);
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Impl.User Name", System.getProperty("user.name"));

        return extent;

    }

    public static void testAPIStepHandle(String testStatus, ExtentTest extentTest, Throwable throwable ){

        if ("FAIL".equals(testStatus)) {
            extentTest.fail(MarkupHelper.createLabel("Test Case is Failed!", ExtentColor.RED));
            //    extentTest.error("API request to URL "+ RestAssured.baseURI + api.pathParameter + "\nresponded with\n"+ api.responseBody);
            extentTest.error(throwable.fillInStackTrace());
        } else if ("PASS".equals(testStatus)) {
            extentTest.pass(MarkupHelper.createLabel("Test Case is Passed!", ExtentColor.GREEN));
        }

    }

}