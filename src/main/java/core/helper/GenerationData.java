package core.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GenerationData {
    private static final int PHONE_NUMBER_LENGTH = 13;
    public static String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 30) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }

    public static String getRandomString(Integer i) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < i) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }
    public static String getRandomText(Integer i) {
        String SALTCHARS = "ABCD EFGHIJK LMNOPQ RSTUVW XYZ1 234 5 6789 0";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < i) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString().trim();

    }

    public static String getRandomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString()+ "007!";
    }

    public static String getRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return "testMail" + salt.toString() + "@" + salt.toString() + ".com";

    }

    public static String getRandomChars(Integer s, Integer e) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString().substring(s, e);
    }

    public static Integer getRandomInt(Integer max) {
        return (int) (Math.random() * max);
    }

    public static Integer getRandomInt(Integer min, Integer max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    public static Map<String, String> getTimePeriod(int days) {
        LocalDate localDate = LocalDate.now();
        Map<String, String> period = new HashMap<>();
        period.put("current", localDate.toString());
        period.put("plusDays", localDate.plusDays(days - 1).toString());
        period.put("minusDays", localDate.minusDays(days).toString());
        return period;
    }

    public static String getRandomPhone() {
        String s = "123456789";
        StringBuffer phoneNumber = new StringBuffer();

        for (int i = 0; i < PHONE_NUMBER_LENGTH; i++) {
            phoneNumber.append(s.charAt(new Random().nextInt(s.length())));
        }
        return phoneNumber.toString();
    }

    public static Map<String, String> getTimePeriod(int days, DateTimeFormatter formatter) {
        LocalDate localDate = LocalDate.now();
        Map<String, String> period = new HashMap<>();
        period.put("current", localDate.format(formatter));
        period.put("plusDays", localDate.plusDays(days - 1).format(formatter));
        period.put("minusDays", localDate.minusDays(days).format(formatter));
        return period;
    }

    public static Object getRandomArrayItem(List arr) {
        int id = getRandomInt(0, arr.size() - 1);
        return arr.get(id);
    }

    public static String getSensitiveString(String string){
        String newStr = "";
        for (int i = 0; i < string.length(); i++) {
            int random = (int) (Math.random() * 2);
            if (random == 1) {
                newStr += Character.toString(string.charAt(i)).toLowerCase();
            }else {
                newStr += Character.toString(string.charAt(i)).toUpperCase();
            }
        }
        return newStr;
    }
}
