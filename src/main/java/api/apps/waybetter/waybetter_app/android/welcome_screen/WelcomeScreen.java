package api.apps.waybetter.waybetter_app.android.welcome_screen;


import core.selectors.native_selectors.AndroidNativeSelector;
import core.selectors.native_selectors.AndroidUiObject;

public class WelcomeScreen {
    private static AndroidUiObject
            background_image,
            sign_up_btn,
            log_in_btn;

    public AndroidUiObject background_image() {
        if (background_image == null)
            background_image = new AndroidNativeSelector().resourceId("backgroundImage1").get();
        return background_image;
    }

    public AndroidUiObject log_in_btn() {
        if (log_in_btn == null)
            log_in_btn = new AndroidNativeSelector().resourceId("Log in").get();
        return log_in_btn;
    }

    public AndroidUiObject sign_up_btn() {
        if (sign_up_btn == null)
            sign_up_btn = new AndroidNativeSelector().resourceId("Sign up").get();
        return sign_up_btn;
    }

}
