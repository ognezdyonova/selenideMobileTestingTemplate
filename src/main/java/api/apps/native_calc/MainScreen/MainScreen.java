package api.apps.native_calc.MainScreen;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

public class MainScreen {
    public MainScreenUIObjects uIObjects = new MainScreenUIObjects();

    public void tapOne() {
        try {
            uIObjects.one().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap one button, element absent or blocked");
        }
    }

    public void tapTwo() {
        try {
            uIObjects.two().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap two button, element absent or blocked");
        }
    }

    public void tapThree() {
        try {
            uIObjects.three().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap three button, element absent or blocked");
        }
    }

    public void tapFour() {
        try {
            uIObjects.four().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap four button, element absent or blocked");
        }
    }

    public void tapFive() {
        try {
            uIObjects.five().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap five button, element absent or blocked");
        }
    }

    public void tapSix() {
        try {
            uIObjects.six().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap six button, element absent or blocked");
        }
    }

    public void tapSeven() {
        try {
            uIObjects.seven().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap seven button, element absent or blocked");
        }
    }

    public void tapEight() {
        try {
            uIObjects.eight().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap eight button, element absent or blocked");
        }
    }

    public void tapNine() {
        try {
            uIObjects.nine().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap nine button, element absent or blocked");
        }
    }

    public void tapZero() {
        try {
            uIObjects.zero().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap zero button, element absent or blocked");
        }
    }

    public void tapPoint() {
        try {
            uIObjects.point().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap point button, element absent or blocked");
        }
    }

    public void tapEql() {
        try {
            uIObjects.eql().single().shouldBe(Condition.visible).click();
            uIObjects.clear().single().shouldBe(Condition.visible, Duration.ofSeconds(10));
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap eql button, element absent or blocked");
        }
    }

    public void tapDel() {
        try {
            uIObjects.del().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap del button, element absent or blocked");
        }
    }

    public void tapClear() {
        try {
            uIObjects.clear().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap clear button, element absent or blocked");
        }

        uIObjects.del().single().shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void tapDivide() {
        try {
            uIObjects.divide().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap divide button, element absent or blocked");
        }
    }

    public void tapMultiply() {
        try {
            uIObjects.multiply().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap multiply button, element absent or blocked");
        }
    }

    public void tapMinus() {
        try {
            uIObjects.minus().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap minus button, element absent or blocked");
        }
    }

    public void tapPlus() {
        try {
            uIObjects.plus().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap plus button, element absent or blocked");
        }
    }

    public void tapMoreOptions() {
        try {
            uIObjects.more_options().single().shouldBe(Condition.visible).click();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't tap more_options button, element absent or blocked");
        }
    }

    public String getTextResult() {
        try {
            return uIObjects.result().single().shouldBe(Condition.visible).getText();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Can't get text result, element absent or blocked");
        }
    }
}
