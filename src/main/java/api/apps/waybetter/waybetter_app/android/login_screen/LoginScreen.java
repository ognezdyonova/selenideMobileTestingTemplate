package api.apps.waybetter.waybetter_app.android.login_screen;

import core.selectors.native_selectors.AndroidNativeSelector;
import core.selectors.native_selectors.AndroidUiObject;

public class LoginScreen {
    private static AndroidUiObject
            header_back_btn,
            title_screen,
            google_auth_btn,
            facebook_auth_btn,
            email_address_input,
            password_input,
            errors_block,
            sign_up_lnk,
            login_btn;

    public AndroidUiObject header_back_btn() {
        if (header_back_btn == null)
            header_back_btn = new AndroidNativeSelector().description("headerBackButton").get();
        return header_back_btn;
    }

    public AndroidUiObject title_screen() {
        if (title_screen == null)
            title_screen = new AndroidNativeSelector().textContains("Login").get();
        return title_screen;
    }

    public AndroidUiObject errors_block() {
        if (errors_block == null)
            errors_block = new AndroidNativeSelector().description("Unable to complete your request. (Network error)").get();
        return errors_block;
    }

    public AndroidUiObject google_auth_btn() {
        if (google_auth_btn == null)
            google_auth_btn = new AndroidNativeSelector().resourceId("google_login_btn").get();
        return google_auth_btn;
    }

    public AndroidUiObject facebook_auth_btn() {
        if (facebook_auth_btn == null)
            facebook_auth_btn = new AndroidNativeSelector().resourceId("facebook_login_btn").get();
        return facebook_auth_btn;
    }

    public AndroidUiObject email_address_input() {
        if (email_address_input == null)
            email_address_input = new AndroidNativeSelector().resourceId("email_inp").get();
        return email_address_input;
    }

    public AndroidUiObject password_input() {
        if (password_input == null)
            password_input = new AndroidNativeSelector().resourceId("password_inp").get();
        return password_input;
    }

    public AndroidUiObject login_btn() {
        if (login_btn == null)
            login_btn = new AndroidNativeSelector().resourceId("login_btn").get();
        return login_btn;
    }

    public AndroidUiObject sign_up_lnk() {
        if (sign_up_lnk == null)
            sign_up_lnk = new AndroidNativeSelector().resourceId("sign_up_lnk").get();
        return sign_up_lnk;
    }
}
