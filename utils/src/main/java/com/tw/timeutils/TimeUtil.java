package com.tw.timeutils;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
public class TimeUtil {
    public static long currentTime() {
        Instant now = Instant.now();
        return TimeUnit.MILLISECONDS.toNanos(now.toEpochMilli()) + now.getNano();
    }
}
