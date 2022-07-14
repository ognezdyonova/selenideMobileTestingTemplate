package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class Swipper {

    private WebDriverWait wait;
    private Dimension windowSize;
    private AppiumDriver driver;

    private static Duration SCROLL_DUR = Duration.ofMillis(300);
    private static double SCROLL_RATIO = 0.8;
    private static int ANDROID_SCROLL_DIVISOR = 3;

    private Duration STEP_DURATION = Duration.ofMillis(20);
    private Duration NO_TIME = Duration.ofMillis(0);
    private PointerInput.Origin VIEW = PointerInput.Origin.viewport();


    public Swipper(AppiumDriver driver) {
        this.driver = driver;
    }

    private AppiumDriver getDriver() {
        return driver;
    }

    private Dimension getWindowSize() {
        if (windowSize == null) {
            windowSize = getDriver().manage().window().getSize();
        }
        return windowSize;
    }

    private void swipe(Point start, Point end, Duration duration) {

//        Android.apps.clinicianConnect.switchToNative();
//        Dimension size = Android.driver.manage().window().getSize();
//        int wight = (int)(size.getWidth()/2);
//        int start = (int) (size.getHeight() * 0.8);
//        int end = (int) (size.getHeight() * 0.2);
//        new TouchAction<>(Android.driver).press(PointOption.point(wight, start))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                .moveTo(PointOption.point(wight, end))
//                .release()
//                .perform();
//
//
//        int wight2 = (int)(size.getWidth()/2);
//        int start2 = (int) (size.getHeight() * 0.2);
//        int end2 = (int) (size.getHeight() * 0.8);
//        new TouchAction<>(Android.driver).press(PointOption.point(wight2, start2))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                .moveTo(PointOption.point(wight2, end2))
//                .release()
//                .perform();
//
//
//
//        Android.apps.clinicianConnect.switchToWebView();

        AppiumDriver driver = getDriver();

        new TouchAction<>((PerformsTouchActions) driver)
                .press(PointOption.point(start.x, start.y))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(end.x, end.y))
                .release()
                .perform();
    }

    public void swipe(double startXPct, double startYPct, double endXPct, double endYPct, Duration duration) {
        Dimension size = getWindowSize();
        Point start = new Point((int) (size.width * startXPct), (int) (size.height * startYPct));
        Point end = new Point((int) (size.width * endXPct), (int) (size.height * endYPct));
        swipe(start, end, duration);
    }

    public void scroll(ScrollDirection dir, double distance) {
        ConsoleLogger.log.info("Scrolling " + dir.name() + " with the scroll distance " + distance);

        if (distance < 0 || distance > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

        int top = midPoint.y - (int) ((size.height * 0.2) * 0.5);
        int bottom = midPoint.y + (int) ((size.height * distance) * 0.5);
        int left = midPoint.x - (int) ((size.width * 0.2) * 0.5);
        int right = midPoint.x + (int) ((size.width * distance) * 0.5);

        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    public Swipper scroll(Point to, ScrollDirection dir, double duration) {
        ConsoleLogger.log.info("Scrolling " + dir.name() + " to element with coordinates " + to + " with the scroll ratio " + duration);

        Dimension size = getWindowSize();

        int top = to.y - (int) ((size.height * duration) * 0.5);
        int bottom = to.y + (int) ((size.height * duration) * 0.5);

        if (dir == ScrollDirection.UP) {
            swipe(new Point(to.x, top), new Point(to.x, bottom), SCROLL_DUR);
        } else {
            swipe(new Point(to.x, bottom), new Point(to.x, top), SCROLL_DUR);
        }
        return this;
    }

    public Swipper scroll(ScrollDirection dir) {
        ConsoleLogger.log.info("Scrolling " + dir.name() + " with the scroll ratio " + SCROLL_RATIO);
        scroll(dir, SCROLL_RATIO);
        return this;
    }

    public void scroll() {
        scroll(ScrollDirection.DOWN, SCROLL_RATIO);
    }

    public void signature() {
        Dimension size = getWindowSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

        int left = midPoint.x - (int) ((size.width * 0.2) * 0.5);
        int right = midPoint.x + (int) ((size.width * 0.5) * 0.5);
        swipe(new Point(right / 2, midPoint.y), new Point(left / 2, midPoint.y), SCROLL_DUR);
    }

    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

    private Point getPointOnCircle(int step, int totalSteps, Point origin, double radius) {
        double theta = 2 * Math.PI * ((double) step / totalSteps);
        int x = (int) Math.floor(Math.cos(theta) * radius);
        int y = (int) Math.floor(Math.sin(theta) * radius);
        return new Point(origin.x + x, origin.y + y);
    }

    private void drawCircle(Point origin, double radius, int steps) {
        AppiumDriver driver = getDriver();

        Point firstPoint = getPointOnCircle(0, steps, origin, radius);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence circle = new Sequence(finger, 0);
        circle.addAction(finger.createPointerMove(NO_TIME, VIEW, firstPoint.x, firstPoint.y));
        circle.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        for (int i = 1; i < steps + 1; i++) {
            Point point = getPointOnCircle(i, steps, origin, radius);
            circle.addAction(finger.createPointerMove(STEP_DURATION, VIEW, point.x, point.y));
        }

        circle.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(circle));
    }

    public void drawFace() {
        Dimension size = driver.manage().window().getSize();
        Point position = driver.manage().window().getPosition();
        int y = size.height / 2;
        int x = size.width / 2;
        Point head = new Point(x, y);
        Point leftEye = head.moveBy(-50, -50);
        Point rightEye = head.moveBy(50, -50);
        Point mouth = head.moveBy(0, 50);

        drawCircle(head, 150, 30);
        drawCircle(leftEye, 20, 20);
        drawCircle(rightEye, 20, 20);
        drawCircle(mouth, 40, 20);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ign) {
        }
    }


}
