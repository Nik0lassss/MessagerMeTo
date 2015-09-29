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
    private String first_name;
    private String last_name;
    private SecurityUser securityUser;

    public User(Integer id, String first_name, String last_name, SecurityUser securityUser, String photoAvatar) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.securityUser = securityUser;
        this.photoAvatar = photoAvatar;
    }

    private String photoAvatar;



    public User() {
    }

    public User(Integer id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public User(Integer id, String first_name, String last_name, SecurityUser securityUser) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.securityUser = securityUser;
    }


    protected User(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        securityUser = in.readParcelable(SecurityUser.class.getClassLoader());
        photoAvatar = in.readString();
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

    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }
    public String getPhotoAvatar() {
        return photoAvatar;
    }

    public void setPhotoAvatar(String photoAvatar) {
        this.photoAvatar = photoAvatar;
    }

    public static User fromJson(final JSONObject object)
    {
      final   Integer id =object.optInt("id", 0);
      final   String firstName = object.optString("first_name", "");
      final   String lastName = object.optString("last_name","");
        final   String photoAvatar = object.optString("photo_avatar","");
        SecurityUser securityUser = new SecurityUser();
        try {
            JSONObject securityJSONObject = object.getJSONObject("securityUser");
            securityUser.setId(securityJSONObject.optInt("id",0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  new User(id,firstName,lastName,securityUser);
    }

    public static ArrayList<User> fromJson(final JSONArray arrayObject)
    {
    final ArrayList<User> userArrayList = new ArrayList<User>();
        for (int i=0;i<arrayObject.length();i++)
        {
            try {
                final User user = fromJson(arrayObject.getJSONObject(i));
                if(null != user) userArrayList.add(user);
            } catch (JSONException e) {
            }
        }
        return  userArrayList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeParcelable(securityUser, flags);
        dest.writeString(photoAvatar);
    }
}
