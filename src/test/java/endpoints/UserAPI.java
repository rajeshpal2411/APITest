package endpoints;

import base.BaseTest;
import io.restassured.response.Response;
import payloads.UserPayload;
import utils.TokenManager;

import static io.restassured.RestAssured.*;

public class UserAPI extends BaseTest {

    public static Response createUser(UserPayload payload) {

        return given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_57f9143dca5d43ccaa66fcf5693e8dbf")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(payload)
                .when()
                .post("/api/users");
    }

    public static Response getUser(String id) {

        Response response = given()
                .header("x-api-key", "reqres_57f9143dca5d43ccaa66fcf5693e8dbf")
                .pathParam("id", id)
                .when()
                .get("/api/users/{id}");
        System.out.println(response.getBody().asString());
        return response;

    }
}