package com.pivotaltracker.api.projects;

import com.pivotaltracker.RequestManager;
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
                    "name": "Automation_fgkjsdfglsdjfglsdkfgjsfdl;gk04345850=+ 3"
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
