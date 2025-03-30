package Automation;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BurgerMenuTest extends BaseTest {
    @BeforeEach
    public void setUp(Page page) {
        Logs.info("Navegando a la pagina");
        page.navigate("https://www.saucedemo.com/");

        Logs.info("Escribiendo el usuario");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("standard_user");

        Logs.info("Escribiendo el pasword del usuario");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("secret_sauce");

        Logs.info("clic en el login");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        Logs.info("Haciendo clic en el burger menu");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu")).click();
    }

    @Test
    public void VerificarBurgerMenuTest(Page page) {
        Logs.info("Verificando los elementos de burger menus");
        assertAll(
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("All Items"))).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About"))).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"))).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reset App State"))).isVisible()
        );
    }

    @Test
    public void logautTest(Page page) {
        Logs.info("Verificando el direcionamiento desde burger menus a Logaut");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout")).click();

        Logs.info("Verificando que regreso a la pagina de Login");
        assertThat(page.getByText("Swag labs")).isVisible();

    }
}
