package core.managers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class AndroidDriverSelenide implements WebDriverProvider {
    private URL url = null;

    public AndroidDriverSelenide(URL url) {
        this.url = url;
    }

    @NotNull
    @Override
    public WebDriver createDriver(@NotNull Capabilities capabilities) {
        return new AndroidDriver(url, capabilities);
    }
}
