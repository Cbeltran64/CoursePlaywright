package utilities;

import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@UsePlaywright(CustomOptions.class)
public class BaseTest {
    @BeforeEach
    public void beforeEach() {
        Logs.info("Soy la condicion del parde");
    }

    @AfterEach
    public void tearDown() {
        Logs.info("Soy la post condicion del parde");
    }
}
