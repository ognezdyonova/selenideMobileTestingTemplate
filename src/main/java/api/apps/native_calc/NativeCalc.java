package api.apps.native_calc;

import api.android.Android;
import api.apps.native_calc.MainScreen.MainScreen;
import api.interfaces.Activity;
import api.interfaces.Application;

public class NativeCalc implements Activity, Application {
    public MainScreen mainScreen = new MainScreen();

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
        Android.adb.openAppsActivity(packageId(), activityId());
        return null;
    }

    @Override
    public void clearData() {
        Android.adb.clearAppsData(packageId());
    }

    @Override
    public String packageId() {
        return "com.android.calculator2";
    }

    @Override
    public String activityId() {
        return ".Calculator";
    }

    @Override
    public void switchToWebView() {

    }

    @Override
    public void switchToNative() {

    }
}
