package core.selectors.native_selectors;

public class AndroidNativeSelector {
    private String locator = "new UiSelector()";

    public AndroidNativeSelector resourceId(String value) {
        locator += ".resourceId(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector className(String value) {
        locator += ".className(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector classNameMatches(String regex) {
        locator += ".classNameMatches(\"" + regex + "\")";
        return this;
    }

    public AndroidNativeSelector text(String value) {
        locator += ".text(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector textStartsWith(String value) {
        locator += ".textStartsWith(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector textContains(String value) {
        locator += ".textContains(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector index(Integer value) {
        locator += ".index(" + value + ")";
        return this;
    }

    public AndroidNativeSelector checkable(Boolean value) {
        locator += ".checkable(" + value + ")";
        return this;
    }

    public AndroidNativeSelector checked(Boolean value) {
        locator += ".checked(" + value + ")";
        return this;
    }

    public AndroidNativeSelector clickable(Boolean value) {
        locator += ".clickable(" + value + ")";
        return this;
    }

    public AndroidNativeSelector description(String value) {
        locator += ".description(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector descriptionContains(String value) {
        locator += ".descriptionContains(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector descriptionMatches(String regex) {
        locator += ".descriptionMatches(\"" + regex + "\")";
        return this;
    }

    public AndroidNativeSelector descriptionStartsWith(String regex) {
        locator += ".descriptionStartsWith(\"" + regex + "\")";
        return this;
    }

    public AndroidNativeSelector enabled(Boolean value) {
        locator += ".enabled(" + value + ")";
        return this;
    }

    public AndroidNativeSelector focusable(Boolean value) {
        locator += ".focusable(" + value + ")";
        return this;
    }

    public AndroidNativeSelector focused(Boolean value) {
        locator += ".focused(" + value + ")";
        return this;
    }

    public AndroidNativeSelector fromParent(AndroidNativeSelector selector) {
        locator += ".fromParent(" + selector + ")";
        return this;
    }

    public AndroidNativeSelector childSelector(AndroidNativeSelector selector) {
        locator += ".childSelector(" + selector + ")";
        return this;
    }

    public AndroidNativeSelector instance(Integer index) {
        locator += ".instance(" + index + ")";
        return this;
    }

    public AndroidNativeSelector longClickable(Boolean value) {
        locator += ".longClickable(" + value + ")";
        return this;
    }

    public AndroidNativeSelector packageName(String value) {
        locator += ".packageName(\"" + value + "\")";
        return this;
    }

    public AndroidNativeSelector packageNameMatches(String regex) {
        locator += ".packageNameMatches(\"" + regex + "\")";
        return this;
    }

    public AndroidNativeSelector resourceIdMatches(String regex) {
        locator += ".resourceIdMatches(\"" + regex + "\")";
        return this;
    }

    public AndroidNativeSelector textMatches(String regex) {
        locator += ".textMatches(\"" + regex + "\")";
        return this;
    }

    public AndroidNativeSelector xPath(String path) {
        locator = path;
        return this;
    }

    public AndroidNativeSelector scrollable(Boolean val) {
        locator += ".scrollable(" + val + ")";
        return this;
    }

    public AndroidNativeSelector selected(Boolean val) {
        locator += ".selected(" + val + ")";
        return this;
    }

    public AndroidNativeSelector cloneSelector() {
        locator += ".cloneSelector()";
        return this;
    }

    public String getLocator() {
        return locator;
    }

    public AndroidUiObject get() {
        AndroidUiObject element = new AndroidUiObject(locator);
        locator = null;
        return element;
    }
}
