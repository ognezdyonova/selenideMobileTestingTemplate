package org.company_name.tests.waybetter.android;

import api.android.Android;
import core.CustomLogger;
import core.Logger;
import core.config.Prop;
import core.constants.BuilderLogLevel;
import core.managers.DriverManager;
import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;

public class TestPrime {
    @BeforeClass(alwaysRun = true)
    public void alwaysRun() {
        Logger.setCurrentClass(this.getClass().getSimpleName());
    }

    public static void start() {
        CustomLogger.log.setLevel(Level.INFO);
        DriverManager.createAndroidDriver(BuilderLogLevel.ERROR.get(),
                Android.apps.wayBetterAndroidApp.activityId(),
                Android.apps.wayBetterAndroidApp.packageId(),
                Prop.getField("bs.available"),
                Prop.getField("bs.wb.name"),
                Prop.getField("bs.wb.build.android"));

        /*
         * Need to save current instants for current Thread
         * Don't forget to add it
         * */
        Android.setDriver();

//        Android.apps.wayBetterAndroidApp.clearData();
        Android.apps.wayBetterAndroidApp.open();
//        Android.apps.wayBetterAndroidApp.welcomeScreen.waitToLoad();
    }

    public static void end() {
        Android.apps.wayBetterAndroidApp.clearData();
        DriverManager.killAndroidDriver();
    }
}
