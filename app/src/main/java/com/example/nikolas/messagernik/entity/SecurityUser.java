package com.example.nikolas.messagernik.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 22.09.2015.
 */
public class SecurityUser implements Parcelable {
    private Integer id;
    private String password;

    public SecurityUser() {
    }


    protected SecurityUser(Parcel in) {
        id=in.readInt();
        password=in.readString();
    }

    public static final Creator<SecurityUser> CREATOR = new Creator<SecurityUser>() {
        @Override
        public SecurityUser createFromParcel(Parcel in) {
            return new SecurityUser(in);
        }

        @Override
        public SecurityUser[] newArray(int size) {
            return new SecurityUser[size];
        }
    };

    public SecurityUser(String password, Integer id) {
        this.password = password;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(password);
    }
}

