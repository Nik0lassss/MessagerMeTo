package com.example.nikolas.messagernik.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nikolas on 18.09.2015.
 */
public class User implements Parcelable {


    private Integer id;
    private String user_login;
    private String first_name;
    private String last_name;
    private Integer isFriends;
    private Integer isFollower;
    private Integer iFollow;
    private Integer friendRequestId;


    public User(Integer id, String first_name, String last_name, SecurityUser securityUser, String photoAvatar) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photoAvatar = photoAvatar;
    }

    private String photoAvatar;


    public User() {
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        if (this.getId().equals(user.getId()))
            return true;
        return false;
    }


    protected User(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        user_login = in.readString();
        photoAvatar = in.readString();
        isFriends = in.readInt();
        isFollower = in.readInt();
        iFollow = in.readInt();
        friendRequestId = in.readInt();
    }


    public User(Integer id, String user_login, String first_name, String last_name, String photoAvatar) {
        this.id = id;
        this.user_login = user_login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photoAvatar = photoAvatar;
    }

    public User(Integer id, String user_login, String first_name, String last_name, Integer isFriends, String photoAvatar) {
        this.id = id;
        this.user_login = user_login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.isFriends = isFriends;
        this.photoAvatar = photoAvatar;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(Integer id, String user_login, String first_name, String last_name, Integer isFriends, Integer isFollower, Integer iFollow, String photoAvatar) {
        this.id = id;
        this.user_login = user_login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.isFriends = isFriends;
        this.isFollower = isFollower;
        this.iFollow = iFollow;
        this.photoAvatar = photoAvatar;
    }

    public User(Integer id, String user_login, String first_name, String last_name, Integer isFriends, Integer isFollower, Integer iFollow, Integer friendRequestId, String photoAvatar) {
        this.id = id;
        this.user_login = user_login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.isFriends = isFriends;
        this.isFollower = isFollower;
        this.iFollow = iFollow;
        this.friendRequestId = friendRequestId;
        this.photoAvatar = photoAvatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public void setPhotoAvatar(String photoAvatar) {
        this.photoAvatar = photoAvatar;
    }

    public static User fromJson(final JSONObject object) {
        final Integer id = object.optInt("user_id", 0);
        final String firstName = object.optString("first_name", "");
        final String lastName = object.optString("last_name", "");
        final String photoAvatar = object.optString("photo_avatar", "");
        final String user_login = object.optString("user_login", "");
        final Integer isFriends = object.optInt("isFriend", 0);
        final Integer isFollower = object.optInt("isFollower", 0);
        final Integer iFollow = object.optInt("iFollow", 0);
        final Integer friendRequestId = object.optInt("friendRequestId", 0);
        return new User(id, user_login, firstName, lastName, isFriends, isFollower, iFollow, friendRequestId, photoAvatar);
    }

    public static User fromJsonWithToken(final JSONObject object) {
        JSONObject userJson = null;
        try {
            userJson = object.getJSONObject("user");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final Integer id = userJson.optInt("user_id", 0);
        final String firstName = userJson.optString("first_name", "");
        final String lastName = userJson.optString("last_name", "");
        final String photoAvatar = userJson.optString("photo_avatar", "");
        final String user_login = userJson.optString("user_login", "");
        final Integer isFriends = object.optInt("isFriend", 0);
        final Integer isFollower = object.optInt("isFollower", 0);
        final Integer iFollow = object.optInt("iFollow", 0);
        final Integer friendRequestId = object.optInt("friendRequestId", 0);
        return new User(id, user_login, firstName, lastName, isFriends, isFollower, iFollow, friendRequestId,photoAvatar);
    }

    public static ArrayList<User> fromJson(final JSONArray arrayObject) {
        final ArrayList<User> userArrayList = new ArrayList<User>();
        try {
            for (int i = 0; i < arrayObject.length(); i++) {

                final User user = fromJson(arrayObject.getJSONObject(i));
                if (null != user) userArrayList.add(user);

            }
        } catch (JSONException e) {
        }
        return userArrayList;
    }


    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getPhotoAvatar() {
        return photoAvatar;
    }

    public Integer getIsFriends() {
        return isFriends;
    }

    public void setIsFriends(Integer isFriends) {
        this.isFriends = isFriends;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_login);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(photoAvatar);
        dest.writeInt(isFriends);
        dest.writeInt(isFollower);
        dest.writeInt(iFollow);
        dest.writeInt(friendRequestId);
    }

    public Integer getIsFollower() {
        return isFollower;
    }

    public void setIsFollower(Integer isFollower) {
        this.isFollower = isFollower;
    }

    public Integer getiFollow() {
        return iFollow;
    }

    public void setiFollow(Integer iFollow) {
        this.iFollow = iFollow;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public Integer getFriendRequestId() {
        return friendRequestId;
    }

    public void setFriendRequestId(Integer friendRequestId) {
        this.friendRequestId = friendRequestId;
    }
}
