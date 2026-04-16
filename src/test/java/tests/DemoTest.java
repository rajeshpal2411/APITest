package tests;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})
public class DemoTest {

    @Test
    public void test() {
        System.out.println("Allure test running");
    }
}