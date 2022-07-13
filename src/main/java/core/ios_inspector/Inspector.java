package core.ios_inspector;

import api.Ios.Ios;
import core.ConsoleLogger;
import core.constants.BuilderLogLevel;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.Level;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Inspector {
    private static AppiumDriverLocalService serviceDevice;

    public static void main(String[] args) {
        ConsoleLogger.log.setLevel(Level.INFO);
        createIosDriver(BuilderLogLevel.ERROR.get());

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server socket created, command console reader for listen to server commands");
            while (true) {
                if (br.ready()) {
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Main Server initiate exiting...");
                        serviceDevice.stop();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void startAppiumLocalDriverService(String logLevel) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();
        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        builder.withEnvironment(environment);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.withBasePath("/wd/hub/");
        service.start();
        serviceDevice = service;
    }

    private static DesiredCapabilities getIosCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone (Tunik)");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.4.3");
        capabilities.setCapability("noReset", false);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("xcodeOrgId", "VVJMB5SXML");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        capabilities.setCapability("udid", "auto");
        capabilities.setCapability("clearSystemFiles", true);
        capabilities.setCapability("autoWebview", false);
        capabilities.setCapability(MobileCapabilityType.APP, Ios.apps.wayBetterIosApp.packageId());
        return capabilities;

    }

    public static void createIosDriver(String logLevel) {
        try {
            startAppiumLocalDriverService(logLevel);
//                System.out.println("Version device for " + device + "   " + Android.adb.getAndroidVersionAsString());
            Ios.driver = new IOSDriver(serviceDevice.getUrl(), getIosCapabilities());
        } catch (Exception e) {
            e.printStackTrace();
            //ignore and try to text device
        }
    }

    public static void killIosDriver() {
        if (Ios.driver != null) {
            ConsoleLogger.log.info("Killing ios driver..");
            Ios.driver.quit();
        } else ConsoleLogger.log.info("Android driver is not initialized, nothing to kill");
    }
}
