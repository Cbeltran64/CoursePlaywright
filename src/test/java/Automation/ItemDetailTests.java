package Automation;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ItemDetailTests extends BaseTest {
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

        Logs.info("Haciendo clic en la primera imagen");
        page.locator(".inventory_item_img").first().click();
    }

    @Test
    public void VerificarPaginaDealleItemTest(Page page) {
        Logs.info("Verificando la pagina del item");
        assertAll(
                () -> assertThat(page.getByTestId("inventory-item-name")).isVisible(),
                () -> assertThat(page.getByTestId("inventory-item-desc")).isVisible(),
                () -> assertThat(page.getByTestId("inventory-item-price")).isVisible(),
                () -> assertThat(page.getByTestId("item-sauce-labs-backpack-img")).isVisible(),
                () -> assertThat(page.locator(".btn_inventory")).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go back Back to products"))).isVisible()


        );
    }

    @Test
    public void backToProductTest(Page page) {
        Logs.info("Haciendo clic en back to product page");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go back Back to products")).click();

        Logs.info("Verificando que regrese a la pagina de Shopping");
        assertThat(page.getByText("Products")).isVisible();

    }
}
