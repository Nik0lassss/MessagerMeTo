package com.example.nikolas.messagernik.api;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.Message;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.response.ResponseList;
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
    private static UpdateLoginFragmentInterface.onUpdateLoginFragmentListener onUpdateLoginFragmenyListenerInterface;

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
    private static Response.Listener getMessagesListener = new Response.Listener() {
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

    public static void putMessage(Fragment listenerFragment,Integer from_id, Integer to_id, Integer conversation_id, String message) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("from_id", from_id.toString());
        values.put("to_id", to_id.toString());
        values.put("conversation_id", conversation_id.toString());
        values.put("message_text", message);
        receiver.sendPutRequest(values, Config.PUT_MESSAGE_URL, response, erroreResponse);
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

    public static void getConversationMessages(Fragment listenerFragment, Integer userId,Integer conversationId) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("userId", userId.toString());
        values.put("conversation_id", conversationId.toString());
        receiver.sendPostRequest(values, Config.USER_GET_NEW_MESSAGE, getMessagesListener, erroreResponse);
    }

    public static void getAllUsers(Fragment listenerFragment) {
        onUpdateListenerInterface = (onUpdateListener) listenerFragment;
        receiver.sendGetRequest(Config.GET_ALL_USERS, response, erroreResponse);
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
