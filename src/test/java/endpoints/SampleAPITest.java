package endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class SampleAPITest {

    public static void main(String[] args) {

        // Base URI
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        // =========================
        // STEP 1: Generate Token
        // =========================
        String token =
                given()
                        .header("Content-Type", "application/json")
                        .header("x-api-key", "reqres_57f9143dca5d43ccaa66fcf5693e8dbf")
                        .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                        .when()
                        .post("/api/login")
                        .then()
                        .statusCode(200)
                        .extract().path("token");

        System.out.println("Generated Token: " + token);

        // =========================
        // STEP 2: Create User
        // =========================
        Response createResponse =
                given()
                        .header("Content-Type", "application/json")
                        .header("x-api-key", "reqres_57f9143dca5d43ccaa66fcf5693e8dbf")
                        .header("Authorization", "Bearer " + token)
                        .body("{\"name\":\"Rajesh2\",\"job\":\"QA Engineer2\"}")
                        .when()
                        .post("/api/users")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("name", equalTo("Rajesh2"))
                        .extract().response();

        String userId = createResponse.jsonPath().getString("id");
        System.out.println("Created User ID: " + userId);

        // =========================
        // STEP 3: Get User
        // =========================
        given()
                .header("x-api-key", "reqres_57f9143dca5d43ccaa66fcf5693e8dbf")
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .log().all();

        // =========================
        // STEP 4: Validate Response
        // =========================
        given()
                .header("x-api-key", "reqres_57f9143dca5d43ccaa66fcf5693e8dbf")
                .pathParam("id", 2)
                .when()
                .get("/api/users/{id}")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"))
               .body(matchesJsonSchemaInClasspath("schema1.json"))
        ;

        System.out.println("API FLOW COMPLETED SUCCESSFULLY");
   }
}