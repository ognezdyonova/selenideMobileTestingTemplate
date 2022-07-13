package core.api_requester.core.rest;

import core.ConsoleLogger;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <h2>Helper class</h2>
 * <h3>Generation data</h3>
 *
 * @author Olga Gnezdyonova
 * @since 2
 */
public class DataGenerator {

    /**
     * Get rundom string
     *
     * @return String
     */
    public static String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 30) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }

    /**
     * Get random string
     *
     * @param i count of symbols
     * @return random string
     */
    public static String getRandomString(Integer i) {
        String SALTCHARS = "AB CDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtuvwxyz1234567890 ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < i) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString().concat("@");

    }

    /**
     * Get random email
     *
     * @return String email
     */
    public static String getRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return "testMail" + salt.toString() + "@" + salt.toString() + ".com";
    }

    /**
     * @param s start symbols
     * @param e end symbols
     * @return random charts
     */
    public static String getRandomChars(Integer s, Integer e) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.substring(s, e);
    }

    /**
     * Random passwords
     *
     * @return string
     */
    public static String getRandomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    /**
     * @param max max of int
     * @return integer
     */
    public static Integer getRandomInt(Integer max) {
        return (int) (Math.random() * max);
    }

    /**
     * @param max max of int
     * @return integer
     */
    public static Integer getRandomInt(Integer min, Integer max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    /**
     * @param days days for period
     * @return Map<String, String>
     */
    public static Map<String, String> getTimePeriod(int days) {
        LocalDate localDate = LocalDate.now();
        Map<String, String> period = new HashMap<>();
        period.put("current", localDate.toString());
        period.put("plusDays", localDate.plusDays(days - 1).toString());
        period.put("minusDays", localDate.minusDays(days).toString());
        return period;
    }

    /**
     * @param t time for period
     * @return Map<String, String>
     */
    public static Map<String, String> getTime(Long t) {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        Map<String, String> period = new HashMap<>();
        period.put("current", time.withMinute(0).withSecond(0).format(formatter));
        period.put("plus", time.plusHours(t).withMinute(0).withSecond(0).format(formatter));
        period.put("minus", time.minusHours(t).withMinute(0).withSecond(0).format(formatter));
        return period;
    }

    /**
     * @param days      days period
     * @param formatter format returned data
     * @return Map
     */
    public static Map<String, String> getTimePeriod(int days, DateTimeFormatter formatter) {
        LocalDate localDate = LocalDate.now();
        Map<String, String> period = new HashMap<>();
        period.put("current", localDate.format(formatter));
        period.put("plusDays", localDate.plusDays(days - 1).format(formatter));
        period.put("minusDays", localDate.minusDays(days).format(formatter));
        return period;
    }

    @SneakyThrows
    /*
      Time for dashboard requests
      @return Map
     */
    public static Map<String, Object> timeForDashboard() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        LocalDate today = LocalDate.now();

        LocalTime start = LocalTime.parse("2021-12-03T00:00:00+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalTime end = LocalTime.parse("2021-12-03T23:59:59+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime startMidnight = LocalDateTime.of(today, start);
        LocalDateTime endMidnight = LocalDateTime.of(today, end);


        Map<String, Object> period = new HashMap<>();
        period.put("start", startMidnight.atZone(TimeZone.getTimeZone("America/New_York").toZoneId()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        period.put("end", endMidnight.atZone(TimeZone.getTimeZone("America/New_York").toZoneId()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return period;
    }

    /**
     * Today Midnight Timestamp
     *
     * @return long
     */
    public static long getTodayMidnightTimestamp() {
        LocalTime midnight = LocalTime.parse("00:00:00", DateTimeFormatter.ISO_TIME);
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        /*Use this method if you need to get TodayMidnightTimestamp in UTC  */

        //return todayMidnight.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();

        /*Use this method if you need to get TodayMidnightTimestamp in localTime  */
        return todayMidnight.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Random items from Array
     *
     * @param arr list
     * @return Object
     */
    public static Object getRandomArrayItem(List arr) {
        int id = getRandomInt(0, arr.size() - 1);
        return arr.get(id);
    }

    /**
     * @param string string for Sensitive
     * @return String
     */
    public static String getSensitiveString(String string) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            int random = (int) (Math.random() * 2);
            if (random == 1) {
                newStr.append(Character.toString(string.charAt(i)).toLowerCase());
            } else {
                newStr.append(Character.toString(string.charAt(i)).toUpperCase());
            }
        }
        return newStr.toString();
    }

    /**
     * Get time period by timestamp
     *
     * @param days days that you need
     * @return Map
     */
    public static Map<String, String> getTimePeriodTS(int days) {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();

        Map<String, String> period = new HashMap<>();
        period.put("current", localTime.atDate(localDate).withNano(0).withSecond(2).toString());
        period.put("plusDays", localTime.atDate(localDate).withNano(0).plusDays(days - 1).withSecond(2).toString());
        period.put("minusDays", localTime.atDate(localDate).withNano(0).minusDays(days).withSecond(2).toString());
        return period;
    }

    /**
     * Changing date
     *
     * @param dateStr   start date
     * @param field     what you need to change in the date {@link Calendar}
     * @param timeShift time shift
     * @return String
     */
    public static String changeDate(String dateStr, int field, int timeShift) {
        String str = dateStr.replace("T", " ").replace("+00:00", "");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(Objects.requireNonNull(date));
        cal.add(field, timeShift);
        Date oneHourBack = cal.getTime();

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter2.format(oneHourBack).replaceFirst(" ", "T");
    }

    /**
     * @return current timestamp
     */
    public static long getCurrentTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Denver"));
        calendar.getTimeInMillis();
        return calendar.getTimeInMillis();
    }

    /**
     * @param ts comparing with
     * @return true/false
     */
    public static Boolean comparingCurrentDateAsBefore(Long ts) {
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();

        calendar.setTimeInMillis(ts);
        Date comparingDate = calendar.getTime();
        ConsoleLogger.log.debug("Current: " + current + " => " + comparingDate);
        return current.before(comparingDate);
    }

    /**
     * @param ts comparing with
     * @return true/false
     */
    public static Boolean comparingCurrentDateAsAfter(Long ts) {
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();

        calendar.setTimeInMillis(ts);
        Date comparingDate = calendar.getTime();
        ConsoleLogger.log.debug("Current: " + current + " => " + comparingDate + " => " + ts);
        return current.after(comparingDate);
    }

    /**
     * Decoding data
     *
     * @param value that what you need to decode
     * @return String
     */
    public static String decode64(final String value) {
        return new String(Base64.decodeBase64(value.getBytes()));
    }

    /**
     * @param value checking a string for tags
     * @return true/false
     */
    public static Boolean isHtml(String value) {
        Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
        return htmlPattern.matcher(value).matches();
    }

    /**
     * Convert timestamp to 8601
     *
     * @param timestamp that what you need to convert
     * @return integer
     */
    public static Integer tsToSec8601(String timestamp) {
        if (timestamp == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date dt = sdf.parse(timestamp);
            long epoch = dt.getTime();
            return (int) (epoch / 1000);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Encoding data
     *
     * @param value that what you need to encode
     * @return String
     */
    public String encode64(String value) {
        return new String(Base64.encodeBase64(value.getBytes()));
    }

    /**
     * Parsing tags from html string
     *
     * @param value html string
     * @param tag   parse tag
     * @return String
     */
    public String parseHtmlByTag(String value, String tag) {
        String text;
        if (isHtml(value)) {
            Document doc = Jsoup.parse(value);
            Element link = doc.select(tag).first();

            text = link.text();
        } else {
            throw new AssertionError("String does not have html tags: " + value);
        }
        return text;
    }
}
