package core.selectors.native_selectors;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AndroidUiObject {
    private final String locator;
    private static ThreadLocal<SelenideElement> element = null;
    private static ThreadLocal<List<SelenideElement>> elementsList = null;

    public AndroidUiObject(String locator) {
        this.locator = locator;
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
        setElement($(AppiumBy.androidUIAutomator(locator)));
        return getElement();
    }

    public List<SelenideElement> list() {
        setListElements($$(AppiumBy.androidUIAutomator(locator)));
        return getElementsList();
    }
}