package core.managers;

import api.Ios.Ios;
import api.android.Android;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.collect.ImmutableMap;
import core.ADB;
import core.ConsoleLogger;
import core.NodeExecutor;
import core.browserstack.BrowserStack;
import core.config.Prop;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;

public class DriverManager {
    private static String unlockPackage = "io.appium.unlock";
    private static AppiumDriverLocalService serviceDevice;
    private static ThreadLocal<AndroidDriver> tm_android_driver = new ThreadLocal<AndroidDriver>();
    private static ThreadLocal<IOSDriver> tm_ios_driver = new ThreadLocal<IOSDriver>();
    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;

    public static AndroidDriver getAndroidDriver() {
        return tm_android_driver.get();
    }

    public static void setAndroidDriver(AndroidDriver android_driver) {
        tm_android_driver.set(android_driver);
    }

    public static IOSDriver getIosDriver() {
        return tm_ios_driver.get();
    }

    public static void setIosDriver(IOSDriver ios_driver) {
        tm_ios_driver.set(ios_driver);
    }

    private static void startAppiumLocalDriverService(String logLevel) {
        if (serviceDevice == null || !serviceDevice.isRunning()) {
            ConsoleLogger.log.info("Starting Appium local service.");
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, logLevel);
            builder.usingAnyFreePort();

            HashMap<String, String> environment = new HashMap();
            environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
            builder.withEnvironment(environment);
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);

            AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
            service.withBasePath("/wd/hub/");
            service.start();
            if (service.isRunning()) {
                ConsoleLogger.log.info("Appium local service has been started.");
            }
            serviceDevice = service;
        } else {
            ConsoleLogger.log.info("Appium service started earlier " + serviceDevice.getUrl());
        }
    }

    private static void stopAppiumLocalDriverService() {
        if (serviceDevice.isRunning()) {
            serviceDevice.stop();
            ConsoleLogger.log.info("Appium local service has been stoped.");
        } else {
            ConsoleLogger.log.info("Not available any appium services");
        }
    }

    private static ArrayList<String> getAvailableDevices() {
        ConsoleLogger.log.info("Checking available devices.");
        ArrayList<String> connectedDevices = ADB.getConnectedDevices();
        ArrayList<String> availableDevices = new ArrayList<>();

        for (Object connectedDevice : connectedDevices) {
            String device = connectedDevice.toString();

            ArrayList apps = new ADB(device).getInstallPackages();

            if (!apps.contains(unlockPackage)) {
                availableDevices.add(device);
            } else
                ConsoleLogger.log.info("Device " + device + "has " + unlockPackage + "installed, assuming it is under testing");
        }
        if (availableDevices.size() == 0) throw new RuntimeException("Does not have any available devices for testing");
        return availableDevices;
    }

    private static DesiredCapabilities getAndroidCapabilities(String deviceId, String appActivity, String appPackage) {
        ConsoleLogger.log.debug("Creating driver caps for android device " + deviceId);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:".concat(MobileCapabilityType.PLATFORM_NAME), "Android");
        capabilities.setCapability("appium:".concat(MobileCapabilityType.PLATFORM_VERSION), new ADB(deviceId).getAndroidVersionAsString());
        capabilities.setCapability("appium:".concat(MobileCapabilityType.DEVICE_NAME), deviceId);
        capabilities.setCapability("appium:appActivity", appActivity);
        capabilities.setCapability("appium:appPackage", appPackage);
        capabilities.setCapability("appium:".concat(MobileCapabilityType.AUTOMATION_NAME), AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability("appium:skipUnlock", false);
        capabilities.setCapability("appium:noReset", false);
        capabilities.setCapability("appium:appWaitForLaunch", true);
        capabilities.setCapability("appium:autoGrantPermissions", "true");
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
//        capabilities.setCapability("appium:chromedriverExecutable", DriverManager.class.getClassLoader().getResource("driver/chromedriver_69").getPath());
//        capabilities.setCapability("webviewDevtoolsPort", "9543");
//        capabilities.setCapability(MobileCapabilityType.APP, DriverManager.class.getResource("../../unlock_apk-debug.apk").getFile());
        /*You can add your  app for installing to device capabilities.setCapability(MobileCapabilityType.APP, <app path>); */
        return capabilities;
    }

    public static void createAndroidDriver(String logLevel, String appActivity, String appPackage, String remote, String remoteAppName, String remoteAppBuild) {
        if (!Boolean.parseBoolean(remote)) {
            ADB.setWindowAnimation(0);
            ADB.setAnimationDuration(0);
            ADB.setTransitionAnimation(0);
            ArrayList<String> devices = getAvailableDevices();
            startAppiumLocalDriverService(logLevel);
            for (String device : devices) {
                try {
                    ConsoleLogger.log.info("Created android driver for " + device + " device.");
                    ConsoleLogger.log.info(serviceDevice.getUrl());
                    AndroidDriverSelenide driver = new AndroidDriverSelenide(serviceDevice.getUrl());
                    setAndroidDriver((AndroidDriver) driver.createDriver(getAndroidCapabilities(device, appActivity, appPackage)));
                    Configuration.timeout = 30000;
                    Configuration.remote = serviceDevice.getUrl().getPath();
                    Configuration.reportsFolder = "test-result/reports";
                    Android.adb = new ADB(device);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            ConsoleLogger.log.info("Created remote Android");
            BrowserStack browserStack = new BrowserStack();
            setAndroidDriver(browserStack.remoteAndroidDriver(remoteAppName, remoteAppBuild));
        }
    }

    public static void killAndroidDriver() {
        if (!Boolean.parseBoolean(Prop.getField("bs.available"))) {
            if (Android.driver() != null) {
                ConsoleLogger.log.info("Killing android driver..");
                Android.driver().quit();
                stopAppiumLocalDriverService();
                Android.adb.uninstallApp(unlockPackage);
                ADB.setWindowAnimation(1);
                ADB.setAnimationDuration(1);
                ADB.setTransitionAnimation(1);
            } else ConsoleLogger.log.info("Android driver is not initialized, nothing to kill");
        } else {
            ConsoleLogger.log.info("Closing remote android driver..");
            WebDriverRunner.closeWebDriver();
            ConsoleLogger.log.info("Remote driver has been Closed");
        }
    }

    private static DesiredCapabilities getIosCapabilities(String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, Prop.getField("ios.appiumVersion"));
        capabilities.setCapability("appium:".concat(MobileCapabilityType.DEVICE_NAME), Prop.getField("ios.deviceName"));
        capabilities.setCapability("appium:".concat(MobileCapabilityType.PLATFORM_NAME), "iOS");
        capabilities.setCapability("appium:".concat(MobileCapabilityType.PLATFORM_VERSION), Prop.getField("ios.platformVersion"));
        capabilities.setCapability("appium:".concat(MobileCapabilityType.NO_RESET), false);
        capabilities.setCapability("appium:".concat(MobileCapabilityType.AUTOMATION_NAME), AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("appium:xcodeOrgId", Prop.getField("ios.xcodeOrgId"));
        capabilities.setCapability("appium:xcodeSigningId", Prop.getField("ios.xcodeSigningId"));
        capabilities.setCapability("appium:".concat(MobileCapabilityType.APP), bundleId);
        capabilities.setCapability("appium:".concat(MobileCapabilityType.UDID), "auto");
        capabilities.setCapability("appium:".concat(MobileCapabilityType.CLEAR_SYSTEM_FILES), true);
        capabilities.setCapability("appium:".concat(MobileCapabilityType.AUTO_WEBVIEW), false);
        capabilities.setCapability("appium:autoGrantPermissions", true);
        capabilities.setCapability("appium:autoDissmissAlerts", true);
        capabilities.setCapability("appium:launchTimeout", Prop.getField("ios.launchTimeout"));
        return capabilities;
    }

    public static void createIosDriver(String logLevel, String bundleId, String remote, String remoteAppName, String remoteAppBuild) {
        if (!Boolean.parseBoolean(remote)) {
            try {
                NodeExecutor.killAllTasks();
                startAppiumLocalDriverService(logLevel);
                ConsoleLogger.log.info("Created ios driver");
                IosDriverSelenide driver = new IosDriverSelenide(serviceDevice.getUrl());
                setIosDriver((IOSDriver) driver.createDriver(getIosCapabilities(bundleId)));
                Configuration.timeout = 30000;
                Configuration.remote = serviceDevice.getUrl().getPath();
                Configuration.reportsFolder = "test-result/reports";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ConsoleLogger.log.info("Creating remote IOS driver");
            BrowserStack browserStack = new BrowserStack();
            setIosDriver(browserStack.remoteIosDriver(remoteAppName, remoteAppBuild));
        }
    }

    public static void killIosDriver() {
        if (!Boolean.parseBoolean(Prop.getField("bs.available"))) {
            if (Ios.driver() != null) {
                ConsoleLogger.log.info("Killing ios driver..");
                Ios.driver().quit();
                stopAppiumLocalDriverService();
            } else ConsoleLogger.log.info("Ios driver is not initialized, nothing to kill");
        } else {
            ConsoleLogger.log.info("Closing remote Ios driver..");
            WebDriverRunner.closeWebDriver();
            ConsoleLogger.log.info("Remote driver has been Closed");
        }
    }
}
