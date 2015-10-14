package com.example.nikolas.messagernik.api;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.response.ResponseObject;
import com.example.nikolas.messagernik.receiver.Receiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by User on 23.09.2015.
 */
public class ServerApi {

    private static Receiver receiver;

    private static onUpdateFragmentListener onUpdateFragmentListenerInterface;

    public static void setUpRecuever(Context context) {
        receiver = new Receiver(context);
    }

    private static Response.Listener response = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            onUpdateFragmentListenerInterface.onUpdateFragment(response);
        }
    };

    private static Response.ErrorListener erroreResponse = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
        }
    };

    private static Response.Listener loginListener = new Response.Listener() {
        @Override
        public void onResponse(Object object) {
            String response = (String) object;
            try {
                ResponseObject responseObject = ResponseObject.fromJson(new JSONObject(response));
                if (responseObject.getCode() != 0) {
                } else {
                    JSONObject jsonObject = (JSONObject) responseObject.getResponseObject();
                    User user = User.fromJson((JSONObject) jsonObject);
                    onUpdateFragmentListenerInterface.onUpdateFragment(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public static void getAllUsers(Fragment listenerFragment) {
        onUpdateFragmentListenerInterface = (onUpdateFragmentListener) listenerFragment;
        receiver.sendGetRequest(Config.GET_ALL_USERS, response, erroreResponse);
    }


    public static void uploadFileToServer(String path, String login, ProgressBar prBar) {
        new UploadFileToServer(path, login, prBar).execute();
    }

    public static void uploadFileToServer(byte[] bytes, String filename, String login, ProgressBar prBar) {
        new UploadFromByteToServer(bytes, filename, login, prBar).execute();
    }

    public static int loginUser(Fragment listenerFragment, String login, String password) {
        onUpdateFragmentListenerInterface = (onUpdateFragmentListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("login", login);
        values.put("password", password);
        receiver.sendPostRequest(values, Config.LOGIN_URL, loginListener, erroreResponse);
        return 0;
    }


    public interface onUpdateFragmentListener {
        public void onUpdateFragment(Object object);
    }

    public static int getRecieveMessage(Fragment listenerFragment, Integer id) {
        onUpdateFragmentListenerInterface = (onUpdateFragmentListener) listenerFragment;
        receiver.sendGetRequest(Config.GET_RECIEVED_MESSAGE + id, response, erroreResponse);
        return 0;
    }

    ;

    public static int createAccount(Fragment listenerFragment, String firstName, String lastName, String login, String password) {
        onUpdateFragmentListenerInterface = (onUpdateFragmentListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("login", login);
        values.put("password", password);
        receiver.sendPutRequest(values, Config.CREATE_ACCOUNT, response, erroreResponse);
        return 0;
    }

}
