package core.selectors.native_selectors;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class IOSUiObject {
    private String locator;
    private By byLocator;
    private static ThreadLocal<SelenideElement> element = null;
    private static ThreadLocal<List<SelenideElement>> elementsList = null;

    public IOSUiObject(String locator) {
        this.locator = locator;
        element = new ThreadLocal<SelenideElement>();
        elementsList = new ThreadLocal<List<SelenideElement>>();
    }

    public IOSUiObject(By locator) {
        this.byLocator = locator;
        element = new ThreadLocal<SelenideElement>();
        elementsList = new ThreadLocal<List<SelenideElement>>();
    }

    private static void setElement(final SelenideElement el) {
        element.set(el);
    }

    private static void setListElements(final List<SelenideElement> elements) {
        elementsList.set(elements);
    }

    private static SelenideElement getElement() {
        return element.get();
    }

    private static List<SelenideElement> getElementsList() {
        return elementsList.get();
    }

    public SelenideElement single() {
        if (locator != null) {
            setElement($(AppiumBy.iOSNsPredicateString(locator)));
        } else if (byLocator != null) {
            setElement($(byLocator));
        }

        return getElement();
    }

    public List<SelenideElement> list() {
        if (locator != null) {
            setListElements($$(AppiumBy.iOSNsPredicateString(locator)));
        } else if (byLocator != null) {
            setListElements($$(byLocator));
        }
        return getElementsList();
    }
}