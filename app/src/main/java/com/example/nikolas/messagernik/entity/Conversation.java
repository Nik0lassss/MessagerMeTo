package com.example.nikolas.messagernik.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.nikolas.messagernik.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 27.10.2015.
 */
public class Conversation implements Parcelable {
    Integer id;
    User user_first;
    User user_second;

    public Conversation(Integer id, User user_first, User user_second) {
        this.id = id;
        this.user_first = user_first;
        this.user_second = user_second;
    }

    protected Conversation(Parcel in) {
        id = in.readInt();
        user_first = in.readParcelable(User.class.getClassLoader());
        user_second = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Conversation> CREATOR = new Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel in) {
            return new Conversation(in);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser_first() {
        return user_first;
    }

    public void setUser_first(User user_first) {
        this.user_first = user_first;
    }

    public User getUser_second() {
        return user_second;
    }

    public void setUser_second(User user_second) {
        this.user_second = user_second;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(user_first, flags);
        dest.writeParcelable(user_second, flags);
    }

    public static Conversation fromJson(JSONObject jsonObject) {
        Integer id;
        User user_first = null;
        User user_second = null;
        id = jsonObject.optInt("id", 0);
        try {
            user_first = (User) User.fromJson(jsonObject.getJSONObject("toUser"));
            user_second = (User) User.fromJson(jsonObject.getJSONObject("fromUser"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Conversation(id, user_first, user_second);
    }

    public static ArrayList<Conversation> fromJson(JSONArray jsonArray) {
        ArrayList<Conversation> conversationArrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Conversation conversation = Conversation.fromJson(jsonArray.getJSONObject(i));
                if (null != conversation) conversationArrayList.add(conversation);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return conversationArrayList;
    }
}
