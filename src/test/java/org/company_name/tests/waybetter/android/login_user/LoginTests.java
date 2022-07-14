package org.company_name.tests.waybetter.android.login_user;

import api.android.Android;
import com.codeborne.selenide.Condition;
import org.company_name.tests.waybetter.android.TestPrime;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests extends TestPrime {
    @BeforeClass
    void startTesting() {
        start();
    }

    @AfterClass
    void endTesting() {
        end();
    }

    @Test
    public void signUpWithEmptyForm() {
        Android.apps.wayBetterAndroidApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .sign_up_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible);
    }

/*
    @Test(dependsOnMethods = {"signUpWithEmptyForm"})
    public void signUpWithBrokenEmail() {
        Android.apps.wayBetterAndroidApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test first name");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test last name");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("testemail");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("23231");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("23231");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible);
    }
*/

    @Test(dependsOnMethods = {"signUpWithEmptyForm"})
    public void signUpWithDifferentPasswords() {
        Android.apps.wayBetterAndroidApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .sign_up_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test first name");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test last name");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("testemail@gmail.com");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("agent09101990");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("agent091019901");

        Android.apps.wayBetterAndroidApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible);
    }

    @Test(dependsOnMethods = {"signUpWithEmptyForm", "signUpWithDifferentPasswords"})
    public void signUpWithRandomData() {
        Android.apps.wayBetterAndroidApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .random_link()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Android.apps.wayBetterAndroidApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldNotBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldNotBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .first_name_input()
                .single()
                .shouldNotBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .last_name_input()
                .single()
                .shouldNotBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .email_address_input()
                .single()
                .shouldNotBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_input()
                .single()
                .shouldNotBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldNotBe(Condition.visible);
    }


}
