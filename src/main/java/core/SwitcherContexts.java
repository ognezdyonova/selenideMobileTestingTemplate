package core;

import api.Ios.Ios;
import api.android.Android;
import core.managers.DriverManager;

import java.util.Set;

public class SwitcherContexts {
    private String contextName = null;

    public SwitcherContexts(String contextName) {
        this.contextName = contextName;
    }

    public SwitcherContexts() {
    }

    public SwitcherContexts waitToAppear(int seconds) {
        Timer timer = new Timer();
        timer.start();
        while (!timer.expired(seconds)) if (exists()) break;

        if (timer.expired(seconds) && !exists())
            throw new AssertionError("WebView " + contextName + " failed to appear within " + seconds + " seconds");
        return this;
    }

    private boolean exists() {
        boolean state = false;
        Set contexts = null;
        if(DriverManager.getAndroidDriver() !=null) contexts = Android.driver().getContextHandles();
        else contexts = DriverManager.getIosDriver().getContextHandles();

        System.out.println(contexts);
        boolean status = contexts.stream().anyMatch(context -> context.toString().contains(this.contextName));

        if (contexts != null && status) {
            state = true;
        }
        return state;
    }

    public SwitcherContexts switchToContext() {
        ConsoleLogger.log.info("The current context switches to " + contextName);
        if (!Android.driver().getContext().equals(contextName)) Android.driver().context(contextName);
        else ConsoleLogger.log.info("Context is already switched to " + contextName);
        return this;
    }

    public SwitcherContexts switchToContextIos() {
        ConsoleLogger.log.info("The current context switches to " + this.contextName);
        String contextName = Ios.driver().getContextHandles().stream().filter(context -> context.toString().contains(this.contextName)).findFirst().get().toString();
        ConsoleLogger.log.info("Founded context by predicate: " + contextName);
        if (!Ios.driver().getContext().equals(contextName)) Ios.driver().context(contextName);
        else ConsoleLogger.log.info("Context is already switched to " + contextName);
        return this;
    }
}
