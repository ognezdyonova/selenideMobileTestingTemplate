package api.apps.native_calc.MainScreen;

import api.android.Android;
import core.selectors.native_selectors.AndroidNativeSelector;
import core.selectors.native_selectors.AndroidUiObject;

public class MainScreenUIObjects {
    private static AndroidUiObject
            one,
            two,
            three,
            four,
            five,
            six,
            seven,
            eight,
            nine,
            zero,
            point,
            eql,
            del,
            divide,
            multiply,
            minus,
            plus,
            more_options,
            result,
            clear;

    public AndroidUiObject one() {
        if (one == null)
            one = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_1")).get();
        return one;
    }

    public AndroidUiObject two() {
        if (two == null)
            two = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_2")).get();
        return two;
    }

    public AndroidUiObject three() {
        if (three == null)
            three = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_3")).get();
        return three;
    }

    public AndroidUiObject four() {
        if (four == null)
            four = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_4")).get();
        return four;
    }

    public AndroidUiObject five() {
        if (five == null)
            five = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_5")).get();
        return five;
    }

    public AndroidUiObject six() {
        if (six == null)
            six = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_6")).get();
        return six;
    }

    public AndroidUiObject seven() {
        if (seven == null)
            seven = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_7")).get();
        return seven;
    }

    public AndroidUiObject eight() {
        if (eight == null)
            eight = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_8")).get();
        return eight;
    }

    public AndroidUiObject nine() {
        if (nine == null)
            nine = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_9")).get();
        return nine;
    }

    public AndroidUiObject zero() {
        if (zero == null)
            zero = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/digit_0")).get();
        return zero;
    }

    public AndroidUiObject point() {
        if (point == null)
            point = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/dec_point")).get();
        return point;
    }

    public AndroidUiObject eql() {
        if (eql == null)
            eql = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/eq")).get();
        return eql;
    }

    public AndroidUiObject del() {
        if (del == null)
            del = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/del")).get();
        return del;
    }

    public AndroidUiObject divide() {
        if (divide == null)
            divide = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/op_div")).get();
        return divide;
    }

    public AndroidUiObject multiply() {
        if (multiply == null)
            multiply = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/op_mul")).get();
        return multiply;
    }

    public AndroidUiObject minus() {
        if (minus == null)
            minus = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/op_sub")).get();
        return minus;
    }

    public AndroidUiObject plus() {
        if (plus == null)
            plus = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/op_add")).get();
        return plus;
    }

    public AndroidUiObject more_options() {
        if (more_options == null) more_options = new AndroidNativeSelector().descriptionContains("More options").get();
        return more_options;
    }

    public AndroidUiObject result() {
        if (result == null)
            result = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/result")).get();
        return result;
    }

    public AndroidUiObject clear() {
        if (clear == null)
            clear = new AndroidNativeSelector().resourceId(Android.apps.calc.packageId().concat(":id/clr")).get();
        return clear;
    }
}
