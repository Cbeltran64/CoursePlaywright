package Automation;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginTests extends BaseTest {

    @BeforeEach
    public void Setup(Page page) {
        Logs.info("Navegando a la pagina");
        page.navigate("https://www.saucedemo.com/");
    }

    @Test
    public void mensajeErrorTest(Page page) {
        Logs.info("Escribiendo el usuario");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("locked_out_user");

        Logs.info("Escribiendo el pasword del usuario");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("secret_sauce");

        Logs.info("clic en el login");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        Logs.info("Verification menses de error");
        final var mensajeError = page.getByTestId("error");
        assertAll(
                () -> assertThat(mensajeError).isVisible(),
                () -> assertThat(mensajeError).hasText("Epic sadface: Sorry, this user has been locked out.")
        );
    }

    @Test
    public void VerificarPaginaLoginTest(Page page) {
        Logs.info("Verificando la pagina de login");
        assertAll(
                () -> assertThat(page.getByPlaceholder("Username")).isVisible(),
                () -> assertThat(page.getByTestId("password")).isVisible(),
                () -> assertThat(page.locator("#login-button")).isVisible(),
                () -> assertThat(page.getByText("Swag Labs")).isVisible()
                //() -> assertThat(page.getByTestId("error")).isVisible(),
                //() -> assertThat(page.locator("#login_button_container")).isVisible(),
        );
    }


}
