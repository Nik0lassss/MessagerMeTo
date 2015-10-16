package com.example.nikolas.messagernik.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 16.10.2015.
 */
public class SharedPreferencesHelper {
    private static Context context;
    private static SharedPreferences sPref;
    private static final String SECRET_TOCKEN_SHARED_PREFERENCES_KEY = "SECRET_TOCKEN_SHARED_PREFERENCES_KEY";
    private static final String SSHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY";
    public static void initSharedPreferencesHelper(Context context) {
        SharedPreferencesHelper.context = context;
    }

    public static String getSecretTocken() {
        return context.getSharedPreferences(SSHARED_PREFERENCES_KEY,Context.MODE_PRIVATE).getString(SECRET_TOCKEN_SHARED_PREFERENCES_KEY,"");
    }
    public static int setSecretTocken(String scret_tocken) {
        sPref=context.getSharedPreferences(SSHARED_PREFERENCES_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SECRET_TOCKEN_SHARED_PREFERENCES_KEY,scret_tocken);
        editor.commit();
        return 0;
    }
}
