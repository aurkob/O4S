package Utilities;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

public class RestAssuredUtility extends BaseUtility{

    public RequestSpecification httpRequest;
    public Response response;
    public String responseBody, statusLine;
    public Integer statusCode;
    public Headers responseHeaders;
    public Long responseTime;
    public String pathParameter;


    public void getCall(String baseURI, HashMap<String, String> headers, String pathParameter){

        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();

        putHeaders(headers);

        this.pathParameter = pathParameter;
        response = httpRequest.request(Method.GET, this.pathParameter);
        getResponseVariables(response);
    }

    public void postCall(String baseURI, String pathParameter, HashMap<String, String> headers, String requestBody){

        try {

            RestAssured.baseURI = baseURI;
            httpRequest = RestAssured.given();

            httpRequest.body(requestBody);
            putHeaders(headers);

            this.pathParameter = pathParameter;

            response = httpRequest.request(Method.POST, this.pathParameter);

        } catch (Exception e){

            Assert.fail(e.toString());

        }

        getResponseVariables(response);

    }

    public void getResponseVariables(Response response){

        responseBody = response.getBody().asString();
        statusCode = response.getStatusCode();
        statusLine = response.getStatusLine();
        responseTime = response.getTime();
        responseHeaders = response.headers();

    }

    public void deleteCall(String baseURI, String pathParameter, HashMap<String, String> headers, String requestBody ){

        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();

        httpRequest.body(requestBody);
        putHeaders(headers);

        this.pathParameter = pathParameter;
        response = httpRequest.request(Method.DELETE, this.pathParameter);
        getResponseVariables(response);

    }


    public void putHeaders(HashMap<String, String> map){

        for(String key:map.keySet()) {
            httpRequest.headers(key, map.get(key));
        }
    }

    public ArrayList<Object> getListFromResponse(String key){

        JsonPath json = new JsonPath(response.asString());

        return (ArrayList<Object>) json.getList(key);


    }

    public String getValueFromResponse(String key){

        JsonPath json = new JsonPath(response.asString());

        return json.getString(key);


    }





}