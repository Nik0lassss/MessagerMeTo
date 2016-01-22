package com.example.nikolas.messagernik.api;

import android.app.Activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.Cursor;
import com.example.nikolas.messagernik.entity.Friend;
import com.example.nikolas.messagernik.entity.Message;
import com.example.nikolas.messagernik.entity.NotifyMessage;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.response.ResponseList;
import com.example.nikolas.messagernik.entity.response.ResponseListCursors;
import com.example.nikolas.messagernik.entity.response.ResponseListUsers;
import com.example.nikolas.messagernik.entity.response.ResponseObject;
import com.example.nikolas.messagernik.interfaces.UpdateLoginFragmentInterface;
import com.example.nikolas.messagernik.listeners.LoginFragmentListener;
import com.example.nikolas.messagernik.receiver.Receiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 23.09.2015.
 */
public class ServerApi {

    private static Receiver receiver;

    private static onUpdateListener onUpdateListenerInterface;
    private static onGetAllUsers onGetAllUsersInterface;
    private static onUpdateMessageFragmentMessageList onUpdateMessageFragmentMessageListInterface;
    private static UpdateLoginFragmentInterface.onUpdateLoginFragmentListener onUpdateLoginFragmenyListenerInterface;
    private static onUpdateFrinedsList onUpdateFrinedsListInterface;
    private static onSubmitAddToFriends onSubmitAddToFriendsInteface;
    private static onSendRequestToFriend onSendRequestToFriendInterface;
    private static getFriendsRequestToMe getFriendsRequestToMeInterface;

    public static void setUpReciever(Context context) {
        receiver = new Receiver(context);
    }

    private static Response.Listener response = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            onUpdateListenerInterface.onUpdate(response);
        }
    };

    private static Response.ErrorListener erroreResponse = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
        }
    };

    //    private static Response.Listener loginListener = new Response.Listener() {
