package Impl;

import Utilities.BaseUtility;
import Utilities.RestAssuredUtility;

import java.util.ArrayList;

public class Battle extends BaseUtility {

    public static ArrayList<Hero> heroes = new ArrayList<>();

    public void startBattle(){

        api = new RestAssuredUtility();

        requestBody.clear();

        api.postCall(connectionProperties.get("Host").toString(),"/fight",headers,makeJsonRequestBody(requestBody));

    }

    public void reset() {

        api = new RestAssuredUtility();

        requestBody.clear();

        api.deleteCall(connectionProperties.get("Host").toString(),"/fight",headers,makeJsonRequestBody(requestBody));

    }
}
