package com.example.nikolas.messagernik.api;

import com.android.volley.Response;
import com.example.nikolas.messagernik.config.Config;
import com.example.nikolas.messagernik.receiver.Receiver;

import java.util.HashMap;

/**
 * Created by User on 23.09.2015.
 */
public class ServerApi {

    private static Receiver receiver;
    public static void setUpRecuever()
    {

    }
    public static int createUserAccount(String firstName,String lastName, String password,String login)
    {
        return 0;
    }

    public static int loginUser(Response.ErrorListener errorListener,Response.Listener responseListener, String login,String password)
    {
        HashMap<String ,String > values = new HashMap<String, String>();
        values.put("login",login);
        values.put("password",password);
        receiver.sendPostRequest(values, Config.LOGIN_URL);
        return  0;
    }
    public static int createAccount(Response.ErrorListener errorListener,Response.Listener responseListener,String firstName,String lastName, String login,String password)
    {
        HashMap<String ,String > values = new HashMap<String, String>();
        values.put("firstName",firstName);
        values.put("lastName",lastName);
        values.put("login",login);
        values.put("password",password);
        receiver.sendPutRequest(values, Config.CREATE_ACCOUNT);
        return  0;
    }
}
