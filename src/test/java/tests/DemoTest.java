package tests;
import base.BaseTest;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})
public class DemoTest extends BaseTest {

    @Test
    public void test() {
logger.info("demo class methos running");
        System.out.println("Allure test running");
    }
}