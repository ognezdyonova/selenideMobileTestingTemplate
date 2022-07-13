package api.apps.waybetter.waybetter_app.ios.welcome_screen;

import core.selectors.native_selectors.IOSUiObject;
import core.selectors.native_selectors.IosNativeSelector;

public class WelcomeScreen {
    private static IOSUiObject
            background_image,
            sign_up_btn,
            log_in_btn;

    public IOSUiObject background_image() {
        if (background_image == null)
            background_image = new IosNativeSelector().name("backgroundImage1").get();
        return background_image;
    }

    public IOSUiObject log_in_btn() {
        if (log_in_btn == null)
            log_in_btn = new IosNativeSelector().name("Log in").get();
        return log_in_btn;
    }

    public IOSUiObject sign_up_btn() {
        if (sign_up_btn == null)
            sign_up_btn = new IosNativeSelector().name("Sign up").get();
        return sign_up_btn;
    }

}
