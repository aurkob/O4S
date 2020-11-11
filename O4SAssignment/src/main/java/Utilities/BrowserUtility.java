package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BrowserUtility {

    public static WebDriver openBrowser(String browserName, String url, int timeout) throws Exception {

        if(browserName.equals("Chrome")){

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
            return driver;

        }
        else if(browserName.equals("Firefox")){}
        else if(browserName.equals("IE")){}
        return null;
    }

}
