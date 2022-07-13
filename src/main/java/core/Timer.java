package core;

import java.util.Date;

public class Timer {
    public Long startStamp;

    public void start() {
        startStamp = getTimeStamp();
    }

    public static Long getTimeStamp() {
        return new Date().getTime();
    }

    public Boolean expired(Integer seconds) {
        Integer difference = (int) ((getTimeStamp() - startStamp) / 1000);
        return difference > seconds;
    }
}
