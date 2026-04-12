package utils;

import static io.restassured.RestAssured.*;

public class TokenManager {

    private static String token;

    public static String getToken() {

        if (token == null) {
            token = generateToken();
        }
        return token;
    }

    private static String generateToken() {

        String token =  given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres_57f9143dca5d43ccaa66fcf5693e8dbf")
                .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().path("token");
        System.out.println("Generated Token: " + token);
        return token;
    }
}