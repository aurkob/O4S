package Impl;

import Utilities.BaseUtility;
import Utilities.RestAssuredUtility;

public class Hero extends BaseUtility {

    String id;
    String name;
    int power;

    public Hero(String id, String name, String power) {
        this.id = id;
        this.name = name;
        this.power = Integer.parseInt(power);
    }

    public void joinFight() {

        api = new RestAssuredUtility();

        request = excel.readExcelData("JSONData",requestBodiesLocation, "joinFight");

        requestBody.clear();
        requestBody.put("{heroId}", this.id);

        api.postCall("http://localhost:3000","/fight/addHero", headers, makeJsonRequestBody(requestBody));
    }
}
