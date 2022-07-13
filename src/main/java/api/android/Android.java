package api.android;

import api.apps.Apps;
import api.requests.endpoints.ProjectNameApi;
import com.codeborne.selenide.WebDriverRunner;
import core.ADB;
import core.ConsoleLogger;
import core.managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

public class Android {
    public static AndroidDriver driver;
    public static ADB adb;
    public static Apps apps = new Apps();
    public static ProjectNameApi api = new ProjectNameApi();

    public static void setDriver() {
        WebDriverRunner.setWebDriver(DriverManager.getAndroidDriver());
        DriverManager.setAndroidDriver((AndroidDriver) WebDriverRunner.getWebDriver());
    }
    public static AndroidDriver driver() {
        return DriverManager.getAndroidDriver();
    }

    public static void implicitWait(int seconds) {
        ConsoleLogger.log.info("Set up driver implicit wait as ".concat(String.valueOf(seconds)));
        driver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
