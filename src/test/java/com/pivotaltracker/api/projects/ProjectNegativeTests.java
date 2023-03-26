package com.pivotaltracker.api.projects;

import com.pivotaltracker.RequestManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ProjectNegativeTests {
        private RequestManager requestManager = new RequestManager();
    // Esta prueba es para verificar que no se puede  crear una cuenta con mas de 50 caracteres.
    @Test
    public void negativePostTest() {
        String endPoint = "projects";
        String bodyContent = """
                { "name": "esta Prueba trata de ingresar mas de 50 caracteres para crear un nombre de proyecto, por lo cual deberia enviarnos un error"
                }
                """;
        Response response = requestManager.sendPostRequest(bodyContent, endPoint);
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 400;

        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        File schemaFile = new File("src/test/resources/schemas/errorProjectSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

        String actualProjectName = response.jsonPath().getString("error");
        String expectedProjectName = "One or more request parameters was missing or invalid.";

        Assert.assertEquals(actualProjectName, expectedProjectName);

    }
    // Esta prueba es para verificar que no se puede crear una cuenta sin nombre.
    @Test
    public void negativePostTestEmpty() {
        String endPoint = "projects";
        String bodyContent = """
                { "name": ""
                }
                """;
        Response response = requestManager.sendPostRequest(bodyContent, endPoint);
        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 400;

        Assert.assertEquals(actualStatusCode, expectedStatusCode);

        File schemaFile = new File("src/test/resources/schemas/errorProjectSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

        String actualProjectName = response.jsonPath().getString("error");
        String expectedProjectName = "One or more request parameters was missing or invalid.";

        Assert.assertEquals(actualProjectName, expectedProjectName);

    }
}

