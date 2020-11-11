package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Simulator {

    WebDriver driver;
    public Simulator(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(how= How.XPATH, using="//*[text()='Simulator Widget']")
    public WebElement heading;

    @FindBy(how= How.XPATH, using="//*[text()='Calculate your instalment amount with ']")
    public WebElement sectionHeader;

    @FindBy(how= How.XPATH, using="//*[text()='Minus']")
    public WebElement reduceButton;

    @FindBy(how= How.XPATH, using="//*[text()='Plus']")
    public WebElement increaseButton;

    @FindBy(how= How.NAME, using="input-quotes")
    public WebElement inputBox;

    @FindBy(how= How.XPATH, using="/html/body/div/div/div/div/dl/dt")
    public WebElement valueUnits;

    @FindBy(how= How.ID, using="value-quotes")
    public WebElement outputValue;

    @FindBy(how= How.XPATH, using="/html/body/div/div/div/div/dl/dd/small")
    public WebElement totalAmount;

    public void verifyElements(){

        heading.isDisplayed();
        totalAmount.isDisplayed();
        valueUnits.isDisplayed();
        sectionHeader.isDisplayed();
        inputBox.isDisplayed();
        reduceButton.isDisplayed();
        increaseButton.isDisplayed();
        outputValue.isDisplayed();


       /*
       String in = inputBox.getText();
        if(in.equals("12")){ Assert.assertTrue(!increaseButton.isEnabled()); }
        */
    }




}
