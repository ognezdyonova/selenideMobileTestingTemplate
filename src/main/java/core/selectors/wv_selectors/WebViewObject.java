package core.selectors.wv_selectors;

import api.Ios.Ios;
import api.android.Android;
import com.codeborne.selenide.SelenideElement;
import core.Swipper;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class WebViewObject {
    private static ThreadLocal<SelenideElement> tm_element = null;
    private static ThreadLocal<List<SelenideElement>> tm_elementsList = null;
    private By tm_locator = null;

    public WebViewObject(By locator) {
        this.tm_locator = locator;
        tm_element = new ThreadLocal<SelenideElement>();
        tm_elementsList = new ThreadLocal<List<SelenideElement>>();
    }

    private static void setElement(final SelenideElement el) {
        tm_element.set(el);
    }

    private static void setListElements(final List<SelenideElement> elements) {
        tm_elementsList.set(elements);
    }

    private static SelenideElement getElement() {
        return tm_element.get();
    }

    private static List<SelenideElement> getListElements() {
        return tm_elementsList.get();
    }

    public SelenideElement single() {
        setElement($(this.tm_locator));
        return getElement();
    }

    public List<SelenideElement> list() {
        setListElements($$(this.tm_locator));
        return getListElements();
    }

    private AppiumDriver getDriver() {
        AppiumDriver driver = null;
        if (Android.driver() != null) driver = Android.driver();
        else driver = Ios.driver();
        return driver;
    }

    public WebViewObject scroll(Swipper.ScrollDirection param) {
        new Swipper(getDriver()).scroll(param);
        return this;
    }

    public WebViewObject scroll(Swipper.ScrollDirection param, double duration) {
        new Swipper(getDriver()).scroll(param, duration);
        return this;
    }
}
