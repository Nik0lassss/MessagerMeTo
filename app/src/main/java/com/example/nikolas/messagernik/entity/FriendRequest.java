package com.example.nikolas.messagernik.entity;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriendRequest implements Parcelable {
    private Integer id;
    private User me;
    private User friend;


    public FriendRequest(Integer id, User me, User friend) {
        this.id = id;
        this.me = me;
        this.friend = friend;
    }

    public FriendRequest() {
    }


    protected FriendRequest(Parcel in) {
        me = in.readParcelable(User.class.getClassLoader());
        friend = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<FriendRequest> CREATOR = new Creator<FriendRequest>() {
        @Override
        public FriendRequest createFromParcel(Parcel in) {
            return new FriendRequest(in);
        }

        @Override
        public FriendRequest[] newArray(int size) {
            return new FriendRequest[size];
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

    public static FriendRequest fromJson(final JSONObject jsonObject) {
        FriendRequest friend = new FriendRequest();
        try {
            friend.setFriend((User) User.fromJson(jsonObject.getJSONObject("toUserFriendRequest")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return friend;
    }

    public static ArrayList<FriendRequest> fromJson(final JSONArray friendsJsonArray) {
        ArrayList<FriendRequest> friendArrayList = new ArrayList<>();
        for (int i = 0; i < friendsJsonArray.length(); i++) {
            try {
                FriendRequest friend = FriendRequest.fromJson(friendsJsonArray.getJSONObject(i));
                if (null != friend) friendArrayList.add(friend);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return friendArrayList;
    }
}
