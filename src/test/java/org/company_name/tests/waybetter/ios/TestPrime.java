package org.company_name.tests.waybetter.ios;

import api.Ios.Ios;
import core.CustomLogger;
import core.Logger;
import core.PropertyWorker;
import core.config.Prop;
import core.constants.BuilderLogLevel;
import core.managers.DriverManager;
import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;

public class TestPrime {
    private PropertyWorker properties;

    @BeforeClass(alwaysRun = true)
    public void alwaysRun() {
        properties = new PropertyWorker();
        Logger.setCurrentClass(this.getClass().getSimpleName());
    }

    public static void start() {
        CustomLogger.log.setLevel(Level.INFO);
        DriverManager.createIosDriver(BuilderLogLevel.ERROR.get(),
                Ios.apps.wayBetterIosApp.packageId(),
                Prop.getField("bs.available"),
                Prop.getField("bs.wb.name"),
                Prop.getField("bs.wb.build.ios"));

        /*
         * Need to save current instants for current Thread
         * Don't forget to add it
         * */
        Ios.setDriver();

//        Ios.apps.wayBetterIosApp.clearData();
        Ios.apps.wayBetterIosApp.open();
//        Ios.apps.wayBetterIosApp.welcomeScreen.waitToLoad();
    }

    public static void end() {
        Ios.apps.wayBetterIosApp.clearData();
        DriverManager.killIosDriver();
    }
}
