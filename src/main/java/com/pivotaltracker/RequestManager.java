package com.pivotaltracker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestManager {

    private ConfigReader configReader = new ConfigReader();
    private String tokenHeaderKey = "X-TrackerToken";
    private String tokenHeaderValue = configReader.getToken();
    private String contentTypeHeaderKey = "Content-Type";
    private String contentTypeHeaderValue = "application/json";
    private String urlBase = configReader.getApiUrl();


    public RequestManager() {

    }

    public Response sendPostRequest(String bodyContent, String endpoint) {
        Response response = RestAssured.given()
                .header(tokenHeaderKey, tokenHeaderValue)
                .header(contentTypeHeaderKey, contentTypeHeaderValue)
                .body(bodyContent)
                .when()
                .post(urlBase + endpoint);
        return response;
    }

    public Response sendGetRequest(String endpoint) {
        Response response = RestAssured.given()
                .header(tokenHeaderKey, tokenHeaderValue)
                .when()
                .get(urlBase + endpoint);
        return response;
    }

    public Response sendDeleteRequest(String endpoint) {
        Response response = RestAssured.given()
                .header(tokenHeaderKey, tokenHeaderValue)
                .header(contentTypeHeaderKey, contentTypeHeaderValue)
                .when()
                .delete(urlBase + endpoint);
        return response;
    }
}
