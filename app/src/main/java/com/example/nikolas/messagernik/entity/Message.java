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
public class Message implements Parcelable {
    private Integer id;
    private User fromUser;
    private User toUser;
    private String message_text;
    private Timestamp time;
    private Conversation conversation;

    public Message(Integer id, User fromUser, User toUser, String message_text) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message_text = message_text;
    }

    protected Message(Parcel in) {

        fromUser = in.readParcelable(User.class.getClassLoader());
        toUser = in.readParcelable(User.class.getClassLoader());
        message_text = in.readString();
        time =Timestamp.valueOf(in.readString());
        conversation=in.readParcelable(Conversation.class.getClassLoader());
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


    public Message(Integer id, User fromUser, User toUser, String message_text, Timestamp time, Conversation conversation) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.conversation = conversation;
        this.message_text = message_text;
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
        return message_text;
    }

    public void setMessage(String message_text) {
        this.message_text = message_text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(fromUser, flags);
        dest.writeParcelable(toUser, flags);
        dest.writeString(message_text);
        dest.writeString(time.toString());
        dest.writeParcelable(conversation, flags);
    }

    public static Message fromJson(final JSONObject jsonObject) {
        final Integer userId = jsonObject.optInt("id", 0);
        String message_text = jsonObject.optString("message", "");
        User userFrom = null;
        User userTo = null;
        Timestamp timestamp=null;
        Date dateObj;
        Conversation conversation=null;
        try {
            userFrom = (User) User.fromJson(jsonObject.getJSONObject("user_from"));
            userTo = (User) User.fromJson(jsonObject.getJSONObject("user_to"));
            String time= jsonObject.optString("time","");
            long timeLong = Long.parseLong(time);
            dateObj = new Date(timeLong);
            timestamp= new Timestamp(dateObj.getTime());
            conversation=(Conversation) Conversation.fromJson(jsonObject.getJSONObject("conversation"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Message(userId, userFrom, userTo, message_text,timestamp,conversation);
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



}
