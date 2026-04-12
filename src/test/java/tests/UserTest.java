package tests;

import base.BaseTest;
import endpoints.UserAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.UserPayload;

import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest {

    @Test
    public void fullFlowTest() {

        // Step 1: Create User
        UserPayload payload = new UserPayload("Rajesh", "QA");

        Response createRes = UserAPI.createUser(payload);

        createRes.then()
                .statusCode(201)
                .body("name", equalTo("Rajesh"));

        String userId = createRes.jsonPath().getString("id");

        System.out.println("Created User ID: " + userId);

        // Step 2: Get User (Note: ReqRes doesn't persist, so using static id)
        UserAPI.getUser("2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .log().all()
                ;
    }
}