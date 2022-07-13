package core;


import org.apache.log4j.Logger;

public class ConsoleLogger {
    public static Logger log = Logger.getLogger(ConsoleLogger.class);
    private static boolean debug;

    public static void debuggingApi(boolean debugStatus){
        ConsoleLogger.log.info("Debug mode:"+ debugStatus);
        debug = debugStatus;
    }

    public static boolean isDebug() {
        return debug;
    }
}
