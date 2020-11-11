package Utilities;

import Impl.Battle;
import Impl.Hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class BaseUtility {

    public static Properties connectionProperties;
    PropertiesFileReader obj = new PropertiesFileReader();

    public static RestAssuredUtility api;
    public static HashMap<String, String> headers = new HashMap();
    public static String request = "";
    public static HashMap<String, String> requestBody = new HashMap();
    public static String requestBodiesLocation = System.getProperty("user.dir")+"/src/main/resources/AutomationConfig.xlsx";
    public static ExcelUtility excel = new ExcelUtility();

    public static Battle battle;
    public static ArrayList<Hero> heroes = new ArrayList();

    public BaseUtility(){

        try {

            connectionProperties = obj.getProperty(System.getProperty("user.dir")+"/src/main/resources/connection-config.properties");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String makeJsonRequestBody(HashMap<String, String> values){

        for(String key : values.keySet()){

            if(values.get(key)!=null){
                request = request.replace(key.trim(), values.get(key).trim());
            }else {
                request = request.replace(key.trim(), "null");
            }
        }

        return request;

    }

}