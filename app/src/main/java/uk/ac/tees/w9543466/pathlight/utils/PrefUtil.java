package uk.ac.tees.w9543466.pathlight.utils;

import android.content.Context;
import android.content.SharedPreferences;

import uk.ac.tees.w9543466.pathlight.auth.User;
import uk.ac.tees.w9543466.pathlight.auth.UserRole;

public class PrefUtil {
    private static final String PREF_NAME = "path-light";
    private final SharedPreferences pref;

    public PrefUtil(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private void saveString(String key, String value) {
        pref.edit().putString(key, value).apply();
    }

    private String getString(String key) {
        return pref.getString(key, "");
    }

    public void saveLoginInfo(String email, String pwd, String role) {
        saveString(Keys.KEY_EMAIL, email);
        saveString(Keys.KEY_PWD, pwd);
        saveString(Keys.KEY_ROLE, role);
    }

    public boolean isLoggedIn() {
        return !getString(Keys.KEY_EMAIL).equals("");
    }

    public User getLoginInfo() {
        String email = getString(Keys.KEY_EMAIL);
        String pwd = getString(Keys.KEY_PWD);
        String role = getString(Keys.KEY_ROLE);
        return new User(email, pwd, role);
    }

    public UserRole getUserRole() {
        String role = getString(Keys.KEY_ROLE);
        return UserRole.valueOf(role);
    }

    static class Keys {
        static final String KEY_PWD = "pref_pwd";
        static final String KEY_EMAIL = "pref_email";
        static final String KEY_ROLE = "pref_role";
    }
}
