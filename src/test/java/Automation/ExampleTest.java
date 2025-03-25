package Automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;
import utilities.Logs;

public class ExampleTest extends BaseTest {
    @Test
    @Regression
    public void primerAutomationTest(Page page) {
        Logs.info("Unavenged en la URL");
        page.navigate("https://www.saucedemo.com/");
        final var url = page.url();

        Logs.info("Verification Url");
        Assertions.assertEquals("https://www.saucedemo.com/", url);
    }

    @Test
    @Regression
    public void segundoTest(Page page) {
        Logs.info("Unavenged en la URL 1 ");
        page.navigate("https://www.heroku.com/");

        Logs.info("Unavenged en la URL 2");
        page.navigate("https://www.github.com/");

        Logs.info("Devolviendo la URL");
        page.goBack();

        Logs.info("Verification Url");
        Assertions.assertEquals("https://www.heroku.com/", page.url());
    }
}
