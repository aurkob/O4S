package StepDefinitions;

import Impl.Battle;
import Impl.Hero;
import Utilities.RestAssuredUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Random;

public class HeroesStepDefinitions extends StepDefs{


    public HeroesStepDefinitions()  { }


    @Given("^Get list of heroes$")
    public void get_list_of_heroes() {

        ExtentTest logInfo = null;

        try {

            test = extent.createTest(Feature.class, "Heroes");
            test = test.createNode(Scenario.class, "Validate correct functionalities of API");
            logInfo = test.createNode(new GherkinKeyword("Given"), "Get list of heroes!");

            api = new RestAssuredUtility();
            headers.put("Authorization", "Bearer pag4nt1stoken");
            api.getCall(connectionProperties.get("Host").toString(), headers, "/heroes");
            ArrayList<Object>  heroIds = api.getListFromResponse("id");
            ArrayList<Object> heroNames = api.getListFromResponse("name");
            ArrayList<Object> powerLevels = api.getListFromResponse("powerlevel");

            for(int i=0; i<heroIds.size(); i++){ heroes.add(new Hero(heroIds.get(i).toString(), heroNames.get(i).toString(), powerLevels.get(i).toString())); }


            logInfo.pass("Status code is "+api.response.statusCode());
            logInfo.pass("Status code is "+api.responseBody);

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @Given("^Choose and add distinct random hero$")
    public void choose_add_random_hero() {

        ExtentTest logInfo = null;

        try {


            logInfo = test.createNode(new GherkinKeyword("Given"), "Choose and add random hero!");

            battle = new Battle();

            Hero hero = heroes.get(new Random().nextInt(heroes.size()));

            while(battle.heroes.contains(hero)){ hero = heroes.get(new Random().nextInt(heroes.size())); }

            hero.joinFight();

            logInfo.pass("Status code is "+api.statusCode);
            logInfo.pass("Response is "+api.responseBody);

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @Given("^Verify fight cannot start$")
    public void verify_fight_cannot_start() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("Given"), "Verify fight cannot start!");

            battle.startBattle();

            logInfo.pass("Status code is "+api.statusCode);
            logInfo.pass("Response is "+api.responseBody);

            if(battle.heroes.size()<2){
            Assert.assertEquals(api.getValueFromResponse("error"), "You can not start a fight with less than 2 heroes");}
            else if(battle.heroes.size()>3){
                //Assert.assertEquals(api.getValueFromResponse("error"), "You can not start a fight with less than 2 heroes");
            }

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @Given("^Verify same hero cannot be added to fight$")
    public void verify_same_hero_cannot_be_added() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("Given"), "Verify same hero cannot be added!");

            battle.heroes.get(0).joinFight();

            logInfo.pass("Status code is "+api.statusCode);
            logInfo.pass("Response is "+api.responseBody);

            //Assert.assertEquals(api.getValueFromResponse("error"), "You can not start a fight with less than 2 heroes");}

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @Given("^Start fight$")
    public void start_fight() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("Given"), "Verify fight start!");

            battle.startBattle();

            logInfo.pass("Status code is "+api.statusCode);
            logInfo.pass("Response is "+api.responseBody);

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @Given("^Verify hero cant be added anymore$")
    public void verify_hero_cannot_be_added() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("And"), "Verify more than 3 heroes cannot join battle!");

            Hero hero = heroes.get(new Random().nextInt(heroes.size()));

            while(battle.heroes.contains(hero)){ hero = heroes.get(new Random().nextInt(heroes.size())); }

            logInfo.pass("Status code is "+api.statusCode);
            logInfo.pass("Response is "+api.responseBody);

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @And("^Verify winner has highest power level$")
    public void verify_winner_has_highest_power() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("And"), "Verify more than 3 heroes cannot join battle!");


           //code for asserting winner of battle is hero in battle with highest power

            logInfo.pass("Status code is "+api.statusCode);
            logInfo.pass("Response is "+api.responseBody);

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

    @And("^Reset battle$")
    public void reset_battle() {

        ExtentTest logInfo = null;

        try {

            logInfo = test.createNode(new GherkinKeyword("And"), "Reset battle!");

            battle.reset();

            logInfo.pass("Status code is "+api.statusCode);
            logInfo.pass("Response is "+api.responseBody);

        } catch (AssertionError | Exception e) {

            testAPIStepHandle("FAIL", logInfo, e);

        }

    }

}
