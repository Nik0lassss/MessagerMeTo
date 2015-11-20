package com.example.nikolas.messagernik.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by User on 20.11.2015.
 */
public class NotifyMessage {
    private int status;
    private int notifyCodeObject;
    private String notifyMessageString;

    public NotifyMessage(int status, int notifyCodeObject, String notifyMessageString) {
        this.notifyMessageString = notifyMessageString;
        this.status = status;
        this.notifyCodeObject = notifyCodeObject;
    }

    public NotifyMessage() {
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNotifyCodeObject() {
        return notifyCodeObject;
    }

    public void setNotifyCodeObject(int notifyCodeObject) {
        this.notifyCodeObject = notifyCodeObject;
    }

    public String getNotifyMessageString() {
        return notifyMessageString;
    }

    public void setNotifyMessageString(String notifyMessageString) {
        this.notifyMessageString = notifyMessageString;
    }

    public static NotifyMessage fromJson(final JSONObject jsonObject) {
        //JSONObject notifyJsonObject = jsonObject.getJSONObject("")
        int status = jsonObject.optInt("status", 0);
        int notifyObject = jsonObject.optInt("notifyObject",0);
        String notifyMessageString = jsonObject.optString("notifyMessage", "");
        return new NotifyMessage(status, notifyObject, notifyMessageString);
    }

    public static ArrayList<NotifyMessage> fromJson(JSONArray jsonArray) {
        ArrayList<NotifyMessage> arrayListNotifyMessage = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                final NotifyMessage notifyMessage = NotifyMessage.fromJson(jsonArray.getJSONObject(i));
                if (null != notifyMessage) arrayListNotifyMessage.add(notifyMessage);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return arrayListNotifyMessage;
    }
}
