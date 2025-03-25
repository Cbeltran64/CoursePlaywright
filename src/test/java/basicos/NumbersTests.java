package basicos;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;
import utilities.Logs;

public class NumbersTests extends BaseTest {

    private final Faker faker = new Faker();
    private int a;
    private int b;

    @BeforeEach
    public void setUp() {
        a = faker.number().numberBetween(10, 10);
        b = faker.number().numberBetween(10, 10);

        Logs.info("A: %d " + a);
        Logs.info("B: %d " + b);
    }

    @Test
    public void numberTest() {
        final var suma = a + b;
        Logs.info("Verificando que la suma sea 30");
        Assertions.assertEquals(30, suma);

        Logs.info("Verificando que la suma sea 30");
        Assertions.assertTrue(suma > 0);
    }

    @Test
    public void producTest() {
        final var producto = a * b;
        System.out.println("Suma: " + producto);
    }

    @Test
    public void igualdadTest() {
        final var sonIguales = a == b;
        System.out.println("Suma: " + sonIguales);
    }

    @Test
    public void comparartTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(a > 15, "A no es mayor que 15"),
                () -> Assertions.assertTrue(b > 15, "B no es mayor que 15")
        );
    }
}
