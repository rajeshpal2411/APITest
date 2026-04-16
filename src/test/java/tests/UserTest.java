package tests;

import base.BaseTest;
import endpoints.UserAPI;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import payloads.UserPayload;

import static org.hamcrest.Matchers.*;
@Listeners({AllureTestNg.class})
public class UserTest extends BaseTest {

    @Test
    @Description("Create user API test")
    @Severity(SeverityLevel.CRITICAL)
    public void fullFlowTest() {
        test.get().info("Creating user payload");
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
        Allure.addAttachment("Response", createRes.asPrettyString());
    }

    @Test
    @Description("Sample test 2")
    @Severity(SeverityLevel.BLOCKER)
    public void test2() {
        test.get().info("Dummy test 2");
        System.out.println("This is test 2");
        Assert.assertTrue(true);
    }

    @Test
    @Description("Sample test 3")
    @Severity(SeverityLevel.MINOR)
    public void test3() {
        test.get().info("Dummy test 3");
        System.out.println("This is test 3");
        Assert.assertTrue(true);
    }
}