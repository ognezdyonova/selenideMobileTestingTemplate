package api.apps.waybetter.waybetter_app.ios;

import api.Ios.Ios;

import api.apps.waybetter.waybetter_app.ios.login_screen.LoginScreen;
import api.apps.waybetter.waybetter_app.ios.sign_up_screen.SignUpScreen;
import api.apps.waybetter.waybetter_app.ios.welcome_screen.WelcomeScreen;
import api.interfaces.Activity;
import api.interfaces.Application;
import core.CustomLogger;
import core.SwitcherContexts;
import core.config.Prop;

public class WayBetterIosApp implements Activity, Application {
    private static final String PREFIX = "com.waybetter";
    public WelcomeScreen welcomeScreen = new WelcomeScreen();
    public LoginScreen loginScreen = new LoginScreen();
    public SignUpScreen signUpScreen = new SignUpScreen();

    @Override
    public Object waitToLoad() {
        return null;
    }

    @Override
    public void forceStop() {
        CustomLogger.log.info("Close app: " + packageId());
        Ios.driver().terminateApp(packageId());
        Ios.driver().closeApp();
    }

    @Override
    public Object open() {
        CustomLogger.log.info("Open app: " + packageId());
        if (!Boolean.parseBoolean(Prop.getField("bs.available"))) {
            if (Ios.driver().isAppInstalled(packageId())) Ios.driver().activateApp(packageId());
            else
                throw new RuntimeException("App: " + packageId() + " not installed, please check bundle id or app in your device.");
        }
        return null;
    }

    @Override
    public void clearData() {
        CustomLogger.log.info("Reset app:" + packageId());
        Ios.driver().resetApp();
    }

    @Override
    public String packageId() {
        //com.waybetter.WayBetter.stage
        return PREFIX.concat(".WayBetter.stage");
    }

    @Override
    public String activityId() {
        return null;
    }

    @Override
    public void switchToWebView() {
        CustomLogger.log.info("Waiting for context switch to WEBVIEW_" + packageId());
        new SwitcherContexts("WEBVIEW").waitToAppear(100).switchToContextIos();
    }

    @Override
    public void switchToNative() {
        CustomLogger.log.info("Waiting for context switch to NATIVE_APP");
        new SwitcherContexts("NATIVE_APP").waitToAppear(100).switchToContextIos();
    }
}
