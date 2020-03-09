/*
 * Copyright (c) 2019. Create by Terry Huang (黃昭維)
 */

package com.terry.home.test.bitcoinwatcher.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

    // Java SE 7 以上才支援 X -> X 代表時區
    private static final SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static String ISO8601StringFormat(Date date) {
        if (date == null) {
            return "";
        }

        return iso8601Format.format(date);
    }

    public static Date parseISO8601String(String dateString) {
        try {
            return iso8601Format.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

}
