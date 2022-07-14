package core;

import core.selectors.native_selectors.AndroidNativeSelector;

public class UiScroller {
    private String locator = "new UiScrollable";

    public String scrollIntoView(AndroidNativeSelector container, String to) {
        return locator + "(" + container + ").scrollIntoView(" + to + ")";
    }

    public String flingBackward(AndroidNativeSelector container) {
        return locator + "(" + container + ").flingBackward()";
    }

    public String flingForward(AndroidNativeSelector container) {
        return locator + "(" + container + ").flingForward()";
    }

    public String flingToBeginning(AndroidNativeSelector container, Integer maxSwipes) {
        return locator + "(" + container + ").flingToBeginning(" + maxSwipes + ")";
    }

    public String scrollToBeginning(AndroidNativeSelector container, Integer maxSwipes) {
        return locator + "(" + container + ").scrollToBeginning(" + maxSwipes + ")";
    }

    public String scrollToBeginning(AndroidNativeSelector container, Integer maxSwipes, Integer steps) {
        return locator + "(" + container + ").scrollToBeginning(" + maxSwipes + "," + steps + ")";
    }

    public String scrollTextIntoView(AndroidNativeSelector container, String text) {
        return locator + "(" + container + ").scrollTextIntoView(" + text + ")";
    }

    public String flingToEnd(AndroidNativeSelector container, Integer maxSwipes) {
        return locator + "(" + container + ").flingToEnd(" + maxSwipes + ")";
    }

    public String scrollToEnd(AndroidNativeSelector container, Integer maxSwipes, Integer steps) {
        return locator + "(" + container + ").flingToEnd(" + maxSwipes + "," + steps + ")";
    }

    public String getChildByDescription(AndroidNativeSelector container, AndroidNativeSelector childPattern, String text) {
        return locator + "(" + container + ").getChildByDescription(" + childPattern + "," + text + ")";
    }

    public String getLocator() {
        return locator;
    }

}
