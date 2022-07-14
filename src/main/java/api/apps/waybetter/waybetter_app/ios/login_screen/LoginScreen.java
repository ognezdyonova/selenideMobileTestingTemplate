package api.apps.waybetter.waybetter_app.ios.login_screen;

import core.selectors.native_selectors.IOSUiObject;
import core.selectors.native_selectors.IosNativeSelector;

public class LoginScreen {
    private static IOSUiObject
            header_back_btn,
            title_screen,
            google_auth_btn,
            facebook_auth_btn,
            email_address_input,
            password_input,
            errors_block,
            sign_up_lnk,
            login_btn;

    public IOSUiObject header_back_btn() {
        if (header_back_btn == null)
            header_back_btn = new IosNativeSelector().containsValue("headerBackButton").get();
        return header_back_btn;
    }

    public IOSUiObject title_screen() {
        if (title_screen == null)
            title_screen = new IosNativeSelector().containsValue("Login").get();
        return title_screen;
    }

    public IOSUiObject errors_block() {
        if (errors_block == null)
            errors_block = new IosNativeSelector().containsValue("Unable to complete your request. (Network error)").get();
        return errors_block;
    }

    public IOSUiObject google_auth_btn() {
        if (google_auth_btn == null)
            google_auth_btn = new IosNativeSelector().accessibilityId("google_login_btn").get();
        return google_auth_btn;
    }

    public IOSUiObject facebook_auth_btn() {
        if (facebook_auth_btn == null)
            facebook_auth_btn = new IosNativeSelector().accessibilityId("facebook_login_btn").get();
        return facebook_auth_btn;
    }

    public IOSUiObject email_address_input() {
        if (email_address_input == null)
            email_address_input = new IosNativeSelector().accessibilityId("email_inp").get();
        return email_address_input;
    }

    public IOSUiObject password_input() {
        if (password_input == null)
            password_input = new IosNativeSelector().accessibilityId("password_inp").get();
        return password_input;
    }

    public IOSUiObject login_btn() {
        if (login_btn == null)
            login_btn = new IosNativeSelector().accessibilityId("login_btn").get();
        return login_btn;
    }

    public IOSUiObject sign_up_lnk() {
        if (sign_up_lnk == null)
            sign_up_lnk = new IosNativeSelector().accessibilityId("sign_up_lnk").get();
        return sign_up_lnk;
    }
}
