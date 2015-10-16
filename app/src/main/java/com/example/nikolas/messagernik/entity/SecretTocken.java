package com.example.nikolas.messagernik.entity;

import com.example.nikolas.messagernik.helper.SharedPreferencesHelper;

import org.json.JSONObject;

/**
 * Created by User on 16.10.2015.
 */
public class SecretTocken {

    private static String secretTockenString;

    public static void initialSecretTocken() {
        String tocken = SharedPreferencesHelper.getSecretTocken();
        if (!tocken.equals("")) {
            SecretTocken.secretTockenString = tocken;
        }

    }

    public static boolean isCorrectToken() {
        if (null == SecretTocken.secretTockenString || SecretTocken.secretTockenString == "") return false;
        else return true;
    }

    public SecretTocken(String secretTockenString) {
        SecretTocken.secretTockenString = secretTockenString;
    }

    public static String getSecretTockenString() {
        return secretTockenString;
    }

    public static void saveSecretToken() {
        if (null != secretTockenString && !secretTockenString.equals(""))
            SharedPreferencesHelper.setSecretTocken(secretTockenString);
    }

    public static void setSecretTockenString(String secretTockenString) {
        SecretTocken.secretTockenString = secretTockenString;
        saveSecretToken();
    }

    public static String fromJson(final JSONObject jsonObject) {
        String secretTokenJson = jsonObject.optString("secretTokenString", "");
        return secretTokenJson;
    }
}
