package core.config;

import java.io.*;
import java.util.Properties;

public class Prop {
    private static final String filePatch = "config.properties";

    public static String getField(String filed) {
        String field = null;
        try (InputStream input = Prop.class.getClassLoader().getResourceAsStream(filePatch)) {
            Properties prop = new Properties();
            prop.load(input);
            field = prop.getProperty(filed);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return field;
    }

    public static void setField(String filed, String value) {
        String field = null;
        Properties prop = new Properties();
        FileInputStream file = null;
        try {
            file = new FileInputStream(Prop.class.getClassLoader().getResource(filePatch).getFile());
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (OutputStream input = new FileOutputStream(Prop.class.getClassLoader().getResource(filePatch).getFile())) {
            prop.setProperty(filed, value);
            prop.store(input, null);
            if (!prop.getProperty(field).equals(value)) {
                throw new RuntimeException("Data not set to property " + field + " with value " + value + "\n" +
                        "Please check file by " + filePatch);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
