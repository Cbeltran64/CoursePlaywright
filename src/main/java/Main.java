public class Main {
    public static void main(String[] args) {

        final var text = "Sauce Labs Fleece Jacket";
        final var text2 = String.format("Add to Cart %s", text);
        final var text3 = text2.toLowerCase();
        final var text4 = text3.replaceAll(" ", "-");
        System.out.println(text4);
    }
}
