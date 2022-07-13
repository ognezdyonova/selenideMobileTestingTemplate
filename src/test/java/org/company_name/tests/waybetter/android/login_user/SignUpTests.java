package org.company_name.tests.waybetter.android.login_user;

import api.android.Android;
import com.codeborne.selenide.Condition;
import org.company_name.tests.waybetter.android.TestPrime;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUpTests extends TestPrime {
    @BeforeClass
    void startTesting() {
        start();
    }

    @AfterClass
    void endTesting() {
        end();
    }

    @Test
    public void loginWithFailedData() {
        Android.apps.wayBetterAndroidApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .sign_up_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.loginScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Login"));

        Android.apps.wayBetterAndroidApp.loginScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .sign_up_lnk()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test11@gmail.com");

        Android.apps.wayBetterAndroidApp.loginScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("374bbxb768bx");

        Android.apps.wayBetterAndroidApp.loginScreen
                .login_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.loginScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible);
    }

    @Test(dependsOnMethods = {"loginWithFailedData"})
    public void loginWithGoodData() {
        Android.apps.wayBetterAndroidApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .sign_up_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.loginScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Login"));

        Android.apps.wayBetterAndroidApp.loginScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .sign_up_lnk()
                .single()
                .shouldBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test11@gmail.com");

        Android.apps.wayBetterAndroidApp.loginScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("374bbxb768bx");

        Android.apps.wayBetterAndroidApp.loginScreen
                .login_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Android.apps.wayBetterAndroidApp.loginScreen
                .email_address_input()
                .single()
                .shouldNotBe(Condition.visible);

        Android.apps.wayBetterAndroidApp.loginScreen
                .password_input()
                .single()
                .shouldNotBe(Condition.visible);
    }

}
