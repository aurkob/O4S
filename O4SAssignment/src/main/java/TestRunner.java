import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import static Utilities.PropertiesFileReader.getProperty;

@CucumberOptions(
        features = "src/test/Features/",
        glue = "StepDefinitions",
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {

        Annotation options = this.getClass().getAnnotation(CucumberOptions.class);
        InvocationHandler proxyHandler = Proxy.getInvocationHandler(options);
        Field f = proxyHandler.getClass().getDeclaredField("memberValues");
        f.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) f.get(proxyHandler);
        memberValues.remove("tags");
        memberValues.put("tags", new String [] {String.valueOf(getProperty("src/main/resources/test-data-config.properties").get("TestTag"))});
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature){
        System.out.println("Running feature "+cucumberFeature.toString());
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object [][] features(){ return testNGCucumberRunner.provideFeatures(); }

    @AfterClass(alwaysRun = true)
    public void tearDownClass(){ testNGCucumberRunner.finish(); }

}