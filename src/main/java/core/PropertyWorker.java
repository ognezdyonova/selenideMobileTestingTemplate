package core;


import core.config.Prop;

import java.io.*;
import java.util.Properties;

public class PropertyWorker {
    private static PropertyWorker propertyWorker;
    private Properties properties;
    private FileInputStream file = null;
    private FileOutputStream outputStream = null;
    private final String path = Prop.getField("rest.api.temp.path");

    public PropertyWorker() {
        properties = new Properties();
        try {
            if (new File(path).exists()) {
                file = new FileInputStream(path);
                properties.load(file);
                file.close();
            }
        } catch (FileNotFoundException e) {
            ConsoleLogger.log.debug("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyWorker getInstance() {
        if (propertyWorker == null) {
            propertyWorker = new PropertyWorker();
        }
        return propertyWorker;
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    public Properties getProperties() {
        return getInstance().properties;
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    public String getProperty(String name) {
        return getProperties().getProperty(name);
    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    public void setProperty(String name, String value) {
        if (new File(path).exists()) {
            setDataToProp(name, value);
        } else {
            File file = new File(path);
            try {
                file.createNewFile();
                setDataToProp(name, value);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //If necessary, if there are problems with the synchronization of drivers, use 'synchronized'
    private void setDataToProp(String name, String value) {
        try {
            outputStream = new FileOutputStream(path);
            getProperties().setProperty(name, value);
            getProperties().store(outputStream, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeAllProperty() {
        File file = new File(path);
        file.deleteOnExit();
        ConsoleLogger.log.info("File property is deleted");
    }


}
