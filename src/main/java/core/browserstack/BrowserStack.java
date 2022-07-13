package core.browserstack;

import com.codeborne.selenide.Configuration;
import core.ConsoleLogger;
import core.Logger;
import core.config.Prop;
import core.constants.DevicesBS;
import core.managers.AndroidDriverSelenide;
import core.managers.IosDriverSelenide;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BrowserStack {
    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    public AndroidDriver remoteAndroidDriver(String appName, String appBuild) {
        DesiredCapabilities caps = desiredCapabilitiesRemoteAndroidDriver(appName, appBuild);
        ConsoleLogger.log.info("Connect to bs://" + appBuild + "in browser stack");
        AndroidDriverSelenide driver = new AndroidDriverSelenide(serverHost());
        AndroidDriver androidDriver = (AndroidDriver) driver.createDriver(caps);
        Configuration.timeout = 30000;
        Configuration.remote = Objects.requireNonNull(serverHost()).getPath();
        Configuration.reportsFolder = "test-result/reports/".concat(Thread.currentThread().getName());
        return androidDriver;
    }
    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    public IOSDriver remoteIosDriver(String appName, String appBuild) {
        DesiredCapabilities caps = desiredCapabilitiesRemoteIOSDriver(appName, appBuild);
        ConsoleLogger.log.info("Connect to bs://" + appBuild + "in browser stack");
        IosDriverSelenide driver = new IosDriverSelenide(serverHost());
        IOSDriver iosDriver = (IOSDriver) driver.createDriver(caps);
        Configuration.timeout = 30000;
        Configuration.remote = Objects.requireNonNull(serverHost()).getPath();
        Configuration.reportsFolder = "test-result/reports/".concat(Thread.currentThread().getName());
        return iosDriver;
    }

    private static URL serverHost() {
        try {
            return new URL("https://"
                    + Prop.getField("bs.username")
                    + ":"
                    + Prop.getField("bs.accessKey")
                    + Prop.getField("bs.server")
                    + "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    private synchronized DesiredCapabilities desiredCapabilitiesRemoteIOSDriver(String name, String app) {
        String className;
        if (Logger.testClassName == null) className = "only one suite";
        else className = Logger.testClassName;

        DesiredCapabilities caps = new DesiredCapabilities();
        if (DevicesBS.getAppName() != null) {
            caps.setCapability("device", deviceInfo().get("device"));
            caps.setCapability("os_version", deviceInfo().get("os_version"));
            caps.setCapability("project", DevicesBS.getAppName().concat(deviceInfo().get("device")));
        } else {
            caps.setCapability("device", Prop.getField("bs.device.ios"));
            caps.setCapability("os_version", Prop.getField("bs.device.ios.os.version"));
            caps.setCapability("project", Prop.getField("bs.project"));
        }

        ConsoleLogger.log.info("Capabilities for ios " + caps);

        caps.setCapability("build", getLocalTime());
        caps.setCapability("name", name + ": iOS - " + className);
        caps.setCapability("app", "bs://" + app);
        caps.setCapability("browserstack.debug", Prop.getField("bs.browserstack.debug"));
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("browserstack.appium_version", Prop.getField("bs.appium.version"));
        caps.setCapability("browserstack.networkLogs", Prop.getField("bs.browserstack.network.debug"));
        caps.setCapability("browserstack.deviceLogs", Prop.getField("bs.browserstack.device.debug"));
        caps.setCapability("browserstack.appiumLogs", Prop.getField("bs.browserstack.appium.debug"));
        caps.setCapability("autoGrantPermissions", true);
//        caps.setCapability("autoDismissAlerts", true);
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("appWaitForLaunch", true);
        caps.setCapability("launchTimeout", Prop.getField("ios.launchTimeout"));
        caps.setCapability("browserstack.idleTimeout", Prop.getField("bs.browserstack.idle.timeout"));

        return caps;
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    private synchronized DesiredCapabilities desiredCapabilitiesRemoteAndroidDriver(String name, String app) {
        String className;
        if (Logger.testClassName == null) className = "only one suite";
        else className = Logger.testClassName;

        DesiredCapabilities caps = new DesiredCapabilities();
        if (DevicesBS.getAppName() != null) {
            caps.setCapability("device", deviceInfo().get("device"));
            caps.setCapability("os_version", deviceInfo().get("os_version"));
            caps.setCapability("project", DevicesBS.getAppName().concat(deviceInfo().get("device")));
        } else {
            caps.setCapability("device", Prop.getField("bs.device.android"));
            caps.setCapability("os_version", Prop.getField("bs.device.android.os.version"));
            caps.setCapability("project", Prop.getField("bs.project"));
        }

        caps.setCapability("build", getLocalTime());
        caps.setCapability("name", name + ": Android - " + className);
        caps.setCapability("app", "bs://" + app);
        caps.setCapability("browserstack.debug", Prop.getField("bs.browserstack.debug"));
        caps.setCapability("browserstack.appium_version", Prop.getField("bs.appium.version"));
        caps.setCapability("browserstack.networkLogs", Prop.getField("bs.browserstack.network.debug"));
        caps.setCapability("browserstack.deviceLogs", Prop.getField("bs.browserstack.device.debug"));
        caps.setCapability("browserstack.appiumLogs", Prop.getField("bs.browserstack.appium.debug"));
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("appWaitForLaunch", true);
        caps.setCapability("browserstack.idleTimeout", Prop.getField("bs.browserstack.idle.timeout"));

        ConsoleLogger.log.info("Capabilities for android " + caps);
        return caps;
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    private synchronized String getLocalTime() {
        LocalDate localDate = LocalDate.now();
        return localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    private synchronized Map<String, String> deviceInfo() {
        String threadName = Thread.currentThread().getName();
        Map<String, String> device = new HashMap<>();
        device.put("device", threadName.substring(0, threadName.lastIndexOf("_V:")));
        device.put("os_version", threadName.substring(threadName.lastIndexOf("_V:")).replace("_V:", ""));
        return device;
    }
}