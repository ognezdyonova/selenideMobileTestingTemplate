package core.selectors.wv_selectors;

import org.openqa.selenium.By;

public class WebViewSelector<T> {
    public WebViewObject findBy(By by) {
        return new WebViewObject(by);
    }
}
