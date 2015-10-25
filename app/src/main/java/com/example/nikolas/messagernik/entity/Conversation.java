package com.example.nikolas.messagernik.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 12.10.2015.
 */
public class Conversation implements Parcelable {
    private Integer id;
    private User fromUser;
    private User toUser;
    private String message;
    private Timestamp time;

    public Conversation(Integer id, User fromUser, User toUser, String message) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
    }

    protected Conversation(Parcel in) {

        fromUser = in.readParcelable(User.class.getClassLoader());
        toUser = in.readParcelable(User.class.getClassLoader());
        message = in.readString();
        time =Timestamp.valueOf(in.readString());
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

    public Conversation() {
    }

    public Conversation(Integer id, User fromUser, User toUser, String message, Timestamp time) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
        this.time = time;
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
        dest.writeInt(id);
        dest.writeParcelable(fromUser, flags);
        dest.writeParcelable(toUser, flags);
        dest.writeString(message);
        dest.writeString(time.toString());
    }

    public static Conversation fromJson(final JSONObject jsonObject) {
        final Integer userId = jsonObject.optInt("id", 0);
        String message = jsonObject.optString("message", "");
        User userFrom = null;
        User userTo = null;
        Timestamp timestamp=null;
        Date dateObj;
        //SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            userFrom = (User) User.fromJson(jsonObject.getJSONObject("user_from"));
            userTo = (User) User.fromJson(jsonObject.getJSONObject("user_to"));
            String time= jsonObject.optString("time","");
            long timeLong = Long.parseLong(time);
            dateObj = new Date(timeLong);
            timestamp= new Timestamp(dateObj.getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Conversation(userId, userFrom, userTo, message,timestamp);
    }

    public static ArrayList<Conversation> fromJson(final JSONArray jsonArray) {
        ArrayList<Conversation> conversationArrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                final Conversation conversation = fromJson(jsonArray.getJSONObject(i));
                if (null != conversation) conversationArrayList.add(conversation);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return conversationArrayList;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    ;
}
