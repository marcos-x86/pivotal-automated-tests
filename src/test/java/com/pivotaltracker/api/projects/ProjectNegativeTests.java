package com.pivotaltracker.api.projects;

import com.pivotaltracker.RequestManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

public class ProjectNegativeTests {

    private String projectId;
    private RequestManager requestManager = new RequestManager();
    @Test
    public void postProjectWithoutNameTest(){
        String endpoint = "projects";

        String bodyContent = """
                {
                    "name": ""
                }
                """;

        Response response = requestManager.sendPostRequest(bodyContent, endpoint);

        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 400;

        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        projectId = response.jsonPath().getString("id");

        File schemaFile = new File("src/test/resources/schemas/postProjectSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

        String actualProjectName = response.jsonPath().getString("name");
        String expectedProjectName = "null";

        Assert.assertEquals(actualProjectName, expectedProjectName);
    }

   /* @AfterMethod
    public void deleteProject() {
        String endpoint = "projects/" + projectId;

        Response response = requestManager.sendDeleteRequest(endpoint);
    }*/
}
