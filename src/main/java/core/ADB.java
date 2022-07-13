package core;

import api.android.Android;
import core.managers.ServerManager;

import java.util.ArrayList;
import java.util.Arrays;

public class ADB {
    private String ID;

    public ADB(String deviceID) {
        ID = deviceID;
    }

    public static String command(String command) {
        ConsoleLogger.log.debug("Formatting ADB command: " + command);
        if (command.startsWith("adb")) {
            command.replace("adb", ServerManager.getAndroidHome() + "/platform-tools/adb ");
        } else {
            throw new RuntimeException("This method is design for ADB commands only! Please check!");
        }

        ConsoleLogger.log.debug("Formatted ADB command: " + command);
        String output = ServerManager.runCommand(command);
        ConsoleLogger.log.debug("Output ADB command: " + output);
        if (output == null) return "";
        else return output.trim();
    }

    public static void killServer() {
        ConsoleLogger.log.info("Killing server");
        command("adb kill-server");
        ConsoleLogger.log.info("The server was killed.");
    }

    public static void startServer() {
        ConsoleLogger.log.info("Starting server");
        command("adb start-server");
    }

    public static void setWindowAnimation(int i) {
        try {
            if (i == 0 || i == 1) {
                ConsoleLogger.log.info("Setting window animation to " + i);
                command("adb shell settings put global window_animation_scale " + i);
            } else {
                throw new AssertionError("Param should equals 0 or 1");
            }
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void setTransitionAnimation(int i) {
        try {
            if (i == 0 || i == 1) {
                ConsoleLogger.log.info("Setting transition animation to " + i);
                command("adb shell settings put global transition_animation_scale " + i);
            } else {
                throw new AssertionError("Param should equals 0 or 1");
            }
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void setAnimationDuration(int i) {
        try {
            if (i == 0 || i == 1) {
                ConsoleLogger.log.info("Setting animation duration to " + i);
                command("adb shell settings put global animator_duration_scale " + i);
            } else {
                throw new AssertionError("Param should equals 0 or 1");
            }
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getConnectedDevices() {
        ConsoleLogger.log.info("Searching device...");
        ArrayList<String> devices = new ArrayList<>();
        String output = command("adb devices");

        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) {
                devices.add(line.replace("device", "").trim());
            }
        }

        ConsoleLogger.log.info("Device was searched: " + devices);

        if (devices.isEmpty()) {
            Thread.yield();
            throw new RuntimeException("Does have any available android devices");
        }

        return devices;
    }

    public String getForegroundActivity() {
        return command("adb -s " + ID + " shell dumpsys window windows | grep mCurrentFocus");
    }

    public String getAndroidVersionAsString() {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        if (output.length() == 3) output += ".0";
        return output;
    }

    public Integer getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    public ArrayList getInstallPackages() {
        ArrayList packages = new ArrayList();

        String[] output = command("adb -s " + ID + " shell pm list packages").split("\n");
        for (String id : output) packages.add(id.replace("package:", "").trim());

        return packages;
    }

    public void openAppsActivity(String packageId, String activityId) {
        ConsoleLogger.log.debug("Opening app by activity id: " + activityId);
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageId + "/" + activityId);

        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(20)) if (Android.driver.getCurrentPackage().equals(packageId)) break;
        if (timer.expired(20) && !Android.driver.getCurrentPackage().equals(packageId))
            throw new AssertionError("App " + activityId + " was not opened.");
        else
            ConsoleLogger.log.info("App " + packageId + " was opened.");
    }

    public boolean isFocusedApp(String packageId) {
        ConsoleLogger.log.debug("Checking app " + packageId);
        String[] output = command("adb shell dumpsys window windows | grep -E 'mFocusedApp'| cut -d / -f 1 | cut -d \" \" -f 7").split("\n");

        if (output[0].equals(packageId)) {
            ConsoleLogger.log.debug("App " + packageId + " is opened.");
            return true;
        } else {
            ConsoleLogger.log.debug("App " + packageId + " is not opened.");
            return false;
        }

    }

    public void clearAppsData(String packageId) {
        command("adb -s " + ID + " shell pm clear " + packageId);
    }

    public void forceStopApp(String packageId) {
        ConsoleLogger.log.debug("Force stop app by package id: " + packageId);
        command("adb -s " + ID + " shell am force-stop " + packageId);
        ConsoleLogger.log.debug("App " + packageId + " was stopped.");
    }

    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    public void uninstallApp(String packageId) {
        command("adb -s " + ID + " uninstall " + packageId);
    }

    public void clearLogBuffer() {
        command("adb -s " + ID + " shell -c");
    }

    public void pushFile(String source, String target) {
        command("adb -s " + ID + " push " + source + " " + target);
    }

    public void pullFile(String source, String target) {
        command("adb -s " + ID + " pull " + source + " " + target);
    }

    public void deletelFile(String target) {
        command("adb -s " + ID + " shell rm " + target);
    }

    public void moveFile(String source, String target) {
        command("adb -s " + ID + " shell mv " + source + " " + target);
    }

    public void takeScreenshot(String target) {
        command("adb -s " + ID + " shell screencap " + target);
    }

    public void rebootDevice() {
        command("adb -s " + ID + " reboot");
    }

    public String getDeviceModel() {
        return command("adb -s " + ID + " shell getprop ro.product.model");
    }

    public String getDeviceSerialNumber() {
        return command("adb -s " + ID + " shell getprop ro.serialno");
    }

    public String getDeviceCerrier() {
        return command("adb -s " + ID + " shell getprop gsm.operator.alpha");
    }

    public String turnOnWiFi(Boolean state) {
        if(state)
            return command("adb shell svc wifi enable");
        else
            return command("adb shell svc wifi disable");
    }

    public ArrayList getLogcatProcesses() {
        String[] output = command("adb -s " + ID + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList processes = new ArrayList();

        for (String line : output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

    public Object startLogcat(String logId, String grep) {
        ArrayList pidBefore = getLogcatProcesses();
        Thread logcat = new Thread(new Runnable() {
            @Override
            public void run() {
                if (grep == null) command("adb -s " + ID + " shell logcat -v threadtime > /sdcard/" + logId + ".txt");
                else
                    command("adb -s " + ID + " shell logcat -v threadtime | grep -i '" + grep + "' > /sdcard/" + logId + ".txt");
            }
        });
        logcat.setName(logId);
        logcat.start();
        logcat.interrupt();


        ArrayList pidAfter = getLogcatProcesses();
        Timer timer = new Timer();
        timer.start();

        while (!timer.expired(10)) {
            if (pidBefore.size() > 0) pidAfter.removeAll(pidBefore);
            if (pidAfter.size() > 0) break;

            pidAfter = getLogcatProcesses();
        }

        if (pidAfter.size() == 1) return pidAfter.get(0);
        else if (pidAfter.size() > 1)
            throw new RuntimeException("Multiple logcat processes was started when only one was expected!");
        else throw new RuntimeException("Failed tc start logcat process");
    }

    public void stopLogcat(String PID) {
        command("adb -s " + ID + " shell kill " + PID);
    }
}
