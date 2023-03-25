package com.pivotaltracker.api.projects;

import com.pivotaltracker.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectNegativeTests {

    private ConfigReader configReader = new ConfigReader();
    @Test
    public void postProjectNegativeTests(){
        String endpoint = configReader.getApiUrl() + "projects";
        String bodyContent = """
                {
                    "name": ""
                }
                """;
        Response response = RestAssured.given()
                .header("X-TrackerToken",configReader.getToken())
                .header("Content-Type","application/json")
                .body(bodyContent)
                .post(endpoint);
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 400;

        Assert.assertEquals(actualStatusCode,expectedStatusCode);

        java.io.File schemaFile = new java.io.File("src/test/resources/schemas/postProjectSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
        String actualProjectName = response.jsonPath().getString("name");
        String expectedProjectName = null;

        Assert.assertEquals(actualProjectName,expectedProjectName);
        System.out.println("Error, there is no name for the project");
    }
}
