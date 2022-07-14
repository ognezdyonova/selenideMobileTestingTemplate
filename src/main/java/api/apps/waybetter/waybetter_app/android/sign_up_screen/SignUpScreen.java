package api.apps.waybetter.waybetter_app.android.sign_up_screen;

import core.selectors.native_selectors.AndroidNativeSelector;
import core.selectors.native_selectors.AndroidUiObject;

public class SignUpScreen {
    private static AndroidUiObject
            header_back_btn,
            title_screen,
            random_link,
            google_auth_btn,
            facebook_auth_btn,
            first_name_input,
            last_name_input,
            email_address_input,
            password_input,
            password_confirmation_input,
            errors_block,
            create_account_btn;

    public AndroidUiObject header_back_btn() {
        if (header_back_btn == null)
            header_back_btn = new AndroidNativeSelector().description("headerBackButton").get();
        return header_back_btn;
    }

    public AndroidUiObject title_screen() {
        if (title_screen == null)
            title_screen = new AndroidNativeSelector().textContains("Sign Up").get();
        return title_screen;
    }

    public AndroidUiObject errors_block() {
        if (errors_block == null)
            errors_block = new AndroidNativeSelector().description("Unable to complete your request. (Network error)").get();
        return errors_block;
    }

    public AndroidUiObject random_link() {
        if (random_link == null)
            random_link = new AndroidNativeSelector().textContains("Random").get();
        return random_link;
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

    public AndroidUiObject first_name_input() {
        if (first_name_input == null)
            first_name_input = new AndroidNativeSelector().resourceId("first_name_inp").get();
        return first_name_input;
    }

    public AndroidUiObject last_name_input() {
        if (last_name_input == null)
            last_name_input = new AndroidNativeSelector().resourceId("last_name_inp").get();
        return last_name_input;
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

    public AndroidUiObject password_confirmation_input() {
        if (password_confirmation_input == null)
            password_confirmation_input = new AndroidNativeSelector().resourceId("password_confirmation_inp").get();
        return password_confirmation_input;
    }

    public AndroidUiObject create_account_btn() {
        if (create_account_btn == null)
            create_account_btn = new AndroidNativeSelector().resourceId("create_account_btn").get();
        return create_account_btn;
    }
}
