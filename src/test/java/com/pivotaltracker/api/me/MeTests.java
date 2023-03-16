package com.pivotaltracker.api.me;

import com.pivotaltracker.RequestManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MeTests {

    private RequestManager requestManager = new RequestManager();

    @Test
    public void getMeTest() {
        String endpoint = "me";

        Response response = requestManager.sendGetRequest(endpoint);

        int actualStatusCode = response.statusCode();
        int expectedStatusCode = 200;

        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }
}
