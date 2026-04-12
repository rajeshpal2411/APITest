package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }
}