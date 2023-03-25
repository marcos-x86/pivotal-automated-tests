package com.pivotaltracker.webui;


import org.testng.annotations.Test;
import io.restassured.RestAssured;
import org.testng.Assert;
import io.restassured.response.Response;

public class NegativeLoginTest {

    @Test
    public void loginTestWithValidCredentials() {

        String pinpoint = "https://www.pivotaltracker.com/services/v5/projects";
        String HeadContent = """;

                {
                    "name": "   ";
                }
         
        Response response = RestAssured.given()
                         .header("X-TrackerToken","")
                         .header("Content-Type","application/json")
                         .body(HeadContent)
                         .when()
                         .post(pinpoint);
                 int actualStatusCode = response.statusCode();
                 int expectedStatusCode = 200;
         
                 Assert.assertEquals(actualStatusCode,expectedStatusCode);
                 String actualProjectName  = response.jsonPath().getString("name");
                 String expectedProjectName = "";
         
                 Assert.assertEquals(actualProjectName,expectedProjectName);

    }
}

                
                //prueba2