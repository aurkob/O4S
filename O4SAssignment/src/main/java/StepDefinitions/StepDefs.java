package StepDefinitions;

import Listeners.ExtentReportTestListener;
import Utilities.BrowserUtility;
import org.openqa.selenium.WebDriver;

public class StepDefs extends ExtentReportTestListener {

    public static WebDriver driver;

    public void createBrowser(String browser, String URL, String timeout) throws Exception {

        driver = BrowserUtility.openBrowser(browser, URL, Integer.parseInt(timeout));
    }
}
