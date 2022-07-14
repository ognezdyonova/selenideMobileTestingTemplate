package org.company_name.tests.waybetter.ios.login_user;

import api.Ios.Ios;
import com.codeborne.selenide.Condition;
import org.company_name.tests.waybetter.ios.TestPrime;
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
        Ios.apps.wayBetterIosApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.enabled);

        Ios.apps.wayBetterIosApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.welcomeScreen
                .sign_up_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Ios.apps.wayBetterIosApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Ios.apps.wayBetterIosApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Ios.apps.wayBetterIosApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible);
    }

/*
    @Test(dependsOnMethods = {"signUpWithEmptyForm"})
    public void signUpWithBrokenEmail() {
        Ios.apps.wayBetterIosApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Ios.apps.wayBetterIosApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test first name");

        Ios.apps.wayBetterIosApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test last name");

        Ios.apps.wayBetterIosApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("testemail");

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("23231");

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("23231");

        Ios.apps.wayBetterIosApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Ios.apps.wayBetterIosApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible);
    }
*/

    @Test(dependsOnMethods = {"signUpWithEmptyForm"})
    public void signUpWithDifferentPasswords() {
        Ios.apps.wayBetterIosApp.welcomeScreen
                .background_image()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.welcomeScreen
                .log_in_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.welcomeScreen
                .sign_up_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Ios.apps.wayBetterIosApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Ios.apps.wayBetterIosApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test first name");

        Ios.apps.wayBetterIosApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("test last name");

        Ios.apps.wayBetterIosApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("testemail@gmail.com");

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("agent09101990");

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible)
                .sendKeys("agent091019901");

        Ios.apps.wayBetterIosApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Ios.apps.wayBetterIosApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible);
    }

    @Test(dependsOnMethods = {"signUpWithEmptyForm", "signUpWithDifferentPasswords"})
    public void signUpWithRandomData() {
        Ios.apps.wayBetterIosApp.signUpScreen
                .title_screen()
                .single()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Sign up"));

        Ios.apps.wayBetterIosApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .random_link()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Ios.apps.wayBetterIosApp.signUpScreen
                .first_name_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Ios.apps.wayBetterIosApp.signUpScreen
                .last_name_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Ios.apps.wayBetterIosApp.signUpScreen
                .email_address_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldBe(Condition.visible)
                .shouldNotHave(Condition.exactText(""));

        Ios.apps.wayBetterIosApp.signUpScreen
                .create_account_btn()
                .single()
                .shouldBe(Condition.visible)
                .click();

        Ios.apps.wayBetterIosApp.signUpScreen
                .google_auth_btn()
                .single()
                .shouldNotBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .facebook_auth_btn()
                .single()
                .shouldNotBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .first_name_input()
                .single()
                .shouldNotBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .last_name_input()
                .single()
                .shouldNotBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .email_address_input()
                .single()
                .shouldNotBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_input()
                .single()
                .shouldNotBe(Condition.visible);

        Ios.apps.wayBetterIosApp.signUpScreen
                .password_confirmation_input()
                .single()
                .shouldNotBe(Condition.visible);
    }


}
