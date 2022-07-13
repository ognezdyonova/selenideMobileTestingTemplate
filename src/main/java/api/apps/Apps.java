package api.apps;

import api.apps.native_calc.NativeCalc;
import api.apps.waybetter.waybetter_app.android.WayBetterAndroidApp;
import api.apps.waybetter.waybetter_app.ios.WayBetterIosApp;

public class Apps {
    /*Android*/
    public WayBetterAndroidApp wayBetterAndroidApp = new WayBetterAndroidApp();

    /*IOS*/
    public WayBetterIosApp wayBetterIosApp = new WayBetterIosApp();

    /*Native android*/
    public NativeCalc calc = new NativeCalc();
}
