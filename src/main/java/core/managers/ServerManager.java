package core.managers;

import java.io.IOException;
import java.util.Scanner;

public class ServerManager {
    private static String OS;
    private static String ANDROID_HOME;

    private static String getOs() {
        if (OS == null) OS = System.getProperty("os.name");
        return OS;
    }

    public static String getAndroidHome() {
        if (ANDROID_HOME == null) {

            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null) throw new RuntimeException("Please install android sdk and components");
        }
        return ANDROID_HOME;
    }

    public static Boolean isWindows() {
        return getOs().startsWith("Windows");
    }

    public static Boolean isMac() {
        return getOs().startsWith("Mac");
    }

    public static String runCommand(String command) {
        String output = null;

        Scanner scanner = null;
        try {
            scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if(scanner.hasNext()) output = scanner.next();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return output;

    }

}
