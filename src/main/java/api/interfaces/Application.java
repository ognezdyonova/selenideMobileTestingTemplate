package api.interfaces;

public interface Application {

    void forceStop();

    Object open();

    void clearData();

    String packageId();

    String activityId();

    void switchToWebView();

    void switchToNative();
}
