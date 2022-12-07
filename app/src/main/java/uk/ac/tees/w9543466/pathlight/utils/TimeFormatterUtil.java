package uk.ac.tees.w9543466.pathlight.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormatterUtil {
    public static long toMillis(String dateTime) {
        DateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            Date parse = formatter.parse(dateTime);
            if (parse != null) {
                return parse.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
