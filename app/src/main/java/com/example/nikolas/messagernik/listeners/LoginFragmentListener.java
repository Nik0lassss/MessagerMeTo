package com.example.nikolas.messagernik.listeners;

import com.android.volley.Response;
import com.example.nikolas.messagernik.entity.SecretTocken;
import com.example.nikolas.messagernik.entity.User;
import com.example.nikolas.messagernik.entity.response.ResponseObject;
import com.example.nikolas.messagernik.interfaces.UpdateLoginFragmentInterface;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 16.10.2015.
 */
public class LoginFragmentListener {
    public static Response.Listener newInstanceLoginFragmentListener(final UpdateLoginFragmentInterface.onUpdateLoginFragmentListener onUpdateListenerInterface)
    {
        return  new Response.Listener() {
            @Override
            public void onResponse(Object object) {
                String response = (String) object;
                try {
                    ResponseObject responseObject = ResponseObject.fromJson(new JSONObject(response));
                    if (responseObject.getCode() != 0) {
                    } else {
                        JSONObject jsonObject = (JSONObject) responseObject.getResponseObject();
                        User user = User.fromJsonWithToken(jsonObject);
                        SecretTocken.setSecretTockenString(SecretTocken.fromJson(jsonObject));
                        onUpdateListenerInterface.onUpdate(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
