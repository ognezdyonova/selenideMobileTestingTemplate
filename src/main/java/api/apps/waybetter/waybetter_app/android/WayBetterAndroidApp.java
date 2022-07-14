package api.apps.waybetter.waybetter_app.android;

import api.android.Android;
import api.apps.waybetter.waybetter_app.android.login_screen.LoginScreen;
import api.apps.waybetter.waybetter_app.android.sign_up_screen.SignUpScreen;
import api.apps.waybetter.waybetter_app.android.welcome_screen.WelcomeScreen;
import api.interfaces.Activity;
import api.interfaces.Application;
import core.CustomLogger;
import core.SwitcherContexts;
import core.browserstack.BSApi;
import core.browserstack.NetworkProfiles;
import core.config.Prop;

public class WayBetterAndroidApp implements Activity, Application {
    private static final String PREFIX = "com.waybetter";
    public WelcomeScreen welcomeScreen = new WelcomeScreen();
    public LoginScreen loginScreen = new LoginScreen();
    public SignUpScreen signUpScreen = new SignUpScreen();

    /*for menu https://take.ms/OLUKd */

    @Override
    public Object waitToLoad() {
        return null;
    }

    @Override
    public void forceStop() {
        Android.adb.forceStopApp(packageId());
    }

    @Override
    public Object open() {
        if (!Boolean.parseBoolean(Prop.getField("bs.available"))) {
            if (!Android.adb.isFocusedApp(packageId()) || !Android.driver().getCurrentPackage().equals(packageId())) {
//            clearData();
                Android.adb.openAppsActivity(packageId(), activityId());
            } else
                CustomLogger.log.info(packageId() + " already opened.");
        }
        return null;
    }

    @Override
    public void clearData() {
        if (!Boolean.parseBoolean(Prop.getField("bs.available"))) {
            CustomLogger.log.info("Clearing data for ".concat(packageId()));
            Android.adb.clearAppsData(packageId());
        }
    }

    @Override
    public String packageId() {
        return PREFIX.concat(".app.stage");
    }

    @Override
    public String activityId() {
        return PREFIX.concat(".MainActivity");
    }

    @Override
    public void switchToWebView() {
        CustomLogger.log.info("Waiting for context switch to WEBVIEW_" + packageId());
        new SwitcherContexts("WEBVIEW_" + packageId()).waitToAppear(100).switchToContext();
    }

    @Override
    public void switchToNative() {
        CustomLogger.log.info("Waiting for context switch to NATIVE_APP");
        new SwitcherContexts("NATIVE_APP").waitToAppear(100).switchToContext();
    }

    public void offline() {
        if (Boolean.parseBoolean(Prop.getField("bs.available")))
            new BSApi().setNetworkProfile(NetworkProfiles.NO_NETWORK);
        else
            Android.adb.turnOnWiFi(false);
    }

    public void online() {
        if (Boolean.parseBoolean(Prop.getField("bs.available")))
            new BSApi().setNetworkProfile(NetworkProfiles.RESET);
        else
            Android.adb.turnOnWiFi(true);
    }
}
