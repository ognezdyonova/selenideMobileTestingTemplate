package core;

import core.managers.ServerManager;

public class NodeExecutor {

    public static String command(String command) {
        String output = ServerManager.runCommand(command);
        ConsoleLogger.log.debug("Output command: " + output);
        if (output == null) return "";
        else return output.trim();
    }

    public static void killAllTasks() {
        ConsoleLogger.log.info("Killing all ran tasks");
        command("/usr/bin/killall -KILL node");
        ConsoleLogger.log.info("All tasks was killed.");
    }
}
