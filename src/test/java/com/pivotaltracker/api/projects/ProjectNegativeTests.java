package com.pivotaltracker.api.projects;

import com.pivotaltracker.RequestManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ProjectNegativeTests {

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

        File schemaFile = new File("src/test/resources/schemas/postProjectSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

        String actualErrorMessage = response.jsonPath().getString("general_problem");
        String expectedErrorMessage = "this endpoint requires the parameter: name";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void postProjectWithMoreThan50Chars(){
        String endpoint = "projects";

        String bodyContent = """
                {
                    "name": "postProjectWithMoreThan50CharsInTheNameAPITestHildaQuiroz"
                }
                """;

        Response response = requestManager.sendPostRequest(bodyContent, endpoint);

        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 400;

        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        File schemaFile = new File("src/test/resources/schemas/postProjectSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

        String actualErrorMessage = response.jsonPath().getString("general_problem");
        String expectedErrorMessage = "This extended_string is too long:  'postProjectWithMoreThan50CharsInTheNameAPITestHildaQuiroz'";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

}
