package com.pivotaltracker.api.projects;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectNegativeTests {
    @Test
    public  void postProjectNegativeTests(){
        String endpoint = "https://www.pivotaltracker.com/services/v5/projects";
        String bodyContent = """
                {
                    "name": " "
                }
                """;
        Response response = RestAssured.given()
                .header("X-TrackerToken","")
                .header("Content-Type","application/json")
                .body(bodyContent)
                .when()
                .post(endpoint);
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;

        Assert.assertEquals(actualStatusCode,expectedStatusCode);
        String actualProjectName  = response.jsonPath().getString("name");
        String expectedProjectName = " ";

        Assert.assertEquals(actualProjectName,expectedProjectName);

    }
}
