package com.example.nikolas.messagernik.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 12.10.2015.
 */
public class Message implements Parcelable {
    private Integer id;
    private User fromUser;
    private User toUser;
    private String message;

    public Message(Integer id, User fromUser, User toUser, String message) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
    }

    protected Message(Parcel in) {
        fromUser = in.readParcelable(User.class.getClassLoader());
        toUser = in.readParcelable(User.class.getClassLoader());
        message = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public Message() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(fromUser, flags);
        dest.writeParcelable(toUser, flags);
        dest.writeString(message);
    }

    public static Message fromJson(final JSONObject jsonObject) {
        final Integer userId = jsonObject.optInt("id", 0);
        String message = jsonObject.optString("message", "");
        User userFrom = null;
        User userTo = null;
        try {
            userFrom = (User) User.fromJson(jsonObject.getJSONObject("from_id"));
            userTo = (User) User.fromJson(jsonObject.getJSONObject("to_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Message(userId, userFrom, userTo, message);
    }

    public static ArrayList<Message> fromJson(final JSONArray jsonArray) {
        ArrayList<Message> messageArrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                final Message message = fromJson(jsonArray.getJSONObject(i));
                if (null != message) messageArrayList.add(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return messageArrayList;
    }

    ;
}
