package com.pivotaltracker.api.projects;

import com.pivotaltracker.RequestManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectNegativeTest {
    private String negativeId;
    RequestManager requestManager = new RequestManager();

    @Test
    public void postProjectTest() {
        String endpoint = "projects";

        // Invalid characters
        String bodyContent = """
                {
                    "name": "apiTestProjectWithMoreThanFiftyCharactersByCristinaTorrico"
                }
                """;

        Response response = requestManager.sendPostRequest(bodyContent, endpoint);
        System.out.println(response);

        int actualStatusCode = response.statusCode();
        System.out.println(actualStatusCode);

        int expectedStatusCode = 400;
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }
}