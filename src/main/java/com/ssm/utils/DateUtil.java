package com.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String yMd = "yyyyMMdd";
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMdd080000 = "yyyy-MM-dd 08:00:00";
    public static final String yyyyMMddHHmmsslll = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String FORMAT_HOUR = "yyyy-MM-dd HH:00:00";
    private static ZoneId zoneId = ZoneId.systemDefault();
    public static String format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static Date parse(String date, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.parse(date);
    }

    /**
     * 获得之前n天的Date（年月日）
     * System.out = "Thu Aug 02 00:00:00 CST 2018"
     */
    public static Date getBeforeNDays0H0M0S0MS(int n) {
        LocalDate minusDays = LocalDate.now().minusDays(n);
        ZonedDateTime zdt = minusDays.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
}
