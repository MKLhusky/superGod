package com.system.supercommon.util;

import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public static long parsTimeToMillisecond(long time, TimeUnit timeUnit){
        long expiresAt = switch (timeUnit){
            case DAYS -> time * 24 * 60 * 60 * 1000;
            case HOURS -> time * 60 * 60 * 1000;
            case MINUTES -> time * 60 * 1000;
            case SECONDS -> time  * 1000;
            default -> 0;
        };
        return expiresAt;
    }

}
