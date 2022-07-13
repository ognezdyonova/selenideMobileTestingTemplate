package core;

import api.Ios.Ios;
import api.android.Android;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScreenshotCaptureMobile {
    private AndroidDriver androidDriver = Android.driver();
    private IOSDriver iosDriver = Ios.driver();
    private static String localRootFolder = "./screen_shot/";
    public static final int IMAGE_UNKNOWN = -1;
    public static final int IMAGE_JPEG = 0;
    public static final int IMAGE_PNG = 1;
    public static final int IMAGE_GIF = 2;
    private static Point location = null;
    private static Dimension elementSize = null;


    private Boolean isAndroid() {
        androidDriver = Android.driver();
        iosDriver = Ios.driver();
        if (androidDriver != null || iosDriver != null) {
            return androidDriver != null;
        } else {
            throw new RuntimeException("Something with driver. I can't create screenshot.");
        }

    }

    public void takeScreenShotAsFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
        String fileName = dateFormat.format(new Date());
        File screen = null;

        if (isAndroid())
            screen = androidDriver.getScreenshotAs(OutputType.FILE);
        else
            screen = iosDriver.getScreenshotAs(OutputType.FILE);


        try {
            FileUtils.copyFile(screen, new File(getRootDir() + "/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String takeScreenShotAsBase64() {
        String screen = null;

        if (isAndroid()) {
            screen = androidDriver.getScreenshotAs(OutputType.BASE64);
        } else {
            screen = iosDriver.getScreenshotAs(OutputType.BASE64);
        }

        return screen;
    }


    public String currentContext() {
        String context = null;

        if (isAndroid()) {
            context = androidDriver.getContext();
        } else {
            context = iosDriver.getContext();
        }

        return context;
    }

    public void drawDots(String caseName) {
        String fileName;
        if (isAndroid())
            fileName = caseName;
        else
            fileName = caseName;

        Point co = location;
        Dimension size = elementSize;

        String screen = takeScreenShotAsBase64();
        byte[] imageBytes = Base64.getMimeDecoder().decode(screen);
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

            if (co != null && size != null) {
                int y = co.y * 3 + 50 + (size.height / 2);
                int x = co.x * 3 + 50 + (size.width / 2);

                Graphics2D graphics2D = image.createGraphics();
                graphics2D.setPaint(Color.RED);
                graphics2D.fillOval(x, y, 20, 20);
                graphics2D.dispose();
            }
            ImageIO.write(image, "png", new File(getRootDir() + "/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRootDir() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        File rootDir = new File(localRootFolder + currentDate);
        if (!rootDir.exists() || rootDir.isFile()) {
            if (rootDir.mkdirs()) {
                ConsoleLogger.log.info("Root directory " + localRootFolder + currentDate + " is created!");
            }

        }
        return rootDir.getPath();
    }

    public static String findFile(String key) {
        String path = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        File rootDir = new File(localRootFolder + currentDate);
        if (rootDir.exists() || rootDir.isFile()) {
            String[] files = rootDir.list();
            String file = Arrays.stream(Objects.requireNonNull(files)).filter(f -> f.contains(key)).findFirst().get();
            path = localRootFolder + currentDate + "/" + file;
        }
        return path;
    }

    public static void deleteScreenshots(Boolean state) {
        if (state) {
            try {
                FileUtils.cleanDirectory(new File(localRootFolder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveCompressedImage(BufferedImage image, String toFileName, int type) {
        try {
            if (type == IMAGE_PNG) {
                throw new UnsupportedOperationException("PNG compression not implemented");
            }

            Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer;
            writer = (ImageWriter) iter.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(new File(toFileName));
            writer.setOutput(ios);

            ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());

            iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwparam.setCompressionQuality(0.7F);

            writer.write(null, new IIOImage(image, null, null), iwparam);

            ios.flush();
            writer.dispose();
            ios.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLocationElement(Point point) {
        ScreenshotCaptureMobile.location = point;
    }

    public static void setDimension(Dimension elementSize) {
        ScreenshotCaptureMobile.elementSize = elementSize;
    }
}
