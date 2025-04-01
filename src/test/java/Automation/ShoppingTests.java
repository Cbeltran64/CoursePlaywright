package Automation;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ShoppingTests extends BaseTest {
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
    }

    @Test
    public void verificarPaginaaShoppingTest(Page page) {
        Logs.info("Verificando la pagina de shopping");
        assertAll(
                () -> assertThat(page.getByText("Products")).isVisible(),
                () -> assertThat(page.getByTestId("inventory-list")).isVisible(),
                () -> assertThat(page.getByTestId("product-sort-container")).isVisible()
        );
    }

    @Test
    public void botonRemoveTest(Page page) {
        final var text = "Sauce Labs Fleece Jacket";
        final var text2 = String.format("Add to Cart %s", text);
        final var text3 = text2.toLowerCase();
        final var testId = text3.replaceAll(" ", "-");

        final var textRemove1 = String.format("Remove %s", text);
        final var textRemove2 = textRemove1.toLowerCase();
        final var textRemoveId = textRemove2.replaceAll(" ", "-");

        Logs.info("Haciendo clic en el boton de Sauce Labs Fleece jacket");
        page.getByTestId(testId).click();


        Logs.info("Verificando que diga Remove");
        assertThat(page.getByTestId(textRemoveId)).hasText("Remove");
    }

    @Test
    public void precioTest(Page page) {
        Logs.info("Verificar Precio");
        assertThat(page.getByTestId("inventory-item-price").nth(2)).hasText("$15.99");
    }

    @Test
    public void addToCartTest(Page page) {
        Logs.info("Haciendo clic en el boton de Add to Cart");
        final var list = page.locator(".btn_inventory").all();
        for (var item : list) {
            item.click();
        }
        assertThat(page.getByTestId("shopping-cart-link")).hasText("6");
    }
}
