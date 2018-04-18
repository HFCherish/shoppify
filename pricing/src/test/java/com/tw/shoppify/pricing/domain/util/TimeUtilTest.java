package com.tw.shoppify.pricing.domain.util;

import org.junit.Test;

import java.time.Instant;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
public class TimeUtilTest {
    @Test
    public void should_with_nano_accuracy() {
        System.out.println(Instant.now().getNano());
        System.out.println(Instant.now().getNano());
        System.out.println(Instant.now().toString());
        System.out.println(Instant.now().toString());
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(TimeUtil.currentTime());
        System.out.println(TimeUtil.currentTime());
    }
}