package api.Ios;

import api.apps.Apps;
import api.requests.endpoints.ProjectNameApi;
import com.codeborne.selenide.WebDriverRunner;
import core.ConsoleLogger;
import core.managers.DriverManager;
import io.appium.java_client.ios.IOSDriver;

import java.util.concurrent.TimeUnit;

public class Ios {
    public static IOSDriver driver;
    public static Apps apps = new Apps();
    public static ProjectNameApi api = new ProjectNameApi();

    public static void setDriver() {
        WebDriverRunner.setWebDriver(DriverManager.getIosDriver());
        DriverManager.setIosDriver((IOSDriver) WebDriverRunner.getWebDriver());
    }

    public static IOSDriver driver() {
        return DriverManager.getIosDriver();
    }

    public static void implicitWait(int seconds) {
        ConsoleLogger.log.info("Set up driver implicit wait as ".concat(String.valueOf(seconds)));
        driver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