//        @Override
//        public void onResponse(Object object) {
//            String response = (String) object;
//            try {
//                ResponseObject responseObject = ResponseObject.fromJson(new JSONObject(response));
//                if (responseObject.getCode() != 0) {
//                } else {
//                    JSONObject jsonObject = (JSONObject) responseObject.getResponseObject();
//                    User user = User.fromJsonWithToken(jsonObject);
//                    SecretTocken.setSecretTockenString(SecretTocken.fromJson(jsonObject));
//                    onUpdateLoginFragmenyListenerInterface.onUpdate(user);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    };

    private static Response.Listener sendRequestToAddToFriends = new Response.Listener() {
        @Override
        public void onResponse(Object o) {

        }
    };

    private static Response.Listener submitAddToFriendsListener = new Response.Listener() {

        @Override
        public void onResponse(Object object) {

        }
    };

    private static Response.Listener getMessagesListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            ResponseListCursors responseListCursor = null;
            ArrayList<Message> messageArrayList;
            Cursor cursor;
            try {
                JSONObject jsonObject = new JSONObject((String) object);
                responseListCursor = ResponseListCursors.fromJson(jsonObject);
                messageArrayList = Message.fromJson((JSONArray) responseListCursor.getResponseList());
                cursor = responseListCursor.getCursor();
                onUpdateMessageFragmentMessageListInterface.onUpdate(messageArrayList, cursor);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private static Response.Listener getFrinedsListListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            ArrayList<Friend> friendArrayList = null;
            try {
                JSONObject jsonObject = new JSONObject((String) object);
                ResponseList responseList = ResponseList.fromJson(jsonObject);
                friendArrayList = Friend.fromJson((JSONArray) responseList.getResponseList());
                onUpdateFrinedsListInterface.onUpdate(friendArrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private static Response.Listener getNotifyNewMessagesListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            ResponseObject responseObject = null;
            ArrayList<Message> messageArrayList;
            try {
                JSONObject jsonObject = new JSONObject((String) object);
                responseObject = ResponseObject.fromJson(jsonObject);
                NotifyMessage notifyMessage = NotifyMessage.fromJson((JSONObject) responseObject.getResponseObject());
                onUpdateMessageFragmentMessageListInterface.onUpdate(notifyMessage);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };

    private static Response.Listener getFriendsRequestToMe = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            ArrayList<Friend> friendArrayList = new ArrayList<>();

            try {
//                jsonObject = new JSONObject((String) object);
//
//                ResponseList responseList = ResponseList.fromJson(jsonObject);
//                userArrayList = User.fromJson((JSONArray) responseList.getResponseList());
                JSONObject jsonObject = new JSONObject((String) object);
                ResponseList responseList = ResponseList.fromJson(jsonObject);
                friendArrayList = Friend.fromJson((JSONArray) responseList.getResponseList());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            getFriendsRequestToMeInterface.onGetFriendsRequestToMe(friendArrayList);
        }
    };

    private static Response.Listener getResponsePutMessageListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            ResponseObject responseObject = null;
            Message message = null;
            ArrayList<Message> messageArrayList;
            try {
                JSONObject jsonObject = new JSONObject((String) object);
                responseObject = ResponseObject.fromJson(jsonObject);
                message = Message.fromJson((JSONObject) responseObject.getResponseObject());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            onUpdateMessageFragmentMessageListInterface.onUpdate(message);


        }
    };
    private static Response.Listener getConversationListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            ResponseList responseObject = null;
            ArrayList<Message> messageArrayList;
            try {
                JSONObject jsonObject = new JSONObject((String) object);
                responseObject = ResponseList.fromJson(jsonObject);
                messageArrayList = Message.fromJson((JSONArray) responseObject.getResponseList());
                onUpdateListenerInterface.onUpdate(messageArrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    private static Response.Listener getAllUsers = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            try {
                ArrayList<User> userArrayList;
                JSONObject jsonObject = new JSONObject((String) object);
                ResponseListUsers responseList = ResponseListUsers.fromJson(jsonObject);
                userArrayList = User.fromJson((JSONArray) responseList.getResponseList());
                onGetAllUsersInterface.onUpdate(userArrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private static Response.Listener getUserListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            Boolean isValid = false;
            try {
                JSONObject jsonObject = new JSONObject((String) object);
                ResponseObject responseObject = ResponseObject.fromJson(jsonObject);
                User user = User.fromJson((JSONObject) responseObject.getResponseObject());
                onUpdateListenerInterface.onUpdate(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };
    private static Response.Listener validateSecretTockenListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            Boolean isValid = false;
            try {
                JSONObject jsonObject = new JSONObject((String) object);
                isValid = jsonObject.optBoolean("responseObject", false);
                onUpdateListenerInterface.onUpdate(isValid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    public static void putMessage(Fragment listenerFragment, Integer from_id, Integer to_id, Integer conversation_id, String message, String secretTockenString) {
        onUpdateMessageFragmentMessageListInterface = (onUpdateMessageFragmentMessageList) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("from_id", from_id.toString());
        values.put("to_id", to_id.toString());
        values.put("conversation_id", conversation_id.toString());
        values.put("message_text", message);
        values.put("secretTockenString", secretTockenString);
        receiver.sendPutRequest(values, Config.PUT_MESSAGE_URL, getResponsePutMessageListener, erroreResponse);
    }

    public static void getUser(Activity listenerFragment, String tocken) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("secretTocken", tocken);
        receiver.sendPostRequest(values, Config.USER_GET_URL, getUserListener, erroreResponse);
    }

    public static void getConversation(Fragment listenerFragment, Integer userId) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("userId", userId.toString());
        receiver.sendPostRequest(values, Config.CONVERSATION_GET_URL, getConversationListener, erroreResponse);
    }

    public static void getFriendsRequestToMe(Object listener, Integer id) {
        getFriendsRequestToMeInterface = (getFriendsRequestToMe) listener;
        receiver.sendGetRequest(Config.GET_FRIENDS_REQUEST_TO_ME + id, getFriendsRequestToMe, erroreResponse);

    }

    public static void sendRequestToFriend(Object listener, Integer fromUserId, Integer toUserIdFriendsRequest) {
        onSendRequestToFriendInterface = (onSendRequestToFriend) listener;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("fromUserId", fromUserId.toString());
        values.put("toUserIdFriendsRequest", toUserIdFriendsRequest.toString());
        receiver.sendPostRequest(values, Config.POST_PUT_REQUEST_TO_FRIEND, sendRequestToAddToFriends, erroreResponse);
    }

    public static void submitAddToFriends(Fragment listenerFragment, Integer requestId) {
        onSubmitAddToFriendsInteface = (onSubmitAddToFriends) listenerFragment;
        HashMap<String, String> values = new HashMap<>();
        values.put("requestId", requestId.toString());
        receiver.sendPostRequest(values, Config.POST_SUBMIT_ADD_TO_FRIENDS, submitAddToFriendsListener, erroreResponse);
    }

    public static void getConversationMessages(Fragment listenerFragment, Integer userId, Integer conversationId, Cursor cursor) {
        onUpdateMessageFragmentMessageListInterface = (onUpdateMessageFragmentMessageList) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("userId", userId.toString());
        values.put("conversation_id", conversationId.toString());
        values.put("cursorFrom", cursor.getFromId().toString());
        values.put("cursorTo", cursor.getCount().toString());
        receiver.sendPostRequest(values, Config.USER_GET_NEW_MESSAGE, getMessagesListener, erroreResponse);
    }

    public static void getFriendsList(Fragment friendsFragment, Integer myId) {
        onUpdateFrinedsListInterface = (onUpdateFrinedsList) friendsFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("my_id", myId.toString());
        receiver.sendPostRequest(values, Config.POST_GET_FRIENDS_LIST, getFrinedsListListener, erroreResponse);
    }

    public static void getAllUsers(Fragment listenerFragment, Integer myId) {
        onGetAllUsersInterface = (onGetAllUsers) listenerFragment;
        receiver.sendGetRequest(Config.GET_ALL_USERS + myId, getAllUsers, erroreResponse);
    }

    public static void getNotifyNewMessage(Fragment listenerFragment, Integer conversationId, String secretTockenString) {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("secretTockenString", secretTockenString);
        onUpdateMessageFragmentMessageListInterface = (onUpdateMessageFragmentMessageList) listenerFragment;
        receiver.sendPostRequest(values, Config.POST_NOTIFY_NEW_MESSAGE + conversationId, getNotifyNewMessagesListener, erroreResponse);
    }

    public static void validateSecretTocken(Activity listenerFragment, String tocken) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("secretTocken", tocken);
        receiver.sendPostRequest(values, Config.VALIDATE_SECRET_TOCKEN, validateSecretTockenListener, erroreResponse);
    }

    public static void uploadFileToServer(String path, String login, ProgressBar prBar) {
        new UploadFileToServer(path, login, prBar).execute();
    }

    public static void uploadFileToServer(byte[] bytes, String filename, String login, ProgressBar prBar) {
        new UploadFromByteToServer(bytes, filename, login, prBar).execute();
    }

    public static int loginUser(Fragment listenerFragment, String login, String password) {
        onUpdateLoginFragmenyListenerInterface = (UpdateLoginFragmentInterface.onUpdateLoginFragmentListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("login", login);
        values.put("password", password);
        receiver.sendPostRequest(values, Config.LOGIN_URL, LoginFragmentListener.newInstanceLoginFragmentListener(onUpdateLoginFragmenyListenerInterface), erroreResponse);
        return 0;
    }


    public interface onUpdateListener {
        public void onUpdate(Object object);
    }

    public interface onGetAllUsers {
        public void onUpdate(ArrayList<User> userArrayList);
    }


    public interface onSubmitAddToFriends {
        void onUpdateSearchFragmentViewSubmitFriends();
    }

    public interface onSendRequestToFriend {
        void onSendRequestToFriend();
    }

    public interface getFriendsRequestToMe {
        void onGetFriendsRequestToMe(ArrayList<Friend> userArrayList);
    }

    public interface onUpdateMessageFragmentMessageList {
        public void onUpdate(ArrayList<Message> messageList, Cursor cursor);

        public void onUpdate(Message message);

        public void onUpdate(NotifyMessage notifyMessage);
    }

    public interface onUpdateFrinedsList {
        public void onUpdate(ArrayList<Friend> frinedsList);

    }

    public static int getRecieveMessage(Fragment listenerFragment, Integer id) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        receiver.sendGetRequest(Config.GET_RECIEVED_MESSAGE + id, response, erroreResponse);
        return 0;
    }


    public static int createAccount(Fragment listenerFragment, String firstName, String lastName, String login, String password) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("login", login);
        values.put("password", password);
        receiver.sendPutRequest(values, Config.CREATE_ACCOUNT, response, erroreResponse);
        return 0;
    }

}
