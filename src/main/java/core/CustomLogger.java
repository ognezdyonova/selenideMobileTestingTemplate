package core;


import org.apache.log4j.Logger;

public class CustomLogger {
    public static Logger log = Logger.getLogger(CustomLogger.class);
    private static boolean debug;

    public static void debuggingApi(boolean debugStatus) {
        CustomLogger.log.info("Debug mode:" + debugStatus);
        debug = debugStatus;
    }

    public static boolean isDebug() {
        return debug;
    }
}
