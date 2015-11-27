package com.example.nikolas.messagernik.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nikolas on 27.11.2015.
 */
public class Cursor implements Parcelable {
    private Integer fromId;
    private Integer count;

    public Cursor() {
    }

    public Cursor(Integer fromId, Integer count) {
        this.fromId = fromId;
        this.count = count;
    }

    protected Cursor(Parcel in) {
        fromId = in.readInt();
        count = in.readInt();
    }

    public static final Creator<Cursor> CREATOR = new Creator<Cursor>() {
        @Override
        public Cursor createFromParcel(Parcel in) {
            return new Cursor(in);
        }

        @Override
        public Cursor[] newArray(int size) {
            return new Cursor[size];
        }
    };

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(fromId);
        dest.writeInt(count);
    }

    public static Cursor fromJson(JSONObject jsonObject) {
        final int fromId;
        final int count;
        fromId = jsonObject.optInt("fromId", 0);
        count = jsonObject.optInt("count", 0);
        return new Cursor(fromId, count);
    }

    public static ArrayList<Cursor> fromJson(JSONArray jsonArray) {
        ArrayList<Cursor> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                final Cursor cursor = Cursor.fromJson(jsonArray.getJSONObject(i));
                if (null != cursor) arrayList.add(cursor);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
