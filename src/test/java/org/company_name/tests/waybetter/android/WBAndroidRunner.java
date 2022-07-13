package org.company_name.tests.waybetter.android;

import api.android.Android;
import core.ConsoleLogger;
import core.PropertyWorker;
import core.config.Prop;
import org.apache.log4j.Level;
import org.company_name.tests.waybetter.PrimaryWBConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class WBAndroidRunner {
    public static void main(String[] args) {

        PropertyWorker propertyWorker = new PropertyWorker();
        propertyWorker.setProperty("device_index", String.valueOf(0));
        ArrayList<Map<String, String>> devices = getDevices("android_environments");
        devices.forEach(device -> {
            ConsoleLogger.log.setLevel(Level.INFO);
            ConsoleLogger.log.info("Started for " + device.get("device"));
            PrimaryWBConfiguration configurator = new PrimaryWBConfiguration(
                    "android_environments",
                    "[Android]",
                    "WB-",
                    Android.apps.wayBetterAndroidApp.packageId());
            configurator.start();
        });
        propertyWorker.setProperty("device_index", String.valueOf(0));
    }

    private static ArrayList<Map<String, String>> getDevices(String env) {
        JSONParser parser = new JSONParser();
        JSONObject config = null;
        try {
            config = (JSONObject) parser.parse(new FileReader(
                    Objects.requireNonNull(WBAndroidRunner.class.getClassLoader()
                            .getResource(Prop.getField("bs.devices"))).getPath()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return (ArrayList<Map<String, String>>) Objects.requireNonNull(config).get(env);
    }
}
