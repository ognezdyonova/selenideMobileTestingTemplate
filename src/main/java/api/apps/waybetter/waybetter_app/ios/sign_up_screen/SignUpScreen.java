package api.apps.waybetter.waybetter_app.ios.sign_up_screen;

import core.selectors.native_selectors.IosNativeSelector;
import core.selectors.native_selectors.IOSUiObject;

public class SignUpScreen {
    private static IOSUiObject
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

    public IOSUiObject header_back_btn() {
        if (header_back_btn == null)
            header_back_btn = new IosNativeSelector().containsValue("headerBackButton").get();
        return header_back_btn;
    }

    public IOSUiObject title_screen() {
        if (title_screen == null)
            title_screen = new IosNativeSelector().containsValue("Sign Up").get();
        return title_screen;
    }

    public IOSUiObject errors_block() {
        if (errors_block == null)
            errors_block = new IosNativeSelector().containsValue("Unable to complete your request. (Network error)").get();
        return errors_block;
    }

    public IOSUiObject random_link() {
        if (random_link == null)
            random_link = new IosNativeSelector().containsValue("Random").get();
        return random_link;
    }

    public IOSUiObject google_auth_btn() {
        if (google_auth_btn == null)
            google_auth_btn = new IosNativeSelector().name("google_login_btn").get();
        return google_auth_btn;
    }

    public IOSUiObject facebook_auth_btn() {
        if (facebook_auth_btn == null)
            facebook_auth_btn = new IosNativeSelector().name("facebook_login_btn").get();
        return facebook_auth_btn;
    }

    public IOSUiObject first_name_input() {
        if (first_name_input == null)
            first_name_input = new IosNativeSelector().name("first_name_inp").get();
        return first_name_input;
    }

    public IOSUiObject last_name_input() {
        if (last_name_input == null)
            last_name_input = new IosNativeSelector().name("last_name_inp").get();
        return last_name_input;
    }

    public IOSUiObject email_address_input() {
        if (email_address_input == null)
            email_address_input = new IosNativeSelector().name("email_inp").get();
        return email_address_input;
    }

    public IOSUiObject password_input() {
        if (password_input == null)
            password_input = new IosNativeSelector().name("password_inp").get();
        return password_input;
    }

    public IOSUiObject password_confirmation_input() {
        if (password_confirmation_input == null)
            password_confirmation_input = new IosNativeSelector().name("password_confirmation_inp").get();
        return password_confirmation_input;
    }

    public IOSUiObject create_account_btn() {
        if (create_account_btn == null)
            create_account_btn = new IosNativeSelector().name("create_account_btn").get();
        return create_account_btn;
    }
}
