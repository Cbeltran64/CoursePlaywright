package utilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

import java.util.List;

public class CustomOptions implements OptionsFactory {

    @Override
    public Options getOptions() {
        return new Options()
                .setLaunchOptions(createLauchOptions())
                .setContextOptions(createContextOptions())
                .setHeadless(false)
                .setBrowserName("chromium")
                .setChannel("chrome")
                .setTestIdAttribute("data-test");

    }

    // Option del browser
    private BrowserType.LaunchOptions createLauchOptions() {
        final var arguments = List.of("--start-maximized");

        return new BrowserType
                .LaunchOptions()
                //.setSlowMo(1500)
                .setArgs(arguments);
    }

    //Option del browserContext
    private Browser.NewContextOptions createContextOptions() {
        return new Browser.NewContextOptions().setViewportSize(null);
    }


}
