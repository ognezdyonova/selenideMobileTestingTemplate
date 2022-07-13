package core.selectors.native_selectors;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

public class IosNativeSelector {
    public static final String BUTTON = "button";
    public static final String SWITCH = "switch";
    public static final String ALERT = "alert";
    public static final String CELL = "cell";
    public static final String CHECKBOX = "checkbox";

    private String locator = "";
    private By byLocator = null;
    private SelenideElement single = null;
    private List<SelenideElement> list = null;

    public IosNativeSelector accessibilityId(String value) {
        ///https://developer.apple.com/documentation/xctest/xcuielementtype
        if (locator.isEmpty()) locator += "identifier == '" + value + "'";
        else locator += " AND identifier == '" + value + "'";
        return this;
    }
    public IosNativeSelector name(String value) {
        ///https://developer.apple.com/documentation/xctest/xcuielementtype
        if (locator.isEmpty()) locator += "name == '" + value + "'";
        else locator += " AND name == '" + value + "'";
        return this;
    }

    public IosNativeSelector type(String typeElement) {
        ///https://developer.apple.com/documentation/xctest/xcuielementtype
        switch (typeElement) {
            case "button":
                if (locator.isEmpty()) locator += "type == 'XCUIElementTypeButton'";
                else locator += " AND type == 'XCUIElementTypeButton'";
                break;
            case "alert":
                if (locator.isEmpty()) locator += "type == 'XCUIElementTypeAlert'";
                else locator += " AND type == 'XCUIElementTypeAlert'";
                break;
            case "cell":
                if (locator.isEmpty()) locator += "type == 'XCUIElementTypeCell'";
                else locator += " AND type == 'XCUIElementTypeCell'";
                break;
            case "checkbox":
                if (locator.isEmpty()) locator += "type == 'XCUIElementTypeCheckBox'";
                else locator += " AND type == 'XCUIElementTypeCheckBox'";
                break;
            case "switch":
                if (locator.isEmpty()) locator += "type == 'XCUIElementTypeSwitch'";
                else locator += " AND type == 'XCUIElementTypeSwitch'";
                break;
        }
        return this;
    }

    public IosNativeSelector containsValue(String value) {
        ///https://developer.apple.com/documentation/xctest/xcuielementtype
        if (locator.isEmpty()) locator += "value CONTAINS[c] '" + value + "'";
        else locator += " AND value CONTAINS[c] '" + value + "'";
        return this;
    }

    public IosNativeSelector endWithValue(String value) {
        ///https://developer.apple.com/documentation/xctest/xcuielementtype
        if (locator.isEmpty()) locator += "value ENDSWITH[c] '" + value + "'";
        else locator += " AND value ENDSWITH[c] '" + value + "'";
        return this;
    }

    public IosNativeSelector beginWithValue(String value) {
        ///https://developer.apple.com/documentation/xctest/xcuielementtype
        if (locator.isEmpty()) locator += "value BEGINSWITH[c] '" + value + "'";
        else locator += " AND value BEGINSWITH[c] '" + value + "'";
        return this;
    }

    public IosNativeSelector likeValue(String value) {
        ///https://developer.apple.com/documentation/xctest/xcuielementtype
        if (locator.isEmpty()) locator += "value LIKE[c] '" + value + "'";
        else locator += " AND value LIKE[c] '" + value + "'";
        return this;
    }

    public String getLocator() {
        return locator;
    }

    public IosNativeSelector findBy(By element) {
        byLocator = element;
        return this;
    }

    public IOSUiObject get() {
        IOSUiObject element = null;
        if (locator != null) {
            element = new IOSUiObject(locator);
            locator = null;
        } else if (byLocator != null) {
            element = new IOSUiObject(byLocator);
            byLocator = null;
        }
        return element;
    }
}



