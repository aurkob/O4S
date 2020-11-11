package StepDefinitions;

import Listeners.ExtentReportTestListener;
import PageObjects.Simulator;
import Utilities.BaseUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import cucumber.api.java.en.Given;

public class SimulatorStepDefinitions extends StepDefs {


    public SimulatorStepDefinitions()  { }


    @Given("^Open browser to Index.html$")
    public void open_browser_to() {

        ExtentTest logInfo = null;

        try {

            test = extent.createTest(Feature.class, "Installment simulator");
            test = test.createNode(Scenario.class, "Validate correct amounts for duration given");
            logInfo = test.createNode(new GherkinKeyword("Given"), "Open browser and navigate to simulator!");
            createBrowser("Chrome", "file:///"+System.getProperty("user.dir")+"//src/main/resources/index.html","10");

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @Given("^Verify correct page layout$")
    public void verify_correct_page_layout() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("Given"), "Verify correct page layout!");

            new Simulator(driver).verifyElements();

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @Given("^Verify correct values for monthly installment for all months$")
    public void verify_correct_values_for_months() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("Given"), "Verify correct values for given durations!");

           // new Simulator(driver).verifyElements();

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }
}
