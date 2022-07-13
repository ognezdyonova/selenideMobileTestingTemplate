package core.managers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.ios.IOSDriver;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class IosDriverSelenide implements WebDriverProvider {
    private URL url = null;

    public IosDriverSelenide(URL url) {
        this.url = url;
    }


    @NotNull
    @Override
    public WebDriver createDriver(@NotNull Capabilities capabilities) {
        return new IOSDriver(url, capabilities);
    }
}
