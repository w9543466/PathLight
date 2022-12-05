package uk.ac.tees.w9543466.pathlight.utils;

public class TextUtil {
    public static boolean isValid(String... text) {
        for (String t : text) {
            if (!isValid(t)) return false;
        }
        return true;
    }

    public static boolean isValid(String text) {
        return text != null && !text.isEmpty();
    }
}
