package com.example.nikolas.messagernik.api;

import android.app.Fragment;
import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.receiver.Receiver;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by User on 23.09.2015.
 */
public class ServerApi {

    private static Receiver receiver;
  private static onUpdateAccountFragmentListener onUpdateAccountFragmentListenerInterface;
    private static onUpdateLoginFragmentListener onUpdateLoginFragmentListenerInterface;
    public static void setUpRecuever(Context context) {
        receiver = new Receiver(context);
    }

    private static Response.Listener response = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //onUpdateAccountFragmentListenerInterface.onGetResponseFromServerCreateAccount(1);
            onUpdateLoginFragmentListenerInterface.onUpdateFragment(response);
            //Toast.makeText(getContext(),"Succesful create account",Toast.LENGTH_LONG).show();
        }
    };

    private static  Response.ErrorListener erroreResponse = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            //Toast.makeText(getContext(),"Errore create account",Toast.LENGTH_LONG).show();
        }
    };
    public static int createUserAccount(String firstName, String lastName, String password, String login) {
        return 0;
    }

    public static int loginUser(Fragment listenerFragment, String login, String password) {
        onUpdateLoginFragmentListenerInterface = (onUpdateLoginFragmentListener) listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("login", login);
        values.put("password", password);
        receiver.sendPostRequest(values, Config.LOGIN_URL,response,erroreResponse);
        return 0;
    }
    public interface onUpdateAccountFragmentListener
    {
        public void onGetResponseFromServerCreateAccount(int id);
    }

    public interface onUpdateLoginFragmentListener
    {
    public  void onUpdateFragment(Object object);
    }

    public static int createAccount(Fragment listenerFragment,String firstName, String lastName, String login, String password) {
        onUpdateAccountFragmentListenerInterface=(onUpdateAccountFragmentListener)listenerFragment;
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("login", login);
        values.put("password", password);
        receiver.sendPutRequest(values, Config.CREATE_ACCOUNT,response,erroreResponse);
        return 0;
    }
}
