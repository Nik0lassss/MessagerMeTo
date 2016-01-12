package com.example.nikolas.messagernik.entity;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Friend implements Parcelable {
    private Integer id;
    private User me;
    private User friend;


    public Friend(Integer id, User me, User friend) {
        this.id = id;
        this.me = me;
        this.friend = friend;
    }

    public Friend() {
    }


    protected Friend(Parcel in) {
        me = in.readParcelable(User.class.getClassLoader());
        friend = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getMe() {
        return me;
    }

    public void setMe(User me) {
        this.me = me;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(me, flags);
        dest.writeParcelable(friend, flags);
    }

    public static Friend fromJson(final JSONObject jsonObject) {
        Friend friend = new Friend();
        try {
            friend.setFriend((User) User.fromJson(jsonObject.getJSONObject("friend")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return friend;
    }

    public static ArrayList<Friend> fromJson(final JSONArray friendsJsonArray) {
        ArrayList<Friend> friendArrayList = new ArrayList<>();
        for (int i = 0; i < friendsJsonArray.length(); i++) {
            try {
                Friend friend = Friend.fromJson(friendsJsonArray.getJSONObject(i));
                if (null != friend) friendArrayList.add(friend);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return friendArrayList;
    }
}
